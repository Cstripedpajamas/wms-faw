package cn.stylefeng.guns.modular.utils.DingDing;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: ll
 * @Date: 2021/12/1 15:15
 * @Version 1.0
 */
@Slf4j
public class RobotHelperUtilTest {
    /**
     * 钉钉机器人消息发送的响应结果
     */

    private OapiRobotSendResponse response;
    @Test
    public void sendMessageByText() {
        String content = "要补货了,快点~";
        response = RobotHelperUtil.sendMessageByText(content, Arrays.asList("17863812108"), true);
    }




    @Test
    public void sendMessageByLink() {
        String title = "";
        String text = "听说你在测试钉钉机器人, 悟空推荐看下钉钉开发文档自定义机器人";
        String messageUrl = "https://blog.csdn.net/niaonao";
        String picUrl = "http://img01.taobaocdn.com/top/i1/LB1lIUlPFXXXXbGXFXXXXXXXXXX#align=left&display=inline&height=294&originHeight=1372&originWidth=2088&status=done&width=447";
        response = RobotHelperUtil.sendMessageByLink(title, text, messageUrl, picUrl);
    }


    @Test
    public void sendMessageByMarkdown() {
        String title = "悟空建议你看下Markdown 语法";
        String markdownText =
                "## 列表\n" +
                        "无序列表\n" +
                        "- item1\n" +
                        "- item2\n" +
                        "## 代码片\n" +
                        "```java\n" +
                        "public void sendMessageByMarkdown() {\n" +
                        "        String title = \"title\";\n" +
                        "        String markdownText = \"text\";\n" +
                        "        response = RobotHelperUtil.sendMessageByMarkdown(title, markdownText,null, false);\n" +
                        "    }\n" +
                        "```";
        response = RobotHelperUtil.sendMessageByMarkdown(title, markdownText, Arrays.asList("17788559966"), false);
    }

    @Test
    public void sendMessageByActionCardSingle() {
        String title = "ActionCard 整体跳转Card";
        String markdownText = "### 消息内容";
        String singleTitle = "查看原文";
        String singleURL = "https://open-doc.dingtalk.com/microapp/serverapi3/iydd5h";
        response = RobotHelperUtil.sendMessageByActionCardSingle(title, markdownText, singleTitle, singleURL, true);

    }
    @Test
    public void sendMessageByActionCardMulti() {
        String title = "悟空建议查看下ActionCard 单独跳转类型消息";
        String markdownText = "### 备注\n此处省略了一些字^_^";
        List<OapiRobotSendRequest.Btns> btns = new ArrayList<>();
        btns.add(new OapiRobotSendRequest.Btns());
        btns.add(new OapiRobotSendRequest.Btns());
        btns.add(new OapiRobotSendRequest.Btns());
        btns.forEach((btn) -> {
            btn.setTitle("Button 标题" + btns.indexOf(btn));
            btn.setActionURL("https://open-doc.dingtalk.com/microapp/serverapi3/iydd5h");
        });
        response = RobotHelperUtil.sendMessageByActionCardMulti(title, markdownText, btns, true);
    }


}
