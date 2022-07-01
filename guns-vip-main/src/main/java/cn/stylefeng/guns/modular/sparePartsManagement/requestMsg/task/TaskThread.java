package cn.stylefeng.guns.modular.sparePartsManagement.requestMsg.task;

import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import cn.stylefeng.guns.modular.WebApi.WmsApiService;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.result.WmsUseApplyResult;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.service.WmsCabinet2CheckTaskService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.entity.WmsCabinet2InputScrap;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.params.WmsCabinet2InputScrapParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.service.WmsCabinet2InputScrapService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.params.WmsCabinet2ReplenishmentTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.result.WmsCabinet2ReplenishmentTaskResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.service.WmsCabinet2ReplenishmentTaskService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.entity.WmsCabinet2Stock;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.params.WmsCabinet2StockParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.result.WmsCabinet2StockResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service.WmsCabinet2StockService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result.WmsCabinet2TurnoverResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service.WmsCabinet2TurnoverService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.service.WmsCabinet2TurnoverBindService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.result.WmsCabinet2UseTaskResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
import cn.stylefeng.guns.modular.utils.WebSocket.WebSocket;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: ll
 * @Date: 2021/11/8 8:52
 * @Version 1.0
 */
@Service
@Slf4j
public class TaskThread {

    private final static Logger logger = LoggerFactory.getLogger(TaskThread.class);

    @Autowired
    private WmsCabinet2UseTaskService wmsCabinet2UseTaskService; // 任务
    @Autowired
    private WmsCabinet2StockService wmsCabinet2StockService; // 库存
    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService; // 物料类型
    @Autowired
    private WmsApiService wmsApiService;
    @Autowired
    private WmsUseApplyService wmsUseApplyService;
    @Autowired
    private WmsCabinet2InputScrapService wmsCabinet2InputScrapService;
    @Autowired
    private WmsCabinet2TurnoverService wmsCabinet2TurnoverService;
    @Autowired
    private WmsCabinet2TurnoverBindService wmsCabinet2TurnoverBindService;
    @Autowired
    private WmsCabinet2ReplenishmentTaskService wmsCabinet2ReplenishmentTaskService;
    @Autowired
    private WmsCabinet2CheckTaskService wmsCabinet2CheckTaskService;


    public static TaskThread taskThread;


    @PostConstruct
    public void init() {
        taskThread = this;
        taskThread.wmsCabinet2UseTaskService = wmsCabinet2UseTaskService;
        taskThread.wmsCabinet2StockService = wmsCabinet2StockService;
        taskThread.wmsMaterialTypeService = wmsMaterialTypeService;
        taskThread.wmsApiService = wmsApiService;
        taskThread.wmsCabinet2InputScrapService = wmsCabinet2InputScrapService;
        taskThread.wmsUseApplyService = wmsUseApplyService;
        taskThread.wmsCabinet2TurnoverService = wmsCabinet2TurnoverService;
        taskThread.wmsCabinet2TurnoverBindService = wmsCabinet2TurnoverBindService;
        taskThread.wmsCabinet2ReplenishmentTaskService = wmsCabinet2ReplenishmentTaskService;
        taskThread.startTask();
    }

    // 判断用户是否登入
    public static boolean isLogin = false;

    // 用户的ID
    public static String userId = "";

    // 用户的类型
    public static String userType = "";

    // 用户姓名
    public static String userName = "";


    // 有无任务
    public static boolean taskState = false;//

    // 当前任务的id
    public static String _runningId = "";//

    // 领用申请中的报废数量
    public static int _scrapNumber = 0;//

    // 执行方法标识:
    public static Integer taskType = 0;//

    // 判断是否投入完成的标识
    public static boolean isTouState = false;//

    // 判断是否出库完成
    private static boolean isOutStockFinish = false;//
    private static boolean isOutSend = true;//
    private static boolean isIntSend = true;//

    // 取货完成按钮
    public static boolean isQuHuoCargoFinish = false;//

    // 空周转箱回库操作
    private static boolean isBackTurnover = false;//

    // 入库结果
    public static boolean isIsBackTurnoverResult = false;

    // 投入中
    public static boolean ifNewState = true;//
    public static boolean ifNewState1 = true;//

    // 跳出出库
    public static boolean jumpMethod = true;//

    // 锁定库位标识
    public static boolean lockStock = false;//

    // 取货完成后 周装箱剩余的数量
    private static Integer remainNumber = 0;//

    // 锁定空库存的信息
    public static WmsCabinet2StockResult wsr = null;//


    // 开始补货入货
    public static boolean startInMaterial = false;//


    // 当前出库的信息 库位编号  周转箱编号
    public static String stockID = "";//
    public static String turnoverID = "";//

    // 取消任务标识
    public static Boolean isCancelTask = false;//


    // 是否可以入库
    private static boolean isInStock = true;//
    // 查询库存判断条件
    private static boolean stockNumber = true;//

    public static Map<String, Object> allMsg = new HashMap<>();//

    // 已经投入的报废数量
    private static String ScrapQuantityInvested = "";


    // 任务线程
    private void startTask() {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
//                    try {
                        runTask();
                        Thread.sleep(1000);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
                }
            }
        }).start();
    }

    ;

    public static void runTask() {
        if (taskState) {
            // 任务类型
            switch (taskType) {
                case 1: // 领用
                    receiveFlow(_runningId);
                    break;
                case 2: // 补货
                    replenishFlow();
                    break;
                case 3:
                    checkFlow();
            }
        }
    }


    /**
     * 领用业务流程梳理 传入选中的一条领用任务信息的id
     */

    public static ResponseData receiveFlow(String id) {

        if (taskState) {
            // 根据任务id 查询出任务详情
            WmsCabinet2UseTaskResult wm = taskThread.wmsCabinet2UseTaskService.findById(id);

            // 根据任务的详情中的领用申请id 查询出领用申请中的报废数量
            WmsUseApplyResult applyMsg = taskThread.wmsUseApplyService.findById(wm.getUseRequestId());
            if (applyMsg != null) {
                _scrapNumber = Integer.parseInt(applyMsg.getScrapNum());
            }
            //  1.查询库存(领用物料类型ID 领用物料信息ID 数量) 做一次判断后就不在继续判断,库存只在这里查询一次
            if (stockNumber) {
                Integer count = taskThread.wmsCabinet2StockService.stockIsEnough(wm.getUseMaterialTypeId(), wm.getUseMaterialId(), Integer.parseInt(wm.getUseNumber()));
                if (count > 0) {
                    stockNumber = false;
                    initState(); // 初始化状态
                    allMsg.put("stockNumber", 1); // 推送库存判断
                    WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                } else {
                    allMsg.put("stockNumber", 0);
                    WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                    finishTask();
                }
            }

            if (!stockNumber) {
                // 3.根据领用任务中的报废物料类型ID查询出物料的详情信息并和报废数量一同推送给前台(websocket)
//                String sMaterialTypeId = wm.getSMaterialTypeId(); //
//                WmsMaterialTypeResult wt = taskThread.wmsMaterialTypeService.findById(sMaterialTypeId);
//                wt.setNumber(applyMsg.getScrapNum());
//                try {
//                    allMsg.put("running_task", wt); // 正在执行的信息
//                    WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
//                } catch (RuntimeException e) {
//                    System.out.println(e.getMessage());
//                    logger.error("Websocket连接异常");
//
//                }
//                try {
                    _runningId = wm.getId().toString();
                    //todo 开始报废品
                    //2.请求外部接口:开始报废品计数,传入id 和 申请报废数量
                    Declension requestMsg = null;
                    if (ifNewState1) {
                        requestMsg = getBadNumber(_runningId, _scrapNumber);
                        ifNewState1 = false;
                    }

                    if (!ifNewState1 && ifNewState) {
                        // 3.有库存 修改任务状态(1.开始投入)
                        taskThread.wmsCabinet2UseTaskService.updateState(id, "1");
                        ifNewState = false;
                    }


                    // 4.定时请求领用任务的报废数量,获取结果
                    if (!isTouState) {
                        getScrapNumber(_runningId, _scrapNumber);
                    }
                    // 报废投入完成后
                    if (getScrapNumber(_runningId, _scrapNumber) && jumpMethod) {
                        // 投入完成判断是否可以出库, 查找相应的信息开始出库  物料类型id 物料数量
                        if (isOutSend) {
                            outStockMsg(wm.getUseMaterialTypeId(), wm.getUseNumber());
                            isOutSend = false;
                        }
                        // 出库结果(WCS请求)返回后
                        if (isOutStockFinish) {
                            // 出库完成后  更新库存信息
                            allMsg.put("isOutStockFinish", 1); // 出库完成
                            WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                            updateOutStockMsg(stockID, turnoverID);
//                            isOutSend = true;
                        }
                    }

                    // 点击取货完成按钮之后,更新库内的信息
                    if (isQuHuoCargoFinish) {
//                        WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                        // 修改任务状态为取货完成(5) 更新周转箱状态为空闲(0) 及删除周转箱绑定的信息
                        taskThread.wmsCabinet2UseTaskService.updateState(_runningId, "5");

                        // 一对一
                        // 周装箱绑定的货物数量
                        WmsCabinet2TurnoverBindResult wtb = taskThread.wmsCabinet2TurnoverBindService.findByTurnId(turnoverID);
                        // 领用的数量
                        WmsCabinet2UseTaskResult byId = taskThread.wmsCabinet2UseTaskService.findById(_runningId);

                        // 判断是否全部取完
                        int i = Integer.parseInt(wtb.getMNumber());
                        int i1 = Integer.parseInt(byId.getUseNumber());
                        if ((remainNumber = i - i1) > 0) {
                            // 更新周转箱绑定的货物数量
                            taskThread.wmsCabinet2TurnoverBindService.updateNumber(turnoverID, remainNumber.toString());
                        } else {
                            // 删除 更新周转箱状态(无货)
                            taskThread.wmsCabinet2TurnoverBindService.delByTurnoverID(turnoverID);
                            taskThread.wmsCabinet2TurnoverService.updateTurnState(turnoverID, "0");
                        }
                        isQuHuoCargoFinish = false;
                        // 开始入库
                        isBackTurnover = true;
                    }

                    // 空周转箱回库
                    if (isBackTurnover) {
                        // 查找空库位
//                        WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                        if (!lockStock) {
                            wsr = taskThread.wmsCabinet2StockService.findNullStock();
                            if (wsr.getId() != null) {
                                lockStock = true;
                            }
                        }
                        if (lockStock) {
                            // 是否可以入库
                            Map<String, Object> map = new HashMap<>();
                            // 查询库位编号 周转箱编号
                            map.put("OrderId", _runningId);
                            String turnoverNumber = taskThread.wmsCabinet2TurnoverService.findTurnoverNumber(turnoverID);
                            final WmsCabinet2StockParam byId = taskThread.wmsCabinet2StockService.findById(stockID);
                            map.put("HUNumber", turnoverNumber);
                            map.put("LocationId", byId.getLocaNumber());

                            // todo 跳过入库判断
                            if (isIntSend) {
                                Declension inbound = taskThread.wmsApiService.Inbound(map);
                                if (Objects.equals("1", inbound.getResult())) {
                                    // 锁定空库位 只锁定一次
                                    taskThread.wmsCabinet2StockService.updateState(wsr.getId().toString(), "2");
                                    isIntSend = false;
                                }
                            }
                            if (isIsBackTurnoverResult) { // 入库判断 : 空周转箱回库成功
                                // 更新库存的信息 (空库位id绑定周转箱的id) 库位状态为有货,更新周装箱绑定的货物信息
                                if (remainNumber > 0) {
                                    // 周转箱上有货 更新数量
                                    WmsCabinet2TurnoverBindResult byTurnId = taskThread.wmsCabinet2TurnoverBindService.findByTurnId(turnoverID);
                                    updateStockMsg(byTurnId);
                                } else {
                                    taskThread.wmsCabinet2StockService.updateTurnover(wsr.getId().toString(), "0", turnoverID);
                                }
                                // 更新周转箱的信息 排 列 层 存放状态(库内)
                                taskThread.wmsCabinet2TurnoverService.updateStockLocal(turnoverID, wsr.getLocaRow(), wsr.getLocaCol(), wsr.getLocaLayer(), "1");
                                // 更新任务状态为结束
                                taskThread.wmsCabinet2UseTaskService.updateState(_runningId, "6");
                                // 结束此次任务 并初始化所有的状态
                                allMsg.put("isBackTurnover", "1"); // 回库完成
                                WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                                finishTask();
                                isIntSend = true;
                            }
                        } else {
                            return ResponseData.error("空库为不足~");
                        }
                    }
//
//                } catch (RuntimeException e) {
//                    System.out.println(e.getMessage());
//                    logger.error("外部接口访问失败,请检查网络连接!!!!!!");
//                }

                //
                return ResponseData.success("任务执行完毕");
            }

            finishTask();
            logger.info("Insufficient inventory");
            return ResponseData.error("库存不足");
        }
        finishTask();
        logger.info("No task execution~");
        return ResponseData.error("没有任务了");
    }


    /**
     * 补货业务流程梳理
     */
    public static void replenishFlow() {

        try {
            if (jumpMethod) {
                initReplenishState();
                //判断是否可以出库
                Map<String, Object> map = new HashMap<>();
                map.put("OrderId", _runningId);
                WmsCabinet2StockResult byTurnId = taskThread.wmsCabinet2StockService.findByTurnId(turnoverID);
                String turnoverNumber = taskThread.wmsCabinet2TurnoverService.findTurnoverNumber(turnoverID);
                map.put("HUNumber", turnoverNumber);
                map.put("LocationId", byTurnId.getLocaNumber());

                //todo 跳过出库判断
                if (isOutSend) {
                    Declension outbound = taskThread.wmsApiService.Outbound(map);
                    if (Objects.equals("1", outbound.getResult())) {
                        // 更新任务的状态 空箱开始出库
                        taskThread.wmsCabinet2ReplenishmentTaskService.updateStateById(_runningId, "1");
                        WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                        isOutSend = false;
                    }
                }
                // 判断出库结果
                if (isOutStockFinish) {
                    allMsg.put("isOutStockFinish", "1"); // 出库完成
                    WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                    // 更新库存信息,跟新周转箱箱信息,任务绑定周转箱信息
                    updateOutReplair();
                    isOutSend = true;
                }
            }

            // todo 点击取消之后执行
            if (isCancelTask) {
                if (isIsBackTurnoverResult) {
                    // 更新库存信息  // 更新周转箱的信息
                    taskThread.wmsCabinet2StockService.updateTurnover(wsr.getId().toString(), "1", TaskThread.turnoverID);
                    taskThread.wmsCabinet2TurnoverService.updateStockLocal(TaskThread.turnoverID, wsr.getLocaRow(), wsr.getLocaCol(), wsr.getLocaLayer(), "1");
                    // webSocket推送数据
                    allMsg.put("isInStockFinish", "1"); // 入库完成
                    WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                    // 任务状态改为结束
                    taskThread.wmsCabinet2ReplenishmentTaskService.updateStateById(_runningId, "5");
                    TaskThread.finishTask();
                }
            }

            // 点击确认之后继续向下执行
            if (startInMaterial) {
                if (isInStock) {
                    // 是否可以入库 更新任务状态为 开始入库
                    Map<String, Object> map = new HashMap<>();
                    map.put("OrderId", _runningId);
                    String turnoverNumber = taskThread.wmsCabinet2TurnoverService.findTurnoverNumber(TaskThread.turnoverID);
                    map.put("HUNumber", turnoverNumber);
                    WmsCabinet2StockParam byId = taskThread.wmsCabinet2StockService.findById(TaskThread.stockID);
                    map.put("LocationId", byId.getLocaNumber());
                    // todo 跳过入库判断
                    Declension outbound = taskThread.wmsApiService.Inbound(map);
                    allMsg.put("isInStockFinish", "0"); // 入库中
                    if (Objects.equals("1", outbound.getResult())) {
                        // 跟新任务状态为入库中
                        taskThread.wmsCabinet2ReplenishmentTaskService.updateStateById(_runningId, "3");
                        isInStock = false;
                    }
                }
                // 获取入库结果 入库完成
                if (isIsBackTurnoverResult) {
                    // 更新信息
                    boolean b = updateReplenishTask();
                    if (b) {
                        // 入库完成推送信息
                        allMsg.put("isInStockFinish", "1");
                        WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                        finishTask();
                    }
                }


            }


        } catch (Exception e) {
            // 推送异常信息
            System.out.println("错误信息"+e);
        }


    }

    /**
     * 盘点业务流程梳理
     */
    public static void checkFlow() {

        if (isOutStockFinish) { // 出库结果
            // 更新库存信息(清除库中周转箱的id 和 货物信息 并将库位状态改为空闲)
            taskThread.wmsCabinet2StockService.updateStockById(stockID, "0");
            //  周转箱信息(排列层置空 位置改为在出入口)
            taskThread.wmsCabinet2TurnoverService.updateState(turnoverID, "3");
            //  任务信息 状态改为出库完成
            taskThread.wmsCabinet2CheckTaskService.updateStateById(_runningId, "2");
            // webSocket 推送给前台出库完成
            Map<String, Object> info = new HashMap<>();
            info.put("code", "2");
            info.put("isOutStockFinish", "1");
            WebSocket.sendMessageOfSession2(JSONObject.toJSONString(info));
            isOutStockFinish = false;
        }
        if (isIsBackTurnoverResult) { // 入库结果
            // 更新任务的状态(入库完成)
            taskThread.wmsCabinet2CheckTaskService.updateStateById(_runningId, "4");
            // 更新库存信息 周转箱id 物料信息
            WmsCabinet2StockParam wms = taskThread.wmsCabinet2StockService.findById(stockID);

            WmsCabinet2TurnoverBindResult byTurnId = taskThread.wmsCabinet2TurnoverBindService.findByTurnId(turnoverID);
            wms.setLocationState("1");
            wms.setTurnoverId(turnoverID); // 周转箱id
            if (byTurnId != null) {
                wms.setMaterialTypeId(byTurnId.getMaterialTypeId()); // 物料类型id
                wms.setMaterialId(byTurnId.getMaterialId()); // 物料信息id
                wms.setMaterialName(byTurnId.getMaterialName()); // 物料名称
                wms.setMaterialSku(byTurnId.getMaterialSku()); // 物料SKU
                wms.setMBatch(byTurnId.getMBatch()); // 批次
                wms.setMNumber(byTurnId.getMNumber()); // 数量
            }
            taskThread.wmsCabinet2StockService.update(wms);

            //更信周转箱的信息(排列层)
            taskThread.wmsCabinet2TurnoverService.updateStockLocal(turnoverID, wms.getLocaRow(), wms.getLocaCol(), wms.getLocaLayer(), "1");
            // 更新任务的状态
//            taskThread.wmsCabinet2CheckTaskService.updateStateById(_runningId,"5");

            // webSocket推送数据
            Map<String, Object> info = new HashMap<>();
            info.put("code", "2");
            info.put("isBackTurnover", "1");
            WebSocket.sendMessageOfSession2(JSONObject.toJSONString(info));

            isIsBackTurnoverResult = false;

            // 清除执行的任务id 周转箱id 库位的id
            _runningId = "";
            turnoverID = "";
            stockID = "";
        }


    }

    // 开始报废品计数
    private static Declension getBadNumber(String OrderId, Integer ScrapNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("OrderId", OrderId);
        map.put("ScrapNumber", ScrapNumber.toString());

        // 返回的数据 如果 Result的数据 跟 ScrapNumber数据相同 跳出数据
        Declension declension = taskThread.wmsApiService.StartScrapCount(map);
        log.info("Access the returned result of scrap counting{}", declension);
        return declension;
    }


    // 获取领用任务已经报废的数量(传入的是 领用任务的id 和 领用申请中的报废数量)
    private static boolean getScrapNumber(String TaskId, Integer ScrapNumber) {
        if (isTouState) {
            return true;
        }

        try {
            WmsCabinet2UseTaskResult updatedMsg = taskThread.wmsCabinet2UseTaskService.findById(TaskId);
            if (!Objects.equals(ScrapQuantityInvested, updatedMsg.getSNumber())) {
                ScrapQuantityInvested = updatedMsg.getSNumber();
                allMsg.put("putInNumber", updatedMsg.getSNumber()); // 推送投入数量
                WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
            }
            if (Integer.parseInt(updatedMsg.getSNumber()) >= ScrapNumber) {
                //1.更新任务的状态(投入完成)
                taskThread.wmsCabinet2UseTaskService.updateState(TaskId, "2");

                allMsg.put("putInState", "1");
                WebSocket.sendMessageOfSession2(JSONObject.toJSONString(allMsg));
                // 2. 记录投入报废信息
                WmsCabinet2InputScrapParam new_Msg = new WmsCabinet2InputScrapParam();
                new_Msg.setUseTask(updatedMsg.getId().toString()); // 领用任务id
                new_Msg.setMaterialTypeId(updatedMsg.getSMaterialTypeId()); // 报废物料类型id
                new_Msg.setMNumber(updatedMsg.getSNumber()); // 报废数量
                new_Msg.setOperator(userName);
                new_Msg.setOperationTime(new Date());
                // 退出
                taskThread.wmsCabinet2InputScrapService.add(new_Msg);
                isTouState = true;
//                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 库存查找 -> 出库流程
    private static boolean outStockMsg(String matterTypeID, String matterNumber) {
        // 1 查询库存的数量 获取库存id 周转箱的id 根据周转箱的id去查询出绑定的数量 如果够用 绑定 库位锁定
//        WmsCabinet2StockResult wsp = taskThread.wmsCabinet2StockService.findNumberInfo(matterTypeID, matterNumber);
        System.out.println("物料类型id" + matterTypeID + "物料数量" + matterNumber);
        // 1. 查询出周转箱绑定货物信息表
        WmsCabinet2TurnoverBindResult wtr = taskThread.wmsCabinet2TurnoverBindService.findNumberInfo(matterTypeID, matterNumber);
        if (wtr != null) {
            // 1.更新库位为锁定
            System.out.println("周转箱信息:" + wtr);
            taskThread.wmsCabinet2StockService.updateStateByTurnId(wtr.getTurnoverId(), "2");
            // 根据周转箱的id查询出库位的id
            WmsCabinet2StockResult wsp = taskThread.wmsCabinet2StockService.findByTurnId(wtr.getTurnoverId());

            // 是否出库

            Map<String, Object> map = new HashMap<>();
            map.put("OrderId", _runningId);
//            map.put("HUNumber", wtr.getTurnoverId());
            String turnoverNumber = taskThread.wmsCabinet2TurnoverService.findTurnoverNumber(wtr.getTurnoverId());
            map.put("HUNumber", turnoverNumber);
//            map.put("LocationId", wsp.getId().toString());
            map.put("LocationId", wsp.getLocaNumber());
            // todo 跳过出库判断
            Declension outbound = taskThread.wmsApiService.Outbound(map);
            if (Objects.equals("1", outbound.getResult())) { // 判断是否可以出库
                // 根据任务id更新任务中的: 库存id字段 库存编号字段 任务状态字段
                taskThread.wmsCabinet2UseTaskService.updateStockMsgById(_runningId, wsp.getId().toString(), wsp.getLocaNumber(), "3");
                return true;

            } else {
                taskThread.wmsCabinet2StockService.updateStateByTurnId(wtr.getTurnoverId(), "1");
                return false;
            }

        }
        return false;
    }


    // 是否出库完成(库位编号  周转箱编号)
    public static void isOutStockFinish(String LocationId, String HUNumber) {
        // 查询库位编号和周转箱编号
        stockID = taskThread.wmsCabinet2StockService.findIdByLoaclNumber(LocationId);
        turnoverID = taskThread.wmsCabinet2TurnoverService.findIdByTurnoverNumber(HUNumber);
//        stockID = LocationId;
//        turnoverID = HUNumber;
        log.info("Issue completed location ID{},Delivery completed turnover box ID{}", stockID, turnoverID);
        isOutStockFinish = true;
    }

    // 领用出库完成之后
    private static void updateOutStockMsg(String stockID, String turnoverID) {
        // 跟新任务状态
        taskThread.wmsCabinet2UseTaskService.updateState(_runningId, "4");
        // 更新库存信息 状态 清空周转箱信息
        taskThread.wmsCabinet2StockService.updateStockById(stockID, "0");
        // 更新周转箱信息
        taskThread.wmsCabinet2TurnoverService.updateState(turnoverID, "3");
        isOutStockFinish = false;
        jumpMethod = false;

    }

    // 补货出库完成后
    public static void updateOutReplair() {
        // 更新库存信息(无周转箱,状态为无货)
        taskThread.wmsCabinet2StockService.clearTurnMsg(stockID, "0");
        // 更新周转箱的信息 状态改为出入口
        taskThread.wmsCabinet2TurnoverService.updateState(turnoverID, "3");
        // 更新任务的状态 出库完成
        taskThread.wmsCabinet2ReplenishmentTaskService.updateStateById(_runningId, "2");

        // 查询周转箱的编码
        String turnoverNumber = taskThread.wmsCabinet2TurnoverService.findTurnoverNumber(turnoverID);
        // 任务绑定周转箱
        WmsCabinet2ReplenishmentTaskParam w = taskThread.wmsCabinet2ReplenishmentTaskService.findById(_runningId);
        w.setTurnoverId(turnoverID);
        w.setTurnoverNumber(turnoverNumber);
        taskThread.wmsCabinet2ReplenishmentTaskService.update(w);
        isOutStockFinish = false;
        jumpMethod = false;
    }


    // 更新库存信息
    private static void updateStockMsg(WmsCabinet2TurnoverBindResult byTurnId) {
        WmsCabinet2StockParam wmsCabinet2StockParam = new WmsCabinet2StockParam();
        // id
        wmsCabinet2StockParam.setId(wsr.getId());
        // 库位编号
        wmsCabinet2StockParam.setLocaNumber(wsr.getLocaNumber());
        // 排
        wmsCabinet2StockParam.setLocaRow(wsr.getLocaRow());
        // 列
        wmsCabinet2StockParam.setLocaCol(wsr.getLocaCol());
        // 层
        wmsCabinet2StockParam.setLocaLayer(wsr.getLocaLayer());
        // 设备码
        wmsCabinet2StockParam.setLocaEquipment(wsr.getLocaEquipment());
        // 状态
        wmsCabinet2StockParam.setLocationState("1");
        // 周转箱ID
        wmsCabinet2StockParam.setTurnoverId(byTurnId.getTurnoverId());
        // 物料类型ID
        wmsCabinet2StockParam.setMaterialTypeId(byTurnId.getMaterialTypeId());
        // 物料信息ID
        wmsCabinet2StockParam.setMaterialId(byTurnId.getMaterialId());
        // 物料名称
        wmsCabinet2StockParam.setMNumber(byTurnId.getMNumber());
        // 物料SKU
        wmsCabinet2StockParam.setMaterialName(byTurnId.getMaterialName());
        // 批次
        wmsCabinet2StockParam.setMaterialSku(byTurnId.getMaterialSku());
        // 数量
        wmsCabinet2StockParam.setMBatch(remainNumber.toString());
        taskThread.wmsCabinet2StockService.update(wmsCabinet2StockParam);
    }

    // 初始化领用推送的状态
    public static void initState() {
        // 需要推送的数据  1. 库存是否充足 2.  2.投入的数量  3.投入的状态  4.出库的状态  5. 回库的状态 6. 正在执行的任务信息
        allMsg.put("code", "0");  // 标识
        allMsg.put("stockNumber", "0"); // 库存是否充足
        allMsg.put("putInNumber", "0");  // 投入的报废数量
        allMsg.put("putInState", "0");  // 投入的状态
        allMsg.put("isOutStockFinish", "0"); // 出库状态
        allMsg.put("isBackTurnover", "0");  // 入库完成
//        allMsg.put("running_task", "");  // 正在执行的任务信息

    }


    // 初始化补货推送状态
    public static void initReplenishState() {
        // 需推送的数据 1. 出库状态
        allMsg.put("code", "1");
        allMsg.put("isOutStockFinish", "0"); // 出库状态
        // 是否入库完成
        allMsg.put("isInStockFinish", "0");
    }

    // 空周转箱入库(补货取消按钮触发)
    public static void nullTurnoverInStock(WmsCabinet2StockResult nullStock) {
        while (true) {

        }
    }

    // 清空领用任务信息
    public static void finishTask() {
        _runningId = "";
        _scrapNumber = 0;
        isTouState = false;
        isOutStockFinish = false;
        isQuHuoCargoFinish = false;
        isBackTurnover = false;
        isIsBackTurnoverResult = false;
        stockID = "";
        turnoverID = "";
        taskState = false;
        taskType = 0;
        stockNumber = true;
        ifNewState = true;
        ifNewState1 = true;
        jumpMethod = true;
        lockStock = false;
        wsr = null;
        remainNumber = 0;
        allMsg.clear();
        startInMaterial = false;
        isInStock = true;
        isCancelTask = false;
        ScrapQuantityInvested = "";
        isIntSend = true;
        isOutSend = true;
    }


    // 清空用户信息
    public static void clearUser() {
        userId = "";
        userType = "";
        userName = "";
        isLogin = false;
    }

    // 更新备品备件补货任务数据
    public static boolean updateReplenishTask() {
        // 更新任务状态为入库完成
        taskThread.wmsCabinet2ReplenishmentTaskService.updateStateById(_runningId, "4");
        // 更新库存信息:周转箱的id 物料信息 状态:有货
        WmsCabinet2TurnoverBindResult byTurnId = taskThread.wmsCabinet2TurnoverBindService.findByTurnId(turnoverID);
        WmsCabinet2StockParam wms = new WmsCabinet2StockParam();
        wms.setId(wsr.getId());
        wms.setLocaNumber(wsr.getLocaNumber());
        wms.setLocaRow(wsr.getLocaRow());
        wms.setLocaCol(wsr.getLocaCol());
        wms.setLocaLayer(wsr.getLocaLayer());
        wms.setLocaEquipment(wsr.getLocaEquipment());
        wms.setLocationState("1"); // 有货
        wms.setTurnoverId(turnoverID); // 周转箱id
        wms.setMaterialTypeId(byTurnId.getMaterialTypeId()); // 物料类型id
        wms.setMaterialId(byTurnId.getMaterialId()); // 物料id
        wms.setMaterialName(byTurnId.getMaterialName()); // 物料名称
        wms.setMaterialSku(byTurnId.getMaterialSku()); // 物料SKU
        wms.setMBatch(byTurnId.getMBatch()); // 物料批次
        wms.setMNumber(byTurnId.getMNumber()); // 物料数量
        wms.setCreateTime(wsr.getCreateTime());
        taskThread.wmsCabinet2StockService.update(wms);

        // 更新周转箱信息 改为 库内  排列层更新上
        taskThread.wmsCabinet2TurnoverService.updateStockLocal(turnoverID, wsr.getLocaRow(), wsr.getLocaCol(), wsr.getLocaLayer(), "1");
        // 更新任务库存信息 库位编码
        WmsCabinet2ReplenishmentTaskParam byId = taskThread.wmsCabinet2ReplenishmentTaskService.findById(_runningId);
        byId.setStockId(stockID); // 库存id
        // 查询库位编号并保存
        String stockNumber = taskThread.wmsCabinet2StockService.findStockNumber(stockID);
        byId.setLocaNumber(stockNumber);
        byId.setOperationTime(new Date());
        byId.setTaskState("5");
        taskThread.wmsCabinet2ReplenishmentTaskService.update(byId);
        return true;
    }
}
