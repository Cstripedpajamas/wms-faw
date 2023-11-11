package cn.stylefeng.guns.modular.fawPurchase0rder.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * faw商城发货单
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
@Data
public class FawmallPurchaseorderCancelParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * ERP订单号
     */
    private String purdocno;

    /**
     * ERP凭证行项号
     */
    private String itemno;

    /**
     * 单状态（Y删除）
     */
    private String status;

    @Override
    public String checkParam() {
        return null;
    }

}
