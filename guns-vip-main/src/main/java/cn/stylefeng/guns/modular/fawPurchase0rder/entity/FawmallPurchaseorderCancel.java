package cn.stylefeng.guns.modular.fawPurchase0rder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * faw商城发货单
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
@TableName("fawmall_purchaseorder_cancel")
public class FawmallPurchaseorderCancel implements Serializable {

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

    @Override
    public String toString() {
        return "FawmallPurchaseorderCancel{" +
        "id=" + id +
        ", purdocno=" + purdocno +
        ", itemno=" + itemno +
        ", status=" + status +
        "}";
    }
}
