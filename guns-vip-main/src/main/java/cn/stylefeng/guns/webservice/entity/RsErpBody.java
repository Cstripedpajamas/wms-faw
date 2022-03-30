package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

/**
 * @ClassName RsBody
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:47
 * @Version 1.0
 **/
@Data
public class RsErpBody {
    private RsMsgHeader msgHeader;
    private RsMsgErpBody msgBody;
}
