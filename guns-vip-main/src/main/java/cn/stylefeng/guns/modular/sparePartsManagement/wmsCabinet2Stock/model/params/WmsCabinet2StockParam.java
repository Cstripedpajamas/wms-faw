package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅱ类柜库存信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2StockParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 库位编号
     */
    private String locaNumber;

    /**
     * 地址-排
     */
    private String locaRow;

    /**
     * 地址-列
     */
    private String locaCol;

    /**
     * 地址-层
     */
    private String locaLayer;

    /**
     * 库位设备码
     */
    private String locaEquipment;

    /**
     * 库位状态（0空闲/1有货/2锁定）
     */
    private String locationState;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

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

    @Override
    public String checkParam() {
        return null;
    }

}
