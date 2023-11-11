package cn.stylefeng.guns;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiV2UserGetbymobileRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiV2UserGetbymobileResponse;
import com.taobao.api.ApiException;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;



@Configuration
public class DingDingSendMsg {


    private String Appkey="dingotsmhynm0kuntqmp";


    private String Appsecret="S_1wYkvX93FI5NME5jytYvuOhUkqI3TTUynXZBkJUeT1HXvrkE-_jGw_M_q8mYWS";


    private Long AgentId= Long.valueOf(2033790029);

    public String getToken(){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setAppkey(Appkey);
            req.setAppsecret(Appsecret);
            req.setHttpMethod("GET");
            OapiGettokenResponse rsp = client.execute(req);
//            System.out.println(rsp.getBody());
            return rsp.getAccessToken();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getUserId(String phoneNumber){
        try {
            if (!isValidPhoneNumber(phoneNumber)){
                return null;
            }
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getbymobile");
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getuserinfo");
//        OapiV2UserGetuserinfoRequest req = new OapiV2UserGetuserinfoRequest();
            OapiV2UserGetbymobileRequest req = new OapiV2UserGetbymobileRequest();
            req.setMobile(phoneNumber);
            System.out.println(phoneNumber);
            System.out.println(getToken());
            OapiV2UserGetbymobileResponse rsp = client.execute(req, getToken());
            return rsp.getResult().getUserid();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

//        req.setCode(code);
//        OapiV2UserGetuserinfoResponse rsp = client.execute(req, getToken());
//        System.out.println(rsp.getBody());
//        if (rsp.getErrcode()!=0) {
//
//        }
//        return rsp.getResult().getUserid();
    }

    public void sendMsg(String ddId,String text){
        try{
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
            OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
            request.setAgentId(AgentId);
            request.setUseridList(ddId);
            request.setToAllUser(false);
            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msg.setMsgtype("text");
            msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
            msg.getText().setContent(text);
            request.setMsg(msg);
//        msg.setMsgtype("link");
//        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
//        msg.getLink().setTitle("这是个标题");
//        msg.getLink().setText("这是个内容");
//        msg.getLink().setMessageUrl("http://218.24.35.83:81");
//        msg.getLink().setPicUrl("test");
//        request.setMsg(msg);
            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, getToken());
        }catch(ApiException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isValidPhoneNumber(String phoneNumber){
        String regex = "^(\\+\\d{1,3})?1[0-9]{10}$";
        // 或者使用 String regex = "^1[0-9]{10}$";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phoneNumber).matches();
    }
}
