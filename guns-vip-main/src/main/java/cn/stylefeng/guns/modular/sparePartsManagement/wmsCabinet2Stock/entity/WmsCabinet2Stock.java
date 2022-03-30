package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Ⅱ类柜库存信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@TableName("wms_cabinet2_stock")
public class WmsCabinet2Stock implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 库位编号
     */
    @TableField("loca_number")
    private String locaNumber;

    /**
     * 地址-排
     */
    @TableField("loca_row")
    private String locaRow;

    /**
     * 地址-列
     */
    @TableField("loca_col")
    private String locaCol;

    /**
     * 地址-层
     */
    @TableField("loca_layer")
    private String locaLayer;

    /**
     * 库位设备码
     */
    @TableField("loca_equipment")
    private String locaEquipment;

    /**
     * 库位状态（0空闲/1有货/2锁定）
     */
    @TableField("location_state")
    private String locationState;

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

    public String getLocaNumber() {
        return locaNumber;
    }

    public void setLocaNumber(String locaNumber) {
        this.locaNumber = locaNumber;
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

    public String getLocaLayer() {
        return locaLayer;
    }

    public void setLocaLayer(String locaLayer) {
        this.locaLayer = locaLayer;
    }

    public String getLocaEquipment() {
        return locaEquipment;
    }

    public void setLocaEquipment(String locaEquipment) {
        this.locaEquipment = locaEquipment;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
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
        return "WmsCabinet2Stock{" +
        "id=" + id +
        ", locaNumber=" + locaNumber +
        ", locaRow=" + locaRow +
        ", locaCol=" + locaCol +
        ", locaLayer=" + locaLayer +
        ", locaEquipment=" + locaEquipment +
        ", locationState=" + locationState +
        ", turnoverId=" + turnoverId +
        ", materialTypeId=" + materialTypeId +
        ", materialId=" + materialId +
        ", materialName=" + materialName +
        ", materialSku=" + materialSku +
        ", mBatch=" + mBatch +
        ", mNumber=" + mNumber +
        ", createTime=" + createTime +
        "}";
    }
}
