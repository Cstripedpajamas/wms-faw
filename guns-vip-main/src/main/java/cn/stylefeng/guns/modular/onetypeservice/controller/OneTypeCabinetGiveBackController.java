package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by li wen ya on 2021/11/9
 */
@RestController
@CrossOrigin
@RequestMapping("/give-back")
@Api(description = "Ⅰ类柜归还服务接口")
public class OneTypeCabinetGiveBackController {

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;

    @GetMapping("/list")
    @ApiOperation(value = "归还流程：1.1任务列表")
    public LayuiPageInfo giveBackList(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return oneTypeCabinetService.wmsCabinet1RepairReturnTaskList(serialNumber);
    }

    @GetMapping("/all")
    @ApiOperation(value = "归还流程：1.2归还申请任务")
    public LayuiPageInfo giveBackWmsReturnApply(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return oneTypeCabinetService.wmsReturnApplyList(serialNumber);
    }

    @GetMapping("/cancel")
    @ApiOperation(value = "归还流程：2.取消接口")
    public ResponseData giveBackCancel(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.updateCabinet1ReturnTask(taskNumber, StateEnum.ZERO.getState());
        return ResponseData.success();
    }

    @GetMapping("/start")
    @ApiOperation(value = "归还流程：3.开始接口")
    public ResponseData giveBackStart(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.updateCabinet1ReturnTask(taskNumber,StateEnum.ONE.getState());
        return ResponseData.success();
    }

    @GetMapping("/tool")
    @ApiOperation(value = "归还流程：4.工具查看")
    public ResponseData giveBackTool(@ApiParam("物料编号") @RequestParam String materialSerialNumber){
        // 将工具信息返回
        WmsMaterialTool wmsMaterialTool = oneTypeCabinetService.findToolByMaterialSerialNumber(materialSerialNumber);
        if(wmsMaterialTool == null){
            return ResponseData.error("工具维护中无此数据或被禁用");
        }
        return ResponseData.success(wmsMaterialTool);
    }

    @GetMapping("/conform")
    @ApiOperation(value = "归还流程：5.确认接口")
    public ResponseData giveBackConform(@ApiParam("任务编号") @RequestParam String taskNumber,
                                        @ApiParam("用户编号") @RequestParam String serialNumber,
                                        @ApiParam("工具状态") @RequestParam String materialState,
                                        @ApiParam("物料编号") @RequestParam String materialSerialNumber){
        return oneTypeCabinetService.conformCabinet1ReturnTask(taskNumber,serialNumber,materialState,materialSerialNumber);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "归还流程 没有的接口说明：打开指定库门与关闭指定库门(下一步)复用换新任务接口 /open-door与/CabinetClosed")
    public ResponseData detail(){
        return ResponseData.success();
    }


}
