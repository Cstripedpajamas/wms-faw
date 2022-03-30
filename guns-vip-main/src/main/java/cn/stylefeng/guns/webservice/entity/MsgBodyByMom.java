package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

/**
 * @ClassName MsgBody - 工具领用信息接口
 * @Description 工具领用信息接口
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:34
 * @Version 1.0
 **/
@Data
public class MsgBodyByMom {
    String accountCode;
    String startTime;
    String endTime;
}
