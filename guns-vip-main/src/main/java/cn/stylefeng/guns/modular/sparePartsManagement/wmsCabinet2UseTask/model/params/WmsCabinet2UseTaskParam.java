package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅱ类柜领用任务信息表
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Data
public class WmsCabinet2UseTaskParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 任务编号
     */
    private String taskNumber;

    /**
     * 领用申请ID
     */
    private String useRequestId;

    /**
     * 流程单号
     */
    private String processNumber;

    /**
     * 流程单号
     */
    private String sMaterialType;

    /**
     * 流程单号
     */
    private String smaterialName;

    /**
     * 流程单号
     */
    private String smaterialSku;

    /**
     * 流程单号
     */
    private String useMaterialType;

    /**
     * 流程单号
     */
    private String useMaterialSku;

    /**
     * 流程单号
     */
    private String useMaterialName;

    /**
     * 报废-物料类型ID
     */
    private String sMaterialTypeId;

    /**
     * 物料信息ID
     */
    private String sMaterialId;

    /**
     * 报废数量
     */
    private String sNumber;

    /**
     * 领用-物料类型ID
     */
    private String useMaterialTypeId;

    /**
     * 物料信息ID
     */
    private String useMaterialId;

    /**
     * 领用数量
     */
    private String useNumber;

    /**
     * 库存信息ID
     */
    private String stockId;

    /**
     * 库位编号
     */
    private String locaNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态(0初始 1开始投入 2投入完成 3开始出库 4出库完成 5取货完成 6结束)
     */
    private String taskState;

    /**
     * 接口状态（0初始/1调用）
     */
    private String interfaceState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 物料 SKU
     * */
    private String materialSku;

    /**
     * 物料名称
     * */
    private String materialName;

    /**
     * 报废规格型号
     */
    private String materialSize;
    /**
     * 领用规格型号
     */
    private String useMaterialSize;
    /**
     * 操作人姓名
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

    @Override
    public String checkParam() {
        return null;
    }

}
