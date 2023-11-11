package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName YQJFUserInfo
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/3/4 9:30
 * @Version 1.0
 **/
@Data
public class YQJFCancelOrder implements Serializable {
    String purDocNo;
    String itemNo;
    String STATUS;
}
