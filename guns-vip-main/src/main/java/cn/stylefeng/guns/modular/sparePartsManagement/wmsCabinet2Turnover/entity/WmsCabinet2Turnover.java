package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Ⅱ类柜周转箱信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@TableName("wms_cabinet2_turnover")
public class WmsCabinet2Turnover implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 周转箱编号
     */
    @TableField("turnover_number")
    private String turnoverNumber;

    /**
     * 条码信息
     */
    @TableField("barcode")
    private String barcode;

    /**
     * 存放状态（1库内/2库外/3出入口）
     */
    @TableField("storage_status")
    private String storageStatus;

    /**
     * 存放-排
     */
    @TableField("loca_row")
    private String locaRow;

    /**
     * 存放-列
     */
    @TableField("loca_col")
    private String locaCol;

    /**
     * 存放-层
     */
    @TableField("loca_layer")
    private String locaLayer;

    /**
     * 周转箱状态（0空闲/1有货/2盘点）
     */
    @TableField("turnover_state")
    private String turnoverState;

    /**
     * 数据状态(0使用/1禁用)
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

    public String getTurnoverNumber() {
        return turnoverNumber;
    }

    public void setTurnoverNumber(String turnoverNumber) {
        this.turnoverNumber = turnoverNumber;
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
        return "WmsCabinet2Turnover{" +
        "id=" + id +
        ", turnoverNumber=" + turnoverNumber +
        ", barcode=" + barcode +
        ", storageStatus=" + storageStatus +
        ", locaRow=" + locaRow +
        ", locaCol=" + locaCol +
        ", locaLayer=" + locaLayer +
        ", turnoverState=" + turnoverState +
        ", dataState=" + dataState +
        ", createTime=" + createTime +
        "}";
    }
}
