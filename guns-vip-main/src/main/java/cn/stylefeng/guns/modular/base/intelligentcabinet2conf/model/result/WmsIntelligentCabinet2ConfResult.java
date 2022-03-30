package cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅱ类柜物料补货阈值配置表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsIntelligentCabinet2ConfResult implements Serializable {

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

}
