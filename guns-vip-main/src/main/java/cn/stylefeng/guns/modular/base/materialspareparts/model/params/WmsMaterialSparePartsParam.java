package cn.stylefeng.guns.modular.base.materialspareparts.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 备品备件-物料信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsMaterialSparePartsParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

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
     * 批次
     */
    private String mBatch;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 规格（最小包装）
     */
    private String minPackageSize;

    /**
     * 存放状态(0初始 1库内 2库外)
     */
    private String storageState;

    /**
     * 存放地址（1立体仓库 2Ⅱ类柜）
     */
    private String storageAddress;

    /**
     * 数据状态（0使用/1禁用）
     */
    private String dataState;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 数量
     * */
    private String mNumber;

    @Override
    public String checkParam() {
        return null;
    }

}
