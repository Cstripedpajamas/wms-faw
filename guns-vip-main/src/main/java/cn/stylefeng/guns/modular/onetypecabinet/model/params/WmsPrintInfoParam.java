package cn.stylefeng.guns.modular.onetypecabinet.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 条码打印信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-24
 */
@Data
public class WmsPrintInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 工具编码
     */
    private String materialSerialNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 打印类型（A补打 B采购）
     */
    private String printType;

    /**
     * 打印状态（0生成 1打印 2完成 3失败 4结束）
     */
    private String printStu;

    /**
     * 生成时间
     */
    private Date createTime;

    /**
     * 数据时间
     */
    private Date dataTime;

    @Override
    public String checkParam() {
        return null;
    }

}
