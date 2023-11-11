package cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备故障统计表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsEquipmentFailureStatisticsParam implements Serializable, BaseValidatingParam {

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



    /**
     * 物料类型
     */
    private String materialType;


    /**
     * 物料名称
     */
    private String materialName;


    /**
     * 物料编号
     */
    private String materialSku;


    /**
     * 包装单位
     */
    private String mUnit;


    /**
     * 规格型号
     */
    private String Sizes;


    /**
     * 维修次数
     */
    private String repairCount;
    @Override
    public String checkParam() {
        return null;
    }

}
