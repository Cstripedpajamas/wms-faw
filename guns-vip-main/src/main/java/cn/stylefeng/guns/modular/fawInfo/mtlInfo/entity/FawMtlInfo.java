package cn.stylefeng.guns.modular.fawInfo.mtlInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * faw物料信息
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
@TableName("faw_mtl_info")
public class FawMtlInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作类型
     */
    @TableField("di_opertype")
    private String diOpertype;

    /**
     * 批次号
     */
    @TableField("di_batch")
    private String diBatch;

    /**
     * 更新时间
     */
    @TableField("di_updatetime")
    private Date diUpdatetime;

    /**
     * 工厂
     */
    @TableField("plant")
    private String plant;

    /**
     * 物料号
     */
    @TableField("mtl_no")
    private String mtlNo;

    /**
     * 描述
     */
    @TableField("mtl_nodes")
    private String mtlNodes;

    /**
     * 物料类型
     */
    @TableField("mtl_type")
    private String mtlType;

    /**
     * 停用标识
     */
    @TableField("delflagforclientmtl")
    private String delflagforclientmtl;

    /**
     * 基本计量单位
     */
    @TableField("measurebaseunit")
    private String measurebaseunit;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiOpertype() {
        return diOpertype;
    }

    public void setDiOpertype(String diOpertype) {
        this.diOpertype = diOpertype;
    }

    public String getDiBatch() {
        return diBatch;
    }

    public void setDiBatch(String diBatch) {
        this.diBatch = diBatch;
    }

    public Date getDiUpdatetime() {
        return diUpdatetime;
    }

    public void setDiUpdatetime(Date diUpdatetime) {
        this.diUpdatetime = diUpdatetime;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getMtlNo() {
        return mtlNo;
    }

    public void setMtlNo(String mtlNo) {
        this.mtlNo = mtlNo;
    }

    public String getMtlNodes() {
        return mtlNodes;
    }

    public void setMtlNodes(String mtlNodes) {
        this.mtlNodes = mtlNodes;
    }

    public String getMtlType() {
        return mtlType;
    }

    public void setMtlType(String mtlType) {
        this.mtlType = mtlType;
    }

    public String getDelflagforclientmtl() {
        return delflagforclientmtl;
    }

    public void setDelflagforclientmtl(String delflagforclientmtl) {
        this.delflagforclientmtl = delflagforclientmtl;
    }

    public String getMeasurebaseunit() {
        return measurebaseunit;
    }

    public void setMeasurebaseunit(String measurebaseunit) {
        this.measurebaseunit = measurebaseunit;
    }

    public String getIndustrystnddes() {
        return industrystnddes;
    }

    public void setIndustrystnddes(String industrystnddes) {
        this.industrystnddes = industrystnddes;
    }

    public String getPagefromat() {
        return pagefromat;
    }

    public void setPagefromat(String pagefromat) {
        this.pagefromat = pagefromat;
    }

    public String getIsconfflag() {
        return isconfflag;
    }

    public void setIsconfflag(String isconfflag) {
        this.isconfflag = isconfflag;
    }

    public String getDatauser() {
        return datauser;
    }

    public void setDatauser(String datauser) {
        this.datauser = datauser;
    }

    public String getPurgrp() {
        return purgrp;
    }

    public void setPurgrp(String purgrp) {
        this.purgrp = purgrp;
    }

    public String getProcuretype() {
        return procuretype;
    }

    public void setProcuretype(String procuretype) {
        this.procuretype = procuretype;
    }

    public String getSpecprocuretype() {
        return specprocuretype;
    }

    public void setSpecprocuretype(String specprocuretype) {
        this.specprocuretype = specprocuretype;
    }

    public String getMrpcontroller() {
        return mrpcontroller;
    }

    public void setMrpcontroller(String mrpcontroller) {
        this.mrpcontroller = mrpcontroller;
    }

    public String getValctg() {
        return valctg;
    }

    public void setValctg(String valctg) {
        this.valctg = valctg;
    }

    public String getCroplant() {
        return croplant;
    }

    public void setCroplant(String croplant) {
        this.croplant = croplant;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getSpmtlstatus() {
        return spmtlstatus;
    }

    public void setSpmtlstatus(String spmtlstatus) {
        this.spmtlstatus = spmtlstatus;
    }

    @Override
    public String toString() {
        return "FawMtlInfo{" +
        "id=" + id +
        ", diOpertype=" + diOpertype +
        ", diBatch=" + diBatch +
        ", diUpdatetime=" + diUpdatetime +
        ", plant=" + plant +
        ", mtlNo=" + mtlNo +
        ", mtlNodes=" + mtlNodes +
        ", mtlType=" + mtlType +
        ", delflagforclientmtl=" + delflagforclientmtl +
        ", measurebaseunit=" + measurebaseunit +
        ", industrystnddes=" + industrystnddes +
        ", pagefromat=" + pagefromat +
        ", isconfflag=" + isconfflag +
        ", datauser=" + datauser +
        ", purgrp=" + purgrp +
        ", procuretype=" + procuretype +
        ", specprocuretype=" + specprocuretype +
        ", mrpcontroller=" + mrpcontroller +
        ", valctg=" + valctg +
        ", croplant=" + croplant +
        ", sizes=" + sizes +
        ", spmtlstatus=" + spmtlstatus +
        "}";
    }
}
