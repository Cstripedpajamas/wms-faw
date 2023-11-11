package cn.stylefeng.guns.modular.statistics.toolinfo.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 工具信息汇总表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsToolInfoParam implements Serializable, BaseValidatingParam {

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
     * 总数
     */
    private String total;

    /**
     * 现存数量
     */
    private String existNumber;

    /**
     * 维修数量
     */
    private String repairNumber;

    /**
     * 报废数量
     */
    private String scrapNumber;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 规格型号
     */
    private String sizes;

    /**
     * 物料状态为新的
     */
    private String materialStateNew;

    /**
     * 物料状态为使用中的
     */
    private String materialStateUsing;

    /**
     * 物料状态为维修的
     */
    private String materialStateFix;

    /**
     * 物料状态为报废的
     */
    private String materialStateDis;

    /**
     * 存放状态为初始的
     */
    private String storageStateInit;

    /**
     * 存放状态为库内的
     */
    private String storageStateIn;

    /**
     * 存放状态为库外的
     */
    private String storageStateOut;

    /**
     * 存放地址为立库的
     */
    private String storageAddressAsRs;

    /**
     * 存放地址为I类柜的
     */
    private String storageAddressI;

    @Override
    public String checkParam() {
        return null;
    }

}
