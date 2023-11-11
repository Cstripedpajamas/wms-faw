package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅱ类柜投入报废信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2InputScrapParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 领用任务ID
     */
    private String useTask;

    /**
     * 领用任务编号
     */
    private String taskNumber;

    /**
     * 报废物料类型ID
     */
    private String materialTypeId;

    /**
     * 报废数量
     */
    private String mNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 领用任务流程单号
     */
    private String processNumber;


    /**
     * 报废物料类型
     */
    private String materialType;

    /**
     * 报废物料描述
     */
    private String materialName;

    /**
     * 报废物料号
     */
    private String materialSku;
    /**
     * 规格型号
     */
    public String sizes;
    /**
     * 操作人姓名
     */
    public String operators;

    @Override
    public String checkParam() {
        return null;
    }

}
