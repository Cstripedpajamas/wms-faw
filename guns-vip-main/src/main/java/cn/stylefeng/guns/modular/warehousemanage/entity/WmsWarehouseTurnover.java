package cn.stylefeng.guns.modular.warehousemanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 立库-周转箱信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@TableName("wms_warehouse_turnover")
@ApiModel(value = "立库周转箱信息")
public class WmsWarehouseTurnover implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 周转箱编号
     */
    @ApiModelProperty(value = "周转箱编号")
    @TableField("turnover_number")
    private String turnoverNumber;

    /**
     * 周转箱类型(A单格口/B双格口)
     */
    @ApiModelProperty(value = "周转箱类型(A 小 B 中 C 大 )")
    @TableField("turnover_type")
    private String turnoverType;

    /**
     * 条码信息
     */
    @ApiModelProperty(value = "条码信息")
    @TableField("barcode")
    private String barcode;

    /**
     * 存放状态（1库内/2库外）
     */
    @ApiModelProperty(value = "存放状态（1库内/2库外）")
    @TableField("storage_status")
    private String storageStatus;

    /**
     * 存放-排
     */
    @ApiModelProperty(value = "存放-排")
    @TableField("loca_row")
    private String locaRow;

    /**
     * 存放-列
     */
    @ApiModelProperty(value = "存放-列")
    @TableField("loca_col")
    private String locaCol;

    /**
     * 存放-层
     */
    @ApiModelProperty(value = "存放-层")
    @TableField("loca_layer")
    private String locaLayer;

    /**
     * 周转箱状态（0空闲/1有货/2盘点）
     */
    @ApiModelProperty(value = "周转箱状态（0空闲/1有货/2盘点）")
    @TableField("turnover_state")
    private String turnoverState;

    /**
     * 格口数量
     */
    @ApiModelProperty(value = "格口数量")
    @TableField("turnover_mouth_quantity")
    private String turnoverMouthQuantity;

    /**
     * 归属仓库
     */
    @ApiModelProperty(value = "归属仓库")
    @TableField("aff_warehouse")
    private String affWarehouse;


    /**
     * 数据状态(0使用/1禁用)
     */
    @ApiModelProperty(value = "数据状态(0使用/1禁用)")
    @TableField("data_state")
    private String dataState;

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

    public String getTurnoverNumber() {
        return turnoverNumber;
    }

    public void setTurnoverNumber(String turnoverNumber) {
        this.turnoverNumber = turnoverNumber;
    }

    public String getTurnoverType() {
        return turnoverType;
    }

    public void setTurnoverType(String turnoverType) {
        this.turnoverType = turnoverType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
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

    public String getTurnoverState() {
        return turnoverState;
    }

    public void setTurnoverState(String turnoverState) {
        this.turnoverState = turnoverState;
    }

    public String getTurnoverMouthQuantity() {
        return turnoverMouthQuantity;
    }

    public void setTurnoverMouthQuantity(String turnoverMouthQuantity) {
        this.turnoverMouthQuantity = turnoverMouthQuantity;
    }

    public String getAffWarehouse() {
        return affWarehouse;
    }

    public void setAffWarehouse(String affWarehouse) {
        this.affWarehouse = affWarehouse;
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
        return "WmsWarehouseTurnover{" +
                "id=" + id +
                ", turnoverNumber='" + turnoverNumber + '\'' +
                ", turnoverType='" + turnoverType + '\'' +
                ", barcode='" + barcode + '\'' +
                ", storageStatus='" + storageStatus + '\'' +
                ", locaRow='" + locaRow + '\'' +
                ", locaCol='" + locaCol + '\'' +
                ", locaLayer='" + locaLayer + '\'' +
                ", turnoverState='" + turnoverState + '\'' +
                ", turnoverMouthQuantity='" + turnoverMouthQuantity + '\'' +
                ", affWarehouse='" + affWarehouse + '\'' +
                ", dataState='" + dataState + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
