package cn.stylefeng.guns.modular.WebApi;

import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.sparePartsManagement.requestMsg.task.TaskThread;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
import cn.stylefeng.guns.modular.utils.WebSocket.WebSocket;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: ll
 * @Date: 2021/11/3 9:52
 * @Version 1.0
 * @Des WebApi 接收请求
 */
@Controller
@RequestMapping("/WmsApi/Receive2")
@Slf4j
public class WmsApiReceive2 {
    @Autowired
    private WmsUserService wmsUserService;
    @Autowired
    private WmsCabinet2UseTaskService wmsCabinet2UseTaskService;

    // 认证人员信息 备品备件柜
//    @RequestMapping(value = "/StaffId", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseData checkUserMsg(@RequestBody String StaffId) {
//        log.info("Face recognition number{}",StaffId);
//        System.out.println("***********RenLianII**************StaffId"+StaffId);
//        WmsApiMethods.II(StaffId);
//        return ResponseData.success();
//    }

    @RequestMapping(value = "/StaffId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData checkUserMsg(@RequestBody String StaffId) {
        log.info("First class cabinet face recognition number{}", StaffId);
        System.out.println("***********RenLianII**************StaffId"+StaffId);
        WmsApiMethods.II(StaffId);
        return ResponseData.success();
    }

    // 出库结果(备品备件柜)
    @RequestMapping(value = "/OutboundResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData OutboundResult(@RequestBody String OutboundResult) {

        // 获取数据
        JSONObject jsonObject = JSONObject.parseObject(OutboundResult);
        String orderId = jsonObject.getString("OrderId");
        String returnCode = jsonObject.getString("ReturnCode");
        String HUNumber = jsonObject.getString("HUNumber");
        String LocationId = jsonObject.getString("LocationId");

        // 判断条件
        if (!Objects.equals("", TaskThread._runningId) && Objects.equals(orderId, TaskThread._runningId)) {
            if (Objects.equals("OK", returnCode)) {
                TaskThread.allMsg.put("outStockResult", "出库成功了");
                // 出库成功后 任务状态 改为出库完成
                TaskThread.isOutStockFinish(LocationId, HUNumber);
                WebSocket.sendMessageOfSession2(JSONObject.toJSONString(TaskThread.allMsg));
                return ResponseData.success();
            }
            String errorMessage = jsonObject.getString("ErrorMessage");
            TaskThread.allMsg.put("outStockResult", errorMessage);
            WebSocket.sendMessageOfSession2(JSONObject.toJSONString(TaskThread.allMsg));
            return ResponseData.success();
        }
        return ResponseData.error("当前无任务,或任务与当前执行任务不一致,请检查~~");
    }

    // 入库申请(备品备件柜)
    @RequestMapping(value = "/InboundRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData InboundRequest(@RequestBody String InboundRequest) {
        JSONObject jsonObject = JSONObject.parseObject(InboundRequest);
        String HUNumber = jsonObject.getString("HUNumber"); //
        String CheckResult = jsonObject.getString("CheckResult");
        log.info("Warehousing application turnover box No{}",HUNumber);
        log.info("Warehousing application inspection results{}",CheckResult);
        return ResponseData.success();
    }

    // 入库结果(备品备件柜)
    @RequestMapping(value = "/InboundResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData InboundResult(@RequestBody String InboundResult) {
        JSONObject jsonObject = JSONObject.parseObject(InboundResult);
        String orderId = jsonObject.getString("OrderId"); // 任务的id
        String ReturnCode = jsonObject.getString("ReturnCode"); // 执行结果
        String ErrorMessage = jsonObject.getString("ErrorMessage"); // 错误信息
        if (!Objects.equals("", TaskThread._runningId) && Objects.equals(orderId, TaskThread._runningId)) {
            if (Objects.equals("OK",ReturnCode)){ // 入库完成

                // webSocket 推送 跳转界面
                TaskThread.isIsBackTurnoverResult = true;
                TaskThread.allMsg.put("isInStockFinish",true);
                WebSocket.sendMessageOfSession2(JSONObject.toJSONString(TaskThread.allMsg));
                return ResponseData.success("ok");
            }
            TaskThread.allMsg.put("isInStockFinish",false);
            WebSocket.sendMessageOfSession2(JSONObject.toJSONString(TaskThread.allMsg));
            return ResponseData.success("error");

        }

        return ResponseData.error("当前无任务,或任务与当前执行任务不一致,请检查~~");
    }

    // 报废品计数结果(备品备件柜)
    @RequestMapping(value = "/ScrapCountResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData ScrapCountResult(@RequestBody String ScrapCountResult) {
        Map<String, Object> str = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(ScrapCountResult);
        String orderId = jsonObject.getString("OrderId");
        // 判断 任务的id
        if (!Objects.equals("", TaskThread._runningId) && Objects.equals(orderId, TaskThread._runningId)) {
            String scrapCount = jsonObject.getString("ScrapCount");
            log.info("Input scrap quantity{}",scrapCount);
            // 更新 领用任务
            wmsCabinet2UseTaskService.updateScropNumber(orderId, scrapCount);

            // webSocket 推送数量
            str.put("hasScrapped", scrapCount);
            WebSocket.sendMessageOfSession2(JSONObject.toJSONString(str));
            return ResponseData.success();
        }

        return ResponseData.error("当前无任务,或任务与当前执行任务不一致,请检查~~");
    }

    // 异常信息(备品备件柜)
    @RequestMapping(value = "/ErrorCallback", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData errorMsg(@RequestBody String errorMsg) {
        System.out.println(errorMsg);
        //todo 业务.....
        log.info("Abnormal information returned by class II cabinet{}",errorMsg);
        return ResponseData.success();
    }

    @RequestMapping(value = "/ASRSAutoExeTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData ASRSAutoExeTask(@RequestBody String StaffId){
        log.info("ASRSAutoExeTask StaffId{}",StaffId);
        WmsApiMethods.ASRSAutoExeTask(StaffId);
        return ResponseData.success();
    }

}
