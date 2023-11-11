package cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅱ类柜物料补货阈值配置表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsIntelligentCabinet2ConfParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

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
     * 工厂
     */
    private String plant;

    /**
     * 批次号
     */
    private String diBatchNo;

    /**
     * 补货阈值
     */
    private String replenishmentThreshold;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 数据时间
     */
    private Date createTime;
    /**
     * 规格型号
     */
    private String sizes;

    /**
     * 操作人工号
     */
    private String account;
    /**
     * 操作人姓名
     */
    private String name;


    @Override
    public String checkParam() {
        return null;
    }

}
