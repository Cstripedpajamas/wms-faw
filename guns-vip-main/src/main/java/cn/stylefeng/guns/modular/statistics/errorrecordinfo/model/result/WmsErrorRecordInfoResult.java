package cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 异常信息记录表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsErrorRecordInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 异常类型
     */
    private String errorType;

    /**
     * 异常描述
     */
    private String errorDescribe;

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
