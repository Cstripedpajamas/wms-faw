package cn.stylefeng.guns.modular.onetypeservice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by li wen ya on 2021/11/22
 */
@Data
@ApiModel(value = "备品备件申请模型")
public class ApplySpareParts {

    @ApiModelProperty(value = "人员编号")
    private String serialNumber;
    @ApiModelProperty(value = "申请物料类型ID")
    private String materialId;
    @ApiModelProperty(value = "申请原因")
    private String reason;
    @ApiModelProperty(value = "申请数量")
    private String number;
    @ApiModelProperty(value = "报废物料类型ID")
    private String materialIdTwo;
    @ApiModelProperty(value = "报废数量")
    private String numberTwo;

}
