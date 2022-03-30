package cn.stylefeng.guns.webservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName RsBody
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 15:47
 * @Version 1.0
 **/
@Data
public class RsBody {
    private RsMsgHeader msgHeader;
    private RsMsgBody msgBody;
}
