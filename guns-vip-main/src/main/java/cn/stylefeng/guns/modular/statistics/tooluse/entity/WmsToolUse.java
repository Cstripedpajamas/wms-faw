package cn.stylefeng.guns.modular.statistics.tooluse.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 工具领用信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_tool_use")
public class WmsToolUse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 人员信息
     */
    @TableField("operator")
    private String operator;

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
     * 数据时间
     */
    @TableField("data_time")
    private Date dataTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    @Override
    public String toString() {
        return "WmsToolUse{" +
        "id=" + id +
        ", operator=" + operator +
        ", materialTypeId=" + materialTypeId +
        ", materialName=" + materialName +
        ", materialSku=" + materialSku +
        ", materialId=" + materialId +
        ", materialSerialNumber=" + materialSerialNumber +
        ", dataTime=" + dataTime +
        "}";
    }
}
