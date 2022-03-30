package cn.stylefeng.guns.modular.warehousemanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-工具领用任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@TableName("wms_warehouse_tool_use_task")
public class WmsWarehouseToolUseTask implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 领用申请ID
     */
    @TableField("use_request_id")
    private String useRequestId;

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
     * 周转箱信息ID
     */
    @TableField("turnover_id")
    private String turnoverId;

    /**
     * 格口编号
     */
    @TableField("lattice_code")
    private String latticeCode;

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
     * 任务状态（0初始 1开始 2出库中 3完成 ）
     */
    @TableField("task_state")
    private String taskState;

    /**
     * 接口状态（0初始/1调用）
     */
    @TableField("interface_state")
    private String interfaceState;

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

    /**
     * 分拣类型（0人工 1自动)
     */
    @TableField("sorting_type")
    private String sortingType;

    /**
     * 分拣状态（0未分拣 1已分拣）
     */
    private String sortingStatus;

    /**
     * 分拣任务 (机械手分拣ID)
     */
    private String sortingTask;

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

    public String getUseRequestId() {
        return useRequestId;
    }

    public void setUseRequestId(String useRequestId) {
        this.useRequestId = useRequestId;
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

    public String getTurnoverId() {
        return turnoverId;
    }

    public void setTurnoverId(String turnoverId) {
        this.turnoverId = turnoverId;
    }

    public String getLatticeCode() {
        return latticeCode;
    }

    public void setLatticeCode(String latticeCode) {
        this.latticeCode = latticeCode;
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

    public String getInterfaceState() {
        return interfaceState;
    }

    public void setInterfaceState(String interfaceState) {
        this.interfaceState = interfaceState;
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

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public String getSortingStatus() {
        return sortingStatus;
    }

    public void setSortingStatus(String sortingStatus) {
        this.sortingStatus = sortingStatus;
    }

    public String getSortingTask() {
        return sortingTask;
    }

    public void setSortingTask(String sortingTask) {
        this.sortingTask = sortingTask;
    }

    @Override
    public String toString() {
        return "WmsWarehouseToolUseTask{" +
                "id=" + id +
                ", taskNumber='" + taskNumber + '\'' +
                ", useRequestId='" + useRequestId + '\'' +
                ", processNumber='" + processNumber + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", materialId='" + materialId + '\'' +
                ", materialSerialNumber='" + materialSerialNumber + '\'' +
                ", stockId='" + stockId + '\'' +
                ", locaNumber='" + locaNumber + '\'' +
                ", turnoverId='" + turnoverId + '\'' +
                ", latticeCode='" + latticeCode + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                ", taskState='" + taskState + '\'' +
                ", interfaceState='" + interfaceState + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sortingType='" + sortingType + '\'' +
                ", sortingStatus='" + sortingStatus + '\'' +
                ", sortingTask='" + sortingTask + '\'' +
                '}';
    }
}
