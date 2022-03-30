package cn.stylefeng.guns.modular.statistics.toolinfo.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 工具信息汇总表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsToolInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

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
     * 总数
     */
    private String total;

    /**
     * 现存数量
     */
    private String existNumber;

    /**
     * 维修数量
     */
    private String repairNumber;

    /**
     * 报废数量
     */
    private String scrapNumber;

    /**
     * 数据时间
     */
    private Date createTime;

}
