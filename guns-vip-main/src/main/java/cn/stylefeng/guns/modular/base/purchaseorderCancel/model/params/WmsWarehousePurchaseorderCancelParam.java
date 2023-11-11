package cn.stylefeng.guns.modular.base.purchaseorderCancel.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

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
@Data
public class WmsWarehousePurchaseorderCancelParam implements Serializable, BaseValidatingParam {

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

    /**
     * 创建时间
     */
    private Date CreateTime;

    @Override
    public String checkParam() {
        return null;
    }

}
