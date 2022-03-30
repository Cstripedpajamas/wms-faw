package cn.stylefeng.guns.modular.base.packageInfo.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 包装信息表
 * </p>
 *
 * @author ll
 * @since 2021-12-14
 */
@Data
public class WmsPackinfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Long id;

    /**
     * 包装名称
     */
    private String packgeName;

    /**
     * 包装规格
     */
    private String packgeSpecif;

    /**
     * 备注
     */
    private String mark;

    /**
     * 数据时间
     */
    private Date createTime;

    @Override
    public String checkParam() {
        return null;
    }

}
