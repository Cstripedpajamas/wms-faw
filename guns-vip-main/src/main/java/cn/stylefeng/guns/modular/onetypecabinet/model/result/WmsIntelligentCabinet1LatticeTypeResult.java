package cn.stylefeng.guns.modular.onetypecabinet.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅰ类柜格口类型信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Data
public class WmsIntelligentCabinet1LatticeTypeResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 类型规格
     */
    private String typeStandards;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 数据状态（1使用/2禁用）
     */
    private String dataState;

    /**
     * 数据时间
     */
    private Date createTime;

}
