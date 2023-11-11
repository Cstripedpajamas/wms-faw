package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
     * 流程单号
     */
    private String useMaterialType;

    /**
     * 流程单号
     */
    private String useMaterialSku;

    /**
     * 流程单号
     */
    private String useMaterialName;

    /**
     * 流程单号
     */
    private String useplant;
    /**
     * 报废工厂
     */
    private String splant;

    /**
     * 流程单号
     */
    private String usediBatchNo;

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

    private String applyCode;

    private String approvedBy;

    /**
     * 申请人姓名
     * */
    private String userName;
    /**
     * 所属组织编码
     * */
    private String workTeam;
    /**
     * 领用物料规格型号
     * */
    private String materialSize;
    /**
     * 报废物料规格型号
     * */
    private String scrapMaterialSize;
    /**
     * 审批人姓名
     * */
    private String approveduserName;
    /**
     * 审批人工号
     * */
    private String approvedserialNumber;
    /**
     * 审批人所属组织编码
     * */
    private String approvedworkTeam;
    /**
     * 记账日期
     * */
    private String postDate;
    /**
     * 项目成本单号
     * */
    private String ordNO;
    /**
     * 成本中心
     * */
    private String costCenter;
    /**
     * 产品编码
     * */
    private String busArea;
    /**
     * 是否结算
     * */
    private Boolean jieSan;

    /**
     * 固定资产编码
     * */
    private String mainAssetNo;


}
