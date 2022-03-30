package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.onetypeservice.response.ToolClaimModel;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskOut;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/claim")
@Api(description = "立库工具领用流程")
public class WarehouseClaimController extends BaseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;

    @GetMapping("/list")
    @ApiOperation(value = "领用流程：1.1 领用任务列表")
    public LayuiPageInfo claimList(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return warehouseService.claimList(serialNumber);
    }

    @GetMapping("/apply")
    @ApiOperation(value = "领用流程：1.2 领用申请列表")
    public LayuiPageInfo claimApply(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return warehouseService.claimUseApply(serialNumber);
    }

    @GetMapping("/conform")
    @ApiOperation(value = "领用流程：2.确认按钮")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK",response = ToolClaimModel.class)
    })
    public ResponseData claimConform(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                                     @ApiParam(value = "任务编号") @RequestParam String taskNumber){
        return warehouseService.claimConform(serialNumber,taskNumber);
    }

    @GetMapping("/tool")
    @ApiOperation(value = "领用流程：3.查询工具内容")
    public ResponseData queryToolContent(@ApiParam("工具条码") @RequestParam String materialSerialNumber){
        WmsMaterialTool wmsMaterialTool = oneTypeCabinetService.findToolByMaterialSerialNumber(materialSerialNumber);
        if(wmsMaterialTool == null){
            return ResponseData.error("工具维护中无此数据或被禁用");
        }
        return ResponseData.success(wmsMaterialTool);
    }

    @GetMapping("/tool-in-task")
    @ApiOperation(value = "领用流程：4.取货完成创建入库任务")
    public ResponseData toolInTask(@ApiParam("任务编号") @RequestParam String messageId){
        return warehouseService.createInTask(messageId);
    }

}


