package cn.stylefeng.guns.modular.onetypeservice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by li wen ya on 2021/11/18
 */
@Data
@ApiModel("立库单格口修改")
public class WarehouseTurnoverModify {

    @ApiModelProperty(value = "周转箱ID")
    private Long id;

    @ApiModelProperty(value = "备品备件ID")
    private Long sparePartsId;

    @ApiModelProperty(value = "格口编号")
    private String latticeCode;

    @ApiModelProperty(value = "数量")
    private String number;

    @ApiModelProperty(value = "物料编码")
    private String materialSerialNumber;

    @ApiModelProperty(value = "任务编号")
    private String taskNumber;

    @ApiModelProperty(value = "任务类型：特殊字段1：工具任务；2：备品备件任务")
    private String taskType;

}
