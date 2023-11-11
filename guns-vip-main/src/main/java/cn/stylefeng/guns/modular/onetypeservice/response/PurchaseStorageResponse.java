package cn.stylefeng.guns.modular.onetypeservice.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by li wen ya on 2021/11/25
 */
@Data
@ApiModel(value = "采购入库任务")
public class PurchaseStorageResponse {

    @ApiModelProperty(value = "任务编号")
    private String taskNumber;
    @ApiModelProperty(value = "物料类型ID")
    private String  materialTypeId;
    @ApiModelProperty(value = "物料类型")
    private String  materialType;
    @ApiModelProperty(value = "描述")
    private String  materialName;
    @ApiModelProperty(value = "物料号")
    private String  materialSku;
    @ApiModelProperty(value = "剩余可接收数量")
    private String number;
}
