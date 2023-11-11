package cn.stylefeng.guns.modular.fawPurchase0rder.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * faw商城取消采购订单
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
@Data
public class FawmallPurchaseorderDeliveryParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

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
    private String sendtime;

    /**
     * 预计交货时间
     */
    private String expectedreceivetime;

    /**
     * 发货行编号
     */
    private String linecode;

    /**
     * 发货单状态（1正常，0关闭）
     */
    private String status;

    @Override
    public String checkParam() {
        return null;
    }

}
