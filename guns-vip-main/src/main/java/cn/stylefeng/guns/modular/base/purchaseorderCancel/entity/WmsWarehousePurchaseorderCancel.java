package cn.stylefeng.guns.modular.base.purchaseorderCancel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单取消
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
@TableName("wms_warehouse_purchaseorder_cancel")
public class WmsWarehousePurchaseorderCancel implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;

    /**
     * ERP订单号
     */
    @TableField("purdocno")
    private String purdocno;

    /**
     * ERP凭证行项号
     */
    @TableField("itemno")
    private String itemno;

    /**
     * 单状态（Y删除）
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
        return "WmsWarehousePurchaseorderCancel{" +
        "id=" + id +
        ", purdocno=" + purdocno +
        ", itemno=" + itemno +
        ", status=" + status +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
