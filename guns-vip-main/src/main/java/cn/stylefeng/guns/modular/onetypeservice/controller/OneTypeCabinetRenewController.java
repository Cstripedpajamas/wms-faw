package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1Stock;
import cn.stylefeng.guns.modular.onetypeservice.request.OpenEmptyDoorParam;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.sys.modular.system.entity.Dict;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by li wen ya on 2021/11/4
 */
@CrossOrigin
@RestController
@RequestMapping("/one-cabinet")
@Api(description = "Ⅰ类柜换新服务接口")
public class OneTypeCabinetRenewController {

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;


    @GetMapping("/renew-tool-error")
    @ApiOperation(value = "换新流程：1.1 工具维修获取维修下拉选内容")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK",response = Dict.class)
    })
    public ResponseData toolErrorContent(){
        return oneTypeCabinetService.toolErrorContent();
    }

    @GetMapping("/renew")
    @ApiOperation(value = "换新流程：1.2 生成换新任务")
    public ResponseData createRenewTask(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        String taskNumber = oneTypeCabinetService.createRenewTask(serialNumber);
        return ResponseData.success(taskNumber);
    }

    @GetMapping("/tool")
    @ApiOperation(value = "换新流程：2.查询工具内容")
    public ResponseData queryToolContent(@ApiParam("工具条码") @RequestParam String materialSerialNumber){
        WmsMaterialTool wmsMaterialTool = oneTypeCabinetService.findToolByMaterialSerialNumber(materialSerialNumber);
        if(wmsMaterialTool == null){
            return ResponseData.error("工具维护中无此数据或被禁用");
        }
        return ResponseData.success(wmsMaterialTool);
    }

    @GetMapping("/renew-cancel")
    @ApiOperation(value = "换新流程：3.取消")
    public ResponseData renewCancel(@ApiParam("任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.updateRenewEnd(taskNumber);
        return ResponseData.success();
    }

    @GetMapping("/open-empty-door")
    @ApiOperation(value = "换新流程：4.打开空库门")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK",response = WmsIntelligentCabinet1Stock.class),
    })
    public ResponseData openEmptyDoor(OpenEmptyDoorParam doorParam){
        return oneTypeCabinetService.openEmptyDoor(doorParam);
    }

    @GetMapping("/open-door")
    @ApiOperation(value = "换新流程：5.打开指定库门")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK",response = Declension.class)
    })
    public ResponseData openDoor(@ApiParam("库位编号") @RequestParam String locaSerialNumber){
        Declension declension = oneTypeCabinetService.openDoor(locaSerialNumber);
        return ResponseData.success(declension);
    }

    @GetMapping("/CabinetClosed")
    @ApiOperation(value = "换新流程：6.2关闭格口(下一步)")
    public ResponseData closeCabinet(@ApiParam("库位编号") @RequestParam String locaSerialNumber){
        String message = oneTypeCabinetService.closeCabinet(locaSerialNumber);
        return ResponseData.success(message);
    }

    @GetMapping("/renew-close")
    @ApiOperation(value = "换新流程：6.1关闭格口(下一步)")
    public ResponseData renewClose(@ApiParam("库位编号") @RequestParam String locaSerialNumber){
        return oneTypeCabinetService.renewClose(locaSerialNumber);
    }

    @GetMapping("/error")
    @ApiOperation(value = "换新流程：7.异常工具")
    public ResponseData errorCabinet(@ApiParam("任务编号") @RequestParam String taskNumber){
        return oneTypeCabinetService.errorDeal(taskNumber);
    }

    @GetMapping("/confirm")
    @ApiOperation(value = "换新流程：8.确认工具")
    public ResponseData confirmFinishTask(@ApiParam("任务编号") @RequestParam String taskNumber,
                                          @ApiParam("自动关闭标识(0:确认关闭 1：自动关闭)") @RequestParam String autoFlag){
        oneTypeCabinetService.confirmFinishTask(taskNumber,autoFlag);
        return ResponseData.success();
    }

}
