package cn.stylefeng.guns.modular.onetypecabinet.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅰ类柜盘点任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Data
public class WmsCabinet1CheckTaskResult implements Serializable {

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
     * 备用字段
     */
    private String taskRmk;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态(0初始 1开始 2格口全部打开 3盘点完成 4结束 )
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
     * 操作人姓名
     */
    private String userName;

}
