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
public class BpmSendBodyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 1工具领用 2备品备件领用
     */
    private Integer applyType;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料编号
     */
    private String materialNumber;

    /**
     * 申请原因
     */
    private String applyReason;

    /**
     * iamId
     */
    private String employeeId;

    /**
     * 物料数量
     */
    private Integer materialQuantity;

    /**
     *WMS主键
     */
    private String processNo;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 审批人 ,
     */
    private String approver;
}
