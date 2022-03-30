package cn.stylefeng.guns.modular.onetypeservice.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by li wen ya on 2021/11/23
 */
@Data
@ApiModel(value = "工具领用模型")
public class ToolClaimModel {

    @ApiModelProperty(value = "任务状态（0初始 1开始 2出库中 3完成 ）")
    private String taskState;
    @ApiModelProperty(value = "分拣状态(0:为分拣，1:已分拣)")
    private String sortingStatus;
    @ApiModelProperty(value = "出库任务编号")
    private String messageId;
    @ApiModelProperty(value = "分拣方式（A人工/B自动）")
    private String sortingInfo;
    @ApiModelProperty(value = "出库周转箱编号")
    private String turnoverNumber;
    @ApiModelProperty(value = "周转箱A格口")
    private String aLatticeCode;
    @ApiModelProperty(value = "周转箱B格口")
    private String bLatticeCode;
    @ApiModelProperty(value = "领用工具编码")
    private String materialSerialNumber;
    @ApiModelProperty(value = "推送字段标识：1：taskState,turnoverNumber,aLatticeCode,bLatticeCode,sortingInfo 2:materialSerialNumber,sortingStatus")
    private String code;
    @ApiModelProperty(value = "分拣数量(推送分拣用)")
    private String number;
    @ApiModelProperty(value = "执行状态（1出库中 2出库完成 3入库中 4入库完成 ）")
    private String state;
}
