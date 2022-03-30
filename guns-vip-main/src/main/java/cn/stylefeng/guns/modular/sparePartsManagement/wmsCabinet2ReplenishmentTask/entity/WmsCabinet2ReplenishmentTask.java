package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Ⅱ类柜补货任务信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@TableName("wms_cabinet2_replenishment_task")
public class WmsCabinet2ReplenishmentTask implements Serializable {

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
     * 库存信息ID
     */
    @TableField("stock_id")
    private String stockId;

    /**
     * 周转箱ID
     * */
    @TableField("turnover_id")
    private String turnoverId;

    /**
     * 周转箱编号
     * */
    @TableField("turnover_number")
    private String turnoverNumber;

    /**
     * 库位编号
     */
    @TableField("loca_number")
    private String locaNumber;

    /**
     * 操作人
     */
    @TableField("operator")
    private String operator;

    /**
     * 操作时间
     */
    @TableField("operation_time")
    private Date operationTime;

    /**
     * 任务状态(0初始 1空箱开始出库 2空箱出库完成 3开始入库 4入库完成 5结束)
     */
    @TableField("task_state")
    private String taskState;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
      @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @Override
    public String toString() {
        return "WmsCabinet2ReplenishmentTask{" +
                "id=" + id +
                ", taskNumber='" + taskNumber + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", mBatch='" + mBatch + '\'' +
                ", mNumber='" + mNumber + '\'' +
                ", stockId='" + stockId + '\'' +
                ", turnoverId='" + turnoverId + '\'' +
                ", turnoverNumber='" + turnoverNumber + '\'' +
                ", locaNumber='" + locaNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                ", taskState='" + taskState + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getTurnoverId() {
        return turnoverId;
    }

    public void setTurnoverId(String turnoverId) {
        this.turnoverId = turnoverId;
    }

    public String getTurnoverNumber() {
        return turnoverNumber;
    }

    public void setTurnoverNumber(String turnoverNumber) {
        this.turnoverNumber = turnoverNumber;
    }

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

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getLocaNumber() {
        return locaNumber;
    }

    public void setLocaNumber(String locaNumber) {
        this.locaNumber = locaNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
