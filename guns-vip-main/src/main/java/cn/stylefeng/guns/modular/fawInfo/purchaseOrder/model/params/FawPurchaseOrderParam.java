package cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * faw采购订单信息表
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
@Data
public class FawPurchaseOrderParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 规格
     */
    private String sizecoL;

    /**
     * 应用环境
     */
    private String client;

    /**
     * 采购凭证行项
     */
    private String purDocitemNo;

    /**
     * 采购凭证号
     */
    private String purDocNo;

    /**
     * 采购申请号
     */
    private String purchaseReqNo;

    /**
     * 采购申请行项号
     */
    private String itemNo;

    /**
     * PURStockBillID
     */
    private String purStockBillId;

    /**
     * 采购凭证类型
     */
    private String buyListStrDes;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建日期
     */
    private Date createdDate;

    /**
     * 凭证日期
     */
    private Date docDate;

    /**
     * 估价标识
     */
    private String estimatedPriceIndic;

    /**
     * 交货日程日期
     */
    private Date itemDeliveryDate;

    /**
     * 材料牌号
     */
    private String matBrand;

    /**
     * 物料号
     */
    private String mtlNo;

    /**
     * 订单的净价总值
     */
    private String netValue;

    /**
     * 采购价格计量单位
     */
    private String ordPriceUnit;

    /**
     * 采购凭证类型
     */
    private String ordType;

    /**
     * 工厂
     */
    private String plant;

    /**
     * 价格
     */
    private String price;

    /**
     * 生产厂家/品牌
     */
    private String promotion;

    /**
     * 申请人描述
     */
    private String proposerDesc;

    /**
     * 申请人ID
     */
    private String proposerId;

    /**
     * 采购组
     */
    private String purGrp;

    /**
     * 采购组描述
     */
    private String purGrpDesc;

    /**
     * 采购组织
     */
    private String purOrg;

    /**
     * 订单头ID
     */
    private String purdocheaderId;

    /**
     * 订单行项ID
     */
    private String purdocitemId;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 存储地点
     */
    private String storeLocation;

    /**
     * 供应商描述
     */
    private String vendorDesc;

    /**
     * 供应商代码
     */
    private String vendorNo;

    /**
     * 创建人电话
     */
    private String phone;

    /**
     * 采购申请创建人电话
     */
    private String reqPhone;

    /**
     * 工厂描述
     */
    private String plantDes;

    /**
     * 存储地点描述
     */
    private String storeLocationDes;

    /**
     * 计量单位描述
     */
    private String unitDes;

    /**
     * 创建人描述
     */
    private String createdByDesc;

    /**
     * 状态描述
     */
    private String stateDesc;

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

    @Override
    public String checkParam() {
        return null;
    }

}
