package cn.stylefeng.guns.modular.statistics.tooluse.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 工具领用信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsToolUseParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 人员信息
     */
    private String operator;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 物料编码
     */
    private String materialSerialNumber;

    /**
     * 数据时间
     */
    private Date dataTime;
    /**
     * 姓名
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
     * 物料类型
     */
    private String materialType;


    /**
     * 包装单位
     */
    private String mUnit;


    /**
     * 规格型号
     */
    private String Sizes;

    /**
     * 库存数量
     */
    private String stockCount;
    @Override
    public String checkParam() {
        return null;
    }

}
