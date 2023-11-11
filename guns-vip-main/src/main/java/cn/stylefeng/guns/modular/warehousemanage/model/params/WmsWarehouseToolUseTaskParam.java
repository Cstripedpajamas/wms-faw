package cn.stylefeng.guns.modular.warehousemanage.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-工具领用任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseToolUseTaskParam implements Serializable, BaseValidatingParam {

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
     * 库存信息ID
     */
    private String stockId;

    /**
     * 库位编号
     */
    private String locaNumber;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

    /**
     * 格口编号
     */
    private String latticeCode;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态（0初始 1开始 2出库中 3完成 ）
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
     * 分拣类型（0人工 1自动)
     */
    private String sortingType;

    /**
     * 分拣状态（0未分拣 1已分拣）
     */
    private String sortingStatus;

    /**
     * 分拣任务 (机械手分拣ID)
     */
    private String sortingTask;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 规格型号
     */
    private String materialSizes;

    /**
     * 操作人名称
     */
    private String userName;

    /**
     * 所属组织
     */
    private String workTeam;

    /**
     * 周转箱编号
     */
    private String barCode;


    @Override
    public String checkParam() {
        return null;
    }

}
