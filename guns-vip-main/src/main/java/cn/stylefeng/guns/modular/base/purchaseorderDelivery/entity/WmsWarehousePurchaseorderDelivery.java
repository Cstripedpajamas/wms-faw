package cn.stylefeng.guns.modular.base.purchaseorderDelivery.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 发货单
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
@TableName("wms_warehouse_purchaseorder_delivery")
public class WmsWarehousePurchaseorderDelivery implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;

    /**
     * 采购订单号
     */
    @TableField("purdocno")
    private String purdocno;

    /**
     * 订单行项号
     */
    @TableField("itemno")
    private String itemno;

    /**
     * 物料号
     */
    @TableField("mtlno")
    private String mtlno;

    /**
     * 发货数量
     */
    @TableField("QTY")
    private String qty;

    /**
     * 发货单编号
     */
    @TableField("CODE")
    private String code;

    /**
     * 发货时间
     */
    @TableField("SENDTIME")
    private Date sendtime;

    /**
     * 预计交货时间
     */
    @TableField("EXPECTEDRECEIVETIME")
    private Date expectedreceivetime;

    /**
     * 发货行编号
     */
    @TableField("LINECODE")
    private String linecode;

    /**
     * 发货单状态（1正常，0关闭）
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private Date CreateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurdocno() {
        return purdocno;
    }

    public void setPurdocno(String purdocno) {
        this.purdocno = purdocno;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getMtlno() {
        return mtlno;
    }

    public void setMtlno(String mtlno) {
        this.mtlno = mtlno;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getExpectedreceivetime() {
        return expectedreceivetime;
    }

    public void setExpectedreceivetime(Date expectedreceivetime) {
        this.expectedreceivetime = expectedreceivetime;
    }

    public String getLinecode() {
        return linecode;
    }

    public void setLinecode(String linecode) {
        this.linecode = linecode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    public String toString() {
        return "WmsWarehousePurchaseorderDelivery{" +
        "id=" + id +
        ", purdocno=" + purdocno +
        ", itemno=" + itemno +
        ", mtlno=" + mtlno +
        ", qty=" + qty +
        ", code=" + code +
        ", sendtime=" + sendtime +
        ", expectedreceivetime=" + expectedreceivetime +
        ", linecode=" + linecode +
        ", status=" + status +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
