package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 领用申请信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@TableName("wms_use_apply")
public class WmsUseApply implements Serializable {

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
     * 物料类型ID
     */
    @TableField("material_id")
    private String materialId;

    /**
     * 物料数量
     */
    @TableField("m_number")
    private String mNumber;

    /**
     * 申请类型（A工具领用/B备品备件领用）
     */
    @TableField("process_type")
    private String processType;

    /**
     * 申请原因
     */
    @TableField("process_reason")
    private String processReason;

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

    /**
     * 报废物料类型id
     * */
    @TableField("scrap_material_id")
    private String scrapMaterialId;

    /**
     * 报废数量
     * */
    @TableField("scrap_num")
    private String scrapNum;

    @Override
    public String toString() {
        return "WmsUseApply{" +
                "id=" + id +
                ", processNumber='" + processNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", materialId='" + materialId + '\'' +
                ", mNumber='" + mNumber + '\'' +
                ", processType='" + processType + '\'' +
                ", processReason='" + processReason + '\'' +
                ", dataTime=" + dataTime +
                ", dataState='" + dataState + '\'' +
                ", scrapMaterialId='" + scrapMaterialId + '\'' +
                ", scrapNum='" + scrapNum + '\'' +
                '}';
    }

    public String getScrapMaterialId() {
        return scrapMaterialId;
    }

    public void setScrapMaterialId(String scrapMaterialId) {
        this.scrapMaterialId = scrapMaterialId;
    }

    public String getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(String scrapNum) {
        this.scrapNum = scrapNum;
    }

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

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessReason() {
        return processReason;
    }

    public void setProcessReason(String processReason) {
        this.processReason = processReason;
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

}
