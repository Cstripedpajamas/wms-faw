package cn.stylefeng.guns.modular.warehousemanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-备品备件补货任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@TableName("wms_warehouse_replenishment_task")
public class WmsWarehouseReplenishmentTask implements Serializable {

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
     * 物料名称
     */
    @TableField("material_name")
    private String materialName;

    /**
     * 规格型号
     */
    @TableField("sizes")
    private String sizes;

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
     * 批次
     */
    @TableField("m_batch")
    private String mBatch;

    /**
     * 单位
     */
    @TableField("m_unit")
    private String mUnit;

    /**
     * 数量
     */
    @TableField("m_number")
    private String mNumber;

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
     * 任务状态（0初始 1开始 2出库中 3完成）
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

    /**
     * 分拣状态（0未分拣 1已分拣）
     */
    @TableField("sorting_status")
    private String sortingStatus;

    /**
     * 分拣类型（0人工）
     */
    @TableField("sorting_type")
    private String sortingType;

    /**
     * 分拣数量
     */
    @TableField("sorting_num")
    private String sortingNum;


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

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.materialName = sizes;
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

    public String getmBatch() {
        return mBatch;
    }

    public void setmBatch(String mBatch) {
        this.mBatch = mBatch;
    }

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
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

    public String getSortingStatus() {
        return sortingStatus;
    }

    public void setSortingStatus(String sortingStatus) {
        this.sortingStatus = sortingStatus;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public String getSortingNum() {
        return sortingNum;
    }

    public void setSortingNum(String sortingNum) {
        this.sortingNum = sortingNum;
    }

    @Override
    public String toString() {
        return "WmsWarehouseReplenishmentTask{" +
                "id=" + id +
                ", taskNumber='" + taskNumber + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", materialId='" + materialId + '\'' +
                ", mBatch='" + mBatch + '\'' +
                ", mUnit='" + mUnit + '\'' +
                ", mNumber='" + mNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                ", taskState='" + taskState + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sortingStatus='" + sortingStatus + '\'' +
                ", sortingType='" + sortingType + '\'' +
                ", sortingNum='" + sortingNum + '\'' +
                '}';
    }
}
