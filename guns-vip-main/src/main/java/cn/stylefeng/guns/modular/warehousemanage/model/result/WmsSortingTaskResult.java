package cn.stylefeng.guns.modular.warehousemanage.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 机械手分拣任务表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-23
 */
@Data
public class WmsSortingTaskResult implements Serializable {

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
     * 周转箱类型(A小/B中/C大)
     */
    private String turnoverType;

    /**
     * 周转箱编号
     */
    private String turnoverNumber;

    /**
     * 周转箱条码
     */
    private String barcode;

    /**
     * 分拣格口（A/B）
     */
    private String latticeCode;

    /**
     * 分拣数量
     */
    private String sortingNum;

    /**
     * 任务状态（0初始 1开始分拣 2分拣完成 3结束）
     */
    private String taskState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 请求时间
     */
    private Date reqTime;

    /**
     * 数据时间
     */
    private Date dataTime;

    /**
     * 分拣物料类型
     * */
    private String sortingMaterialType;

}
