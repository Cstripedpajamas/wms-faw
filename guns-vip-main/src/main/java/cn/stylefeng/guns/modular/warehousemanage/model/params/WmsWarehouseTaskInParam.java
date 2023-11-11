package cn.stylefeng.guns.modular.warehousemanage.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-仓库任务管理信息表-入仓
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseTaskInParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 消息识别ID
     */
    private String messageId;

    /**
     * 订单类别(A采购入库 B入库)
     */
    private String orderType;

    /**
     * 任务信息
     */
    private String taskMg;

    /**
     * 入仓货物类型（A工具/B备品备件/C空周转箱）
     */
    private String goodsType;

    /**
     * 周转箱类型(A单格口/B双格口)
     */
    private String turnoverType;

    /**
     * 周转箱编号
     */
    private String turnoverNumber;

    /**
     * 周转箱条码
     */
    private String tBarcode;

    /**
     * 周转箱格口数量
     * */
    private String turnoverMouthQuality;

    /**
     * 备注
     * */
    private String mark;

    /**
     * 备用字段
     * */
    private String spareField;

    /**
     * 请求标记（0初始 1请求）
     */
    private String reqTag;

    /**
     * 请求状态（0初始 1成功 2失败）
     */
    private String reqStatus;

    /**
     * 结果标记（0初始 1更新 2结束）
     */
    private String resTag;

    /**
     * 结果状态（0初始 1正在执行 2任务完成 3任务失败）
     */
    private String resStatus;

    /**
     * 入仓仓位编号
     */
    private String locaNumber;

    /**
     * 任务反馈
     */
    private String taskFeedback;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 请求时间
     */
    private Date reqTime;

    /**
     * 数据时间
     */
    private Date dataTime;

    /**
     * 入仓位置
     * */
    private String sortingInfo;

    /**
     * 物料号
     * */
    private String materialSku;

    /**
     * 物料类型
     * */
    private String materialType;

    /**
     * 物料名
     * */
    private String materialName;

    /**
     * 规格型号
     * */
    private String materialSizes;

    /**
     * 数量
     * */
    private String materialMNumber;

    @Override
    public String checkParam() {
        return null;
    }

}
