package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅱ类柜盘点任务信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2CheckTaskParam implements Serializable, BaseValidatingParam {

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
     * 库位信息ID
     */
    private String stockId;

    /**
     * 库位编号
     */
    private String locaNumber;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态(0初始 1开始出库 2出库完成 3开始入库 4入库完成 5盘点结束)
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
     * 操作人员姓名
     */
    private String userName;
    /**
     * 操作人员工号
     */
    private String serialNumber;
    /**
     * 物料编号
     */
    private String materialSku;
    /**
     * 物料名称
     */
    private String materialName;

    @Override
    public String checkParam() {
        return null;
    }

}
