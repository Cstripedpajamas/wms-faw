package cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

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
@Data
public class WmsWarehousePurchaseorderDeliveryParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 采购订单号
     */
    private String purdocno;

    /**
     * 订单行项号
     */
    private String itemno;

    /**
     * 物料号
     */
    private String mtlno;

    /**
     * 发货数量
     */
    private String qty;

    /**
     * 发货单编号
     */
    private String code;

    /**
     * 发货时间
     */
    private Date sendtime;

    /**
     * 预计交货时间
     */
    private Date expectedreceivetime;

    /**
     * 发货行编号
     */
    private String linecode;

    /**
     * 发货单状态（1正常，0关闭）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date CreateTime;

    @Override
    public String checkParam() {
        return null;
    }

}
