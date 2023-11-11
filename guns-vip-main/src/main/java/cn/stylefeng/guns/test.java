package cn.stylefeng.guns;

import cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.WmsWarehousePurchaseorderDeliveryService;
import cn.stylefeng.guns.webservice.client.ExecuteBindQSService;
import cn.stylefeng.guns.webservice.client.Req;
import cn.stylefeng.guns.webservice.client.Resp;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName test 测试信息数据
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2021/11/24 11:19
 * @Version 1.0
 **/
public class test {

    @Autowired
    public static WmsWarehousePurchaseorderDeliveryService wmsWarehousePurchaseorderDeliveryService;
    public static   void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        String ki="2022-05-26T02:43:48";
//        try {
//            System.out.printf(sdf.parse(ki).toString());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        webServiceClient();
//        ApplyController applyController= new ApplyController();
//        applyController.listSelectApprovedBy("邢玉祥");
//        pDAWareController.doingTaskOver("1");
//        webServiceClient();
//            PDAWareController doingTaskOver= new    PDAWareController();
//            doingTaskOver.doingTaskOver("CG202304215962");
//        UseAppliesThread UseAppliesThread= new UseAppliesThread();
//        UseAppliesThread.runThreadMainA();
//        WmsWarehousePurchaseorderDeliveryResult wmsWarehouse= wmsWarehousePurchaseorderDeliveryService.selectPurDocNo("PO230414000001","21501");
    }

    public static String addZero(int number){
        StringBuffer sb = new StringBuffer();
        if (number< 10){
           return sb.append("000").append(number).toString();
        }
        else if (number >= 100){
            return sb.append("0").append(number).toString();
        }
        return sb.append("00").append(number).toString();
    }

    public static void webServiceClient(){
        Req req = new Req();
        ExecuteBindQSService executeBindQSService = new ExecuteBindQSService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String a=sdf.format(date);
        Req.MsgHeader msgHeader= new Req.MsgHeader();
        Req.MsgBody msgBody= new Req.MsgBody();

        msgHeader.setMessageID("2222222222222222222222222222");
        msgHeader.setInterfaceID("TWMS-ERP-001");
        msgHeader.setSender("JF_TWMS");
        msgHeader.setReceiver("JF_ERP");
//        req.getMsgHeader().add(msgHeader);
//        msgHeader.setTransID("1");
//        msgHeader.setCount("1");
//        msgHeader.setComment("1");
        msgBody.setCode("INV230420000003");
        msgBody.setLineCode("1063074");
        msgBody.setQty("10");
        msgBody.setMtlno("85ZJ000285");
        msgBody.setStorageLocation("R06B861");
        msgBody.setGetDate("20220401");
        req.setMsgHeader(msgHeader);
        req.getMsgBody().add(msgBody);
        Resp resp = executeBindQSService.getExecuteBindQSPort().execute(req);
//        resp.getMsgHeader().getResultMessage();
    }
}
