package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅱ类柜领用任务信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2UseTaskResult implements Serializable {

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
     * 领用申请ID
     */
    private String useRequestId;

    /**
     * 流程单号
     */
    private String processNumber;

    /**
     * 报废-物料类型ID
     */
    private String sMaterialTypeId;

    /**
     * 物料信息ID
     */
    private String sMaterialId;

    /**
     * 报废数量
     */
    private String sNumber;

    /**
     * 领用-物料类型ID
     */
    private String useMaterialTypeId;

    /**
     * 物料信息ID
     */
    private String useMaterialId;

    /**
     * 领用数量
     */
    private String useNumber;

    /**
     * 库存信息ID
     */
    private String stockId;

    /**
     * 库位编号
     */
    private String locaNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态(0初始 1开始投入 2投入完成 3开始出库 4出库完成 5取货完成 6结束)
     */
    private String taskState;

    /**
     * 接口状态（0初始/1调用）
     */
    private String interfaceState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
