package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅱ类柜库存信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2StockResult implements Serializable {

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
     * 条码信息
     */
    private String barcode;

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
     * 工厂
     */
    private String plant;

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
     * 规格型号
     */
    private String sizes;

}
