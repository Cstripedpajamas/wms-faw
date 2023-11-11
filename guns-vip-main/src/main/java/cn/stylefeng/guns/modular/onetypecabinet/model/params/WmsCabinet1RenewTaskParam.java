package cn.stylefeng.guns.modular.onetypecabinet.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅰ类柜换新任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Data
public class WmsCabinet1RenewTaskParam implements Serializable, BaseValidatingParam {

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
     * 旧物料信息ID
     */
    private String oMaterialId;

    /**
     * 物料编码
     */
    private String oMaterialSerialNumber;

    /**
     * 新-物料信息ID
     */
    private String nMaterialId;

    /**
     * 物料编码
     */
    private String nMaterialSerialNumber;

    /**
     * 存储-库存信息ID
     */
    private String storageStockId;

    /**
     * 库位编号
     */
    private String storageLocaSerialNumber;

    /**
     * 取货-库存信息ID
     */
    private String taskStockId;

    /**
     * 库位编号
     */
    private String taskLocaSerialNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态(0初始 1开始 2开始存储 3存储完成 4开始取货 5取货完成 6结束)
     */
    private String taskState;

    /**
     * 异常标记（0初始 1异常）
     */
    private String errorTrag;

    /**
     * 异常内容
     */
    private String toolErrorContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 自动关闭标识(0:确认关闭 1：自动关闭)
     */
    private String autoFlag;

    /**
     * 规格型号
     */
    private String sizes;
    /**
     * 组合编码
     */
    private String workTeam;

    /**
     * 操作人姓名
     */
    private String userName;


    @Override
    public String checkParam() {
        return null;
    }

}
