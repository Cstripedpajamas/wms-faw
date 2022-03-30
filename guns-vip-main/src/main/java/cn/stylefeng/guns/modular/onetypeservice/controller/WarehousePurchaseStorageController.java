package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by li wen ya on 2021/11/24
 */
@RestController
@CrossOrigin
@RequestMapping("/purchase")
@Api(description = "采购入库业务")
public class WarehousePurchaseStorageController {

    @Autowired
    private WarehouseService warehouseService;

    @ApiOperation(value = "1.1 待接收列表")
    @GetMapping(value = "/order-one")
    public LayuiPageInfo purchaseOrderOne(){
        return warehouseService.purchaseOrder();
    }

    @ApiOperation(value = "1.2 完成接收列表")
    @GetMapping(value = "/order-two")
    public LayuiPageInfo purchaseOrderTwo(){
        return warehouseService.purchaseOrderConform();
    }

    @ApiOperation(value = "2. 确认按钮")
    @GetMapping(value = "/conform")
    public ResponseData purchaseConform(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                                        @ApiParam(value = "采购单号") @RequestParam String purNumber){
        return warehouseService.purchaseConform(serialNumber,purNumber);
    }

    @ApiOperation(value = "3. 扫描入库")
    @GetMapping(value = "/scan-out")
    public ResponseData purchaseScanOutTask(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                                        @ApiParam(value = "周转箱编号") @RequestParam String turnoverNumber,
                                            @ApiParam(value = "任务编号") @RequestParam String taskNumber){
        return warehouseService.purchaseScanInTask(serialNumber,turnoverNumber,taskNumber);
    }


    @ApiOperation(value = "4.空周转箱出库")
    @GetMapping(value = "/out")
    public ResponseData purchaseOutTask(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        return warehouseService.purchaseOutTask(taskNumber);
    }


}
