package cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 采购订单信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsPurchaseOrderInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 采购单号
     */
    private String purNumber;

    /**
     * 类型（1工具 2备品备件）
     */
    private String type;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 到货时间
     */
    private Date arrivalTime;

    /**
     * 到货状态（0初始 1入库 2接收 3退还）
     */
    private String arrivalState;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 接收数量
     */
    private String receivedQuantity;

    /**
     * 打印数量
     */
    private String printNum;

    /**
     * 可接收数量
     */
    private String acceptableQuantity;


    /**
     * 采购订单任务编号
     * */
    private String taskNumber;

}
