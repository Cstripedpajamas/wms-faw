package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.result;

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
public class WmsCabinet2InputScrapResult implements Serializable {

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
     * 操作人员工号
     */
    private String operators;
    /**
     * 规格型号
     */
    private String sizes;
}
