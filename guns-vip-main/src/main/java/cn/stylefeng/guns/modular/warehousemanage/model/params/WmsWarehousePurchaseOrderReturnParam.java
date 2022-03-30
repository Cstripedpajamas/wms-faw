package cn.stylefeng.guns.modular.warehousemanage.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 立库-采购订单退还信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehousePurchaseOrderReturnParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 采购订单信息
     */
    private String purchaseId;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态（0初始 1执行中 2完成）
     */
    private String taskState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public String checkParam() {
        return null;
    }

}
