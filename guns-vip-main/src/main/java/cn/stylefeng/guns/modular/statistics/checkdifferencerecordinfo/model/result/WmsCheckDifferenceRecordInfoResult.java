package cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 盘点差异记录表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsCheckDifferenceRecordInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 盘点仓库（1Ⅰ类柜 2Ⅱ类柜 3仓库）
     */
    private String checkWarehouse;

    /**
     * 盘点任务
     */
    private String checkTask;
    private String taskNumber;

    /**
     * 库位信息
     */
    private String locationInfo;
    private String locaSerialNumber;

    /**
     * 差异类型（多 少 空）
     */
    private String differenceType;

    /**
     * 差异描述
     */
    private String differenceDescribe;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 数据时间
     */
    private Date createTime;
}
