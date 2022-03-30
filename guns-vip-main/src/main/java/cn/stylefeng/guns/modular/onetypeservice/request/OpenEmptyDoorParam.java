package cn.stylefeng.guns.modular.onetypeservice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by li wen ya on 2021/11/22
 */
@Data
@ApiModel("换新打开空库门参数")
public class OpenEmptyDoorParam {

    @ApiModelProperty(value = "用户编号")
    private String serialNumber;
    @ApiModelProperty(value = "任务编号")
    private String taskNumber;
    @ApiModelProperty(value = "工具条码")
    private String materialSerialNumber;
    @ApiModelProperty(value = "工具状态")
    private String materialState;
    @ApiModelProperty(value = "工具异常内容")
    private String toolErrorContent;

}
