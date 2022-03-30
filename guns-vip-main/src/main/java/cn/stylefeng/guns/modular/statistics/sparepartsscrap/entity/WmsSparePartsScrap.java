package cn.stylefeng.guns.modular.statistics.sparepartsscrap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 备品备件报废信息汇总表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_spare_parts_scrap")
public class WmsSparePartsScrap implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 报废总数
     */
    @TableField("scrap_number")
    private String scrapNumber;

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

    public String getScrapNumber() {
        return scrapNumber;
    }

    public void setScrapNumber(String scrapNumber) {
        this.scrapNumber = scrapNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsSparePartsScrap{" +
        "id=" + id +
        ", materialTypeId=" + materialTypeId +
        ", materialName=" + materialName +
        ", materialSku=" + materialSku +
        ", scrapNumber=" + scrapNumber +
        ", createTime=" + createTime +
        "}";
    }
}
