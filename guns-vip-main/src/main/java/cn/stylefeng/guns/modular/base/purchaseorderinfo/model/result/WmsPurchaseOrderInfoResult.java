package cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 采购订单信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsPurchaseOrderInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 采购单号
     */
    private String purNumber;

    /**
     * 类型（1工具 2备品备件）
     */
    private String type;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

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
     * 数量
     */
    private String mNumber;

    /**
     * 到货时间
     */
    private Date arrivalTime;

    /**
     * 到货状态（0初始 1入库 2接收 3退还）
     */
    private String arrivalState;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 接收数量
     */
    private String receivedQuantity;

    /**
     * 打印数量
     */
    private String printNum;

    /**
     * 可接收数量
     */
    private String acceptableQuantity;


    /**
     * 采购订单任务编号
     * */
    private String taskNumber;

    /**
     * 批次号
     */
    private String diBatchNo;

//    /**
//     * 批次号
//     */
//    private String di_batchno;

    /**
     * 采购凭证行项
     */
    private String purdocitemno;

    /**
     * 采购凭证号
     */
    private String purdocno;

    /**
     * 采购申请号
     */
    private String purchasereqno;

    /**
     * 采购申请行项号
     */
    private String itemno;

    /**
     *
     */
    private String purstockbillid;

    /**
     *采购凭证类型描述
     */
    private String buyliststrdes;

    /**
     *创建人
     */
    private String createdby;

    /**
     *创建日期
     */
    private Date createddate;

    /**
     *凭证日期
     */
    private Date docDate;

    /**
     *估价标识
     */
    private String estimatedpriceindic;

    /**
     *材料牌号
     */
    private String matBrand;

    /**
     *订单的净价总值
     */
    private String netvalue;

    /**
     *采购价格计量单位
     */
    private String ordpriceunit;

    /**
     *采购凭证类型
     */
    private String ordtype;

    /**
     *工厂
     */
    private String plant;

    /**
     *价格
     */
    private String price;

    /**
     *生产厂家/品牌
     */
    private String promotion;

    /**
     *申请人描述
     */
    private String proposerdesc;

    /**
     *申请人ID
     */
    private String proposerid;

    /**
     *采购组
     */
    private String purgrp;

    /**
     *采购组描述
     */
    private String purgrpdesc;

    /**
     *采购组织
     */
    private String purOrg;

    /**
     *订单头ID
     */
    private String purdocheaderid;

    /**
     *备注
     */
    private String remark;

    /**
     *备注1
     */
    private String remark1;

    /**
     *存储地点
     */
    private String storelocation;

    /**
     *供应商描述
     */
    private String vendordesc;

    /**
     *供应商代码
     */
    private String vendorno;

    /**
     *创建人电话
     */
    private String phone;

    /**
     *采购申请创建人电话
     */
    private String reqphone;

    /**
     *工厂描述
     */
    private String plantdes;

    /**
     *存储地点描述
     */
    private String storelocationdes;

    /**
     *规格
     */
    private String sizecol;

    /**
     *计量单位描述
     */
    private String unitdes;

    /**
     *创建人描述
     */
    private String createdbydesc;

    /**
     *状态描述
     */
    private String statedesc;

    /**
     *操作类型
     */
    private String diOperType;

    /**
     *更新时间
     */
    private Date diUpdatetime;

    /**
     *应用环境
     */
    private String client;

}
