package cn.stylefeng.guns.modular.warehousemanage.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-周转箱绑定货物信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@TableName("wms_warehouse_turnover_bind")
@ApiModel(value = "立库周转箱绑定数据信息")
public class WmsWarehouseTurnoverBind implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 周转箱信息ID
     */
    @ApiModelProperty(value = "周转箱信息ID")
    @TableField("turnover_id")
    private String turnoverId;
    /**
     * 格口编号
     */
    @ApiModelProperty(value = "格口编号")
    @TableField("lattice_code")
    private String latticeCode;

    /**
     * 货物类型（1工具/2备品备件）
     */
    @ApiModelProperty(value = "货物类型（1工具/2备品备件）")
    @TableField("goods_type")
    private String goodsType;

    /**
     * 物料类型ID
     */
    @ApiModelProperty(value = "物料类型ID")
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    @ApiModelProperty(value = "物料信息ID")
    @TableField("material_id")
    private String materialId;

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
     * 单位
     */
    @ApiModelProperty(value = "基本计量单位")
    @TableField("m_unit")
    private String mUnit;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    @TableField("material_serial_number")
    private String materialSerialNumber;

    /**
     * 批次
     */
    @ApiModelProperty(value = "批次")
    @TableField("m_batch")
    private String mBatch;

    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    @TableField("sizes")
    private String sizes;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @TableField("m_number")
    private String mNumber;

    /**
     * 数据时间
     */
    @ApiModelProperty(value = "数据时间")
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public String getLatticeState() {
        return latticeState;
    }

    public void setLatticeState(String latticeState) {
        this.latticeState = latticeState;
    }

    /**
     * 格口状态
     * */
    @ApiModelProperty(value = "格口状态")
    @TableField(value = "lattice_state")
    private String latticeState;

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

    public String getLatticeCode() {
        return latticeCode;
    }

    public void setLatticeCode(String latticeCode) {
        this.latticeCode = latticeCode;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
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

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getMaterialSerialNumber() {
        return materialSerialNumber;
    }

    public void setMaterialSerialNumber(String materialSerialNumber) {
        this.materialSerialNumber = materialSerialNumber;
    }

    public String getmBatch() {
        return mBatch;
    }

    public void setmBatch(String mBatch) {
        this.mBatch = mBatch;
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
        return "WmsWarehouseTurnoverBind{" +
                "id=" + id +
                ", turnoverId='" + turnoverId + '\'' +
                ", sizes='" + sizes + '\'' +
                ", latticeCode='" + latticeCode + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", mUnit='" + mUnit + '\'' +
                ", materialSerialNumber='" + materialSerialNumber + '\'' +
                ", mBatch='" + mBatch + '\'' +
                ", mNumber='" + mNumber + '\'' +
                ", createTime=" + createTime +
                ", latticeState='" + latticeState + '\'' +
                '}';
    }
}
