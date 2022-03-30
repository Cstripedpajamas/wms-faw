package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅱ类柜周转箱信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2TurnoverResult implements Serializable {

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
     * 条码信息
     */
    private String barcode;

    /**
     * 存放状态（1库内/2库外/3出入口）
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
     * 数据状态(0使用/1禁用)
     */
    private String dataState;

    /**
     * 数据时间
     */
    private Date createTime;

}
