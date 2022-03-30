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
public class BpmSendBody2Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页数
     */
    private Integer pageNo;

    /**
     * 条数
     */
    private Integer pageSize;

    /**
     * iamId
     */
    private String employeeId;
}
