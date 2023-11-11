package cn.stylefeng.guns.modular.base.materialType.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

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
     * 描述
     */
    @TableField("material_name")
    private String materialName;

    /**
     * 工厂
     */
    @TableField("plant")
    private String plant;

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
     * 操作类型
     */
    @TableField("di_opertype")
    private String diOperType;

    /**
     * 批次号
     */
    @TableField("di_batchNo")
    private String diBatchNo;

    /**
     * 更新时间
     */
    @TableField("di_updatetime")
    private Date diUpdatetime;

    /**
     * 停用标识
     */
    @TableField("delflagforclientmtl")
    private String delflagforclientmtl;

    /**
     * 制造路线
     */
    @TableField("industrystnddes")
    private String industrystnddes;

    /**
     * 可配置物料标识
     */
    @TableField("pagefromat")
    private String pagefromat;

    /**
     * 可配置物料标识
     */
    @TableField("isconfflag")
    private String isconfflag;

    /**
     * 创建对象的用户代码
     */
    @TableField("datauser")
    private String datauser;

    /**
     * 采购组代码
     */
    @TableField("purgrp")
    private String purgrp;

    /**
     * 获取类型
     */
    @TableField("procuretype")
    private String procuretype;

    /**
     * 特殊获取类型
     */
    @TableField("specprocuretype")
    private String specprocuretype;


    /**
     * MRP控制者
     */
    @TableField("mrpcontroller")
    private String mrpcontroller;

    /**
     * 估价类别
     */
    @TableField("valctg")
    private String valctg;

    /**
     * 可配置物料
     */
    @TableField("croplant")
    private String croplant;

    /**
     * 规格型号
     */
    @TableField("sizes")
    private String sizes;

    /**
     * C标识
     */
    @TableField("spmtlstatus")
    private String spmtlstatus;

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


    /**
     * 周转箱类型
     * */
    @TableField(value = "turnover_type")
    private String turnoverType;

    /**
     * 周转箱格口数量
     * */
    @TableField(value = "turnover_lattice_type")
    private String turnoverLatticeType;



    /**
     *  RFID 标识
     * */
    @TableField(value = "label")
    private String label;

    /**
     * 来源
     * */
    @TableField(value = "source")
    private String source;

    /**
     * 图片地址
     * */
    @TableField(value = "image_path")
    private String imagePath;

    /**
     * 工厂
     */
//    @TableField("plant")
//    private String plant;

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
                ", turnoverType='" + turnoverType + '\'' +
                ", turnoverLatticeType='" + turnoverLatticeType + '\'' +
                ", label='" + label + '\'' +
                ", source='" + source + '\'' +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTurnoverLatticeType() {
        return turnoverLatticeType;
    }

    public void setTurnoverLatticeType(String turnoverLatticeType) {
        this.turnoverLatticeType = turnoverLatticeType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTurnoverType() {
        return turnoverType;
    }

    public void setTurnoverType(String turnoverType) {
        this.turnoverType = turnoverType;
    }

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
//
//    public String getDiBatchNo() {
//        return di_batchNo;
//    }
//
//    public void setDiBatchNo(String di_batchNo) {
//        this.di_batchNo = di_batchNo;
//    }

    public String getPlant() {
        return plant;
    }

    public String getSizes() {
        return sizes;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getDi_opertype() {
        return diOperType;
    }

    public void setDi_opertype(String di_opertype) {
        this.diOperType = di_opertype;
    }

    public String getDiBatchNo() {
        return diBatchNo;
    }

    public void setDiBatchNo(String diBatchNo) {
        this.diBatchNo = diBatchNo;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
