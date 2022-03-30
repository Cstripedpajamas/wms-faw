package cn.stylefeng.guns.modular.onetypeservice.response;

import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by li wen ya on 2021/11/24
 */
@Data
@ApiModel(value = "立库周转箱详情信息")
public class WarehouseTurnoverInfo {

    @ApiModelProperty(value = "周转箱信息")
    private WmsWarehouseTurnover wmsWarehouseTurnover;

    @ApiModelProperty(value = "绑定数据信息")
    private List<WmsWarehouseTurnoverBind> binds;

}
