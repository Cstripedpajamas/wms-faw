package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

/**
 * @ClassName RsMsgHeader
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:56
 * @Version 1.0
 **/
@Data
public class RsMsgHeader {
    String messageID;
    String interfaceID;
    String transID;
    String resultType;
    String resultCode;
    String resultMessage;
    String count;
    String comment;
}
