package cn.stylefeng.guns.modular.base.materialtool.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 工具-物料信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsMaterialToolResult implements Serializable {

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
     * 物料编码
     */
    private String materialSerialNumber;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 物料状态（0新 1使用中 2维修 3报废）
     */
    private String materialState;

    /**
     * 存放状态(0初始 1库内 2库外)
     */
    private String storageState;

    /**
     * 存放地址（1立体仓库 2Ⅰ类柜）
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
     * 采购单号
     */
    private String purNumber;

}
