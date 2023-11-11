package cn.stylefeng.guns.modular.onetypecabinet.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅰ类柜库存信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Data
public class WmsIntelligentCabinet1StockParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 库位编号
     */
    private String locaSerialNumber;

    /**
     * 地址-排
     */
    private String locaRow;

    /**
     * 地址-列
     */
    private String locaCol;

    /**
     * 提示方向(1左侧/2右侧)
     */
    private String promptDirection;

    /**
     * 格口类型ID
     */
    private String latticeType;

    /**
     * 格口类型
     */
    private String latticeTypeName;

    /**
     * 库位设备码
     */
    private String locaEquipment;

    /**
     * 库位状态（0空闲/1有货/2锁定/3盘点）
     */
    private String locaState;

    /**
     * 格口状态(0开启/1关闭)
     */
    private String latticeState;

    /**
     * 库存-物料类型ID
     */
    private String materialTypeId;

    /**
     * 库存-物料信息ID
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
    private String diBatchNo;

    /**
     * 物料编码
     */
    private String materialSerialNumber;

    /**
     * 数量（默认 1）
     */
    private String mNumber;

    /**
     * 工具状态（0维修 1使用）
     */
    private String toolState;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 工具异常内容
     */
    private String toolErrorContent;
    /**
     * 规格型号
     */
    private String sizes;

    @Override
    public String checkParam() {
        return null;
    }

}
