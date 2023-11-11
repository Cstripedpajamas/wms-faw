package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName MsgBody - 人员信息接收接口
 * @Description 人员信息接收接口
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:34
 * @Version 1.0
 **/
@Data
public class MsgBodyDelivery {
    List<YQJFDeliveryOrder> yqjfdeliverorder;
}
