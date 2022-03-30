package cn.stylefeng.guns.modular.warehousemanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 立库-采购入库任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@TableName("wms_warehouse_purchase_storage_task")
public class WmsWarehousePurchaseStorageTask implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @TableField("task_number")
    private String taskNumber;

    /**
     * 采购订单信息ID
     */
    @ApiModelProperty(value = "采购订单信息ID")
    @TableField("purchase_id")
    private String purchaseId;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    @TableField("operator")
    private String operator;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    @TableField("operation_time")
    private Date operationTime;

    /**
     * 任务状态（0初始 1开始 2入库中 3完成）
     */
    @ApiModelProperty(value = "任务状态（0初始 1开始 2入库中 3完成）")
    @TableField("task_state")
    private String taskState;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
      @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量")
    @TableField(value = "total_quantity")
    private String totalQuantity;

    /**
     * 可接收数量
     */
    @ApiModelProperty(value = "可接收数量")
    @TableField(value = "acceptable_quantity")
    private String acceptableQuantity;

    /**
     * 已接收数量
     */
    @ApiModelProperty(value = "已接收数量")
    @TableField(value = "received_quantity")
    private String receivedQuantity;

    /**
     * 已组盘数量
     */
    @ApiModelProperty(value = "已组盘数量")
    @TableField(value = "groupd_quantity")
    private String groupdQuantity;

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

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
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

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getAcceptableQuantity() {
        return acceptableQuantity;
    }

    public void setAcceptableQuantity(String acceptableQuantity) {
        this.acceptableQuantity = acceptableQuantity;
    }

    public String getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(String receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public String getGroupdQuantity() {
        return groupdQuantity;
    }

    public void setGroupdQuantity(String groupdQuantity) {
        this.groupdQuantity = groupdQuantity;
    }

    @Override
    public String toString() {
        return "WmsWarehousePurchaseStorageTask{" +
                "id=" + id +
                ", taskNumber='" + taskNumber + '\'' +
                ", purchaseId='" + purchaseId + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                ", taskState='" + taskState + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", totalQuantity='" + totalQuantity + '\'' +
                ", acceptableQuantity='" + acceptableQuantity + '\'' +
                ", receivedQuantity='" + receivedQuantity + '\'' +
                ", groupdQuantity='" + groupdQuantity + '\'' +
                '}';
    }
}
