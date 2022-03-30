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
 * 立库-采购订单退还信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@TableName("wms_warehouse_purchase_order_return")
public class WmsWarehousePurchaseOrderReturn implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 采购订单信息
     */
    @ApiModelProperty(value = "采购订单信息")
    @TableField("purchase_id")
    private String purchaseId;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @TableField("m_number")
    private String mNumber;

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
     * 任务状态（0初始 1执行中 2完成）
     */
    @ApiModelProperty(value = "任务状态（0初始 1执行中 2完成）")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
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

    @Override
    public String toString() {
        return "WmsWarehousePurchaseOrderReturn{" +
        "id=" + id +
        ", purchaseId=" + purchaseId +
        ", mNumber=" + mNumber +
        ", operator=" + operator +
        ", operationTime=" + operationTime +
        ", taskState=" + taskState +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
