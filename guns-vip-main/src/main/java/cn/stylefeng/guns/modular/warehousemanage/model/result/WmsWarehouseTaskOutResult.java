package cn.stylefeng.guns.modular.warehousemanage.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-仓库任务管理信息表-出仓
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseTaskOutResult implements Serializable {

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
     * 订单类别(A工具领用 B补货出库 C出库)
     */
    private String orderType;

    /**
     * 任务信息
     */
    private String taskMg;

    /**
     * 出仓货物类型（A工具/B备品备件/C空周转箱）
     */
    private String goodsType;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 批次
     */
    private String mBatch;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 出仓分拣（A人工/B自动）
     */
    private String sortingInfo;

    /**
     * 周转箱类型(A 小 B 中 C 大)
     */
    private String turnoverType;

    /**
     * 周转箱编号
     */
    private String turnoverNumber;

    /**
     * 周转箱条码
     */
    private String barcode;

    /**
     * 周装箱格口数量
     * */
    private String turnoverMouthQuality;

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
     * 出仓库位编号
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
     * 备注
     * */
    private  String mark;

    /**
     * 备注
     * */
    private  String sizes;
    /**
     * 备用字段
     * */
    private String spareField;

}
