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
//        王盼宇增加接口字段，把拣选数量传过来
        String qty = jsonObject.getString("qty");
        String errMsg = jsonObject.getString("errMsg");
        log.info("Delivery result:Process No:{},Barcode of turnover box:{},Outbound status:{},Abnormal information:{}",messageId,turnoverNumber,status,errMsg);
        warehouseService.claimCallbackComplete(messageId,turnoverNumber,qty);
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
        log.info("Warehousing result:Process No:{},Location information:{},Warehousing status:{},Abnormal information:{}",messageId,locaNumber,status,errMsg);
        warehouseService.claimInCallback(messageId,locaNumber);
         return ResponseData.success();
    }

    @PostMapping("/re-callback")
    @ApiOperation(value = "移库完成")
    public ResponseData claimInRellback(@RequestBody String str){
        JSONObject jsonObject = JSONObject.parseObject(str);
        String messageId = jsonObject.getString("messageId");
        String orlocaNumber = jsonObject.getString("orlocaNumber");   //原来位置
        String culocaNumber = jsonObject.getString("culocaNumber");       //现在位置
        String contaioner= jsonObject.getString("contaioner");       //容器
        String errMsg = jsonObject.getString("errMsg");
        log.info("Warehousing result:Process No:{},OrLocation information:{},Warehousing culocaNumber:{},Warehousing contaioner,Abnormal information:{}",messageId,orlocaNumber,culocaNumber,contaioner,errMsg);
        warehouseService.claimInRellback(messageId,orlocaNumber,culocaNumber,contaioner);
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
