package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅱ类柜补货任务信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2ReplenishmentTaskParam implements Serializable, BaseValidatingParam {

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
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 批次
     */
    private String mBatch;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 库存信息ID
     */
    private String stockId;

    /**
     * 周转箱ID
     * */
    private String turnoverId;

    /**
     *  周转箱编号
     * */
    private String turnoverNumber;

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
     * 任务状态(0初始 1空箱开始出库 2空箱出库完成 3开始入库 4入库完成 5结束)
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
