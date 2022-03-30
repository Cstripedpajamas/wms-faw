package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by li wen ya on 2021/11/10
 */
@RestController
@CrossOrigin
@RequestMapping("/inventory")
@Api(description = "Ⅰ类柜盘点服务接口")
public class OneTypeCabinetInventoryController {

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;

    @GetMapping("/create-task")
    @ApiOperation(value = "盘点流程：1.创建盘点任务返回盘点任务号")
    public ResponseData createInventoryTask(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return oneTypeCabinetService.createInventoryTask(serialNumber);
    }

    @GetMapping("/inventory-cabinet")
    @ApiOperation(value = "盘点流程：2.1 获取所有格口信息")
    public LayuiPageInfo inventoryCabinet(){
        return oneTypeCabinetService.inventoryCabinet();
    }

    @GetMapping("/inventory-task")
    @ApiOperation(value = "盘点流程：2.2 获取所有格口信息")
    public LayuiPageInfo inventoryTask(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return oneTypeCabinetService.inventoryTask(serialNumber);
    }


    @GetMapping("/homepage")
    @ApiOperation(value = "盘点流程：3.点击主页按钮 - 结束任务")
    public ResponseData homepage(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.updateInventory(taskNumber, StateEnum.FOUR.getState());
        return ResponseData.success();
    }

    @GetMapping("/open-door-all")
    @ApiOperation(value = "盘点流程：4.点击开始盘点 - 打开所有格口")
    public ResponseData openAllCabinet(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.openAllCabinet(taskNumber);
        return ResponseData.success();
    }

    @GetMapping("/clear-cabinet")
    @ApiOperation(value = "盘点流程：5.指定格口内容清空")
    public ResponseData clearCabinet(@ApiParam(value = "库位编号") @RequestParam String locaSerialNumber){
        oneTypeCabinetService.updateInventoryCabinetEmpty(locaSerialNumber);
        return ResponseData.success();
    }

    @GetMapping("/query-tool-type")
    @ApiOperation(value = "盘点流程：6.1 修改按钮获取所有类型信息")
    public ResponseData queryToolType(){
        return oneTypeCabinetService.queryToolType();
    }

    @GetMapping("/query-tool")
    @ApiOperation(value = "盘点流程：6.2 查询符合类型的工具")
    public ResponseData queryTool(@ApiParam(value = "物料类型ID") @RequestParam Long id){
        return oneTypeCabinetService.queryToolAll(id);
    }

    @GetMapping("/modify-cabinet")
    @ApiOperation(value = "盘点流程：6.3 修改指定格口内容")
    public ResponseData modifyCabinet(@ApiParam(value = "格口编号") @RequestParam String locaSerialNumber,
                                      @ApiParam(value = "物料信息ID") @RequestParam Long id){
        oneTypeCabinetService.updateCabinetContent(locaSerialNumber,id);
        return ResponseData.success();
    }


    @GetMapping("/conform")
    @ApiOperation(value = "盘点流程：7.盘点完成")
    public ResponseData conform(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.closeAllCabinet(taskNumber);
        return ResponseData.success();
    }

}
