package cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 盘点差异记录表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsCheckDifferenceRecordInfoParam implements Serializable, BaseValidatingParam {

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

    /**
     * 库位信息
     */
    private String locationInfo;

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

    @Override
    public String checkParam() {
        return null;
    }

}
