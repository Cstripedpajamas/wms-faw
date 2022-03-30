package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 领用申请信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsUseApplyParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 流程单号
     */
    private String processNumber;

    /**
     * 人员信息
     */
    private String operator;

    /**
     * 物料信息
     */
    private String materialId;

    /**
     * 物料数量
     */
    private String mNumber;

    /**
     * 申请类型（A工具领用/B备品备件领用）
     */
    private String processType;

    /**
     * 申请原因
     */
    private String processReason;

    /**
     * 数据时间
     */
    private Date dataTime;

    /**
     * 数据状态(0初始 1审核中 2通过 3结束)
     */
    private String dataState;

    /**
     * 报废物料类型id
     * */
    private String scrapMaterialId;

    /**
     * 报废物料数量
     * */
    private String scrapNum;

    @Override
    public String checkParam() {
        return null;
    }

}
