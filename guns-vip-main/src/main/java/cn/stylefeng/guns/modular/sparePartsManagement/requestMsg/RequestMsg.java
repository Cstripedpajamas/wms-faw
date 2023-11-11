package cn.stylefeng.guns.modular.sparePartsManagement.requestMsg;

import cn.hutool.http.webservice.SoapClient;
import cn.stylefeng.guns.DingDingSendMsg;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.config.AppConfig;
import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import cn.stylefeng.guns.modular.WebApi.WmsApiService;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.result.WmsIntelligentCabinet2ConfResult;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.service.WmsIntelligentCabinet2ConfService;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.model.params.WmsMaterialSparePartsParam;
import cn.stylefeng.guns.modular.base.materialspareparts.model.result.WmsMaterialSparePartsResult;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.result.WmsWarehousePurchaseorderDeliveryResult;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.WmsWarehousePurchaseorderDeliveryService;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.modular.base.user.entity.LoginEntity;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodeProviderEnum;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.result.WmsUseApplyResult;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.guns.modular.sparePartsManagement.requestMsg.task.TaskThread;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.params.WmsCabinet2CheckTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.result.WmsCabinet2CheckTaskResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.service.WmsCabinet2CheckTaskService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.params.WmsCabinet2ReplenishmentTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.result.WmsCabinet2ReplenishmentTaskResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.service.WmsCabinet2ReplenishmentTaskService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.params.WmsCabinet2StockParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.result.WmsCabinet2StockResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service.WmsCabinet2StockService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service.WmsCabinet2TurnoverService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.params.WmsCabinet2TurnoverBindParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.BatchEnt;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResultP;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.service.WmsCabinet2TurnoverBindService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity.WmsCabinet2UseTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.params.WmsCabinet2UseTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.result.WmsCabinet2UseTaskResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.params.WmsCheckDifferenceRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.service.WmsCheckDifferenceRecordInfoService;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.params.WmsErrorRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.service.WmsErrorRecordInfoService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseStorageTask;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseReplenishmentTask;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehousePurchaseStorageTaskService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseReplenishmentTaskService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.guns.webservice.client.ExecuteBindQSService;
import cn.stylefeng.guns.webservice.client.Req;
import cn.stylefeng.guns.webservice.client.Resp;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: ll
 * @Date: 2021/11/8 8:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/requestMsg")
@CrossOrigin
@Api(description = "II类柜业务处理")
public class RequestMsg extends BaseController {

    @Autowired
    private WmsUserService wmsUserService;
    @Autowired
    private Map<String, Code> mapCodeGenerator;
    @Autowired
    private WmsCabinet2UseTaskService wmsCabinet2UseTaskService;
    @Autowired
    private WmsErrorRecordInfoService wmsErrorRecordInfoService;
    @Autowired
    private WmsCabinet2TurnoverBindService wmsCabinet2TurnoverBindService;
    @Autowired
    private WmsUseApplyService wmsUseApplyService;
    @Autowired
    private WmsCabinet2ReplenishmentTaskService wmsCabinet2ReplenishmentTaskService;
    @Autowired
    private WmsCabinet2StockService wmsCabinet2StockService;
    @Autowired
    private WmsMaterialSparePartsService wmsMaterialSparePartsService;
    @Autowired
    private WmsCabinet2TurnoverService wmsCabinet2TurnoverService;
    @Autowired
    private WmsApiService wmsApiService;
    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;
    @Autowired
    private WmsCabinet2CheckTaskService wmsCabinet2Check;
    @Autowired
    private WmsCabinet2CheckTaskService wmsCabinet2CheckTaskService;
    @Autowired
    private WmsCheckDifferenceRecordInfoService wmsCheckDifferenceRecordInfoService;

    @Autowired
    private WmsIntelligentCabinet2ConfService wmsIntelligentCabinet2ConfService;

    @Autowired
    private WmsWarehouseReplenishmentTaskService wmsWarehouseReplenishmentTaskService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    @Autowired
    private WmsWarehousePurchaseStorageTaskService wmsWarehousePurchaseStorageTaskService;
    @Autowired
    private WmsWarehousePurchaseorderDeliveryService wmsWarehousePurchaseorderDeliveryService;
    // 盘点任务编号
    public static String inventoryTaskNumber = "";


    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    /**
     * 员工登入接口
     *
     * @Des 记录员工的登入情况
     */
    @ResponseBody
    @PostMapping(value = "/login")
    @ApiOperation(value = "II类柜员工登入接口")
    public ResponseData login(@ApiParam("人员登入信息") @RequestBody String loginEntity) {
        /*
         * 登入:
         * II类柜: 标识字段 II 用户名 密码 判断状态 用户名 密码; 响应数据: 人员身份信息(不为维修人员)
         * */
        LoginEntity user = JSONObject.parseObject(loginEntity, LoginEntity.class);
        if (Objects.equals("II", user.getState())) {
            if (TaskThread.isLogin) {
                return ResponseData.error("当前已经有用户登陆了");
            }
            WmsUserResult wm = wmsUserService.findUserInfo2(user.getSerialNumber(), user.getuPwd());
            if (wm != null) {
                saveUserInfo(wm);
                List list = tsUserInfo(wm);
                Map<String, Object> map = new HashMap<>();
                map.put("userInfo", wm);
                map.put("list", list);
                return ResponseData.success(map);
            } else {
                return ResponseData.error("身份认证失败~");
            }
        }
        return ResponseData.error("身份认证失败");
    }


    /**
     * 人员退出登入
     */
    @ResponseBody
    @PostMapping(value = "/outLogin")
    @ApiOperation(value = "退出登入")
    public ResponseData outLogin(@RequestBody String msg) {
        // 退出登录 清空用户信息和任务信息
        TaskThread.finishTask();
        TaskThread.clearUser();
        return ResponseData.success();
    }


    /**
     * 领用任务详情列表
     *
     * @Des 查询出 此员工已经通过审核的领用任务
     */
    @ResponseBody
    @PostMapping(value = "/IIReceiveTask")
    @ApiOperation(value = "员工领用任务列表(根据操作人Id进行查询类型为备品备件)")
    public LayuiPageInfo IIReceiveTask(WmsCabinet2UseTaskParam wmsCabinet2UseTaskParam) {
        // 领用任务
        WmsCabinet2UseTaskParam wmsUserParam = new WmsCabinet2UseTaskParam();
        final WmsUser user = wmsUserService.getById(TaskThread.userId);
        wmsUserParam.setOperator(user.getSerialNumber());
        return wmsCabinet2UseTaskService.findPageBySpec2(wmsUserParam);
    }

    ;

    /**
     * 执行的任务类型(确认)
     */
    @ResponseBody
    @PostMapping(value = "/IITaskType")
    @ApiOperation(value = "执行的任务类型")
    public synchronized ResponseData IITaskType(@RequestBody String taskType) {

        // task中 包含 选中的id 和 任务类型
        JSONObject jsonObject = JSONObject.parseObject(taskType);
        String task = jsonObject.getString("taskType");
        String id = jsonObject.getString("id");
        // 判断是否登入和是否有任务在执行
        boolean b = judgeInfo();
        if (b) {
            // 1 领用 2 补货 3  盘点
            if (Objects.equals("1", task)) {
                TaskThread._runningId = id;
                TaskThread.taskState = true; // 有任务
                TaskThread.taskType = 1;
//                TaskThread.runTask();

                // 查找报废信息返回
                WmsCabinet2UseTaskResult wm = wmsCabinet2UseTaskService.findById(id);
                String sMaterialTypeId = wm.getSMaterialTypeId(); //
                WmsMaterialTypeResult wt = wmsMaterialTypeService.findById(sMaterialTypeId);
                WmsUseApplyResult applyMsg = wmsUseApplyService.findById(wm.getUseRequestId());
                wt.setNumber(applyMsg.getScrapNum());
                return ResponseData.success(wt);
            }
            if (Objects.equals("2", task)) {

            }
            if (Objects.equals("3", task)) {
                TaskThread.taskState = true;
//                TaskThread.taskType = 3;
            }
        }
        return ResponseData.error("有任务正在执行请稍后尝试");
    }

    ;

    /**
     * 取货完成按钮
     */
    @ResponseBody
    @PostMapping(value = "/finishCargo")
    @ApiOperation(value = "取货完成")
    public ResponseData finishCargo(@RequestBody String msg) {
        String runningId = TaskThread._runningId;
        System.out.println("=======================runningId："+ runningId + "=====================");
        TaskThread.isQuHuoCargoFinish = true;

//        TODO 二类柜取货完成需要上报ERP出库完成
        //判断是否需要创建备件补货任务
        WmsCabinet2UseTaskResult byId = wmsCabinet2UseTaskService.findById(runningId);
        System.out.println("=======================byId："+ byId + "=====================");

        String materialTypeId = byId.getUseMaterialId();
        System.out.println("=======================materialTypeId："+ materialTypeId + "=====================");

        // 阀值
        WmsIntelligentCabinet2ConfResult result = wmsIntelligentCabinet2ConfService.findBySku(materialTypeId);
        if (result != null) {

            int replenishmentThreshold = Integer.parseInt(result.getReplenishmentThreshold());

            // 库存
            List<WmsCabinet2TurnoverBindResultP> total = wmsCabinet2TurnoverBindService.findBySku(result.getMaterialSku());
            int reduce = total.stream().mapToInt(WmsCabinet2TurnoverBindResultP::getMNumber).boxed().reduce(0, Integer::sum);
            System.out.println("replenishmentThreshold:"+replenishmentThreshold+"reduce:"+reduce);
            if (replenishmentThreshold > reduce) {

//                王盼宇修改于20230516，修改内容产生补货任务时钉钉推送消息至管理员
                List<WmsUser> wmsUsers=wmsUserService.list(new QueryWrapper<WmsUser>().eq("user_type","A"));

                WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(materialTypeId);
                // 阀值减去库存,等于补货数量
                int number = replenishmentThreshold - reduce;
                WmsWarehouseReplenishmentTask existTask =
                        wmsWarehouseReplenishmentTaskService
                                .getOne(new QueryWrapper<WmsWarehouseReplenishmentTask>()
                                        .eq("material_type_id",materialTypeId)
                                        .eq("task_state","0")
                                );
                if(existTask != null){
                    wmsWarehouseReplenishmentTaskService.update(new UpdateWrapper<WmsWarehouseReplenishmentTask>()
                            .eq("id", existTask.getId()).set("m_number", number));
                    return ResponseData.success();
                }
                // 创建立库备件补货任务
                WmsWarehouseReplenishmentTask taskParam = new WmsWarehouseReplenishmentTask();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
                String format1 = format.format(new Date());
                taskParam.setTaskNumber("BJ" + format1);
                taskParam.setMaterialTypeId(byId.getUseMaterialTypeId());
                taskParam.setMaterialId(byId.getUseMaterialTypeId());
                taskParam.setMaterialSku(result.getMaterialSku());
                taskParam.setMaterialName(result.getMaterialName());
                taskParam.setSizes(wmsMaterialType.getSizes());
                taskParam.setSortingType(wmsMaterialType.getSortType());
                taskParam.setSortingNum("0");
                taskParam.setOperator("admin");
                // 批次 查询库内的所有批次,统计数量,并选择一个批次
                BatchEnt batchEnt = wmsWarehouseTurnoverBindService.findBatch(result.getMaterialSku(), number);

                if (batchEnt == null) {
//                    // todo 如何通知（已解决，钉钉通知）
                    for (WmsUser wmsUser:
                            wmsUsers) {
                        String phoneNumber=wmsUser.getPhoneNumber();
                        if (phoneNumber!=null&&phoneNumber!="") {
                            DingDingSendMsg dingDingSendMsg=new DingDingSendMsg();
                            String uid= dingDingSendMsg.getUserId(phoneNumber);
                            if (uid!=null&&uid!=""){
                                String text="物料："+wmsMaterialType.getMaterialName()+"，"+"立库缺货！   请尽快处理！";
                                dingDingSendMsg.sendMsg(uid,text);
                            }
                        }
                    }
//                    System.out.println("replenishmentThreshold:"+replenishmentThreshold+"reduce:"+reduce);
                    System.out.println("batchEnt:not found");
//                    return ResponseData.error("立库库存不足");

                } else {
                    taskParam.setmBatch(batchEnt.getMBatch());
                    taskParam.setmUnit(batchEnt.getMUnit());
                }
                taskParam.setmNumber("" + number);
                taskParam.setTaskState("0");
                wmsWarehouseReplenishmentTaskService.save(taskParam);


                for (WmsUser wmsUser:
                        wmsUsers) {
                    String phoneNumber=wmsUser.getPhoneNumber();
                    if (phoneNumber!=null&&phoneNumber!="") {
                        DingDingSendMsg dingDingSendMsg=new DingDingSendMsg();
                        String uid= dingDingSendMsg.getUserId(phoneNumber);
                        if (uid!=null&&uid!=""){
                            String text="物料："+wmsMaterialType.getMaterialName()+"，"+"缺货！   请尽快补货！";
                            dingDingSendMsg.sendMsg(uid,text);
                        }
                    }
                }

            }
        }else{
            System.out.println("WmsIntelligentCabinet2ConfResult not found:");
        }

        return ResponseData.success();
    }

    /**
     * 投入完成
     */
    @ResponseBody
    @PostMapping(value = "/finishTou")
    @ApiOperation(value = "投入完成")
    public ResponseData finishTou(@RequestBody String msg) {
        // 记录异常
        if (!Objects.equals("", TaskThread._runningId)) {
            WmsCabinet2UseTask byId = wmsCabinet2UseTaskService.getById(TaskThread._runningId);
            // 异常信息
            WmsErrorRecordInfoParam wms = new WmsErrorRecordInfoParam();
            wms.setCreateTime(new Date());
            wms.setOperationTime(byId.getOperationTime());
            wms.setErrorDescribe("投入完成异常");
            wms.setErrorType("备品备件领用");
            wms.setOperator(byId.getOperator());
            wmsErrorRecordInfoService.add(wms);
            TaskThread.isTouState = true;
            return ResponseData.success("操作成功");
        }
        return ResponseData.error("当前无执行中的任务");
    }


    /**
     * 取消领用
     */
    @ResponseBody
    @PostMapping(value = "/overScrap")
    @ApiOperation(value = "取消领用")
    public ResponseData overScrap() {
        // 记录异常
        if (!Objects.equals("", TaskThread._runningId)) {
            WmsCabinet2UseTask byId = wmsCabinet2UseTaskService.getById(TaskThread._runningId);
            // 异常信息
            WmsErrorRecordInfoParam wms = new WmsErrorRecordInfoParam();
            wms.setCreateTime(new Date());
            wms.setOperationTime(byId.getOperationTime());
            wms.setErrorDescribe("取消领用");
            wms.setErrorType("备品备件领用");
            wms.setOperator(byId.getOperator());
            wmsErrorRecordInfoService.add(wms);
            TaskThread.finishTask();
            return ResponseData.success("任务取消成功~");
        }
        return ResponseData.error("当前无任务执行~");
    }

    /**
     * 回到主页
     */
    @ResponseBody
    @PostMapping(value = "/backFirst")
    @ApiOperation(value = "回到主页")
    public ResponseData backFirst() {
        if (TaskThread.taskState) {
            return ResponseData.error("当前有任务在执行");
        }
        return ResponseData.success();
    }

    /**
     * 出库的周转箱详情信息
     */
    @ResponseBody
    @PostMapping(value = "/turnoverInfo")
    @ApiOperation(value = "周转箱详情信息")
    public ResponseData turnoverInfo() {
//        if (!Objects.equals("", TaskThread.turnoverID)) {
        // TaskThread.turnoverID
        WmsCabinet2TurnoverBindResult byTurnId = wmsCabinet2TurnoverBindService.findByTurnId2(TaskThread.turnoverID);
        return ResponseData.success(byTurnId);
//        }
//        return ResponseData.error("当前无出库周转箱信息~");
    }


    /**
     * 记录人员的信息
     */
    public static void saveUserInfo(WmsUserResult wm) {
        TaskThread.isLogin = true; // 有人员登入
        TaskThread.userId = wm.getId().toString(); // 登入人员的ID
        TaskThread.userName = wm.getUserName(); // 人员姓名
        TaskThread.userType = wm.getUserType(); // 人员类型
        // 开启任务
//        TaskThread taskThread = new TaskThread();
    }

    /**
     * 领用申请详情
     */
    @ResponseBody
    @PostMapping(value = "/IIReceivePlease")
    @ApiOperation(value = "员工领用任务列表(根据操作人Id进行查询类型为备品备件)")
    public LayuiPageInfo IIReceivePlease() {
        // 领用任务
        WmsUseApplyParam wap = new WmsUseApplyParam();
        WmsUser wmsUser = wmsUserService.getById(TaskThread.userId);
        wap.setOperator(wmsUser.getSerialNumber());
        return wmsUseApplyService.findPageBySpec(wap);
    }


    /**
     * @author ll
     * @Date 2021年11月15日08:59:58
     * @Des 补货申请流程
     * ***********************************************************************************************************
     */

    // 创建补货任务
    @ResponseBody
    @RequestMapping("/createRepairTask")
    @ApiOperation(value = "II类柜备品备件补货任务")
    public ResponseData createRepairTask(WmsCabinet2ReplenishmentTaskParam wms) {

        if (!TaskThread.isLogin) {
            return ResponseData.error("请先登录后在进行操作!");
        }

        if (!TaskThread.taskState) {
            //唯一任务编号
            String taskNumber = "RM" + getDate();
            // 创建补货任务,
            wms.setTaskNumber(taskNumber);
            wms.setOperator(TaskThread.userId);
            wmsCabinet2ReplenishmentTaskService.add(wms);

            // 根据补货任务查出当前创建的任务id,返回给前台
            WmsCabinet2ReplenishmentTaskResult wrt = wmsCabinet2ReplenishmentTaskService.findByTaskNumber(taskNumber);
            return ResponseData.success(wrt);
        }
        return ResponseData.error("当前有任务正在执行,请稍后尝试!");
    }

    // 库存查询
    @ResponseBody
    @RequestMapping("/checkStockMsg")
    @ApiOperation(value = "备品备件空周转箱库存查询(任务id)")
    public ResponseData checkStockMsg(String id) {
        // 1.查询库存中有空周转箱的数据 有:锁定库存并保存周转箱编号和库存的信息 无: 返回无库存信息
        WmsCabinet2StockResult wsr = wmsCabinet2StockService.findNullReturnStock();
        if (wsr != null) {
            // 锁定库存
            wmsCabinet2StockService.updateState(wsr.getId().toString(), "2");
            // 执行任务
            TaskThread._runningId = id; // 任务id
            TaskThread.stockID = wsr.getId().toString(); // 库位id
            TaskThread.turnoverID = wsr.getTurnoverId(); // 周转箱id
            TaskThread.taskState = true; // 有任务
            TaskThread.taskType = 2; // 任务类型
            // 根据周转箱id 查询出周转箱的编号
            String turnoverNumber = wmsCabinet2TurnoverService.findTurnoverNumber(wsr.getTurnoverId());
            return ResponseData.success(turnoverNumber);
        }
        wmsCabinet2ReplenishmentTaskService.updateStateById(TaskThread._runningId, "5");
        return ResponseData.error("空库为不足~");
    }

    // 点击取消按钮
    @ResponseBody
    @PostMapping(value = "/cancelRepairTask")
    @ApiOperation(value = "取消备品备件补货任务")
    public ResponseData cancelRepairTask(@RequestBody String arr) {
        if (TaskThread.taskState) {
            // 空周转箱回库 1. 查询空周转箱 2.判断是否可以入库 3.入库 跟新数据
//            WmsCabinet2StockResult nullStock = wmsCabinet2StockService.findNullStock();
//            if (nullStock != null) {
            WmsCabinet2StockParam byId = wmsCabinet2StockService.findById(TaskThread.stockID);
            // 锁定库存
            wmsCabinet2StockService.updateState(byId.getId().toString(), "2");
            Map<String, Object> map = new HashMap<>();
            map.put("OrderId", TaskThread._runningId);
            String turnoverNumber = wmsCabinet2TurnoverService.findTurnoverNumber(TaskThread.turnoverID);
            map.put("HUNumber", turnoverNumber);
            map.put("LocationId", byId.getLocaNumber());

            // todo 跳过入库判断
            Declension inbound = wmsApiService.Inbound(map);
            if (Objects.equals("1", inbound.getResult())) {
                TaskThread.isCancelTask = true;
//                TaskThread.wsr = nullStock;
                return ResponseData.success("空周转箱入库中~");
            } else {
                return ResponseData.error("当前无法进行入库~" + inbound.getMessage());
            }
        } else {
            return ResponseData.error("暂无空库位");
        }
//        return ResponseData.error("当前无任务~");
    }

    // 备品备件信息
    @ResponseBody
    @RequestMapping(value = "/materialInfo")
    @ApiOperation(value = "备品备件物料信息")
    public ResponseData materialInfo() {
        // 查出所有的备品备件信息
        List<WmsMaterialSparePartsResult> wm = wmsMaterialSparePartsService.findAll();
        return ResponseData.success(wm);
    }


    // 点击确认按钮
    @ResponseBody
    @RequestMapping(value = "/affirmRepairTask")
    @ApiOperation(value = "备品备件补货完成,点击确认进行入库")
    @Transactional
    public ResponseData affirmRepairTask(WmsMaterialSparePartsParam wms) {
        // 更新 周转箱的状态为有货
        wmsCabinet2TurnoverService.updateTurnState(TaskThread.turnoverID, "1");

        // 更新周转箱绑定的货物信息
        WmsCabinet2TurnoverBindParam turnoverBindParam = new WmsCabinet2TurnoverBindParam();
        turnoverBindParam.setTurnoverId(TaskThread.turnoverID); // 周装箱id
        turnoverBindParam.setMaterialId(wms.getId().toString()); // 物料信息id
        turnoverBindParam.setMaterialTypeId(wms.getMaterialTypeId()); // 物料类型id
        turnoverBindParam.setMaterialType(wms.getMaterialType()); // 物料类型
        turnoverBindParam.setMaterialName(wms.getMaterialName()); // 物料名称
        turnoverBindParam.setMaterialSku(wms.getMaterialSku()); // 物料SKU
        turnoverBindParam.setMBatch(wms.getMBatch()); // 批次
        turnoverBindParam.setMUnit(wms.getMUnit()); // 单位
        turnoverBindParam.setMNumber(wms.getMNumber()); // 数量
        wmsCabinet2TurnoverBindService.add(turnoverBindParam);

        // 更新任务上绑定的物料信息
        WmsCabinet2ReplenishmentTaskParam byId = wmsCabinet2ReplenishmentTaskService.findById(TaskThread._runningId);
        byId.setMaterialTypeId(wms.getMaterialTypeId()); // 物料类型id
        byId.setMaterialId(wms.getId().toString()); // 物料信息id
        byId.setMBatch(wms.getMBatch());
        byId.setMNumber(wms.getMNumber());
        wmsCabinet2ReplenishmentTaskService.update(byId);

//        WmsCabinet2StockParam byIdStock = wmsCabinet2StockService.findById(TaskThread.stockID);
//
//        // 查找空库位并锁定
//        WmsCabinet2StockResult nullStock = wmsCabinet2StockService.findNullStock();
//        if (nullStock != null) {
//            String id = nullStock.getId().toString();
//            wmsCabinet2StockService.updateState(TaskThread.stockID, "2");
//            // 空库存的id
////            TaskThread.stockID = id;
////            TaskThread.wsr = nullStock;
//            TaskThread.startInMaterial = true;
//            // 开始
//
//            return ResponseData.success();
//        }
        wmsCabinet2StockService.updateState(TaskThread.stockID, "2");
        TaskThread.startInMaterial = true;
        return ResponseData.success();
//        return ResponseData.error("空库为不足~");
    }

    // 点击确认按钮
    @ResponseBody
    @RequestMapping(value = "/affirmPurchaseTask")
    @ApiOperation(value = "采购订单输入收货数量,点击确认进行入库")
    @Transactional
    public ResponseData affirmPurchaseTask(String mNumber) {

        WmsPurchaseOrderInfo wmsPurchaseOrderInfo= wmsPurchaseOrderInfoService.getById(TaskThread._runningId);

        WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam=new WmsPurchaseOrderInfoParam();
        wmsPurchaseOrderInfoParam.setId(wmsPurchaseOrderInfo.getId());
        Integer receiveQty= Integer.valueOf(wmsPurchaseOrderInfo.getReceivedQuantity());
        Integer m=Integer.valueOf(mNumber);
        Integer totalNumber=Integer.valueOf(wmsPurchaseOrderInfo.getmNumber());

        wmsPurchaseOrderInfoParam.setReceivedQuantity(String.valueOf(receiveQty+m));
        wmsPurchaseOrderInfoParam.setAcceptableQuantity(String.valueOf(totalNumber-receiveQty-m));

        WmsWarehousePurchaseStorageTask wmsWarehousePurchaseStorageTask=new WmsWarehousePurchaseStorageTask();
        wmsWarehousePurchaseStorageTask.setPurchaseId(String.valueOf(wmsPurchaseOrderInfo.getId()));
        wmsWarehousePurchaseStorageTask.setTaskNumber(mapCodeGenerator.get(CodeProviderEnum.purchaseWarehousingCode.getProvider()).createCode(null));
        wmsWarehousePurchaseStorageTask.setOperationTime(new Date());
        wmsWarehousePurchaseStorageTask.setTaskState("3");
        WmsUser wmsUser = wmsUserService.getById(TaskThread.userId);
        wmsWarehousePurchaseStorageTask.setOperator(wmsUser.getSerialNumber());
        wmsWarehousePurchaseStorageTask.setGroupdQuantity(mNumber);
        wmsWarehousePurchaseStorageTask.setTotalQuantity(wmsPurchaseOrderInfo.getmNumber());
        wmsWarehousePurchaseStorageTask.setAcceptableQuantity(wmsPurchaseOrderInfoParam.getAcceptableQuantity());
        wmsWarehousePurchaseStorageTask.setReceivedQuantity(wmsPurchaseOrderInfoParam.getReceivedQuantity());
        wmsWarehousePurchaseStorageTaskService.save(wmsWarehousePurchaseStorageTask);


        if (totalNumber==receiveQty+m){
            wmsPurchaseOrderInfoParam.setArrivalState("3");
//          接口上报
            PushErpData(wmsPurchaseOrderInfo.getId());
        }
        wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParam);
        // 更新 周转箱的状态为有货
        wmsCabinet2TurnoverService.updateTurnState(TaskThread.turnoverID, "1");

        // 更新周转箱绑定的货物信息
        WmsCabinet2TurnoverBindParam turnoverBindParam = new WmsCabinet2TurnoverBindParam();
        turnoverBindParam.setTurnoverId(TaskThread.turnoverID); // 周装箱id
        turnoverBindParam.setMaterialId(wmsPurchaseOrderInfo.getMaterialTypeId()); // 物料信息id
        turnoverBindParam.setMaterialTypeId(wmsPurchaseOrderInfo.getMaterialTypeId()); // 物料类型id
        turnoverBindParam.setMaterialType(wmsPurchaseOrderInfo.getMaterialType()); // 物料类型
        turnoverBindParam.setMaterialName(wmsPurchaseOrderInfo.getMaterialName()); // 物料名称
        turnoverBindParam.setMaterialSku(wmsPurchaseOrderInfo.getMaterialSku()); // 物料SKU
        turnoverBindParam.setMBatch(wmsPurchaseOrderInfo.getDiBatchNo()); // 批次
        turnoverBindParam.setMUnit(wmsPurchaseOrderInfo.getmUnit()); // 单位
        turnoverBindParam.setMNumber(mNumber); // 数量
        wmsCabinet2TurnoverBindService.add(turnoverBindParam);

        wmsCabinet2StockService.updateState(TaskThread.stockID, "2");
        TaskThread.startInMaterial = true;
        return ResponseData.success();
    }

    public void PushErpData(Long id){
        try{
            WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getById(id);
            WmsWarehousePurchaseorderDeliveryResult wmsWarehouse= wmsWarehousePurchaseorderDeliveryService.selectPurDocNo(wmsPurchaseOrderInfo.getPurDocNo(),wmsPurchaseOrderInfo.getItemNo(),wmsPurchaseOrderInfo.getPurchasereqno());
            System.out.println(wmsPurchaseOrderInfo.getClient());
            System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFQQQ");

            String storageLocation="R06B901";
            if (wmsPurchaseOrderInfo.getMaterialType().equals("GJ")){
                storageLocation="R06GZ11";
            }
            System.out.println(storageLocation);
            if (wmsPurchaseOrderInfo.getClient().equals("900"))
            {
                System.out.println("huhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuuhuhu");
                if (wmsWarehouse!=null){
                    System.out.println("ppppppppppp66666666666ppppppppppp00000000000ppppppppppp");
                    Req req = new Req();
                    Req.MsgHeader msgHeader= new Req.MsgHeader();
                    Req.MsgBody msgBody= new Req.MsgBody();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    Date date = new Date();
                    String dateString = sdf.format(date);
                    //                System.out.println("--------------------dateString  :---------------------------- " + dateString);
                    ExecuteBindQSService executeBindQSService = new ExecuteBindQSService();
                    msgHeader.setMessageID(String.valueOf(UUID.randomUUID()));
                    msgHeader.setInterfaceID("TWMS-ERP-001");
                    msgHeader.setSender("JF_TWMS");
                    msgHeader.setReceiver("JF_ERP");
                    msgBody.setCode(wmsWarehouse.getCode());
                    msgBody.setLineCode(wmsWarehouse.getLinecode());
                    msgBody.setQty(wmsWarehouse.getQty());
                    msgBody.setMtlno(wmsWarehouse.getMtlno());
//                    msgBody.setStorageLocation("R06B861");//TODO
                    msgBody.setStorageLocation(storageLocation);
//                    msgBody.setGetDate(sdf.format(wmsWarehouse.getCreateTime()));
                    msgBody.setGetDate(dateString);
                    req.setMsgHeader(msgHeader);
                    req.getMsgBody().add(msgBody);
                    logger.info("Begin Send ERP Data:"+msgBody);
                    Resp resp = executeBindQSService.getExecuteBindQSPort().execute(req);
                }
            }else{
                if (wmsPurchaseOrderInfo!=null){
                    System.out.println("ppppppppppp66666666666ppppppppppp11111111111ppppppppppp");
                    //            正式环境IP:10.7.62.76
                    //            测试环境IP:10.6.201.184
                    // 新建客户端 正式环境
                    SoapClient client = SoapClient.create("http://10.7.62.76:8011/ERP/UWMS/UWMS2ERP_SyncInvNoGuardianStorageLocation/ProxyServices/SyncInvNoGuardianStorageLocationPS?wsdl")
                            // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                            .setMethod("ser:rePurInStorageMT", "http://service.noGuardianStor.inv.mm.erp.faw_qm.com/");
                    // 设置参数，此处自动添加方法的前缀：web
                    try {
                        SOAPElement msgHeader = client.getMethodEle().addChildElement("msgHeader");
                        msgHeader.addChildElement("comments").setValue("");
                        msgHeader.addChildElement("count").setValue("");
                        msgHeader.addChildElement("interfaceID").setValue("TWMS-ERP-002");
                        msgHeader.addChildElement("messageID").setValue(String.valueOf(UUID.randomUUID()));
                        msgHeader.addChildElement("receiver").setValue("JF_ERP");
                        msgHeader.addChildElement("sender").setValue("JF_TWMS");
                        msgHeader.addChildElement("transID").setValue("");
                        SOAPElement msgBody = client.getMethodEle().addChildElement("msgBody");
                        SOAPElement invRePurInStorageMTItems=msgBody.addChildElement("invRePurInStorageMTItems");

                        //            凭证日期
                        Date date=new Date();
                        SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                        String dateString=formatter.format(date);
//                        dateString="20220404";

                        String docDate=formatter.format(wmsPurchaseOrderInfo.getDocDate());

//                        凭证日期
                        invRePurInStorageMTItems.addChildElement("docDate").setValue(docDate);
//                        收货者
                        invRePurInStorageMTItems.addChildElement("goodsRecipient").setValue("TWMS");
//                        移动类型
                        invRePurInStorageMTItems.addChildElement("moveMentType").setValue("101");
//                        物料SKU
                        invRePurInStorageMTItems.addChildElement("mtlNO").setValue(wmsPurchaseOrderInfo.getMaterialSku());
//                        记账日期
                        invRePurInStorageMTItems.addChildElement("postDate").setValue(dateString);
//                        采购订单行项号
                        invRePurInStorageMTItems.addChildElement("purDocItem").setValue(wmsPurchaseOrderInfo.getPurdocitemno());
//                        采购订单号
                        invRePurInStorageMTItems.addChildElement("purDocNO").setValue(wmsPurchaseOrderInfo.getPurDocNo());
//                        数量
                        invRePurInStorageMTItems.addChildElement("quantity").setValue(wmsPurchaseOrderInfo.getmNumber());
//                        外部单号
                        invRePurInStorageMTItems.addChildElement("refDocNO").setValue(String.valueOf(UUID.randomUUID()));
//                        外部行号
                        invRePurInStorageMTItems.addChildElement("refItemNO").setValue(wmsPurchaseOrderInfo.getPurdocitemno());
//                        存储地点
                        invRePurInStorageMTItems.addChildElement("storageLocation").setValue(wmsPurchaseOrderInfo.getStorelocation());
                    } catch (SOAPException e) {
                        System.out.println("FFFFFFFFFPPPPPPPPPPPPPPPPPPPPPPPPP");
                        logger.info(e.getMessage());
                    }
                    String str= client.send(true);
                    logger.info("/tool_apply_commit_over upload ERP interface"+str);
                    System.out.println("/tool_apply_commit_over upload ERP interface"+str);
                    System.out.println("666666666666666666----InErpInErpInErp----2222222222222222");

                }
            }
        }catch(Exception exception){
            System.out.println("fffffffffffffffffffffffffffffffff");
            logger.info(exception.getMessage());
        }
    }

    /*************************************************************************************************
     * @author ll
     * @Date 2021年11月17日10:18:03
     * @Des 盘点流程
     */

    // 1.开始盘点(创建所有库位的盘点任务)
    @ResponseBody
    @RequestMapping(value = "/StartCounting")
    @ApiOperation(value = "开始盘点")
    public ResponseData StartCounting() {
        if (!TaskThread.isLogin) {
            return ResponseData.error("请先登录后在进行操作!");
        }
        List<WmsCabinet2CheckTask> wmsCabinet2CheckTask=wmsCabinet2CheckTaskService.
                list(new QueryWrapper<WmsCabinet2CheckTask>().ne("task_state", 1));
//        if (wmsCabinet2CheckTask.isEmpty()) {

        if (!wmsCabinet2CheckTask.isEmpty()){
            wmsCabinet2CheckTaskService.deleteVue();
        }
//             生成任务编号
            String taskNumber = "SC" + getDate();
            inventoryTaskNumber = taskNumber; // 任务编号
            List<WmsCabinet2CheckTaskParam> list = new ArrayList<>();
            // 查询所有的库位信息
//            List<WmsCabinet2StockResult> stockMsg = wmsCabinet2StockService.findAll();
            List<WmsCabinet2StockResult> stockMsg = wmsCabinet2StockService.findAll();
            for (WmsCabinet2StockResult wms : stockMsg) {
                WmsCabinet2CheckTaskParam w = new WmsCabinet2CheckTaskParam();
                w.setTaskNumber(taskNumber); // 任务编号
                w.setStockId(wms.getId().toString()); // 库位信息id
                w.setLocaNumber(wms.getLocaNumber()); // 库位编码
                w.setTurnoverId(wms.getTurnoverId()); // 周转箱id
                w.setOperator(TaskThread.userId); // 操作id
                w.setCreateTime(new Date()); // 操作时间
                list.add(w);
            }
            if (list.size()==0){
                return ResponseData.error("库存为空，无法盘点");
            }
            wmsCabinet2Check.addAll(list);
            // 开启线程任务 来监听出库入库操作
            TaskThread.taskState = true;
            return ResponseData.success(taskNumber);
//        }
//        return ResponseData.error("有之前未盘点完的任务!");
    }

    // 2.盘点详情界面
    @ResponseBody
    @RequestMapping(value = "/StartCountingInfo")
    @ApiOperation(value = "盘点详情界面")
    public LayuiPageInfo StartCountingInfo(WmsCabinet2CheckTaskParam wms) {
        return wmsCabinet2CheckTaskService.findPageBySpec(wms);
    }

    // 盘点按钮
    @ResponseBody
    @RequestMapping(value = "/Inventory")
    @ApiOperation(value = "盘点按钮(点击发送请求)")
    public ResponseData Inventory(@ApiParam("选中的一条盘点信息") WmsCabinet2CheckTaskParam wms) {
        // 开启监听
        TaskThread.taskType = 3;
        // 如果存在盘点中的任务 则提示它先将盘点中的任务做完
        if (!Objects.equals("", TaskThread._runningId)) {
            return ResponseData.error("当前有未盘点完成的任务,请盘点完后在进行~");
        }
        // 锁定库存信息
        wmsCabinet2StockService.updateState(wms.getStockId(), "2");
        // 判断 是否可以出库
        Map<String, Object> map = new HashMap<>();
        map.put("OrderId", wms.getId().toString());
        String turnoverNumber = wmsCabinet2TurnoverService.findTurnoverNumber(wms.getTurnoverId());
        map.put("HUNumber", turnoverNumber);
        map.put("LocationId", wms.getLocaNumber());
        TaskThread._runningId = wms.getId().toString(); // 任务id
        TaskThread.stockID = wms.getStockId(); // 库位id
        TaskThread.turnoverID = wms.getTurnoverId(); // 周转箱id

        // todo 跳过出库判断
        Declension outbound = wmsApiService.Outbound(map);
        if (Objects.equals("1", outbound.getResult())) {
            wms.setTaskState("1"); // 任务状态 开始出库
            wms.setOperationTime(new Date());
            wmsCabinet2CheckTaskService.update(wms);
            return ResponseData.success("开始出库~");
        }
        return ResponseData.success("当前状态无法出库~");
    }

    // 完成按钮
    @ResponseBody
    @RequestMapping(value = "/InventoryFinish")
    @ApiOperation(value = "完成按钮(点击发送请求)")
    public ResponseData InventoryFinish(@ApiParam("选中的一条盘点信息") WmsCabinet2CheckTaskParam wms) {
        // 开启监听
        TaskThread.taskState = true;
        TaskThread.taskType = 3;

        //查询空库存
        WmsCabinet2StockResult nullStock = wmsCabinet2StockService.findNullStock(wms.getLocaNumber());
//        if (nullStock != null) {
            // 锁定库存
            wmsCabinet2StockService.updateState(nullStock.getId().toString(), "2");

            // 更新库存的信息(id)
            TaskThread.stockID = nullStock.getId().toString(); // 库位id
            TaskThread._runningId = wms.getId().toString(); // 任务id
            TaskThread.turnoverID = wms.getTurnoverId();
            // 判断是否可以入库
            Map<String, Object> map = new HashMap<>();
            map.put("OrderId", wms.getId().toString());
            String turnoverNumber = wmsCabinet2TurnoverService.findTurnoverNumber(wms.getTurnoverId());
            map.put("HUNumber", turnoverNumber);
            map.put("LocationId", wms.getLocaNumber());
            // todo 跳过入库判断
            Declension inbound = wmsApiService.Inbound(map);
            if (Objects.equals("1", inbound.getResult())) {
                // 更新任务状态为开始入库
                wmsCabinet2CheckTaskService.updateStateById(wms.getId().toString(), "3");
                return ResponseData.success();
            }
            return ResponseData.error("当前无法进行入库~" + inbound.getMessage());
//        }
//        return ResponseData.error("没有足够的空库位了~");
    }


    // 点击一条盘点信息 显示周转箱上绑定的货物信息
    @ResponseBody
    @RequestMapping(value = "/InventoryTurnoverMsg")
    @ApiOperation(value = "周转箱绑定的货物信息")
    public ResponseData InventoryTurnoverMsg(@ApiParam("周转箱的ID") String turnoverID) {
        WmsCabinet2TurnoverBindResult list = wmsCabinet2TurnoverBindService.findByTurnId(turnoverID);
        return ResponseData.success(list);
    }

    // 纪录盘点差异(修改)
    @ResponseBody
    @RequestMapping(value = "/updateMaterial")
    @ApiOperation(value = "修改周转箱绑定物料信息")
    public ResponseData updateMaterial(@ApiParam("物料信息") WmsCabinet2TurnoverBindParam newWms) {
        // 先查询出原来的信息 比较数量
        WmsCabinet2TurnoverBindResult oldWms = wmsCabinet2TurnoverBindService.findByTurnId(newWms.getTurnoverId());
        // 记录差异
        WmsCheckDifferenceRecordInfoParam wrp = new WmsCheckDifferenceRecordInfoParam();
        wrp.setCheckWarehouse("2"); // 盘点仓库
        wrp.setCheckTask(newWms.getBarcode()); // 盘点任务
        // todo 查找库位的id
//        WmsCabinet2StockResult stockMsg = wmsCabinet2StockService.findByTurnId(newWms.getTurnoverId());
//        wrp.setLocationInfo(stockMsg.getId().toString()); // 库位信息
        // 差异类型
        int number = Integer.parseInt(newWms.getMNumber()) - Integer.parseInt(oldWms.getMNumber());
        if (number > 0) {
            wrp.setDifferenceType("少");
            wrp.setDifferenceDescribe(String.valueOf(number));
        }
        if (number < 0) {
            wrp.setDifferenceType("多");
            wrp.setDifferenceDescribe(String.valueOf(number));
        }
        // 操作人
        wrp.setOperator(TaskThread.userId);
        // 操作时间
        wrp.setOperationTime(new Date());
        wmsCheckDifferenceRecordInfoService.add(wrp);

        // 更新周转箱绑定的信息
        wmsCabinet2TurnoverBindService.update(newWms);

        return ResponseData.success("修改成功");
    }

    // 记录盘点差异(删除)
    @ResponseBody
    @RequestMapping(value = "/deleteMaterial")
    @ApiOperation(value = "修改周转箱绑定物料信息")
    public ResponseData deleteMaterial(@ApiParam("物料信息") WmsCabinet2TurnoverBindParam newWms) {
        // 记录盘点差异
        WmsCheckDifferenceRecordInfoParam wrp = new WmsCheckDifferenceRecordInfoParam();
        wrp.setCheckWarehouse("2"); // 盘点仓库
        wrp.setCheckTask(newWms.getBarcode()); // 箱子编号
//        // 查找库位的id
//        WmsCabinet2StockResult stockMsg = wmsCabinet2StockService.findByTurnId(newWms.getTurnoverId());
//        wrp.setLocationInfo(stockMsg.getId().toString()); // 库位信息
        wrp.setDifferenceType("空");
        wrp.setDifferenceDescribe("周转箱为空");
        wrp.setOperator(TaskThread.userId);
        // 操作时间
        wrp.setOperationTime(new Date());
        wmsCheckDifferenceRecordInfoService.add(wrp);

        // 删除数据
        wmsCabinet2TurnoverBindService.delete(newWms);
        // 更新周转箱的状态为空闲
        wmsCabinet2TurnoverService.updateTurnState(newWms.getTurnoverId(), "0");
        return ResponseData.success("更新成功");

    }

    // 盘点完成按钮
    @ResponseBody
    @RequestMapping(value = "/AllInventoryFinish")
    @ApiOperation(value = "完成按钮(点击发送请求)")
    public ResponseData AllInventoryFinish(@ApiParam("任务编号") String taskNumber) {
        if (Objects.equals("", taskNumber) || Objects.equals(null, taskNumber)) {
            taskNumber = inventoryTaskNumber;
        }
        Integer i = wmsCabinet2CheckTaskService.isFinishTask(taskNumber); // 是否有未完成
        if (i > 0) {
            return ResponseData.error("当前有盘点任务未完成~");
        }
        // 修改任务的状态
        wmsCabinet2CheckTaskService.updateAllState(taskNumber, "5"); // 修改所有的状态
        inventoryTaskNumber = "";
        TaskThread.finishTask();
        return ResponseData.success("盘点完成");
    }

    // 执行中的任务状态: 领用任务 补货任务   盘点任务
    @ResponseBody
    @RequestMapping(value = "/taskInProgress")
    @ApiOperation(value = "执行中的任务状态")
    @CrossOrigin
    public ResponseData TaskInProgress() {
        if (TaskThread.taskType != 0) {
            if (TaskThread.taskType == 1) { // 领用
                WmsCabinet2UseTaskResult byId = wmsCabinet2UseTaskService.findById(TaskThread._runningId);
                return ResponseData.success(byId.getTaskState());
            }
            if (TaskThread.taskType == 2) { // 补货
                WmsCabinet2ReplenishmentTaskParam byId = wmsCabinet2ReplenishmentTaskService.findById(TaskThread._runningId);
                return ResponseData.success(byId.getTaskState());
            }
            if (TaskThread.taskType == 3) { // 盘点
                WmsCabinet2CheckTaskResult wms = wmsCabinet2CheckTaskService.findById(TaskThread._runningId);
                return ResponseData.success(wms.getTaskState());
            }
        }
        return ResponseData.error("无执行的任务");
    }


    // 采购订单执行，查询空箱
    @ResponseBody
    @RequestMapping("/checkStockMsgOfPurchase")
    @ApiOperation(value = "采购订单空箱查询(任务id)")
    public ResponseData checkStockMsgOfPurchase(String id) {
        // 1.查询库存中有空周转箱的数据 有:锁定库存并保存周转箱编号和库存的信息 无: 返回无库存信息
        WmsCabinet2StockResult wsr = wmsCabinet2StockService.findNullReturnStock();
        if (wsr != null) {
            System.out.println("=================="+id+"=================");
            // 锁定库存
//            wmsCabinet2StockService.updateState(wsr.getId().toString(), "2");
            // 执行任务
            TaskThread._runningId = id; // 任务id
            TaskThread.stockID = wsr.getId().toString(); // 库位id
            TaskThread.turnoverID = wsr.getTurnoverId(); // 周转箱id
            TaskThread.taskState = true; // 有任务
            TaskThread.taskType = 4; // 任务类型，采购订单出空箱
            // 根据周转箱id 查询出周转箱的编号
            String turnoverNumber = wmsCabinet2TurnoverService.findTurnoverNumber(wsr.getTurnoverId());

            return ResponseData.success(turnoverNumber);
        }
        wmsWarehousePurchaseStorageTaskService.updateStateById(TaskThread._runningId, "5");
        return ResponseData.error("空库为不足~");
    }


    /**
     * 判断是否登入 并且是否有任务在执行
     */
    public boolean judgeInfo() {
        if (TaskThread.taskState == false && TaskThread.isLogin == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 封装权限
     */
    public static List tsUserInfo(WmsUserResult wmsUserResult) {
        List list = new ArrayList();
        if (Objects.equals("A", wmsUserResult.getUserType())) {
            list.add(0);
            list.add(1);
            list.add(2);
            list.add(3);
        } else {
            list.add(0);
        }
        return list;
    }

    /* 日期格式转换 */

    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return simpleDateFormat.format(new Date());
    }

}
