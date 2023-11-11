package cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 备品备件报废信息汇总表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsSparePartsScrapResult implements Serializable {

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
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 报废总数
     */
    private String scrapNumber;

    /**
     * 数据时间
     */
    private Date createTime;



    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 规格型号
     */
    private String sizes;

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
    private String operationTime;

}
