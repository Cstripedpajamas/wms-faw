package cn.stylefeng.guns.modular.base.purchaseorderinfo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 采购订单信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_purchase_order_info")
public class WmsPurchaseOrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *规格
     */
    @TableField("sizecol")
    private String sizecol;

    /**
     * 采购单号
     */
    @TableField("pur_number")
    private String purNumber;

    /**
     * 类型（1工具 2备品备件）
     */
    @TableField("type")
    private String type;

    /**
     * 物料类型ID
     */
    @TableField("material_type_id")
    private String materialTypeId;

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
     * 数量
     */
    @TableField("m_number")
    private String mNumber;

    /**
     * 到货时间
     */
    @TableField("arrival_time")
    private Date arrivalTime;

    /**
     * 到货状态（0初始 1入库 2接收 3退还）
     */
    @TableField("arrival_state")
    private String arrivalState;

    /**
     * 数据时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 接收数量
     */
    @TableField("received_quantity")
    private String receivedQuantity;

    /**
     * 打印数量
     */
    @TableField("print_num")
    private String printNum;

    /**
     * 可接收数量
     */
    @TableField("acceptable_quantity")
    private String acceptableQuantity;

    /**
     * 批次号
     */
    @TableField("di_batchno")
    private String diBatchNo;


    /**
     * 采购凭证行项
     */
    @TableField("purdocitemno")
    private String purdocitemno;

    /**
     * 采购凭证号
     */
    @TableField("purdocno")
    private String purdocno;

    /**
     * 采购申请号
     */
    @TableField("purchasereqno")
    private String purchasereqno;

    /**
     * 采购申请行项号
     */
    @TableField("itemno")
    private String itemno;

    /**
     *
     */
    @TableField("purstockbillid")
    private String purstockbillid;

    /**
     *采购凭证类型描述
     */
    @TableField("buyliststrdes")
    private String buyliststrdes;

    /**
     *创建人
     */
    @TableField("createdby")
    private String createdby;

    /**
     *创建日期
     */
    @TableField("createddate")
    private Date createddate;

    /**
     *凭证日期
     */
    @TableField("docDate")
    private Date docDate;

    /**
     *估价标识
     */
    @TableField("estimatedpriceindic")
    private String estimatedpriceindic;

    /**
     *材料牌号
     */
    @TableField("matBrand")
    private String matBrand;

    /**
     *订单的净价总值
     */
    @TableField("netvalue")
    private String netvalue;

    /**
     *采购价格计量单位
     */
    @TableField("ordpriceunit")
    private String ordpriceunit;

    /**
     *采购凭证类型
     */
    @TableField("ordtype")
    private String ordtype;

    /**
     *工厂
     */
    @TableField("plant")
    private String plant;

    /**
     *价格
     */
    @TableField("price")
    private String price;

    /**
     *生产厂家/品牌
     */
    @TableField("promotion")
    private String promotion;

    /**
     *申请人描述
     */
    @TableField("proposerdesc")
    private String proposerdesc;

    /**
     *申请人ID
     */
    @TableField("proposerid")
    private String proposerid;

    /**
     *采购组
     */
    @TableField("purgrp")
    private String purgrp;

    /**
     *采购组描述
     */
    @TableField("purgrpdesc")
    private String purgrpdesc;

    /**
     *采购组织
     */
    @TableField("purOrg")
    private String purOrg;

    /**
     *订单头ID
     */
    @TableField("purdocheaderid")
    private String purdocheaderid;

    /**
     *备注
     */
    @TableField("remark")
    private String remark;

    /**
     *备注1
     */
    @TableField("remark1")
    private String remark1;

    /**
     *存储地点
     */
    @TableField("storelocation")
    private String storelocation;

    /**
     *供应商描述
     */
    @TableField("vendordesc")
    private String vendordesc;

    /**
     *供应商代码
     */
    @TableField("vendorno")
    private String vendorno;

    /**
     *创建人电话
     */
    @TableField("phone")
    private String phone;

    /**
     *采购申请创建人电话
     */
    @TableField("reqphone")
    private String reqphone;

    /**
     *工厂描述
     */
    @TableField("plantdes")
    private String plantdes;

    /**
     *存储地点描述
     */
    @TableField("storelocationdes")
    private String storelocationdes;



    /**
     *计量单位描述
     */
    @TableField("unitdes")
    private String unitdes;

    /**
     *创建人描述
     */
    @TableField("createdbydesc")
    private String createdbydesc;

    /**
     *状态描述
     */
    @TableField("statedesc")
    private String statedesc;

    /**
     *操作类型
     */
    @TableField("di_opertype")
    private String diOperType;

    /**
     *更新时间
     */
    @TableField("di_updatetime")
    private Date diUpdatetime;

    /**
     *更新时间
     */
    @TableField("client")
    private String client;

    /**
     *接口调用uid
     */
    @TableField("uid")
    private String uid;

    public Date getDiUpdatetime() {
        return diUpdatetime;
    }

    public void setDiUpdatetime(Date diUpdatetime) {
        this.diUpdatetime = diUpdatetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurNumber() {
        return purNumber;
    }

    public String getClient() {
        return client;
    }

    public void setPurDocHeaderId(String purdocheaderid) {
        this.purdocheaderid = purdocheaderid;
    }

    public String getPurDocHeaderId() {
        return purdocheaderid;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPurDocNo() {
        return purdocno;
    }

    public void setPurDocNo(String purdocno) {
        this.purdocno=purdocno;
    }

    public String getItemNo() {
        return itemno;
    }

    public void setItemNo(String itemno) {
        this.itemno=itemno;
    }

    public void setPurNumber(String purNumber) {
        this.purNumber = purNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
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
    public String getSizecol() {
        return sizecol;
    }

    public void setSizecol(String sizecol) {
        this.sizecol = sizecol;
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

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalState() {
        return arrivalState;
    }

    public void setArrivalState(String arrivalState) {
        this.arrivalState = arrivalState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(String receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public String getPrintNum() {
        return printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    public String getAcceptableQuantity() {
        return acceptableQuantity;
    }

    public void setAcceptableQuantity(String acceptableQuantity) {
        this.acceptableQuantity = acceptableQuantity;
    }

    public String getDiBatchNo() {
        return diBatchNo;
    }

    public void setDiBatchNo(String diBatchNo) {
        this.diBatchNo = diBatchNo;
    }

    public String getPurchasereqno() {
        return purchasereqno;
    }

    public void setPurchasereqno(String purchasereqno) {
        this.purchasereqno = purchasereqno;
    }


    public String getPurdocitemno() {
        return purdocitemno;
    }

    public void setPurdocitemno(String purdocitemno) {
        this.purdocitemno = purdocitemno;
    }

    public String getPurstockbillid() {
        return purstockbillid;
    }

    public void setPurstockbillid(String purstockbillid) {
        this.purstockbillid = purstockbillid;
    }


    public String getBuyliststrdes() {
        return buyliststrdes;
    }

    public void setBuyliststrdes(String buyliststrdes) {
        this.buyliststrdes = buyliststrdes;
    }


    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }


    public String getPurgrp() { return purgrp; }

    public void setPurgrp(String purgrp) {
        this.purgrp = purgrp;
    }


    public String getPurOrg() { return purOrg; }

    public void setPurOrg(String purOrg) {
        this.purOrg = purOrg;
    }


    public String getRemark() { return remark; }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getStorelocation() { return storelocation; }

    public void setStorelocation(String storelocation) {
        this.storelocation = storelocation;
    }


    public String getVendordesc() { return vendordesc; }

    public void setVendordesc(String vendordesc) {
        this.vendordesc = vendordesc;
    }


    public String getVendorno() { return vendorno; }

    public void setVendorno(String vendorno) {
        this.vendorno = vendorno;
    }

    public Date getDocDate() { return docDate; }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "WmsPurchaseOrderInfo{" +
                "id=" + id +
                ", sizecol='" + sizecol + '\'' +
                ", purNumber='" + purNumber + '\'' +
                ", type='" + type + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", mUnit='" + mUnit + '\'' +
                ", mNumber='" + mNumber + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", arrivalState='" + arrivalState + '\'' +
                ", createTime=" + createTime +
                ", receivedQuantity='" + receivedQuantity + '\'' +
                ", printNum='" + printNum + '\'' +
                ", purchasereqno='" + purchasereqno + '\'' +
                ", purdocno='" + purdocno + '\'' +
                ", purdocitemno='" + purdocitemno + '\'' +
                ", diBatchNo='" + diBatchNo + '\'' +
                ", itemno='" + itemno + '\'' +
                ", purstockbillid='" + purstockbillid + '\'' +
                ", buyliststrdes='" + buyliststrdes + '\'' +
                ", promotion='" + promotion + '\'' +
                ", purgrp='" + purgrp + '\'' +
                ", purOrg='" + purOrg + '\'' +
                ", remark='" + remark + '\'' +
                ", storelocation='" + storelocation + '\'' +
                ", vendordesc='" + vendordesc + '\'' +
                ", vendorno='" + vendorno + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
