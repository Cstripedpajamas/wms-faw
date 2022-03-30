package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅱ类柜投入报废信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2InputScrapParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 领用任务ID
     */
    private String useTask;

    /**
     * 报废物料类型ID
     */
    private String materialTypeId;

    /**
     * 报废数量
     */
    private String mNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    @Override
    public String checkParam() {
        return null;
    }

}
