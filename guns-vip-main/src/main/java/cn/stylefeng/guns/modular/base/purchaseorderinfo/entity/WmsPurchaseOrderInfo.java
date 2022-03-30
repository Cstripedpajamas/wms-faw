package cn.stylefeng.guns.modular.base.purchaseorderinfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurNumber() {
        return purNumber;
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

    @Override
    public String toString() {
        return "WmsPurchaseOrderInfo{" +
                "id=" + id +
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
                '}';
    }
}
