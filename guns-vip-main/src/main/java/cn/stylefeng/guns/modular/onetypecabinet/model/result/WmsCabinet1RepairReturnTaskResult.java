package cn.stylefeng.guns.modular.onetypecabinet.model.result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Ⅰ类柜维修归还任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Data
@Api(description = "归还任务")
public class WmsCabinet1RepairReturnTaskResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    private Long id;

    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    private String taskNumber;

    /**
     * 物料类型ID
     */
    @ApiModelProperty(value = "物料类型ID")
    private String materialTypeId;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String materialName;

    /**
     * 物料SKU
     */
    @ApiModelProperty(value = "物料SKU")
    private String materialSku;

    /**
     * 物料信息ID
     */
    @ApiModelProperty(value = "物料信息ID")
    private String materialId;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String materialSerialNumber;

    /**
     * 库存信息ID
     */
    @ApiModelProperty(value = "库存信息ID")
    private String stockId;

    /**
     * 库位编号
     */
    @ApiModelProperty(value = "库位编号")
    private String locaNumber;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String operator;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private Date operationTime;

    /**
     * 任务状态(0初始 1开始 2开始存储 3存储完成 4结束 )
     */
    @ApiModelProperty(value = "任务状态(0初始 1开始 2开始存储 3存储完成 4结束 )")
    private String taskState;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
