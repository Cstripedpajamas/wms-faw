package cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * faw物料信息
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
@Data
public class FawMtlInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 操作类型
     */
    private String diOpertype;

    /**
     * 批次号
     */
    private String diBatch;

    /**
     * 更新时间
     */
    private Date diUpdatetime;

    /**
     * 工厂
     */
    private String plant;

    /**
     * 物料号
     */
    private String mtlNo;

    /**
     * 描述
     */
    private String mtlNodes;

    /**
     * 物料类型
     */
    private String mtlType;

    /**
     * 停用标识
     */
    private String delflagforclientmtl;

    /**
     * 基本计量单位
     */
    private String measurebaseunit;

    /**
     * 制造路线
     */
    private String industrystnddes;

    /**
     * 可配置物料标识
     */
    private String pagefromat;

    /**
     * 可配置物料标识
     */
    private String isconfflag;

    /**
     * 创建对象的用户代码
     */
    private String datauser;

    /**
     * 采购组代码
     */
    private String purgrp;

    /**
     * 获取类型
     */
    private String procuretype;

    /**
     * 特殊获取类型
     */
    private String specprocuretype;

    /**
     * MRP控制者
     */
    private String mrpcontroller;

    /**
     * 估价类别
     */
    private String valctg;

    /**
     * 可配置物料
     */
    private String croplant;

    /**
     * 规格型号
     */
    private String sizes;

    /**
     * C标识
     */
    private String spmtlstatus;

    @Override
    public String checkParam() {
        return null;
    }

}
