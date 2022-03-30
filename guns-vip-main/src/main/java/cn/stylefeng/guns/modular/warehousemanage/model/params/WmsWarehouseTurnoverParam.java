package cn.stylefeng.guns.modular.warehousemanage.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 立库-周转箱信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseTurnoverParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 周转箱编号
     */
    private String turnoverNumber;

    /**
     * 周转箱类型(A单格口/B双格口)
     */
    private String turnoverType;

    /**
     * 条码信息
     */
    private String barcode;

    /**
     * 存放状态（1库内/2库外）
     */
    private String storageStatus;

    /**
     * 存放-排
     */
    private String locaRow;

    /**
     * 存放-列
     */
    private String locaCol;

    /**
     * 存放-层
     */
    private String locaLayer;

    /**
     * 周转箱状态（0空闲/1有货/2盘点）
     */
    private String turnoverState;

    /**
     * 格口数量
     */
    private String turnoverMouthQuantity;

    /**
     * 归属仓库
     */
    private String affWarehouse;

    /**
     * 数据状态(0使用/1禁用)
     */
    private String dataState;

    /**
     * 数据时间
     */
    private Date createTime;

    @Override
    public String checkParam() {
        return null;
    }

}
