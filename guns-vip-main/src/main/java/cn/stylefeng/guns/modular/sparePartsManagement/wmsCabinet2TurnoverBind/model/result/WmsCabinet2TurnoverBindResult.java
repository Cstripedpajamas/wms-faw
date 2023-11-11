package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 周转箱绑定货物信息关联表
 * </p>
 *
 * @author ll
 * @since 2021-11-02
 */
@Data
public class WmsCabinet2TurnoverBindResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

    /**
     * 周转箱信息ID
     */
    private String barcode;

    /**
     * 周转箱编号
     * */
    private String turnoverNumber;

    /**
     * 物料编码
     * */
    private  String materialSerialNumber;
    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    private String materialId;

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
     * 工厂
     */
    private String plant;

    /**
     * 批次
     */
    private String mBatch;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 数据时间
     */
    private Date createTime;
    /**
     * 规格型号
     */
    private String sizes;
}
