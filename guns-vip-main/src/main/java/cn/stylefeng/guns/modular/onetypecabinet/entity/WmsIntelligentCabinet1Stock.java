package cn.stylefeng.guns.modular.onetypecabinet.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅰ类柜库存信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@TableName("wms_intelligent_cabinet1_stock")
@ApiModel(value = "Ⅰ类柜格口信息模型")
public class WmsIntelligentCabinet1Stock implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 库位编号
     */
    @ApiModelProperty(value = "库位编号")
    @TableField("loca_serial_number")
    private String locaSerialNumber;

    /**
     * 地址-排
     */
    @ApiModelProperty(value = "地址-排")
    @TableField("loca_row")
    private String locaRow;

    /**
     * 地址-列
     */
    @TableField("loca_col")
    @ApiModelProperty(value = "地址-列")
    private String locaCol;

    /**
     * 提示方向(1左侧/2右侧)
     */
    @TableField("prompt_direction")
    @ApiModelProperty(value = "提示方向(1左侧/2右侧)")
    private String promptDirection;

    /**
     * 格口类型ID
     */
    @TableField("lattice_type")
    @ApiModelProperty(value = "格口类型ID")
    private String latticeType;

    /**
     * 格口类型
     */
    @ApiModelProperty(value = "格口类型")
    @TableField("lattice_type_name")
    private String latticeTypeName;

    /**
     * 库位设备码
     */
    @ApiModelProperty(value = "库位设备码")
    @TableField("loca_equipment")
    private String locaEquipment;

    /**
     * 库位状态（0空闲/1有货/2锁定/3盘点）
     */
    @ApiModelProperty(value = "库位状态（0空闲/1有货/2锁定/3盘点）")
    @TableField("loca_state")
    private String locaState;

    /**
     * 格口状态(0开启/1关闭)
     */
    @ApiModelProperty(value = "格口状态(0开启/1关闭)")
    @TableField("lattice_state")
    private String latticeState;

    /**
     * 库存-物料类型ID
     */
    @ApiModelProperty(value = "库存-物料类型ID")
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 库存-物料信息ID
     */
    @ApiModelProperty(value = "库存-物料信息ID")
    @TableField("material_id")
    private String materialId;

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
     * 数量（默认 1）
     */
    @ApiModelProperty(value = "数量（默认 1）")
    @TableField("m_number")
    private String mNumber;

    /**
     * 工具状态（0维修 1使用）
     */
    @ApiModelProperty(value = "工具状态（0维修 1使用）")
    @TableField("tool_state")
    private String toolState;

    /**
     * 数据时间
     */
    @ApiModelProperty(value = "数据时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocaSerialNumber() {
        return locaSerialNumber;
    }

    public void setLocaSerialNumber(String locaSerialNumber) {
        this.locaSerialNumber = locaSerialNumber;
    }

    public String getLocaRow() {
        return locaRow;
    }

    public void setLocaRow(String locaRow) {
        this.locaRow = locaRow;
    }

    public String getLocaCol() {
        return locaCol;
    }

    public void setLocaCol(String locaCol) {
        this.locaCol = locaCol;
    }

    public String getPromptDirection() {
        return promptDirection;
    }

    public void setPromptDirection(String promptDirection) {
        this.promptDirection = promptDirection;
    }

    public String getLatticeType() {
        return latticeType;
    }

    public void setLatticeType(String latticeType) {
        this.latticeType = latticeType;
    }

    public String getLatticeTypeName() {
        return latticeTypeName;
    }

    public void setLatticeTypeName(String latticeTypeName) {
        this.latticeTypeName = latticeTypeName;
    }

    public String getLocaEquipment() {
        return locaEquipment;
    }

    public void setLocaEquipment(String locaEquipment) {
        this.locaEquipment = locaEquipment;
    }

    public String getLocaState() {
        return locaState;
    }

    public void setLocaState(String locaState) {
        this.locaState = locaState;
    }

    public String getLatticeState() {
        return latticeState;
    }

    public void setLatticeState(String latticeState) {
        this.latticeState = latticeState;
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

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getToolState() {
        return toolState;
    }

    public void setToolState(String toolState) {
        this.toolState = toolState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsIntelligentCabinet1Stock{" +
        "id=" + id +
        ", locaSerialNumber=" + locaSerialNumber +
        ", locaRow=" + locaRow +
        ", locaCol=" + locaCol +
        ", promptDirection=" + promptDirection +
        ", latticeType=" + latticeType +
        ", latticeTypeName=" + latticeTypeName +
        ", locaEquipment=" + locaEquipment +
        ", locaState=" + locaState +
        ", latticeState=" + latticeState +
        ", materialTypeId=" + materialTypeId +
        ", materialId=" + materialId +
        ", materialName=" + materialName +
        ", materialSku=" + materialSku +
        ", materialSerialNumber=" + materialSerialNumber +
        ", mNumber=" + mNumber +
        ", toolState=" + toolState +
        ", createTime=" + createTime +
        "}";
    }
}
