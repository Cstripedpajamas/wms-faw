package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.onetypeservice.response.WarehouseTurnoverInfo;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by li wen ya on 2021/11/24
 */
@RestController
@CrossOrigin
@RequestMapping("/out-in")
@Api(description = "出库入库业务操作")
public class WarehouseOutInController {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;
    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @ApiOperation(value = "手动出库 - 1.查询物料类型")
    @GetMapping(value = "/out-type")
    public ResponseData findMaterialTypeAll(){
        return warehouseService.findMaterialTypeAll();
    }

    @ApiOperation(value = "手动出库 - 2.查询周转箱信息")
    @GetMapping(value = "/out-type-list")
    public LayuiPageInfo findList(String materialTypeId){
        WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);
        WmsWarehouseTurnoverBindParam wm = new WmsWarehouseTurnoverBindParam();
        if (Objects.equals("001",byId.getMaterialSku())){
           return  wmsWarehouseTurnoverBindService.findTurnoverMsg(wm);
        }
        return warehouseService.findWarehouseList(materialTypeId);
    }

    @ApiOperation(value = "手动出库 - 3.出库接口")
    @GetMapping(value = "/out-warehouse")
    public ResponseData outWarehouse(@ApiParam(value = "物料类型") @RequestParam String materialTypeId){
        WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);
        if (byId.getMaterialSku().startsWith("box_")){
            return  warehouseService.outWarehouse_empty(byId); // 空周转箱出库
        }
        return warehouseService.outWarehouse(materialTypeId);
    }

    @ApiOperation(value = "手动入库 - 1.获取周转型信息")
    @GetMapping(value = "/in-info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = WarehouseTurnoverInfo.class),
            @ApiResponse(code = 200, message = "OK", response = WmsWarehouseTurnover.class),
            @ApiResponse(code = 200, message = "OK", response = WmsWarehouseTurnoverBind.class)
    })
    public ResponseData warehouseTurnoverInfo(@ApiParam(value = "周转箱编号") @RequestParam String turnoverNumber){
        return warehouseService.warehouseTurnoverInfo(turnoverNumber);
    }

    @ApiOperation(value = "手动入库 - 2.入库接口")
    @GetMapping(value = "/in-warehouse")
    public ResponseData inWarehouse(@ApiParam(value = "周转箱编号") @RequestParam String turnoverNumber){
        return warehouseService.inWarehouse(turnoverNumber);
    }

    @ApiOperation(value = "手动出库 - 3.WCS请求入库成功")
    @GetMapping(value = "/in-complement")
    public ResponseData inComplement(@ApiParam(value = "库位信息") @RequestParam String locaNumber,
                                     @ApiParam(value = "流程单号") @RequestParam String messageId){
        warehouseService.inComplement(locaNumber,messageId);
        return ResponseData.success();
    }


}
