package cn.stylefeng.guns.modular.utils.WebSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName WebSocket
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2021/4/23 14:40
 * @Version 1.0
 **/
@Component
@ServerEndpoint(value = "/connectWebSocket/oneToMany")
public class WebSocket {

    private final static Logger logger = LoggerFactory.getLogger(WebSocket.class);

    /** 记录当前在线连接数 */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /** 存放所有在线的客户端 */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();


    // 0 工具柜柜 1 备品备件柜
    private static final Session[] sessions = new Session[3];

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineCount.incrementAndGet(); // 在线数加1
        clients.put(session.getId(), session);
        logger.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
//        try {
//            Thread.sleep(2000);
//            this.sendMessage("op900");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
        clients.remove(session.getId());
        logger.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
//        this.sendMessage(message);
        System.out.println(session.getRequestURI());
        if(Objects.equals("0",message)){
            // I类柜
            sessions[0] = session;
            sendMessageOfSession1("ok");
        }
        if(Objects.equals("1",message)){
            // II类柜
            sessions[1] = session;
            sendMessageOfSession2("ok");
        }
        if(Objects.equals("2",message)){
            // II类柜
            sessions[2] = session;
            sendMessageOfSession3("ok");
        }
    }


    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发消息
     *
     * @param message
     *            消息内容
     */
    public static void sendMessage(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            Session toSession = sessionEntry.getValue();
            toSession.getAsyncRemote().sendText(message);
            logger.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
        }
    }

    /*
     * 工具单发
     * */
    public static void sendMessageOfSession1(String message) {
        Session toSession=sessions[0];
        toSession.getAsyncRemote().sendText(message);
        logger.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
    }

    /*
     * 备品备件单发
     * */
    public static void sendMessageOfSession2(String message) {
        Session toSession=sessions[1];
        toSession.getAsyncRemote().sendText(message);
        logger.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
    }

    /*
     * 仓库
     * */
    public static void sendMessageOfSession3(String message) {
        Session toSession=sessions[2];
        toSession.getAsyncRemote().sendText(message);
        logger.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
    }


}
