package cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 异常信息记录表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsErrorRecordInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 异常类型
     */
    private String errorType;

    /**
     * 异常描述
     */
    private String errorDescribe;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 数据时间
     */
    private Date createTime;





    /**
     * 物料类型
     */
    private String materialType;


    /**
     * 物料名称
     */
    private String materialName;


    /**
     * 物料编号
     */
    private String materialSku;


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
