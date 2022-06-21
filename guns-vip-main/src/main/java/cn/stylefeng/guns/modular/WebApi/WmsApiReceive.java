package cn.stylefeng.guns.modular.WebApi;

import cn.stylefeng.guns.modular.base.redirect.RedirectConfController;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1Stock;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1StockParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1StockResult;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsIntelligentCabinet1StockService;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskIn;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsSortingTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskInParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverBindResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverResult;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsSortingTaskService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTaskInService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.*;


/**
 * @Author: ll
 * @Date: 2021/11/3 9:52
 * @Version 1.0
 * @Des WebApi 接收请求
 */
@Controller
@RequestMapping("/WmsApi/Receive")
@Slf4j
public class WmsApiReceive {


    @Autowired //
    private WmsIntelligentCabinet1StockService wmsIntelligentCabinet1StockService;

    @Autowired
    private WmsSortingTaskService wmsSortingTaskService;

    @Autowired
    private WmsWarehouseTurnoverService wmsWarehouseTurnoverService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WmsWarehouseTaskInService wmsWarehouseTaskInService;

    @Autowired
    private WarehouseService warehouseService;

    // 认证人员信息 工具柜界面
    @RequestMapping(value = "/StaffId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData checkUserMsg(@RequestBody String StaffId) {
        log.info("First class cabinet face recognition number{}", StaffId);
        WmsApiMethods.I(StaffId);
        return ResponseData.success();
    }


    // 关闭格口(工具类)
    @RequestMapping(value = "/LockClosed", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData LockClosed(@RequestBody String LocationId) {
        JSONObject jsonObject = JSON.parseObject(LocationId);
        String str = jsonObject.getString("LocationId");
        log.info("Closing grid number{}", str);
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number", str));
        if (wmsIntelligentCabinet1Stock != null && wmsIntelligentCabinet1Stock.getId() != null) {
            WmsIntelligentCabinet1StockParam stockParam = new WmsIntelligentCabinet1StockParam();
            ToolUtil.copyProperties(wmsIntelligentCabinet1Stock, stockParam);
            stockParam.setLatticeState(StateEnum.ONE.getState());
            wmsIntelligentCabinet1StockService.update(stockParam);
        }
        return ResponseData.success();
    }


    /**
     * 打开格口
     */
    @RequestMapping("/open")
    @ResponseBody
    public ResponseData open(WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam) {
        WmsIntelligentCabinet1Stock detail = this.wmsIntelligentCabinet1StockService.getById(wmsIntelligentCabinet1StockParam.getId());
        Map<String, String> param = new HashMap<>();
        param.put("LocationId", detail.getLocaSerialNumber());
        List list = new ArrayList();
        list.add(param);
        Map<String, Object> map = new HashMap<>();
        map.put("List", list);
        WmsApiService.openDeclension(map);
        wmsIntelligentCabinet1StockParam.setLatticeState("0");
        this.wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1StockParam);
        return ResponseData.success();
    }

    /**
     * 打开全部格口
     */
    @RequestMapping("/openAll")
    @ResponseBody
    public ResponseData openAll() {
        Map<String, String> param = new HashMap<>();
        List<WmsIntelligentCabinet1StockResult> wmsIntelligentCabinet1StockResultList = this.wmsIntelligentCabinet1StockService.findListBySpec(new WmsIntelligentCabinet1StockParam());
        if (wmsIntelligentCabinet1StockResultList != null && !wmsIntelligentCabinet1StockResultList.isEmpty()) {
            for (int i = 0; i < wmsIntelligentCabinet1StockResultList.size(); i++) {
                WmsIntelligentCabinet1StockResult wmsIntelligentCabinet1StockResult = wmsIntelligentCabinet1StockResultList.get(i);
                param.put("LocationId", wmsIntelligentCabinet1StockResult.getLocaSerialNumber());
            }
            Map<String, Object> map = new HashMap<>();
            map.put("List", param);
            WmsApiService.openDeclension(map);
            for (int i = 0; i < wmsIntelligentCabinet1StockResultList.size(); i++) {
                WmsIntelligentCabinet1StockResult wmsIntelligentCabinet1StockResult = wmsIntelligentCabinet1StockResultList.get(i);
                WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam = new WmsIntelligentCabinet1StockParam();
                wmsIntelligentCabinet1StockParam.setId(wmsIntelligentCabinet1StockResult.getId());
                wmsIntelligentCabinet1StockParam.setLatticeState("0");
                this.wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1StockParam);
            }
        }
        return ResponseData.success();
    }


    @PostMapping(value = "/sorting")
    @ResponseBody
    @ApiOperation(value = "分拣站反馈信息")
    public cn.hutool.json.JSONObject TaskcbackInfo(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        // 任务标识(order_id), 任务状态(task_status:OK / ER), 任务数量(task_number), 实际分拣数量(sorting_number), 异常代码(er_code: ER1/ER2)
        System.out.println(jsonObject.get("order_id"));
        System.out.println(jsonObject.get("task_status"));
        System.out.println(jsonObject.get("task_number"));
        System.out.println(jsonObject.get("sorting_number"));
        System.out.println(jsonObject.get("er_code"));
        RedirectConfController.sort_1 = String.valueOf(jsonObject.get("order_id"));
        RedirectConfController.sort_2 = String.valueOf(jsonObject.get("task_status"));
        RedirectConfController.sort_3 = String.valueOf(jsonObject.get("task_number"));
        RedirectConfController.sort_4_1 = String.valueOf(jsonObject.get("sorting_number"));
        RedirectConfController.sort_4_2 = String.valueOf(jsonObject.get("er_code"));

        String orderId = String.valueOf(jsonObject.get("order_id"));
        String taskNumber = String.valueOf(jsonObject.get("task_number"));
        String sortingNumber = String.valueOf(jsonObject.get("sorting_number"));

//        if (taskNumber.split("-")[0].equals("tool")) {
            WmsSortingTaskResult wmsSortTask = wmsSortingTaskService.findById(orderId);
            String barcode = wmsSortTask.getBarcode();
            WmsWarehouseTurnoverResult turnover = wmsWarehouseTurnoverService.findByBarCode(barcode);
            WmsWarehouseTurnoverBindParam param1 = new WmsWarehouseTurnoverBindParam();
            param1.setTurnoverId(turnover.getId().toString());
            WmsWarehouseTurnoverBindResult turnoverBind = wmsWarehouseTurnoverBindService.findByTurnoverId(param1);

            int i = Integer.parseInt(sortingNumber); // 分拣数量
            int i1 = Integer.parseInt(turnoverBind.getMNumber()); // 周转箱绑定的数量

            if (i == i1) {
                // 更新周转箱信息为空闲
                WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam = new WmsWarehouseTurnoverParam();
                turnover.setTurnoverState("0");
                ToolUtil.copyProperties(turnover, wmsWarehouseTurnoverParam);
                wmsWarehouseTurnoverService.update(wmsWarehouseTurnoverParam);

                // 更新周转箱绑定信息
                updateTurnoverBind(turnoverBind);
            } else {
                // 更新周转箱绑定的数量
                int rem = i1 - i;
                turnoverBind.setMNumber("" + rem);
                WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
                ToolUtil.copyProperties(turnoverBind, wmsWarehouseTurnoverBindParam);
                wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverBindParam);
            }

            // 更新分拣任务状态为已完成
            wmsSortTask.setTaskState("3");
            WmsSortingTaskParam wmsSortingTaskParam = new WmsSortingTaskParam();
            ToolUtil.copyProperties(wmsSortTask,wmsSortingTaskParam);
            wmsSortingTaskService.update(wmsSortingTaskParam);

            // 创建入库任务
          String messageId = createInTask(turnover,turnoverBind);

            // 执行入库
            warehouseService.sendTask(messageId);

//        }
//        if (taskNumber.split("-")[0].equals("spare")) {
//            // todo 备件补货 自动分拣业务
//
//
//        }

        cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject();
        object.put("Code", 200);
        object.put("errMsg", "成功");
        object.put("data", null);
        return object;
    }

    private String createInTask(WmsWarehouseTurnoverResult turnover, WmsWarehouseTurnoverBindResult turnoverBind) {
        WmsWarehouseTaskIn wmsWarehouseTaskIn = new WmsWarehouseTaskIn();
        String messageId = RandomStringUtils.randomNumeric(12);
        wmsWarehouseTaskIn.setMessageId(messageId);
        wmsWarehouseTaskIn.setOrderType("B");
        String turnoverState = turnover.getTurnoverState();
        if (Objects.equals("0",turnoverState)){
            wmsWarehouseTaskIn.setGoodsType("C");
        }
        else if (Objects.equals("1",turnoverBind.getGoodsType())){
            wmsWarehouseTaskIn.setGoodsType("A");
        }
        else {
            wmsWarehouseTaskIn.setGoodsType("B");
        }

        wmsWarehouseTaskIn.setTurnoverType(turnover.getTurnoverType());
        wmsWarehouseTaskIn.setTurnoverNumber(turnover.getTurnoverNumber());
        wmsWarehouseTaskIn.settBarcode(turnover.getBarcode());
        wmsWarehouseTaskIn.setTurnoverMouthQuality(turnover.getTurnoverMouthQuantity());
        wmsWarehouseTaskIn.setSortingInfo("B");
        wmsWarehouseTaskIn.setReqTag("0");
        wmsWarehouseTaskIn.setReqStatus("0");
        wmsWarehouseTaskIn.setResStatus("0");
        wmsWarehouseTaskIn.setResTag("0");
        wmsWarehouseTaskIn.setCreateTime(new Date());
        wmsWarehouseTaskInService.save(wmsWarehouseTaskIn);
        return messageId;

    }

    private void updateTurnoverBind(WmsWarehouseTurnoverBindResult turnoverBind) {
        WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
        turnoverBind.setLatticeCode("");
        turnoverBind.setGoodsType("");
        turnoverBind.setMaterialTypeId("");
        turnoverBind.setMaterialId("");
        turnoverBind.setMaterialType("");
        turnoverBind.setMaterialName("");
        turnoverBind.setMaterialSku("");
        turnoverBind.setMUnit("");
        turnoverBind.setMaterialSerialNumber("");
        turnoverBind.setMBatch("");
        turnoverBind.setMNumber("");
        turnoverBind.setLatticeState("0");
        turnoverBind.setCreateTime(new Date());
        ToolUtil.copyProperties(turnoverBind,wmsWarehouseTurnoverBindParam);
        wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverBindParam);


    }


    @PostMapping(value = "/test")
    @ResponseBody
    public String test(@RequestBody String param) {
        System.out.println(param);
        JSONObject jsonObject = JSON.parseObject(param);
        // 任务标识(order_id), 任务状态(task_status:OK / ER), 任务数量(task_number), 实际分拣数量(sorting_number), 异常代码(er_code: ER1/ER2)
        System.out.println(jsonObject.get("batch_id"));
        System.out.println(jsonObject.get("task_nums"));
        System.out.println(jsonObject.get("pick_tote"));
        System.out.println(jsonObject.get("dimension"));

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", null);

        // todo 业务逻辑
        return JSONObject.toJSONString(map);
    }
}
