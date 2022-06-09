package cn.stylefeng.guns.modular.base.materialType.model.params;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

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

    @Override
    public String checkParam() {
        return null;
    }

}
