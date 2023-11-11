package cn.stylefeng.guns.modular.base.redirect;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ApplyController
 * @Description 申请
 * @Author ASD-FuBenHao
 * @Date 2022/5/30 16:22
 * @Version 1.0
 **/
@Controller
@RequestMapping("/apply")
@CrossOrigin
public class GetDingDingUser extends BaseController {

//    public static void main(String[] args) {
//        try {
//            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//            OapiGettokenRequest req = new OapiGettokenRequest();
//            req.setAppkey("dingotsmhynm0kuntqmp");
//            req.setAppsecret("S_1wYkvX93FI5NME5jytYvuOhUkqI3TTUynXZBkJUeT1HXvrkE-_jGw_M_q8mYWS");
//            req.setHttpMethod("GET");
//            OapiGettokenResponse rsp = client.execute(req);
//            System.out.println(rsp.getAccessToken());
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (com.taobao.api.ApiException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getuserinfo");
//            OapiV2UserGetuserinfoRequest req = new OapiV2UserGetuserinfoRequest();
//            OapiV2UserGetuserinfoResponse rsp = client.execute(req, );
//            req.setCode(req.getCode());
//            System.out.println(rsp.getBody());
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (com.taobao.api.ApiException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
