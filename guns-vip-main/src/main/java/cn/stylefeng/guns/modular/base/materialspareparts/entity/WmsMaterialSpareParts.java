package cn.stylefeng.guns.modular.base.materialspareparts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 备品备件-物料信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_material_spare_parts")
public class WmsMaterialSpareParts implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 规格（最小包装）
     */
    @TableField("min_package_size")
    private String minPackageSize;

    /**
     * 存放状态(0初始 1库内 2库外)
     */
    @TableField("storage_state")
    private String storageState;

    /**
     * 存放地址（1立体仓库 2Ⅱ类柜）
     */
    @TableField("storage_address")
    private String storageAddress;

    /**
     * 数据状态（0使用/1禁用）
     */
    @TableField("data_state")
    private String dataState;

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

    public String getMinPackageSize() {
        return minPackageSize;
    }

    public void setMinPackageSize(String minPackageSize) {
        this.minPackageSize = minPackageSize;
    }

    public String getStorageState() {
        return storageState;
    }

    public void setStorageState(String storageState) {
        this.storageState = storageState;
    }

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsMaterialSpareParts{" +
        "id=" + id +
        ", materialTypeId=" + materialTypeId +
        ", materialType=" + materialType +
        ", materialName=" + materialName +
        ", materialSku=" + materialSku +
        ", mBatch=" + mBatch +
        ", mUnit=" + mUnit +
        ", minPackageSize=" + minPackageSize +
        ", storageState=" + storageState +
        ", storageAddress=" + storageAddress +
        ", dataState=" + dataState +
        ", createTime=" + createTime +
        "}";
    }
}
