package cn.stylefeng.guns.webservice;

import cn.stylefeng.guns.webservice.entity.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.text.ParseException;

@WebService(targetNamespace = "http://andot.org/webservice/demo/server")
public interface FawApiService {

    /**
     * 人员信息 IAM
     *
     * @param msgHeader rule id
     * @param msgBody page number
     * @return RsBody
     */
    @WebMethod
    RsBody setUserInfo(@WebParam(name = "msgHeader") MsgHeader msgHeader, @WebParam(name = "msgBody") MsgBody msgBody);

    /**
     * 采购订单 ERP
     *
     * @param msgHeader rule id
     * @param msgBody page number
     * @return RsErpBody
     */
    @WebMethod
    RsErpBody setPurchaseOrder(@WebParam(name = "msgHeader") MsgHeader msgHeader, @WebParam(name = "msgBody") MsgBodyErp msgBody);

    /**
     * 物料信息 ERP
     *
     * @param msgHeader rule id
     * @param msgBody page number
     * @return RsBody
     */
    @WebMethod
    RsBody getMaterielInfos(@WebParam(name = "msgHeader") MsgHeader msgHeader, @WebParam(name = "msgBody") MsgBodyByEsb msgBody);

    /**
     * 工具领用信息 MOM
     *
     * @param msgHeader rule id
     * @param msgBody page number
     * @return RsBody
     */
    @WebMethod
    RsMomBody getToolCollection(@WebParam(name = "msgHeader") MsgHeader msgHeader, @WebParam(name = "msgBody") MsgBodyByMom msgBody);

    /**
     * 采购订单商城发货单
     *
     * @param msgHeader rule id
     * @param msgBody page number
     * @return RsBody
     */
    @WebMethod
    RsBody getPurchaseorderDelivery(@WebParam(name = "msgHeader") MsgHeader msgHeader, @WebParam(name = "msgBody") MsgBodyDelivery msgBody) throws ParseException;

    /**
     * 采购订单商城订单取消
     *
     * @param msgHeader rule id
     * @param msgBody page number
     * @return RsBody
     */
    @WebMethod
    RsBody getPurchaseorderCancel(@WebParam(name = "msgHeader") MsgHeader msgHeader, @WebParam(name = "msgBody") MsgBodyCancel msgBody);
}
