package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

/**
 * @ClassName YQJFUserInfo
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/3/4 9:30
 * @Version 1.0
 **/
@Data
public class YQJFDeliveryOrder {
    String purDocNo;
    String itemNo;
    String mtlNo;
    String QTY;
    String CODE;
    String SENDTIME;
    String EXPECTEDRECEIVETIME;
    String LINECODE;
    String STATUS;
}
