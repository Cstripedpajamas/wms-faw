package cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 归还申请信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsReturnApplyParam implements Serializable, BaseValidatingParam {

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
     * 物料类型
     */
    private String materialType;
    /**
     * 差异类型
     */
    private String differenceType;

    /**
     * 描述
     */
    private String materialName;

    /**
     * 物料号
     */
    private String materialSku;

    /**
     * 工厂
     */
    private String plant;

    /**
     * 批次号
     */
    private String diBatchNo;

    /**
     * 物料数量
     */
    private String mNumber;

    /**
     * 归还原因
     */
    private String returnReason;

    /**
     * 数据时间
     */
    private Date dataTime;

    /**
     * 数据状态(0初始 1审核中 2通过 3结束)
     */
    private String dataState;

    /**
     * 审批人
     */
    private String approvedBy;
    /**
     * 人员姓名
     */
    private String userName;
    /**
     * 所属组织编码
     */
    private String workTeam;
    /**
     * 规格型号
     */
    private String sizes;
    /**
     * 使用状态
     */
    private String objectstatus;

    @Override
    public String checkParam() {
        return null;
    }

}
