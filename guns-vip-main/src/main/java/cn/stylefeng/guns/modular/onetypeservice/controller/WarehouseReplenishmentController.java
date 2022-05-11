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
 * Created by li wen ya on 2021/11/23
 *
 */
@Api(description = "备品备件补货出库任务")
@RestController
@CrossOrigin
@RequestMapping(value = "/replenishment")
public class WarehouseReplenishmentController {

    @Autowired
    private WarehouseService warehouseService;

    @ApiOperation(value = "1.获取补货出库任务")
    @GetMapping("/task")
    public LayuiPageInfo replenishmentList(){
        return warehouseService.replenishmentList();
    }

    @ApiOperation(value = "2.获取补货出库任务")
    @GetMapping("/create")
    public ResponseData replenishmentCreateOutTask(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        return warehouseService.replenishmentCreateOutTask(taskNumber);
    }

    @ApiOperation(value = "3.补货入库")
    @GetMapping("/in")
    public ResponseData replenishmentInTask(@ApiParam(value = "出库任务编号") @RequestParam String taskNumber){
        return warehouseService.replenishmentInTask(taskNumber);
    }

    @ApiOperation(value = "4.入库完成")
    @GetMapping("/in-conform")
    public ResponseData replenishmentConformTask(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        return warehouseService.replenishmentConformTask(taskNumber);
    }

    @ApiOperation(value = "5.补货出库")
    @GetMapping("/out")
    public ResponseData replenishmentOutTask(@ApiParam(value = "出库任务编号") @RequestParam String taskNumber){
//        return warehouseService.replenishmentOutTask(taskNumber);
        return warehouseService.replenishmentCreateOutTask(taskNumber);
    }

    @ApiOperation(value = "6.获取任务信息")
    @GetMapping("/info")
    public ResponseData replenishmentTaskInfo(){
        return warehouseService.replenishmentTaskInfo();
    }

}
