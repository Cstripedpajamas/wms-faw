package cn.stylefeng.guns.modular.statistics.errorrecordinfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 异常信息记录表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_error_record_info")
public class WmsErrorRecordInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 异常类型
     */
    @TableField("error_type")
    private String errorType;

    /**
     * 异常描述
     */
    @TableField("error_describe")
    private String errorDescribe;

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

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorDescribe() {
        return errorDescribe;
    }

    public void setErrorDescribe(String errorDescribe) {
        this.errorDescribe = errorDescribe;
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
        return "WmsErrorRecordInfo{" +
        "id=" + id +
        ", errorType=" + errorType +
        ", errorDescribe=" + errorDescribe +
        ", operator=" + operator +
        ", operationTime=" + operationTime +
        ", createTime=" + createTime +
        "}";
    }
}
