package cn.stylefeng.guns.modular.base.materialType.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 物料类型信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsMaterialTypeParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 类型（1工具 2备品备件）
     */
    private String type;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 周转箱类型(A 小 B 中 C 大)
     */
    private String latticeMouthType;

    /**
     * 数据状态（0使用/1禁用）
     */
    private String dataState;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 分拣类型
     * */
    private String sortType;

    /**
     * 包装类型
     * */
    private String packageType;

    /**
     * 包装数量
     * */
    private String packageNumber;

    /**
     * 周转箱类型
     * */
    private String turnoverType;

    /**
     * 周转箱格口数量
     * */
    private String turnoverLatticeType;

    /**
     *  RFID 标识
     * */
    private String label;

    private String source;


    /**
     *  RFID条码
     * */
    private  String materialSerialNumber;


    /**
     *  工厂
     * */
    private  String plant;
    /**
     * 操作类型
     */
    private String diOperType;

    /**
     * 工厂
     */
//    private String plant;

    /**
     * 批次号
     */
    private String diBatchNo;

    /**
     * 更新时间
     */
    private Date diUpdatetime;

    /**
     * 停用标识
     */
    private String delflagforclientmtl;

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

    /**
     * 图片地址
     */
    private String imagePath;

    /**
     * 库存总数量
     */
    private String totalMNumber;

    /**
     * 领用的数量
     */
    private String useTotalMNumber;

    @Override
    public String checkParam() {
        return null;
    }

}
