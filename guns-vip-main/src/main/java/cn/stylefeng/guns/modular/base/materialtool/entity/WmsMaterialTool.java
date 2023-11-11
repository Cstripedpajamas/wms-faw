package cn.stylefeng.guns.modular.base.materialtool.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 工具-物料信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_material_tool")
@ApiModel(value = "物料模型")
public class WmsMaterialTool implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 物料类型ID
     */
    @ApiModelProperty(value = "物料类型ID")
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 物料类型
     */
    @ApiModelProperty(value = "物料类型")
    @TableField("material_type")
    private String materialType;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "描述")
    @TableField("material_name")
    private String materialName;

    /**
     * 物料SKU
     */
    @ApiModelProperty(value = "物料号")
    @TableField("material_sku")
    private String materialSku;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    @TableField("material_serial_number")
    private String materialSerialNumber;

    /**
     * 单位
     */
    @ApiModelProperty(value = "基本计量单位")
    @TableField("m_unit")
    private String mUnit;

    /**
     * 物料状态（0新 1使用中 2维修 3报废）
     */
    @ApiModelProperty(value = "物料状态（0新 1使用中 2维修 3报废）")
    @TableField("material_state")
    private String materialState;

    /**
     * 存放状态(0初始 1库内 2库外)
     */
    @ApiModelProperty(value = "存放状态(0初始 1库内 2库外)")
    @TableField("storage_state")
    private String storageState;

    /**
     * 存放地址（1立体仓库 2Ⅰ类柜）
     */
    @ApiModelProperty(value = "存放地址（1立体仓库 2Ⅰ类柜）")
    @TableField("storage_address")
    private String storageAddress;

    /**
     * 数据状态（0使用/1禁用）
     */
    @ApiModelProperty(value = "数据状态（0使用/1禁用）")
    @TableField("data_state")
    private String dataState;

    /**
     * 数据时间
     */
    @ApiModelProperty(value = "数据时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 采购单号
     */
    @ApiModelProperty(value = "采购单号")
    @TableField(value = "pur_number")
    private String purNumber;
    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    @TableField(exist = false)
    private String sizes;

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getSizes() {
        return sizes;
    }

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

    public String getMaterialSerialNumber() {
        return materialSerialNumber;
    }

    public void setMaterialSerialNumber(String materialSerialNumber) {
        this.materialSerialNumber = materialSerialNumber;
    }

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getMaterialState() {
        return materialState;
    }

    public void setMaterialState(String materialState) {
        this.materialState = materialState;
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

    public String getPurNumber() {
        return purNumber;
    }

    public void setPurNumber(String purNumber) {
        this.purNumber = purNumber;
    }

    @Override
    public String toString() {
        return "WmsMaterialTool{" +
                "id=" + id +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", materialSerialNumber='" + materialSerialNumber + '\'' +
                ", mUnit='" + mUnit + '\'' +
                ", materialState='" + materialState + '\'' +
                ", storageState='" + storageState + '\'' +
                ", storageAddress='" + storageAddress + '\'' +
                ", dataState='" + dataState + '\'' +
                ", createTime=" + createTime +
                ", purNumber='" + purNumber + '\'' +
                ", sizes='" + sizes + '\'' +
                '}';
    }
}
