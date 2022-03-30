package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

/**
 * @ClassName MsgBody - 流程结果反馈接口
 * @Description 流程结果反馈接口
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:34
 * @Version 1.0
 **/
@Data
public class MsgBodyBpm {
    String processNo;
    String processStatus;
    String processRs;
    String BPM_ID;
}
