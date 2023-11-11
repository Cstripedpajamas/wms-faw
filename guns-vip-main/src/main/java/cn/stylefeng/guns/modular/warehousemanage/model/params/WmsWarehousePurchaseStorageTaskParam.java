package cn.stylefeng.guns.modular.warehousemanage.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-采购入库任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehousePurchaseStorageTaskParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 任务编号
     */
    private String taskNumber;

    /**
     * 采购订单信息ID
     */
    private String purchaseId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态（0初始 1开始 2入库中 3完成）
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

    /**
     * 总数量
     */
    private String totalQuantity;

    /**
     * 可接收数量
     */
    private String acceptableQuantity;

    /**
     * 已接收数量
     */
    private String receivedQuantity;

    /**
     * 已组盘数量
     */
    private String groupdQuantity;

    /**
     * 操作人姓名
     */
    private String userName;

    /**
     * 采购凭证行号
     */
    private String purdocItemno;

    /**
     * 描述
     */
    private String materialName;

    /**
     * 物料号
     */
    private String materialSku;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 规格
     */
    private String sizecol;

    /**
     * 数量
     */
    private String mNumber;


    /**
     * 接口调用Uid
     */
    private String uid;


    @Override
    public String checkParam() {
        return null;
    }

}
