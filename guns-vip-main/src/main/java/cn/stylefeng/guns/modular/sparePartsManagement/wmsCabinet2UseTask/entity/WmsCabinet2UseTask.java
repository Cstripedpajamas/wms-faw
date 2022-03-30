package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Ⅱ类柜领用任务信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@TableName("wms_cabinet2_use_task")
public class WmsCabinet2UseTask implements Serializable {

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
     * 报废-物料类型ID
     */
    @TableField("s_material_type_id")
    private String sMaterialTypeId;

    /**
     * 物料信息ID
     */
    @TableField("s_material_id")
    private String sMaterialId;

    /**
     * 报废数量
     */
    @TableField("s_number")
    private String sNumber;

    /**
     * 领用-物料类型ID
     */
    @TableField("use_material_type_id")
    private String useMaterialTypeId;

    /**
     * 物料信息ID
     */
    @TableField("use_material_id")
    private String useMaterialId;

    /**
     * 领用数量
     */
    @TableField("use_number")
    private String useNumber;

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
     * 任务状态(0初始 1开始投入 2投入完成 3开始出库 4出库完成 5取货完成 6结束)
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

    public String getsMaterialTypeId() {
        return sMaterialTypeId;
    }

    public void setsMaterialTypeId(String sMaterialTypeId) {
        this.sMaterialTypeId = sMaterialTypeId;
    }

    public String getsMaterialId() {
        return sMaterialId;
    }

    public void setsMaterialId(String sMaterialId) {
        this.sMaterialId = sMaterialId;
    }

    public String getsNumber() {
        return sNumber;
    }

    public void setsNumber(String sNumber) {
        this.sNumber = sNumber;
    }

    public String getUseMaterialTypeId() {
        return useMaterialTypeId;
    }

    public void setUseMaterialTypeId(String useMaterialTypeId) {
        this.useMaterialTypeId = useMaterialTypeId;
    }

    public String getUseMaterialId() {
        return useMaterialId;
    }

    public void setUseMaterialId(String useMaterialId) {
        this.useMaterialId = useMaterialId;
    }

    public String getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(String useNumber) {
        this.useNumber = useNumber;
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

    @Override
    public String toString() {
        return "WmsCabinet2UseTask{" +
        "id=" + id +
        ", taskNumber=" + taskNumber +
        ", useRequestId=" + useRequestId +
        ", processNumber=" + processNumber +
        ", sMaterialTypeId=" + sMaterialTypeId +
        ", sMaterialId=" + sMaterialId +
        ", sNumber=" + sNumber +
        ", useMaterialTypeId=" + useMaterialTypeId +
        ", useMaterialId=" + useMaterialId +
        ", useNumber=" + useNumber +
        ", stockId=" + stockId +
        ", locaNumber=" + locaNumber +
        ", operator=" + operator +
        ", operationTime=" + operationTime +
        ", taskState=" + taskState +
        ", interfaceState=" + interfaceState +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
