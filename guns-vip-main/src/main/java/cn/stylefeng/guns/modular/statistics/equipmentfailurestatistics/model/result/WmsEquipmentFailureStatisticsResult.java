package cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 设备故障统计表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsEquipmentFailureStatisticsResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 故障类型
     */
    private String failureType;

    /**
     * 故障描述
     */
    private String failureDescribe;

    /**
     * 故障时间
     */
    private Date createTime;

}
