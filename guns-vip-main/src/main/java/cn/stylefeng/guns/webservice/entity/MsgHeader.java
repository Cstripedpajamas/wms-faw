package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

/**
 * @ClassName MsgHeader
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:34
 * @Version 1.0
 **/
@Data
public class MsgHeader {
    String messageID;
    String interfaceID;
    String transID;
    String sender;
    String receiver;
    String count;
    String comment;
}
