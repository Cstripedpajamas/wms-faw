package cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 设备故障统计表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_equipment_failure_statistics")
public class WmsEquipmentFailureStatistics implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备名称
     */
    @TableField("equipment_name")
    private String equipmentName;

    /**
     * 故障类型
     */
    @TableField("failure_type")
    private String failureType;

    /**
     * 故障描述
     */
    @TableField("failure_describe")
    private String failureDescribe;

    /**
     * 故障时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getFailureType() {
        return failureType;
    }

    public void setFailureType(String failureType) {
        this.failureType = failureType;
    }

    public String getFailureDescribe() {
        return failureDescribe;
    }

    public void setFailureDescribe(String failureDescribe) {
        this.failureDescribe = failureDescribe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsEquipmentFailureStatistics{" +
        "id=" + id +
        ", equipmentName=" + equipmentName +
        ", failureType=" + failureType +
        ", failureDescribe=" + failureDescribe +
        ", createTime=" + createTime +
        "}";
    }
}
