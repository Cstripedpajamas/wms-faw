package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 周转箱绑定货物信息关联表
 * </p>
 *
 * @author ll
 * @since 2021-11-02
 */
@TableName("wms_cabinet2_turnover_bind")
public class WmsCabinet2TurnoverBind implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 周转箱信息ID
     */
    @TableField("turnover_id")
    private String turnoverId;

    /**
     * 物料类型ID
     */
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    @TableField("material_id")
    private String materialId;

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
     * 批次
     */
    @TableField("m_batch")
    private String mBatch;

    /**
     * 单位
     */
    @TableField("m_unit")
    private String mUnit;

    /**
     * 数量
     */
    @TableField("m_number")
    private String mNumber;

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

    public String getTurnoverId() {
        return turnoverId;
    }

    public void setTurnoverId(String turnoverId) {
        this.turnoverId = turnoverId;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
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

    public String getmBatch() {
        return mBatch;
    }

    public void setmBatch(String mBatch) {
        this.mBatch = mBatch;
    }

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsCabinet2TurnoverBind{" +
        "id=" + id +
        ", turnoverId=" + turnoverId +
        ", materialTypeId=" + materialTypeId +
        ", materialId=" + materialId +
        ", materialType=" + materialType +
        ", materialName=" + materialName +
        ", materialSku=" + materialSku +
        ", mBatch=" + mBatch +
        ", mUnit=" + mUnit +
        ", mNumber=" + mNumber +
        ", createTime=" + createTime +
        "}";
    }
}
