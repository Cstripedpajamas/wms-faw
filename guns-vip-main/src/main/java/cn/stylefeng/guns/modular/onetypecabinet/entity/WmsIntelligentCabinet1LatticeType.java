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
 * Ⅰ类柜格口类型信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@TableName("wms_intelligent_cabinet1_lattice_type")
public class WmsIntelligentCabinet1LatticeType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型名称
     */
    @TableField("type_name")
    private String typeName;

    /**
     * 类型规格
     */
    @TableField("type_standards")
    private String typeStandards;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 数据状态（1使用/2禁用）
     */
    @TableField("data_state")
    private String dataState;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeStandards() {
        return typeStandards;
    }

    public void setTypeStandards(String typeStandards) {
        this.typeStandards = typeStandards;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsIntelligentCabinet1LatticeType{" +
        "id=" + id +
        ", typeName=" + typeName +
        ", typeStandards=" + typeStandards +
        ", remarks=" + remarks +
        ", dataState=" + dataState +
        ", createTime=" + createTime +
        "}";
    }
}
