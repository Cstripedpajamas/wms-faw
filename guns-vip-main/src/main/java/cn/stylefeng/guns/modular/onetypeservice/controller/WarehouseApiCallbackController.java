package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.utils.WebSocket.WebSocket;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by li wen ya on 2021/12/1
 */
@RestController
@CrossOrigin
@RequestMapping("/claim")
@Slf4j
@Api(description = "立库工具领用回调接口")
public class WarehouseApiCallbackController {
    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;

    @PostMapping("/out-callback")
    @ApiOperation(value = "出库完成")
    public ResponseData claimOutCallback(@RequestBody String str){
        JSONObject jsonObject = JSONObject.parseObject(str);
        String messageId = jsonObject.getString("messageId");
        String turnoverNumber = jsonObject.getString("turnoverNumber");
        String status = jsonObject.getString("status");
        String errMsg = jsonObject.getString("errMsg");
        log.info("出库结果:流程单号:{},周转箱条码:{},出库状态:{},异常信息:{}",messageId,turnoverNumber,status,errMsg);
        warehouseService.claimCallbackComplete(messageId,turnoverNumber);
        return ResponseData.success();
    }

    @PostMapping("/in-callback")
    @ApiOperation(value = "入库完成")
    public ResponseData claimInCallback(@RequestBody String str){
        JSONObject jsonObject = JSONObject.parseObject(str);
         String messageId = jsonObject.getString("messageId");
         String locaNumber = jsonObject.getString("locaNumber");
         String status = jsonObject.getString("status");
         String errMsg = jsonObject.getString("errMsg");
        log.info("入库结果:流程单号:{},库位信息:{},入库状态:{},异常信息:{}",messageId,locaNumber,status,errMsg);
        warehouseService.claimInCallback(messageId,locaNumber);
         return ResponseData.success();
    }

    @GetMapping("/web-socket")
    @ApiOperation(value = "web socket 测试")
    public ResponseData webSocket(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        Map<String,Object> map = new HashMap<>();
        map.put("code",serialNumber);
        String object = JSONObject.toJSONString(map);
        WebSocket.sendMessageOfSession1(object);
        return oneTypeCabinetService.createRepairGiveBack(serialNumber);
    }


}
