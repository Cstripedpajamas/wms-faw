package cn.stylefeng.guns.modular.WebApi.Entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BpmSendEntity
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/3/29 10:00
 * @Version 1.0
 **/
@Data
public class BpmSendHeaderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String receiver;
    private String sender;
    private String transID;
    private String count;
    private String messageID;
    private String comment;
    private String interfaceID;
}
