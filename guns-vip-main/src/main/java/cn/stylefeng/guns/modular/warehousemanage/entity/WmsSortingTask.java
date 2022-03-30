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
 * 机械手分拣任务表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-23
 */
@TableName("wms_sorting_task")
public class WmsSortingTask implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务编号
     */
    @TableField("task_number")
    private String taskNumber;

    /**
     * 周转箱类型(大C 中B 小A)
     */
    @TableField("turnover_type")
    private String turnoverType;

    /**
     * 周转箱编号
     */
    @TableField("turnover_number")
    private String turnoverNumber;

    /**
     * 周转箱条码
     */
    @TableField("barcode")
    private String barcode;

    /**
     * 分拣格口（编号）
     */
    @TableField("lattice_code")
    private String latticeCode;

    /**
     * 分拣数量
     */
    @TableField("sorting_num")
    private String sortingNum;

    /**
     * 任务状态（0初始 1开始分拣 2分拣完成 3结束）
     */
    @TableField("task_state")
    private String taskState;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 请求时间
     */
    @TableField("req_time")
    private Date reqTime;

    /**
     * 数据时间
     */
    @TableField("data_time")
    private Date dataTime;

    /**
     * 分拣物料类型
     * */
    @TableField("sorting_material_type")
    private String sortingMaterialType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTurnoverType() {
        return turnoverType;
    }

    public void setTurnoverType(String turnoverType) {
        this.turnoverType = turnoverType;
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

    public String getLatticeCode() {
        return latticeCode;
    }

    public void setLatticeCode(String latticeCode) {
        this.latticeCode = latticeCode;
    }

    public String getSortingNum() {
        return sortingNum;
    }

    public void setSortingNum(String sortingNum) {
        this.sortingNum = sortingNum;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getSortingMaterialType() {
        return sortingMaterialType;
    }

    public void setSortingMaterialType(String sortingMaterialType) {
        this.sortingMaterialType = sortingMaterialType;
    }

    @Override
    public String toString() {
        return "WmsSortingTask{" +
                "id=" + id +
                ", taskNumber='" + taskNumber + '\'' +
                ", turnoverType='" + turnoverType + '\'' +
                ", turnoverNumber='" + turnoverNumber + '\'' +
                ", barcode='" + barcode + '\'' +
                ", latticeCode='" + latticeCode + '\'' +
                ", sortingNum='" + sortingNum + '\'' +
                ", taskState='" + taskState + '\'' +
                ", createTime=" + createTime +
                ", reqTime=" + reqTime +
                ", dataTime=" + dataTime +
                ", sortingMaterialType='" + sortingMaterialType + '\'' +
                '}';
    }
}
