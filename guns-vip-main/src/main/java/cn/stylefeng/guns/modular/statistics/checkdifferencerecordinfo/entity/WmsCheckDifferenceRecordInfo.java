package cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 盘点差异记录表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_check_difference_record_info")
public class WmsCheckDifferenceRecordInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 盘点仓库（1Ⅰ类柜 2Ⅱ类柜 3仓库）
     */
    @TableField("check_warehouse")
    private String checkWarehouse;

    /**
     * 盘点任务
     */
    @TableField("check_task")
    private String checkTask;

    /**
     * 库位信息
     */
    @TableField("location_info")
    private String locationInfo;

    /**
     * 差异类型（多 少 空）
     */
    @TableField("difference_type")
    private String differenceType;

    /**
     * 差异描述
     */
    @TableField("difference_describe")
    private String differenceDescribe;

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
     * 数据时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckWarehouse() {
        return checkWarehouse;
    }

    public void setCheckWarehouse(String checkWarehouse) {
        this.checkWarehouse = checkWarehouse;
    }

    public String getCheckTask() {
        return checkTask;
    }

    public void setCheckTask(String checkTask) {
        this.checkTask = checkTask;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getDifferenceType() {
        return differenceType;
    }

    public void setDifferenceType(String differenceType) {
        this.differenceType = differenceType;
    }

    public String getDifferenceDescribe() {
        return differenceDescribe;
    }

    public void setDifferenceDescribe(String differenceDescribe) {
        this.differenceDescribe = differenceDescribe;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsCheckDifferenceRecordInfo{" +
        "id=" + id +
        ", checkWarehouse=" + checkWarehouse +
        ", checkTask=" + checkTask +
        ", locationInfo=" + locationInfo +
        ", differenceType=" + differenceType +
        ", differenceDescribe=" + differenceDescribe +
        ", operator=" + operator +
        ", operationTime=" + operationTime +
        ", createTime=" + createTime +
        "}";
    }
}
