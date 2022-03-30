package cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 归还申请信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@TableName("wms_return_apply")
public class WmsReturnApply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 流程单号
     */
    @TableField("process_number")
    private String processNumber;

    /**
     * 人员信息
     */
    @TableField("operator")
    private String operator;

    /**
     * 物料信息
     */
    @TableField("material_id")
    private String materialId;

    /**
     * 物料数量
     */
    @TableField("m_number")
    private String mNumber;

    /**
     * 归还原因
     */
    @TableField("return_reason")
    private String returnReason;

    /**
     * 数据时间
     */
    @TableField("data_time")
    private Date dataTime;

    /**
     * 数据状态(0初始 1审核中 2通过 3结束)
     */
    @TableField("data_state")
    private String dataState;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    @Override
    public String toString() {
        return "WmsReturnApply{" +
        "id=" + id +
        ", processNumber=" + processNumber +
        ", operator=" + operator +
        ", materialId=" + materialId +
        ", mNumber=" + mNumber +
        ", returnReason=" + returnReason +
        ", dataTime=" + dataTime +
        ", dataState=" + dataState +
        "}";
    }
}
