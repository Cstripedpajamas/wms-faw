package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 周转箱绑定货物信息关联表
 * </p>
 *
 * @author ll
 * @since 2021-11-02
 */
@Data
public class WmsCabinet2TurnoverBindParam implements Serializable, BaseValidatingParam {

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
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 工厂
     */
    private String plant;

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
     * 规格型号
     */
    private String sizes;

    /**
     * 数据时间
     */
    private Date createTime;

    @Override
    public String checkParam() {
        return null;
    }

}
