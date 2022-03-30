package cn.stylefeng.guns.modular.onetypecabinet.entity;

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
 * Ⅰ类柜盘点任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@TableName("wms_cabinet1_check_task")
public class WmsCabinet1CheckTask implements Serializable {

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
     * 备用字段
     */
    @ApiModelProperty(value = "备用字段")
    @TableField("task_rmk")
    private String taskRmk;

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
     * 任务状态(0初始 1开始 2格口全部打开 3盘点完成 4结束 )
     */
    @ApiModelProperty(value = "任务状态(0初始 1开始 2格口全部打开 3盘点完成 4结束 )")
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

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskRmk() {
        return taskRmk;
    }

    public void setTaskRmk(String taskRmk) {
        this.taskRmk = taskRmk;
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
        return "WmsCabinet1CheckTask{" +
        "id=" + id +
        ", taskNumber=" + taskNumber +
        ", taskRmk=" + taskRmk +
        ", operator=" + operator +
        ", operationTime=" + operationTime +
        ", taskState=" + taskState +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
