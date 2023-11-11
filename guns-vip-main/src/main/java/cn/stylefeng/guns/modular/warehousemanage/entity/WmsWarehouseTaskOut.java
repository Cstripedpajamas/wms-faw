package cn.stylefeng.guns.modular.warehousemanage.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

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
@ApiModel("领用工具模型")
@TableName("wms_warehouse_task_out")
public class WmsWarehouseTaskOut implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息识别ID
     */
    @ApiModelProperty(value = "消息识别ID")
    @TableField("message_id")
    private String messageId;

    /**
     * 订单类别(A工具领用 B补货出库 C出库)
     */
    @ApiModelProperty(value = "订单类别(A工具领用 B补货出库 C出库)")
    @TableField("order_type")
    private String orderType;

    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @TableField("task_mg")
    private String taskMg;

    /**
     * 出仓货物类型（A工具/B备品备件/C空周转箱）
     */
    @ApiModelProperty(value = "出仓货物类型（A工具/B备品备件/C空周转箱）")
    @TableField("goods_type")
    private String goodsType;

    /**
     * 物料类型ID
     */
    @ApiModelProperty(value = "物料类型ID")
    @TableField("material_type_id")
    private String materialTypeId;

    /**
     * 物料SKU
     */
    @ApiModelProperty(value = "物料号")
    @TableField("material_sku")
    private String materialSku;

    /**
     * 物料类型
     */
    @ApiModelProperty(value = "物料类型")
    @TableField("material_type")
    private String materialType;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "描述")
    @TableField("material_name")
    private String materialName;

    /**
     * 批次
     */
    @ApiModelProperty(value = "批次")
    @TableField("m_batch")
    private String mBatch;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @TableField("m_number")
    private String mNumber;

    /**
     * 出仓分拣（A人工/B自动）
     */
    @ApiModelProperty(value = "出仓分拣（A人工/B自动）")
    @TableField("sorting_info")
    private String sortingInfo;

    /**
     * 周转箱类型(A单格口/B双格口)
     */
    @ApiModelProperty(value = "周转箱类型(A单格口/B双格口)")
    @TableField("turnover_type")
    private String turnoverType;

    /**
     * 周转箱编号
     */
    @ApiModelProperty(value = "周转箱编号")
    @TableField("turnover_number")
    private String turnoverNumber;

    /**
     * 周转箱条码
     */
    @ApiModelProperty(value = "周转箱条码")
    @TableField("barcode")
    private String barcode;




    /**
     * 周转箱格口数量
     */
    @ApiModelProperty(value = "周转箱格口数量")
    @TableField("turnover_mouth_quality")
     private String turnoverMouthQuality;

    /**
     * 请求标记（0初始 1请求）
     */
    @ApiModelProperty(value = "请求标记（0初始 1请求）")
    @TableField("req_tag")
    private String reqTag;

    /**
     * 请求状态（0初始 1成功 2失败）
     */
    @ApiModelProperty(value = "请求状态（0初始 1成功 2失败）")
    @TableField("req_status")
    private String reqStatus;

    /**
     * 结果标记（0初始 1更新 2结束）
     */
    @ApiModelProperty(value = "结果标记（0初始 1更新 2结束）")
    @TableField("res_tag")
    private String resTag;

    /**
     * 结果状态（0初始 1正在执行 2任务完成 3任务失败）
     */
    @ApiModelProperty(value = "结果状态（0初始 1正在执行 2任务完成 3任务失败）")
    @TableField("res_status")
    private String resStatus;

    /**
     * 出仓库位编号
     */
    @ApiModelProperty(value = "出仓库位编号")
    @TableField("loca_number")
    private String locaNumber;

    /**
     * 任务反馈
     */
    @TableField("task_feedback")
    private String taskFeedback;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 请求时间
     */
    @TableField("req_time")
    private Date reqTime;

    /**
     * 数据时间
     */
    @TableField("data_time")
    private Date dataTime;

    // 备注
    @TableField("mark")
    private String mark;

    // 备用字段
    @TableField("spare_field")
    private String spareField;


    @ApiIgnore(value = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTaskMg() {
        return taskMg;
    }

    public void setTaskMg(String taskMg) {
        this.taskMg = taskMg;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialSku() {
        return materialSku;
    }

    public void setMaterialSku(String materialSku) {
        this.materialSku = materialSku;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getmBatch() {
        return mBatch;
    }

    public void setmBatch(String mBatch) {
        this.mBatch = mBatch;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getSortingInfo() {
        return sortingInfo;
    }

    public void setSortingInfo(String sortingInfo) {
        this.sortingInfo = sortingInfo;
    }

    public String getTurnoverType() {
        return turnoverType;
    }

    public void setTurnoverType(String turnoverType) {
        this.turnoverType = turnoverType;
    }

    public String getTurnoverNumber() {
        return turnoverNumber;
    }

    public void setTurnoverNumber(String turnoverNumber) {
        this.turnoverNumber = turnoverNumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getReqTag() {
        return reqTag;
    }

    public void setReqTag(String reqTag) {
        this.reqTag = reqTag;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getResTag() {
        return resTag;
    }

    public void setResTag(String resTag) {
        this.resTag = resTag;
    }

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    public String getLocaNumber() {
        return locaNumber;
    }

    public void setLocaNumber(String locaNumber) {
        this.locaNumber = locaNumber;
    }

    @ApiIgnore(value = "任务反馈")
    public String getTaskFeedback() {
        return taskFeedback;
    }

    public void setTaskFeedback(String taskFeedback) {
        this.taskFeedback = taskFeedback;
    }

    @ApiIgnore(value = "创建时间")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ApiIgnore(value = "请求时间")
    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    @ApiIgnore(value = "数据时间")
    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getTurnoverMouthQuality() {
        return turnoverMouthQuality;
    }

    public void setTurnoverMouthQuality(String turnoverMouthQuality) {
        this.turnoverMouthQuality = turnoverMouthQuality;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSpareField() {
        return spareField;
    }

    public void setSpareField(String spareField) {
        this.spareField = spareField;
    }

    @Override
    public String toString() {
        return "WmsWarehouseTaskOut{" +
                "id=" + id +
                ", messageId='" + messageId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", taskMg='" + taskMg + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", materialTypeId='" + materialTypeId + '\'' +
                ", materialSku='" + materialSku + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialName='" + materialName + '\'' +
                ", mBatch='" + mBatch + '\'' +
                ", mNumber='" + mNumber + '\'' +
                ", sortingInfo='" + sortingInfo + '\'' +
                ", turnoverType='" + turnoverType + '\'' +
                ", turnoverNumber='" + turnoverNumber + '\'' +
                ", barcode='" + barcode + '\'' +
                ", turnoverMouthQuality='" + turnoverMouthQuality + '\'' +
                ", reqTag='" + reqTag + '\'' +
                ", reqStatus='" + reqStatus + '\'' +
                ", resTag='" + resTag + '\'' +
                ", resStatus='" + resStatus + '\'' +
                ", locaNumber='" + locaNumber + '\'' +
                ", taskFeedback='" + taskFeedback + '\'' +
                ", createTime=" + createTime +
                ", reqTime=" + reqTime +
                ", dataTime=" + dataTime +
                ", mark=" + mark +
                ", spareField=" + spareField +
                '}';
    }
}
