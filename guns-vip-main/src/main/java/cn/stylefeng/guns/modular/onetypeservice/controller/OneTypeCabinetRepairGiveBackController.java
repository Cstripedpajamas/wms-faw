package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.modular.utils.WebSocket.WebSocket;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by li wen ya on 2021/11/10
 * 维修归还控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/repair-give-back")
@Api(description = "Ⅰ类柜维修归还流程")
public class OneTypeCabinetRepairGiveBackController {

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;

    @GetMapping("/create-task")
    @ApiOperation(value = "维修归还流程：1.创建任务返回任务编号")
    public ResponseData createRepairGiveBack(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return oneTypeCabinetService.createRepairGiveBack(serialNumber);
    }

    @GetMapping("/cancel")
    @ApiOperation(value = "维修归还流程：2.点击取消按钮")
    public ResponseData cancel(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        oneTypeCabinetService.cancelRepairGiveBack(taskNumber);
        return ResponseData.success();
    }

    @GetMapping("/tool")
    @ApiOperation(value = "维修归还流程：3.扫码查询工具功能")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK",response = WmsMaterialTool.class)
    })
    public ResponseData queryTool(@ApiParam(value = "工具编号") @RequestParam String materialSerialNumber){
        WmsMaterialTool tool = oneTypeCabinetService.findToolByMaterialSerialNumber(materialSerialNumber);
        return ResponseData.success(tool);
    }

    @GetMapping("/conform")
    @ApiOperation(value = "维修归还流程：4.确认按钮调用")
    public ResponseData conform(@ApiParam(value = "任务编号") @RequestParam String taskNumber,
                                @ApiParam(value = "工具编号") @RequestParam String materialSerialNumber){
        return oneTypeCabinetService.confirmRepairGiveBack(taskNumber,materialSerialNumber);
    }

}
