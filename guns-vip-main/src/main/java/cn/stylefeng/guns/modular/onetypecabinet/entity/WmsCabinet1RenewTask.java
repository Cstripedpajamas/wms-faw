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
 * Ⅰ类柜换新任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@TableName("wms_cabinet1_renew_task")
public class WmsCabinet1RenewTask implements Serializable {

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
     * 物料类型ID
     */
    @ApiModelProperty(value = "物料类型ID")
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    @TableField("material_name")
    private String materialName;

    /**
     * 物料SKU
     */
    @ApiModelProperty(value = "物料SKU")
    @TableField("material_sku")
    private String materialSku;

    /**
     * 旧物料信息ID
     */
    @ApiModelProperty(value = "旧物料信息ID")
    @TableField("o_material_id")
    private String oMaterialId;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    @TableField("o_material_serial_number")
    private String oMaterialSerialNumber;

    /**
     * 新-物料信息ID
     */
    @ApiModelProperty(value = "新-物料信息ID")
    @TableField("n_material_id")
    private String nMaterialId;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    @TableField("n_material_serial_number")
    private String nMaterialSerialNumber;

    /**
     * 存储-库存信息ID
     */
    @ApiModelProperty(value = "存储-库存信息ID")
    @TableField("storage_stock_id")
    private String storageStockId;

    /**
     * 库位编号
     */
    @ApiModelProperty(value = "库位编号")
    @TableField("storage_loca_serial_number")
    private String storageLocaSerialNumber;

    /**
     * 取货-库存信息ID
     */
    @ApiModelProperty(value = "取货-库存信息ID")
    @TableField("task_stock_id")
    private String taskStockId;

    /**
     * 库位编号
     */
    @ApiModelProperty(value = "库位编号")
    @TableField("task_loca_serial_number")
    private String taskLocaSerialNumber;

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
     * 任务状态(0初始 1开始 2开始存储 3存储完成 4开始取货 5取货完成 6结束)
     */
    @ApiModelProperty(value = "任务状态(0初始 1开始 2开始存储 3存储完成 4开始取货 5取货完成 6结束)")
    @TableField("task_state")
    private String taskState;

    /**
     * 异常标记（0初始 1异常）
     */
    @ApiModelProperty(value = "异常标记（0初始 1异常）")
    @TableField("error_trag")
    private String errorTrag;

    /**
     * 异常内容
     */
    @ApiModelProperty(value = "异常内容")
    @TableField("tool_error_content")
    private String toolErrorContent;

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
     * 自动关闭标识(0:确认关闭 1：自动关闭)
     */
    @ApiModelProperty(value = "自动关闭标识")
    @TableField(value = "auto_flag")
    private String autoFlag;


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

    public String getoMaterialId() {
        return oMaterialId;
    }

    public void setoMaterialId(String oMaterialId) {
        this.oMaterialId = oMaterialId;
    }

    public String getoMaterialSerialNumber() {
        return oMaterialSerialNumber;
    }

    public void setoMaterialSerialNumber(String oMaterialSerialNumber) {
        this.oMaterialSerialNumber = oMaterialSerialNumber;
    }

    public String getnMaterialId() {
        return nMaterialId;
    }

    public void setnMaterialId(String nMaterialId) {
        this.nMaterialId = nMaterialId;
    }

    public String getnMaterialSerialNumber() {
        return nMaterialSerialNumber;
    }

    public void setnMaterialSerialNumber(String nMaterialSerialNumber) {
        this.nMaterialSerialNumber = nMaterialSerialNumber;
    }

    public String getStorageStockId() {
        return storageStockId;
    }

    public void setStorageStockId(String storageStockId) {
        this.storageStockId = storageStockId;
    }

    public String getStorageLocaSerialNumber() {
        return storageLocaSerialNumber;
    }

    public void setStorageLocaSerialNumber(String storageLocaSerialNumber) {
        this.storageLocaSerialNumber = storageLocaSerialNumber;
    }

    public String getTaskStockId() {
        return taskStockId;
    }

    public void setTaskStockId(String taskStockId) {
        this.taskStockId = taskStockId;
    }

    public String getTaskLocaSerialNumber() {
        return taskLocaSerialNumber;
    }

    public void setTaskLocaSerialNumber(String taskLocaSerialNumber) {
        this.taskLocaSerialNumber = taskLocaSerialNumber;
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

    public String getErrorTrag() {
        return errorTrag;
    }

    public void setErrorTrag(String errorTrag) {
        this.errorTrag = errorTrag;
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

    public String getAutoFlag() {
        return autoFlag;
    }

    public void setAutoFlag(String autoFlag) {
        this.autoFlag = autoFlag;
    }

    @Override
    public String toString() {
        return "WmsCabinet1RenewTask{" +
                "id=" + id +
                ", taskNumber='" + taskNumber + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", oMaterialId='" + oMaterialId + '\'' +
                ", oMaterialSerialNumber='" + oMaterialSerialNumber + '\'' +
                ", nMaterialId='" + nMaterialId + '\'' +
                ", nMaterialSerialNumber='" + nMaterialSerialNumber + '\'' +
                ", storageStockId='" + storageStockId + '\'' +
                ", storageLocaSerialNumber='" + storageLocaSerialNumber + '\'' +
                ", taskStockId='" + taskStockId + '\'' +
                ", taskLocaSerialNumber='" + taskLocaSerialNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                ", taskState='" + taskState + '\'' +
                ", errorTrag='" + errorTrag + '\'' +
                ", toolErrorContent='" + toolErrorContent + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", autoFlag='" + autoFlag + '\'' +
                '}';
    }
}
