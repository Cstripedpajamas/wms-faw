package cn.stylefeng.guns.modular.warehousemanage.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 立库-备品备件补货任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseReplenishmentTaskParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 任务编号
     */
    private String taskNumber;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 批次
     */
    private String mBatch;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态（0初始 1开始 2出库中 3完成）
     */
    private String taskState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 分拣状态（0未分拣 1已分拣）
     */
    private String sortingStatus;

    /**
     * 分拣类型（0人工）
     */
    private String sortingType;

    /**
     * 分拣数量
     */
    private String sortingNum;

    @Override
    public String checkParam() {
        return null;
    }

}
