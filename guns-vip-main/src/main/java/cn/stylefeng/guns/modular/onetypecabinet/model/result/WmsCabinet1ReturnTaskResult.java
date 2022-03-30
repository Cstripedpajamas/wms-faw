package cn.stylefeng.guns.modular.onetypecabinet.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅰ类柜归还任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Data
public class WmsCabinet1ReturnTaskResult implements Serializable {

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
     * 归还申请ID
     */
    private String returnRequestId;

    /**
     * 流程单号
     */
    private String processNumber;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 物料编码
     */
    private String materialSerialNumber;

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
     * 任务状态(0初始 1开始 2开始存储 3存储完成 4结束)
     */
    private String taskState;

    /**
     * 工具异常内容
     */
    private String toolErrorContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
