package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1StockParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1StockResult;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by li wen ya on 2021/11/9
 */
@CrossOrigin
@RestController
@RequestMapping("/repair")
@Api(description = "Ⅰ类柜维修任务流程")
public class OneTypeCabinetRepairController {

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;

    @GetMapping("/list")
    @ApiOperation(value = "维修流程：1.1 获取维修库存列表")
    public LayuiPageInfo repairList(WmsIntelligentCabinet1StockParam param){
        return oneTypeCabinetService.wmsCabinet1RepairStateList(param);
    }


    @GetMapping("/tool")
    @ApiOperation(value = "维修流程：1.2 获取维修工具列表")
    public LayuiPageInfo repairToolList(){
        return oneTypeCabinetService.repairToolList();
    }


    @GetMapping(value = "/repair-operation")
    @ApiOperation(value = "维修流程：2.点击维修按钮")
    public ResponseData repairOperation(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                                        @ApiParam(value = "库位编号：x001,x002,x003...") @RequestParam String locaSerialNumber){
        return oneTypeCabinetService.createRepairTask(serialNumber,locaSerialNumber);
    }

    @GetMapping("/cabinetClosed")
    @ApiOperation(value = "换新流程：3.关闭格口(下一步)")
    public ResponseData closeCabinet(@ApiParam("任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.repairCloseCabinet(taskNumber);
        return ResponseData.success();
    }

    @GetMapping(value = "/open-door-operation")
    @ApiOperation(value = "维修流程：4.打开格口")
    public ResponseData openDoorOperation(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        return oneTypeCabinetService.openDoorTwo(taskNumber);
    }

    @GetMapping(value = "/mater-type")
    @ApiOperation(value = "维修流程：3.获取物料类型")
    public ResponseData materType(@ApiParam(value = "物料类型ID") @RequestParam String materialTypeId){
        String materTypeName = oneTypeCabinetService.findByMaterTypeId(materialTypeId);
        return ResponseData.success(materTypeName);
    }



}
