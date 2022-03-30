package cn.stylefeng.guns.modular.warehousemanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 立库-仓库库存信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@TableName("wms_warehouse_stock")
public class WmsWarehouseStock implements Serializable {

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
     * 巷道（A=1巷道 B=2巷道 C=3巷道）
     */
    @TableField("roadway")
    private String roadway;

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
    @TableField("loca_state")
    private String locaState;

    /**
     * 周转箱信息ID
     */
    @TableField("turnover_id")
    private String turnoverId;

   /**
    * 备注
    * */
   @TableField("mark")
   private String mark;

   /**
    * 备用字段
    * */
   @TableField("spare_field")
   private String spareField;
    /**
     * 数据时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSpareField() {
        return spareField;
    }

    public void setSpareField(String spareField) {
        this.spareField = spareField;
    }

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

    public String getRoadway() {
        return roadway;
    }

    public void setRoadway(String roadway) {
        this.roadway = roadway;
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

    public String getLocaState() {
        return locaState;
    }

    public void setLocaState(String locaState) {
        this.locaState = locaState;
    }

    public String getTurnoverId() {
        return turnoverId;
    }

    public void setTurnoverId(String turnoverId) {
        this.turnoverId = turnoverId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsWarehouseStock{" +
                "id=" + id +
                ", locaNumber='" + locaNumber + '\'' +
                ", roadway='" + roadway + '\'' +
                ", locaRow='" + locaRow + '\'' +
                ", locaCol='" + locaCol + '\'' +
                ", locaLayer='" + locaLayer + '\'' +
                ", locaEquipment='" + locaEquipment + '\'' +
                ", locaState='" + locaState + '\'' +
                ", turnoverId='" + turnoverId + '\'' +
                ", mark='" + mark + '\'' +
                ", spareField='" + spareField + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
