package cn.stylefeng.guns.modular.onetypecabinet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Ⅰ类柜归还任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@TableName("wms_cabinet1_return_task")
public class WmsCabinet1ReturnTask implements Serializable {

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
     * 归还申请ID
     */
    @TableField("return_request_id")
    private String returnRequestId;

    /**
     * 流程单号
     */
    @TableField("process_number")
    private String processNumber;

    /**
     * 物料类型ID
     */
    @TableField("material_type_id")
    private String materialTypeId;

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
     * 物料信息ID
     */
    @TableField("material_id")
    private String materialId;

    /**
     * 物料编码
     */
    @TableField("material_serial_number")
    private String materialSerialNumber;

    /**
     * 库存信息ID
     */
    @TableField("stock_id")
    private String stockId;

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
     * 任务状态(0初始 1开始 2开始存储 3存储完成 4结束)
     */
    @TableField("task_state")
    private String taskState;

    /**
     * 工具异常内容
     */
    @TableField("tool_error_content")
    private String toolErrorContent;

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

    public String getReturnRequestId() {
        return returnRequestId;
    }

    public void setReturnRequestId(String returnRequestId) {
        this.returnRequestId = returnRequestId;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
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

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialSerialNumber() {
        return materialSerialNumber;
    }

    public void setMaterialSerialNumber(String materialSerialNumber) {
        this.materialSerialNumber = materialSerialNumber;
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

    public String getToolErrorContent() {
        return toolErrorContent;
    }

    public void setToolErrorContent(String toolErrorContent) {
        this.toolErrorContent = toolErrorContent;
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
        return "WmsCabinet1ReturnTask{" +
                "id=" + id +
                ", taskNumber='" + taskNumber + '\'' +
                ", returnRequestId='" + returnRequestId + '\'' +
                ", processNumber='" + processNumber + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", materialId='" + materialId + '\'' +
                ", materialSerialNumber='" + materialSerialNumber + '\'' +
                ", stockId='" + stockId + '\'' +
                ", locaNumber='" + locaNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                ", taskState='" + taskState + '\'' +
                ", toolErrorContent='" + toolErrorContent + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
