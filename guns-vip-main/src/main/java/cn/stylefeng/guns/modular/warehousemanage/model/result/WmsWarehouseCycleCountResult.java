package cn.stylefeng.guns.modular.warehousemanage.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-周转箱绑定货物信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseCycleCountResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料类型
     */
    private String plant;

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
     * 批次
     */
    private String mBatch;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 周转箱数量
     */
    private String boxNumber;

    /**
     * 数据时间
     */
    private Date createTime;

    private String sizes;
}
