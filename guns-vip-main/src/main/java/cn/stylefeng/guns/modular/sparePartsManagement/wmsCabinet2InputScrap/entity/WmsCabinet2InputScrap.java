package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Ⅱ类柜投入报废信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@TableName("wms_cabinet2_input_scrap")
public class WmsCabinet2InputScrap implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 领用任务ID
     */
    @TableField("use_task")
    private String useTask;

    /**
     * 报废物料类型ID
     */
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 报废数量
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUseTask() {
        return useTask;
    }

    public void setUseTask(String useTask) {
        this.useTask = useTask;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
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

    @Override
    public String toString() {
        return "WmsCabinet2InputScrap{" +
        "id=" + id +
        ", useTask=" + useTask +
        ", materialTypeId=" + materialTypeId +
        ", mNumber=" + mNumber +
        ", operator=" + operator +
        ", operationTime=" + operationTime +
        "}";
    }
}
