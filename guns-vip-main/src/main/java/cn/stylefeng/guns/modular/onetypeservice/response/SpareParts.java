package cn.stylefeng.guns.modular.onetypeservice.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by li wen ya on 2021/11/18
 */
@Data
@ApiModel(value = "备品备件盘点模型")
public class SpareParts {

    @ApiModelProperty(value = "任务编号")
    private String taskNumber;

    @ApiModelProperty(value = "周转箱编号")
    private String turnoverNumber;

    @ApiModelProperty(value = "周转箱状态")
    private String turnoverState;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "物料SKU")
    private String materialSku;

    @ApiModelProperty(value = "批次")
    private String mBatch;

    @ApiModelProperty(value = "数量")
    private String mNumber;

    @ApiModelProperty(value = "周转箱ID")
    private Long turnoverId;

}
