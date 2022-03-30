package cn.stylefeng.guns.modular.utils.WebSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName WebSocketStompConfig
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2021/4/23 14:39
 * @Version 1.0
 **/
@Configuration
public class WebSocketStompConfig {

    //这个bean的注册,用于扫描带有@ServerEndpoint的注解成为websocket  ,如果你使用外置的tomcat就不需要该配置文件
    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }
}
