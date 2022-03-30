package cn.stylefeng.guns.modular.warehousemanage.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 立库-周转箱绑定货物信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseTurnoverBindResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

    /**
     * 格口编号
     */
    private String latticeCode;

    /**
     * 货物类型（1工具/2备品备件）
     */
    private String goodsType;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    private String materialId;

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
     * 物料编码
     */
    private String materialSerialNumber;

    /**
     * 批次
     */
    private String mBatch;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 格口状态
     * */
    private String latticeState;

}
