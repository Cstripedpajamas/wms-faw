package cn.stylefeng.guns.modular.statistics.tooluse.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 工具领用信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsToolUseResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 人员信息
     */
    private String operator;

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
     * 物料编码
     */
    private String materialSerialNumber;

    /**
     * 数据时间
     */
    private Date dataTime;

}
