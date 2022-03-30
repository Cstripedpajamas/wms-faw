package cn.stylefeng.guns.modular.base.intelligentcabinet2conf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * Ⅱ类柜物料补货阈值配置表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_intelligent_cabinet2_conf")
public class WmsIntelligentCabinet2Conf implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型（1工具 2备品备件）
     */
    @TableField("type")
    private String type;

    /**
     * 物料类型ID
     */
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 物料类型
     */
    @TableField("material_type")
    private String materialType;

    /**
     * 物料名称
     */
    @TableField("material_name")
    private String materialName;

    /**
     * 物料SKU
     */
    @TableField("material_sku")
    private String materialSku;

    /**
     * 补货阈值
     */
    @TableField("replenishment_threshold")
    private String replenishmentThreshold;

    /**
     * 操作人
     */
    @TableField("operator")
    private String operator;

    /**
     * 数据时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSku() {
        return materialSku;
    }

    public void setMaterialSku(String materialSku) {
        this.materialSku = materialSku;
    }

    public String getReplenishmentThreshold() {
        return replenishmentThreshold;
    }

    public void setReplenishmentThreshold(String replenishmentThreshold) {
        this.replenishmentThreshold = replenishmentThreshold;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsIntelligentCabinet2Conf{" +
                "id=" + id +
                ", type=" + type +
                ", materialTypeId=" + materialTypeId +
                ", materialType=" + materialType +
                ", materialName=" + materialName +
                ", materialSku=" + materialSku +
                ", replenishmentThreshold=" + replenishmentThreshold +
                ", operator=" + operator +
                ", createTime=" + createTime +
                "}";
    }
}
