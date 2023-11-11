package cn.stylefeng.guns.modular.fawInfo.purchaseOrder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * faw采购订单信息表
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
@TableName("faw_purchase_order")
public class FawPurchaseOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 应用环境
     */
    @TableField("client")
    private String client;

    /**
     * 采购凭证行项
     */
    @TableField("pur_docItem_no")
    private String purDocitemNo;

    /**
     * 采购凭证号
     */
    @TableField("pur_doc_no")
    private String purDocNo;

    /**
     * 采购申请号
     */
    @TableField("purchase_req_no")
    private String purchaseReqNo;

    /**
     * 采购申请行项号
     */
    @TableField("item_no")
    private String itemNo;

    /**
     * PURStockBillID
     */
    @TableField("pur_stock_bill_id")
    private String purStockBillId;

    /**
     * 采购凭证类型
     */
    @TableField("buy_list_str_des")
    private String buyListStrDes;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建日期
     */
    @TableField("created_date")
    private Date createdDate;

    /**
     * 凭证日期
     */
    @TableField("doc_date")
    private Date docDate;

    /**
     * 估价标识
     */
    @TableField("estimated_price_indic")
    private String estimatedPriceIndic;

    /**
     * 交货日程日期
     */
    @TableField("item_delivery_date")
    private Date itemDeliveryDate;

    /**
     * 材料牌号
     */
    @TableField("mat_brand")
    private String matBrand;

    /**
     * 物料号
     */
    @TableField("mtl_no")
    private String mtlNo;

    /**
     * 订单的净价总值
     */
    @TableField("net_value")
    private String netValue;

    /**
     * 采购价格计量单位
     */
    @TableField("ord_price_unit")
    private String ordPriceUnit;

    /**
     * 采购凭证类型
     */
    @TableField("ord_type")
    private String ordType;

    /**
     * 工厂
     */
    @TableField("plant")
    private String plant;

    /**
     * 价格
     */
    @TableField("price")
    private String price;

    /**
     * 生产厂家/品牌
     */
    @TableField("promotion")
    private String promotion;

    /**
     * 申请人描述
     */
    @TableField("proposer_desc")
    private String proposerDesc;

    /**
     * 申请人ID
     */
    @TableField("proposer_id")
    private String proposerId;

    /**
     * 采购组
     */
    @TableField("pur_grp")
    private String purGrp;

    /**
     * 采购组描述
     */
    @TableField("pur_grp_desc")
    private String purGrpDesc;

    /**
     * 采购组织
     */
    @TableField("pur_org")
    private String purOrg;

    /**
     * 订单头ID
     */
    @TableField("purdocheader_id")
    private String purdocheaderId;

    /**
     * 订单行项ID
     */
    @TableField("purdocitem_id")
    private String purdocitemId;

    /**
     * 数量
     */
    @TableField("quantity")
    private String quantity;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 备注1
     */
    @TableField("remark1")
    private String remark1;

    /**
     * 存储地点
     */
    @TableField("store_location")
    private String storeLocation;

    /**
     * 供应商描述
     */
    @TableField("vendor_desc")
    private String vendorDesc;

    /**
     * 供应商代码
     */
    @TableField("vendor_no")
    private String vendorNo;

    /**
     * 创建人电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 采购申请创建人电话
     */
    @TableField("req_phone")
    private String reqPhone;

    /**
     * 工厂描述
     */
    @TableField("plant_des")
    private String plantDes;

    /**
     * 存储地点描述
     */
    @TableField("store_location_des")
    private String storeLocationDes;

    /**
     * 规格
     */
    @TableField("sizeco_l")
    private String sizecoL;

    /**
     * 计量单位描述
     */
    @TableField("unit_des")
    private String unitDes;

    /**
     * 创建人描述
     */
    @TableField("created_by_desc")
    private String createdByDesc;

    /**
     * 状态描述
     */
    @TableField("state_desc")
    private String stateDesc;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPurDocitemNo() {
        return purDocitemNo;
    }

    public void setPurDocitemNo(String purDocitemNo) {
        this.purDocitemNo = purDocitemNo;
    }

    public String getPurDocNo() {
        return purDocNo;
    }

    public void setPurDocNo(String purDocNo) {
        this.purDocNo = purDocNo;
    }

    public String getPurchaseReqNo() {
        return purchaseReqNo;
    }

    public void setPurchaseReqNo(String purchaseReqNo) {
        this.purchaseReqNo = purchaseReqNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getPurStockBillId() {
        return purStockBillId;
    }

    public void setPurStockBillId(String purStockBillId) {
        this.purStockBillId = purStockBillId;
    }

    public String getBuyListStrDes() {
        return buyListStrDes;
    }

    public void setBuyListStrDes(String buyListStrDes) {
        this.buyListStrDes = buyListStrDes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getEstimatedPriceIndic() {
        return estimatedPriceIndic;
    }

    public void setEstimatedPriceIndic(String estimatedPriceIndic) {
        this.estimatedPriceIndic = estimatedPriceIndic;
    }

    public Date getItemDeliveryDate() {
        return itemDeliveryDate;
    }

    public void setItemDeliveryDate(Date itemDeliveryDate) {
        this.itemDeliveryDate = itemDeliveryDate;
    }

    public String getMatBrand() {
        return matBrand;
    }

    public void setMatBrand(String matBrand) {
        this.matBrand = matBrand;
    }

    public String getMtlNo() {
        return mtlNo;
    }

    public void setMtlNo(String mtlNo) {
        this.mtlNo = mtlNo;
    }

    public String getNetValue() {
        return netValue;
    }

    public void setNetValue(String netValue) {
        this.netValue = netValue;
    }

    public String getOrdPriceUnit() {
        return ordPriceUnit;
    }

    public void setOrdPriceUnit(String ordPriceUnit) {
        this.ordPriceUnit = ordPriceUnit;
    }

    public String getOrdType() {
        return ordType;
    }

    public void setOrdType(String ordType) {
        this.ordType = ordType;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getProposerDesc() {
        return proposerDesc;
    }

    public void setProposerDesc(String proposerDesc) {
        this.proposerDesc = proposerDesc;
    }

    public String getProposerId() {
        return proposerId;
    }

    public void setProposerId(String proposerId) {
        this.proposerId = proposerId;
    }

    public String getPurGrp() {
        return purGrp;
    }

    public void setPurGrp(String purGrp) {
        this.purGrp = purGrp;
    }

    public String getPurGrpDesc() {
        return purGrpDesc;
    }

    public void setPurGrpDesc(String purGrpDesc) {
        this.purGrpDesc = purGrpDesc;
    }

    public String getPurOrg() {
        return purOrg;
    }

    public void setPurOrg(String purOrg) {
        this.purOrg = purOrg;
    }

    public String getPurdocheaderId() {
        return purdocheaderId;
    }

    public void setPurdocheaderId(String purdocheaderId) {
        this.purdocheaderId = purdocheaderId;
    }

    public String getPurdocitemId() {
        return purdocitemId;
    }

    public void setPurdocitemId(String purdocitemId) {
        this.purdocitemId = purdocitemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getVendorDesc() {
        return vendorDesc;
    }

    public void setVendorDesc(String vendorDesc) {
        this.vendorDesc = vendorDesc;
    }

    public String getVendorNo() {
        return vendorNo;
    }

    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReqPhone() {
        return reqPhone;
    }

    public void setReqPhone(String reqPhone) {
        this.reqPhone = reqPhone;
    }

    public String getPlantDes() {
        return plantDes;
    }

    public void setPlantDes(String plantDes) {
        this.plantDes = plantDes;
    }

    public String getStoreLocationDes() {
        return storeLocationDes;
    }

    public void setStoreLocationDes(String storeLocationDes) {
        this.storeLocationDes = storeLocationDes;
    }

    public String getSizecoL() {
        return sizecoL;
    }

    public void setSizecoL(String sizecoL) {
        this.sizecoL = sizecoL;
    }

    public String getUnitDes() {
        return unitDes;
    }

    public void setUnitDes(String unitDes) {
        this.unitDes = unitDes;
    }

    public String getCreatedByDesc() {
        return createdByDesc;
    }

    public void setCreatedByDesc(String createdByDesc) {
        this.createdByDesc = createdByDesc;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
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

    @Override
    public String toString() {
        return "FawPurchaseOrder{" +
        "id=" + id +
        ", sizecoL=" + sizecoL +
        ", client=" + client +
        ", purDocitemNo=" + purDocitemNo +
        ", purDocNo=" + purDocNo +
        ", purchaseReqNo=" + purchaseReqNo +
        ", itemNo=" + itemNo +
        ", purStockBillId=" + purStockBillId +
        ", buyListStrDes=" + buyListStrDes +
        ", createdBy=" + createdBy +
        ", createdDate=" + createdDate +
        ", docDate=" + docDate +
        ", estimatedPriceIndic=" + estimatedPriceIndic +
        ", itemDeliveryDate=" + itemDeliveryDate +
        ", matBrand=" + matBrand +
        ", mtlNo=" + mtlNo +
        ", netValue=" + netValue +
        ", ordPriceUnit=" + ordPriceUnit +
        ", ordType=" + ordType +
        ", plant=" + plant +
        ", price=" + price +
        ", promotion=" + promotion +
        ", proposerDesc=" + proposerDesc +
        ", proposerId=" + proposerId +
        ", purGrp=" + purGrp +
        ", purGrpDesc=" + purGrpDesc +
        ", purOrg=" + purOrg +
        ", purdocheaderId=" + purdocheaderId +
        ", purdocitemId=" + purdocitemId +
        ", quantity=" + quantity +
        ", remark=" + remark +
        ", remark1=" + remark1 +
        ", storeLocation=" + storeLocation +
        ", vendorDesc=" + vendorDesc +
        ", vendorNo=" + vendorNo +
        ", phone=" + phone +
        ", reqPhone=" + reqPhone +
        ", plantDes=" + plantDes +
        ", storeLocationDes=" + storeLocationDes +
        ", unitDes=" + unitDes +
        ", createdByDesc=" + createdByDesc +
        ", stateDesc=" + stateDesc +
        ", diOpertype=" + diOpertype +
        ", diBatch=" + diBatch +
        ", diUpdatetime=" + diUpdatetime +
        "}";
    }
}
