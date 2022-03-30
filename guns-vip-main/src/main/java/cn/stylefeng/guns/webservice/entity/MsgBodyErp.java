package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName MsgBody - 采购订单
 * @Description 采购订单
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:34
 * @Version 1.0
 **/
@Data
public class MsgBodyErp {
    List<YQJFPurchaseOrderInfo> yqjfPurchaseOrderInfo;
}
