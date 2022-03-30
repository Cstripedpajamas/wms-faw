package cn.stylefeng.guns.modular.base.materialType.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 物料类型信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_material_type")
public class WmsMaterialType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型（1工具 2备品备件）
     */
    @TableField("type")
    private String type;

    /**
     * 物料类型
     */
    @TableField("material_type")
    private String materialType;

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
     * 单位
     */
    @TableField("m_unit")
    private String mUnit;

    /**
     * 格口类型(仅工具)
     */
    @TableField("lattice_mouth_type")
    private String latticeMouthType;

    /**
     * 数据状态（0使用/1禁用）
     */
    @TableField("data_state")
    private String dataState;

    /**
     * 数据时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

      /**
       * 分拣类型 0人工 1 自动
       * */
      @TableField(value = "sort_type")
      private String sortType;

      /**
       * 包装类型
       * */
      @TableField(value = "package_type")
      private String packageType;

      /**
       * 包装数量
       * */
      @TableField(value = "package_number")
      private String packageNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
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

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getLatticeMouthType() {
        return latticeMouthType;
    }

    public void setLatticeMouthType(String latticeMouthType) {
        this.latticeMouthType = latticeMouthType;
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

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    @Override
    public String toString() {
        return "WmsMaterialType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", mUnit='" + mUnit + '\'' +
                ", latticeMouthType='" + latticeMouthType + '\'' +
                ", dataState='" + dataState + '\'' +
                ", createTime=" + createTime +
                ", sortType='" + sortType + '\'' +
                ", packageType='" + packageType + '\'' +
                ", packageNumber='" + packageNumber + '\'' +
                '}';
    }
}
