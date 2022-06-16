package cn.stylefeng.guns.modular.onetypeservice.service;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.WebApi.Entity.runBatch;
import cn.stylefeng.guns.modular.WebApi.WmsApiService;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.model.params.WmsMaterialSparePartsParam;
import cn.stylefeng.guns.modular.base.materialspareparts.model.result.WmsMaterialSparePartsResult;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.materialtool.model.params.WmsMaterialToolParam;
import cn.stylefeng.guns.modular.base.materialtool.service.WmsMaterialToolService;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.base.packageInfo.model.params.WmsPackinfoParam;
import cn.stylefeng.guns.modular.base.packageInfo.model.result.WmsPackinfoResult;
import cn.stylefeng.guns.modular.base.packageInfo.service.WmsPackinfoService;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsPrintInfo;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsPrintInfoParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsPrintInfoResult;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsPrintInfoService;
import cn.stylefeng.guns.modular.onetypeservice.enums.ApplyType;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodeProviderEnum;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.onetypeservice.request.InBoundParam;
import cn.stylefeng.guns.modular.onetypeservice.response.PurchaseStorageResponse;
import cn.stylefeng.guns.modular.onetypeservice.response.ToolClaimModel;
import cn.stylefeng.guns.modular.onetypeservice.response.WarehouseTurnoverInfo;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.model.params.WmsToolUseParam;
import cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.guns.modular.utils.WebSocket.WebSocket;
import cn.stylefeng.guns.modular.warehousemanage.entity.*;
import cn.stylefeng.guns.modular.warehousemanage.model.params.*;
import cn.stylefeng.guns.modular.warehousemanage.model.result.*;
import cn.stylefeng.guns.modular.warehousemanage.service.*;
import cn.stylefeng.guns.print.ZplPrinter;
import cn.stylefeng.guns.sys.modular.consts.service.SysConfigService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.tools.Tool;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static cn.stylefeng.guns.print.ZplPrinterTest.printFawTroue;

/**
 * Created by li wen ya on 2021/11/12
 */
@Component
@Slf4j
public class WarehouseService {

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    ExecutorService cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), namedThreadFactory);

    // 打印机
    static ZplPrinter p = new ZplPrinter("\\\\192.168.26.96\\ZDesigner ZD888-203dpi ZPL");

    // 备品备件补货任务缓存
    public static Map<String, Object> replenishmentMap = new ConcurrentHashMap<>();

    @Autowired
    private Map<String, Code> mapCodeGenerator;

    @Autowired
    private WmsWarehousePurchaseOrderReturnService wmsWarehousePurchaseOrderReturnService;

    @Autowired
    private WmsWarehousePurchaseStorageTaskService wmsWarehousePurchaseStorageTaskService;

    @Autowired
    private WmsWarehouseReplenishmentTaskService wmsWarehouseReplenishmentTaskService;

    @Autowired
    private WmsWarehouseStockService wmsWarehouseStockService;

    @Autowired
    private WmsWarehouseTaskInService wmsWarehouseTaskInService;

    @Autowired
    private WmsWarehouseTaskOutService wmsWarehouseTaskOutService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WmsWarehouseTurnoverService wmsWarehouseTurnoverService;

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    @Autowired
    private WmsMaterialToolService wmsMaterialToolService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private WmsSortingTaskService wmsSortingTaskService;

    @Autowired
    private WmsPrintInfoService wmsPrintInfoService;

    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    @Autowired
    private WmsToolUseService wmsToolUseService;

    @Autowired
    private WmsApiService wmsApiService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private WmsMaterialSparePartsService wmsMaterialSparePartsService;

    @Autowired
    private WmsPackinfoService wmsPackinfoService;



    // 领用 - 领用任务列表
    @SuppressWarnings("All")
    public LayuiPageInfo claimList(String serialNumber) {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage<WmsWarehouseToolUseTask> page = wmsWarehouseToolUseTaskService.page(pageContext, new QueryWrapper<WmsWarehouseToolUseTask>().eq("operator", serialNumber).eq("task_state", StateEnum.ZERO.getState()));
        return LayuiPageFactory.createPageInfo(page);
    }

    public LayuiPageInfo claimListA(String serialNumber) {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage<WmsWarehouseToolUseTask> page = wmsWarehouseToolUseTaskService.page(pageContext, new QueryWrapper<WmsWarehouseToolUseTask>().eq("operator", serialNumber).ne("task_state", StateEnum.THREE.getState()));
        return LayuiPageFactory.createPageInfo(page);
    }

    // 领用 - 领用申请列表
    @SuppressWarnings("All")
    public LayuiPageInfo claimUseApply(String serialNumber) {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage<WmsUseApply> page = wmsUseApplyService.page(pageContext, new QueryWrapper<WmsUseApply>().eq("operator", serialNumber).eq("process_type", ApplyType.A.getType()));
        return LayuiPageFactory.createPageInfo(page);
    }

    // 领用 - 确认按钮
    public ResponseData claimConform(String serialNumber, String taskNumber) {
        // 1.获取选中的任务内容
        WmsWarehouseToolUseTask toolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number", taskNumber));
        if (!Objects.equals(serialNumber, toolUseTask.getOperator())) {
            return ResponseData.error("此任务不是当前登录人员申请的无权操作");
        }
        // 2.查找符合条件的库位数据
//        WmsWarehouseStock wmsWarehouseStocks = wmsWarehouseStockService.getOne(new QueryWrapper<WmsWarehouseStock>().eq("loca_state", StateEnum.ONE.getState()).eq("material_type_id",toolUseTask.getMaterialTypeId()).last("limit 1"));
        // old : 查询库存信息表 new: 查询周转箱绑定货物表
        log.info("Submitted material SKU{}", toolUseTask.getMaterialSku());
        WmsWarehouseTurnoverBindResult turnoverBindResult = wmsWarehouseTurnoverBindService.findBySKU(toolUseTask.getMaterialSku());
        if (turnoverBindResult == null) {
            return ResponseData.error("库中" + toolUseTask.getMaterialSku() + "的物料不足");
        }
        // 3.更新任务为 出库中
        toolUseTask.setOperationTime(new Date());
        toolUseTask.setTaskState(StateEnum.TWO.getState());// 0初始 1开始 2出库中
        WmsWarehouseToolUseTaskParam toolUseTaskParam = new WmsWarehouseToolUseTaskParam();
        ToolUtil.copyProperties(toolUseTask, toolUseTaskParam);
        wmsWarehouseToolUseTaskService.update(toolUseTaskParam);
        // 3.发送出库任务消息到WMS,
        String messageId = RandomStringUtils.randomNumeric(12); // 消息识别id
        String turnoverId = turnoverBindResult.getTurnoverId();
        WmsWarehouseTaskOut taskOut = replenishmentCreateOutTask(toolUseTask, messageId, turnoverId, turnoverBindResult.getMBatch()); // 创建出库任务: 参数:领用任务,消息识别id 周转箱信息
        cachedThreadPool.execute(new SendTOWcs(messageId, StateEnum.ZERO));
        return ResponseData.success();
    }

    // 领用 - wms出库完成 回调
    public void claimCallbackComplete(String messageId, String barCode) {
        WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.getOne(new QueryWrapper<WmsWarehouseTaskOut>().eq("message_id", messageId));
        WmsWarehouseTurnoverResult turnoverResult = wmsWarehouseTurnoverService.findByBarCode(barCode);
        WmsWarehouseStockResult stock = wmsWarehouseStockService.findByTurnoverId(turnoverResult.getId().toString());
        wmsWarehouseTaskOut.setLocaNumber(""); // 出库仓库编号
        if (stock != null) {
            wmsWarehouseTaskOut.setLocaNumber(stock.getLocaNumber());
        }
        WmsWarehouseTurnoverResult wm = wmsWarehouseTurnoverService.findByBarCode(barCode);
        wmsWarehouseTaskOut.setTurnoverNumber(wm.getTurnoverNumber()); // 出库周转箱编号
        wmsWarehouseTaskOut.setTurnoverType(wm.getTurnoverType()); // 出库周转箱类型
        wmsWarehouseTaskOut.setTurnoverMouthQuality(wm.getTurnoverMouthQuantity()); // 格口数量
        wmsWarehouseTaskOut.setBarcode(barCode); // 出库周转箱条码
        wmsWarehouseTaskOut.setResTag(StateEnum.TWO.getState()); // 结果标记
        wmsWarehouseTaskOut.setResStatus(StateEnum.TWO.getState()); // 结果状态
        WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam = new WmsWarehouseTaskOutParam();
        ToolUtil.copyProperties(wmsWarehouseTaskOut, wmsWarehouseTaskOutParam);
        wmsWarehouseTaskOutService.update(wmsWarehouseTaskOutParam); // 更新出库任务标识

        WmsWarehouseStock wmsWarehouseStock = new WmsWarehouseStock();
        ToolUtil.copyProperties(stock, wmsWarehouseStock);

        WmsWarehouseTurnover turnover = new WmsWarehouseTurnover();
        ToolUtil.copyProperties(turnoverResult, turnover);

        // 判断出库类型(A 工具领用 B 补货出库 C 出库)
        if (stock != null) {
            if (ApplyType.A.getType().equals(wmsWarehouseTaskOut.getOrderType())) {// 工具领用出库
                //根据任务编号获取领用任务
                WmsWarehouseToolUseTask wmsWarehouseToolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number", wmsWarehouseTaskOut.getTaskMg()));
                if (StateEnum.ONE.getState().equals(wmsWarehouseToolUseTask.getSortingType())) {// 1.判断分拣任务是否自动分拣
                    WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
                    wmsWarehouseTurnoverBindParam.setTurnoverId(String.valueOf(turnover.getId()));
                    // 机械手分拣的周转箱没分格口
                    WmsWarehouseTurnoverBindResult wmsWarehouseTurnoverBindResult = wmsWarehouseTurnoverBindService.findByTurnoverId(wmsWarehouseTurnoverBindParam);
                    // 创建自动分拣任务 分拣数量默认为 1
                    WmsSortingTask sortingTask = createSortingTask(turnover, wmsWarehouseToolUseTask, wmsWarehouseTurnoverBindResult);

                    // 发送分拣任务
                    WmsSortingTaskResult result = new WmsSortingTaskResult();
                    ToolUtil.copyProperties(sortingTask,result);
                    WmsMaterialTypeParam param = new WmsMaterialTypeParam();
                    param.setMaterialSku(wmsWarehouseToolUseTask.getMaterialSku());
                    WmsMaterialTypeResult byMaterialSku = wmsMaterialTypeService.findByMaterialSku(param);
                    WmsPackinfo packInfo =  wmsPackinfoService.findByMaterialTypeId(byMaterialSku.getId().toString());
                    runBatch runBatchRe = wmsApiService.getRunBatchRe(result, packInfo);


                }
                updateWarehouseToolUseTask(wmsWarehouseStock, wmsWarehouseToolUseTask); // 2.更新任务信息
                updateWarehouseStock(wmsWarehouseStock);// 4.更新库位信息为空
                updateTurnoverToEmpty(turnover);// 5.更新周转箱信息
            } else if (ApplyType.B.getType().equals(wmsWarehouseTaskOut.getOrderType())) {// 补货出库
                WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask = wmsWarehouseReplenishmentTaskService.getOne(new QueryWrapper<WmsWarehouseReplenishmentTask>().eq("task_number", wmsWarehouseTaskOut.getTaskMg()));
                if (Objects.equals("1", wmsWarehouseReplenishmentTask.getSortingType())) {

                    //自动分拣 创建分拣任务
                    WmsWarehouseTurnoverBindParam params = new WmsWarehouseTurnoverBindParam();
                    ToolUtil.copyProperties(turnoverResult, params);
                    WmsWarehouseTurnoverBindResult turnoverBindInfo = wmsWarehouseTurnoverBindService.findByTurnoverId(params);
                    WmsSortingTask sortingTask = createSpareSortingTask(turnover, turnoverBindInfo, wmsWarehouseReplenishmentTask);

                    // 发送分拣任务
                    WmsSortingTaskResult result = new WmsSortingTaskResult();
                    ToolUtil.copyProperties(sortingTask,result);
                    WmsMaterialTypeParam param = new WmsMaterialTypeParam();
                    param.setMaterialSku(wmsWarehouseReplenishmentTask.getMaterialSku());
                    WmsMaterialTypeResult byMaterialSku = wmsMaterialTypeService.findByMaterialSku(param);
                    WmsPackinfo packInfo =  wmsPackinfoService.findByMaterialTypeId(byMaterialSku.getId().toString());
                    runBatch runBatchRe = wmsApiService.getRunBatchRe(result, packInfo);


                }
//                // 1.更新任务信息
//                wmsWarehouseReplenishmentTask.setTaskState(StateEnum.THREE.getState());// 出库任务 0初始 1开始 2出库中 3完成
//                WmsWarehouseReplenishmentTaskParam replenishmentTaskParam = new WmsWarehouseReplenishmentTaskParam();
//                ToolUtil.copyProperties(wmsWarehouseReplenishmentTask, replenishmentTaskParam);
//                wmsWarehouseReplenishmentTaskService.update(replenishmentTaskParam);
                updateWarehouseStock(wmsWarehouseStock);// 3.更新库位信息为空1
                updateTurnoverToEmpty(turnover);// 4.更新周转箱信息

            }
        }

        if (ApplyType.C.getType().equals(wmsWarehouseTaskOut.getOrderType())) {// 手动出库
            if (stock != null) {
                updateWarehouseStock(wmsWarehouseStock);// 2.更新库位信息为空
            }
            updateTurnoverToEmpty(turnover);// 3.更新周转箱信息
        }
    }

    // 更新周转箱库位为空信息
    private void updateTurnoverToEmpty(WmsWarehouseTurnover turnover) {
        turnover.setStorageStatus(StateEnum.TWO.getState());// 存放状态（1库内/2库外）
        turnover.setLocaRow("");// 存放-排
        turnover.setLocaCol("");// 存放-列
        turnover.setLocaLayer("");// 存放-层
        wmsWarehouseTurnoverService.update(turnover, new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", turnover.getTurnoverNumber()));
    }

    // 指定立库信息为空
    private void updateWarehouseStock(WmsWarehouseStock wmsWarehouseStock) {
        wmsWarehouseStock.setLocaState(StateEnum.ZERO.getState());// 库位状态（0空闲/1有货/2锁定）
        wmsWarehouseStock.setTurnoverId("");// 周转箱信息ID
        WmsWarehouseStockParam stockParam = new WmsWarehouseStockParam();
        ToolUtil.copyProperties(wmsWarehouseStock, stockParam);
        wmsWarehouseStockService.update(stockParam);
    }

    // 领用 - 更新工具领用任务
    private void updateWarehouseToolUseTask(WmsWarehouseStock wmsWarehouseStock, WmsWarehouseToolUseTask wmsWarehouseToolUseTask) {
        wmsWarehouseToolUseTask.setTaskState(StateEnum.TWO.getState());// 出库任务 0初始 1开始 2出库中 3完成
        wmsWarehouseToolUseTask.setInterfaceState(StateEnum.ZERO.getState());// 接口状态 0.初始；1.调用
        wmsWarehouseToolUseTask.setStockId(String.valueOf(wmsWarehouseStock.getId()));//库存信息ID
        wmsWarehouseToolUseTask.setLocaNumber(wmsWarehouseStock.getLocaNumber());//库位编号
        wmsWarehouseToolUseTask.setTurnoverId(wmsWarehouseStock.getTurnoverId());//周转箱信息ID
        WmsWarehouseToolUseTaskParam toolUseTaskParam = new WmsWarehouseToolUseTaskParam();
        ToolUtil.copyProperties(wmsWarehouseToolUseTask, toolUseTaskParam);
        wmsWarehouseToolUseTaskService.update(toolUseTaskParam);
    }

    // 领用 - 创建机器人分拣任务
    private WmsSortingTask createSortingTask(WmsWarehouseTurnover turnover, WmsWarehouseToolUseTask wmsWarehouseToolUseTask, WmsWarehouseTurnoverBindResult wmsWarehouseTurnoverBind) {
        WmsSortingTask wmsSortingTask = new WmsSortingTask();
        String taskNumber = "tool-" + wmsWarehouseToolUseTask.getTaskNumber() + "-" + RandomStringUtils.randomNumeric(12);
        wmsSortingTask.setTaskNumber(taskNumber);// 任务编号
        wmsSortingTask.setTurnoverType(turnover.getTurnoverType());// 周转箱类型(A 小 B 中 C 大 )
        wmsSortingTask.setTurnoverNumber(turnover.getTurnoverNumber());// 周转箱编号
        wmsSortingTask.setBarcode(turnover.getBarcode());// 周转箱条码
        wmsSortingTask.setLatticeCode(wmsWarehouseTurnoverBind.getLatticeCode());// 分拣格口（编号）
        wmsSortingTask.setSortingNum(StateEnum.ONE.getState());// 分拣数量
        wmsSortingTask.setSortingMaterialType(wmsWarehouseTurnoverBind.getMaterialSku());// 分拣类型
        wmsSortingTask.setTaskState(StateEnum.ZERO.getState());// 任务状态（0初始 1开始分拣 2分拣完成 3结束）
        wmsSortingTask.setDataTime(new Date());// 数据时间
        wmsSortingTaskService.save(wmsSortingTask);

        // 更新工具领用任务
        wmsWarehouseToolUseTask.setSortingTask(taskNumber);//分拣任务 (机械手分拣ID)
        WmsWarehouseToolUseTaskParam wm = new WmsWarehouseToolUseTaskParam();
        ToolUtil.copyProperties(wmsWarehouseToolUseTask, wm);
        wmsWarehouseToolUseTaskService.update(wm);
        return wmsSortingTask;
    }

    // 备件补货出库分拣任务
    private WmsSortingTask createSpareSortingTask(WmsWarehouseTurnover turnover, WmsWarehouseTurnoverBindResult turnoverBind, WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask) {
        // 获取总数量
        int number = Integer.parseInt(wmsWarehouseReplenishmentTask.getmNumber());
        // 获取已分拣数量
        int sortingNum = Integer.parseInt(wmsWarehouseReplenishmentTask.getSortingNum());
        // 获取周转箱绑定的数量
        int have = Integer.parseInt(turnoverBind.getMNumber());
        // 剩余分拣数量
        int other = number - sortingNum;
        WmsSortingTask wmsSortingTask = new WmsSortingTask();
        String taskNumber = "spare-" + wmsWarehouseReplenishmentTask.getTaskNumber() + "-" + RandomStringUtils.randomNumeric(12);
        wmsSortingTask.setTaskNumber(taskNumber);// 任务编号
        wmsSortingTask.setTurnoverType(turnover.getTurnoverType());// 周转箱类型(A 小 B 中 C 大 )
        wmsSortingTask.setTurnoverNumber(turnover.getTurnoverNumber());// 周转箱编号
        wmsSortingTask.setBarcode(turnover.getBarcode());// 周转箱条码

        if (other > have) {
            wmsSortingTask.setSortingNum(Integer.toString(have));// 分拣数量
        } else {
            wmsSortingTask.setSortingNum(Integer.toString(other));// 分拣数量
        }

        wmsSortingTask.setSortingMaterialType(turnoverBind.getMaterialSku());// 分拣类型
        wmsSortingTask.setTaskState(StateEnum.ZERO.getState());// 任务状态（0初始 1开始分拣 2分拣完成 3结束）
        wmsSortingTask.setDataTime(new Date());// 数据时间
        wmsSortingTaskService.save(wmsSortingTask);
        return wmsSortingTask;
    }

    // 领用 - 创建出库任务
    private WmsWarehouseTaskOut replenishmentCreateOutTask(WmsWarehouseToolUseTask toolUseTask, String messageId, String turnoverId, String mBatch) {
        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(toolUseTask.getMaterialTypeId());
        WmsWarehouseTurnoverResult wmsWarehouseTurnoverResult = wmsWarehouseTurnoverService.findById(turnoverId);

        // 创建出库任务
        WmsWarehouseTaskOut taskOut = new WmsWarehouseTaskOut();
//        taskOut.setTurnoverType(wmsWarehouseTurnoverResult.getTurnoverType()); // 周转箱类型
//        taskOut.setTurnoverMouthQuality(wmsWarehouseTurnoverResult.getTurnoverMouthQuantity()); // 周转箱格口数量
        taskOut.setMessageId(messageId);// 消息识别ID
        taskOut.setOrderType(ApplyType.A.getType());// 订单类别(A工具领用 B补货出库 C出库)
        taskOut.setTaskMg(toolUseTask.getTaskNumber());// 任务信息
        taskOut.setGoodsType(ApplyType.A.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        taskOut.setMaterialTypeId(toolUseTask.getMaterialTypeId());// 物料类型ID
        taskOut.setMaterialSku(wmsMaterialType.getMaterialSku());// 物料SKU
        taskOut.setMaterialType(wmsMaterialType.getMaterialType());// 物料类型
        taskOut.setMaterialName(toolUseTask.getMaterialName());// 物料名称
        taskOut.setmBatch(mBatch); // 批次
        taskOut.setmNumber(StateEnum.ONE.getState());// 数量
        taskOut.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        taskOut.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        taskOut.setResTag(StateEnum.ZERO.getState());// 结果标记（0初始 1更新 2结束）
        taskOut.setResStatus(StateEnum.ZERO.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        if (wmsMaterialType.getSortType().equals("1")) {
            taskOut.setSortingInfo("B");
        } else {
            taskOut.setSortingInfo("A");
        }
        taskOut.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskOutService.save(taskOut);
        return taskOut;
    }

    // 手动出库操作
    public ResponseData outWarehouse(String materialTypeId) {
        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(materialTypeId);
        // 1.创建出库任务
        WmsWarehouseTaskOut taskOut = new WmsWarehouseTaskOut();
        String messageId = RandomStringUtils.randomNumeric(12);
        taskOut.setMessageId(messageId);// 消息识别ID
        taskOut.setOrderType(ApplyType.C.getType());// 订单类别(A工具领用 B补货出库 C出库)
        if (StateEnum.ONE.getState().equals(wmsMaterialType.getType())) {
            taskOut.setGoodsType(ApplyType.A.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        } else {
            taskOut.setGoodsType(ApplyType.B.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        }
        taskOut.setMaterialTypeId(String.valueOf(wmsMaterialType.getId()));// 物料类型ID
        taskOut.setMaterialSku(wmsMaterialType.getMaterialSku());// 物料SKU
        taskOut.setMaterialType(wmsMaterialType.getMaterialType());// 物料类型
        taskOut.setMaterialName(wmsMaterialType.getMaterialName());// 物料名称
        taskOut.setmNumber(StateEnum.ONE.getState());// 数量
        // 1.根据物料sku查询出周转箱绑定的货物信息(随机出一个周转箱?),出库任务绑定上周转箱的信息?
        taskOut.setSortingInfo(Objects.equals("0", wmsMaterialType.getSortType()) ? "A" : "B"); // 分拣类型 A 人工 B 自动
        WmsWarehouseTurnoverBindResult bySKU = wmsWarehouseTurnoverBindService.findBySKU(wmsMaterialType.getMaterialSku());
        WmsWarehouseTurnoverResult byId = wmsWarehouseTurnoverService.findById(bySKU.getTurnoverId());
        taskOut.setTurnoverType(byId.getTurnoverType()); // 周转箱类型
        taskOut.setBarcode(byId.getBarcode()); // 周转箱条码
        taskOut.setTurnoverNumber(byId.getTurnoverNumber()); // 周转箱编号
        taskOut.setTurnoverMouthQuality(byId.getTurnoverMouthQuantity()); // 格口数量
        taskOut.setmBatch(bySKU.getMBatch()); // 批次
        taskOut.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        taskOut.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        taskOut.setResTag(StateEnum.ZERO.getState());// 结果标记（0初始 1更新 2结束）
        taskOut.setResStatus(StateEnum.ZERO.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        taskOut.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskOutService.save(taskOut);
        // 2.发送出库任务到wms
        cachedThreadPool.execute(new SendTOWcs(messageId, StateEnum.ZERO));
        return ResponseData.success();
    }

    // 空周转箱出库
    public ResponseData outWarehouse_empty(WmsMaterialTypeResult wmsMaterialTypeResult) {
        WmsWarehouseTaskOut taskOut = new WmsWarehouseTaskOut();
        String messageId = RandomStringUtils.randomNumeric(12);
        taskOut.setMessageId(messageId);// 消息识别ID
        taskOut.setOrderType(ApplyType.C.getType());// 订单类别(A工具领用 B补货出库 C出库)
        taskOut.setGoodsType(ApplyType.C.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        taskOut.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        taskOut.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        taskOut.setResTag(StateEnum.ZERO.getState());// 结果标记（0初始 1更新 2结束）
        taskOut.setResStatus(StateEnum.ZERO.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        taskOut.setTurnoverType(wmsMaterialTypeResult.getTurnoverType()); // 周转箱类型
        taskOut.setTurnoverMouthQuality(wmsMaterialTypeResult.getTurnoverLatticeType());
        taskOut.setDataTime(new Date());// 数据时间

        wmsWarehouseTaskOutService.save(taskOut);
        WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.getOne(new QueryWrapper<WmsWarehouseTaskOut>().eq("message_id", messageId));
        Map<String, Object> map = new HashMap<>();
        map.put("OutfeedId", messageId); // 消息识别id
        map.put("Type", Byte.parseByte("3")); // 出仓类型
        map.put("BoxType", taskOut.getTurnoverType()); // 周转箱类型(A 小 B 中 C 大)  // 转换为 1 2 3
        map.put("LatticeType", Integer.parseInt(wmsWarehouseTaskOut.getTurnoverMouthQuality()) > 1 ? 4 : 1); // 格口类型 1 单格口 4 多格口
        map.put("Sku", "EmptyBox"); // 物料sku
        map.put("Batch", "1"); // 批次
        map.put("Qty", 1); // 数量
//        map.put("Hits","AH1-PLA-A12"); // 分拣工位 A人工 B自动
        map.put("SortingPosition", "AH1-PLA-A12"); // 分拣工位 A人工 B自动
        log.info("The issue request parameter is{}", map);
        String str = wmsApiService.sendOutReq(map);
        log.info("The return parameter of delivery is{}", str);
        WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam = new WmsWarehouseTaskOutParam();
        ToolUtil.copyProperties(wmsWarehouseTaskOut, wmsWarehouseTaskOutParam);
        wmsWarehouseTaskOutParam.setReqTag(StateEnum.ONE.getState());// 请求标记（0初始 1请求）
        wmsWarehouseTaskOutParam.setReqStatus(StateEnum.ONE.getState());// 请求状态（0初始 1成功 2失败）
        wmsWarehouseTaskOutParam.setReqTime(new Date());
        wmsWarehouseTaskOutService.update(wmsWarehouseTaskOutParam);
        return ResponseData.success();
    }


    // 获取周转箱信息
    public ResponseData warehouseTurnoverInfo(String turnoverNumber) {
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("barcode", turnoverNumber));
        if (wmsWarehouseTurnover == null) {
            return ResponseData.error("无此周转箱");
        }
        List<WmsWarehouseTurnoverBind> binds = wmsWarehouseTurnoverBindService.list(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id", wmsWarehouseTurnover.getId()));
        WarehouseTurnoverInfo warehouseTurnoverInfo = new WarehouseTurnoverInfo();
        warehouseTurnoverInfo.setWmsWarehouseTurnover(wmsWarehouseTurnover);
        warehouseTurnoverInfo.setBinds(binds);
        return ResponseData.success(warehouseTurnoverInfo);
    }

    // 手动入库
    public ResponseData inWarehouse(String turnoverNumber) {
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("barcode", turnoverNumber));
        WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
        wmsWarehouseTurnoverBindParam.setTurnoverId(String.valueOf(wmsWarehouseTurnover.getId()));
        List<WmsWarehouseTurnoverBindResult> bindResultList = wmsWarehouseTurnoverBindService.findListTurnover(wmsWarehouseTurnoverBindParam);

        // 1.创建入库任务信息
        WmsWarehouseTaskIn wmsWarehouseTaskIn = new WmsWarehouseTaskIn();
        String messageId = RandomStringUtils.randomNumeric(12);
        wmsWarehouseTaskIn.setMessageId(messageId);// 消息识别ID
        wmsWarehouseTaskIn.setOrderType(ApplyType.B.getType());// 订单类别(A采购入库 B入库)
        wmsWarehouseTaskIn.setTurnoverType(wmsWarehouseTurnover.getTurnoverType());// 周转箱类型(A 小 B 中 C 大)
        wmsWarehouseTaskIn.setTurnoverNumber(wmsWarehouseTurnover.getTurnoverNumber());// 周转箱编号
        wmsWarehouseTaskIn.settBarcode(wmsWarehouseTurnover.getBarcode());// 周转箱条码
        wmsWarehouseTaskIn.setTurnoverMouthQuality(wmsWarehouseTurnover.getTurnoverMouthQuantity()); // 格口数量
        wmsWarehouseTaskIn.setSortingInfo("A");
        if (StateEnum.ZERO.getState().equals(wmsWarehouseTurnover.getTurnoverState())) {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.C.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        } else {
            String typeStr = "";
            for (WmsWarehouseTurnoverBindResult wmsWarehouseTurnoverBindResult : bindResultList) {
                if (wmsWarehouseTurnoverBindResult.getLatticeState().equals("1")) {
                    typeStr = wmsWarehouseTurnoverBindResult.getGoodsType(); // 获取货物类型
                    break;
                }
            }
            if (StateEnum.ONE.getState().equals(typeStr)) {
                wmsWarehouseTaskIn.setGoodsType(ApplyType.A.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
            } else if (StateEnum.TWO.getState().equals(typeStr)) {
                wmsWarehouseTaskIn.setGoodsType(ApplyType.B.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
            } else {
                wmsWarehouseTaskIn.setGoodsType(ApplyType.C.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
            }
        }
        wmsWarehouseTaskIn.setReqTag(StateEnum.ONE.getState());//请求标记（0初始 1请求）
        wmsWarehouseTaskIn.setResStatus(StateEnum.ONE.getState());//结果状态（0初始 1正在执行 2任务完成 3任务失败）
        wmsWarehouseTaskIn.setReqTime(new Date());// 请求时间
        wmsWarehouseTaskIn.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskInService.save(wmsWarehouseTaskIn);
        // 2.发送给WCS
        cachedThreadPool.execute(new SendTOWcs(messageId, StateEnum.ONE));
        // 3.告诉前端入库中
        return ResponseData.success(wmsWarehouseTaskIn);
    }

    // 模拟入库成功接口(手动出库)
    public void inComplement(String locaNumber, String messageId) {
        // 1.更新任务信息
        WmsWarehouseTaskIn wmsWarehouseTaskIn = wmsWarehouseTaskInService.getOne(new QueryWrapper<WmsWarehouseTaskIn>().eq("message_id", messageId));
        wmsWarehouseTaskIn.setResTag(StateEnum.TWO.getState());//结果标记（0初始 1更新 2结束）
        wmsWarehouseTaskIn.setResStatus(StateEnum.TWO.getState());//结果状态（0初始 1正在执行 2任务完成 3任务失败）
        wmsWarehouseTaskIn.setLocaNumber(locaNumber);//入仓仓位编号
        // 2.更新周转箱信息
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", wmsWarehouseTaskIn.getTurnoverNumber()));
        WmsWarehouseStock wmsWarehouseStock = wmsWarehouseStockService.getOne(new QueryWrapper<WmsWarehouseStock>().eq("loca_number", locaNumber));
        wmsWarehouseTurnover.setStorageStatus(StateEnum.ONE.getState());// 存放状态（1库内/2库外）
        wmsWarehouseTurnover.setLocaRow(wmsWarehouseStock.getLocaRow());// 存放-排
        wmsWarehouseTurnover.setLocaCol(wmsWarehouseStock.getLocaCol());// 存放-列
        wmsWarehouseTurnover.setLocaLayer(wmsWarehouseStock.getLocaLayer());// 存放-层
        wmsWarehouseTurnoverService.update(wmsWarehouseTurnover, new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", wmsWarehouseTaskIn.getTurnoverNumber()));
        // 3.更新库位信息
        wmsWarehouseStock.setLocaState(StateEnum.ONE.getState());// 库位状态（0空闲/1有货/2锁定）
        wmsWarehouseStock.setTurnoverId(String.valueOf(wmsWarehouseTurnover.getId()));// 周转箱信息ID
        WmsWarehouseStockParam wmsWarehouseStockParam = new WmsWarehouseStockParam();
        ToolUtil.copyProperties(wmsWarehouseStock, wmsWarehouseStockParam);
        wmsWarehouseStockService.update(wmsWarehouseStockParam);
    }

    // 补货出库
    @SuppressWarnings("all")
    public LayuiPageInfo replenishmentList() {

        return wmsWarehouseReplenishmentTaskService.findList(new WmsWarehouseReplenishmentTaskParam());

    }

    public ResponseData replenishmentCreateOutTask(String taskNumber) {
        WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask = wmsWarehouseReplenishmentTaskService.getOne(new QueryWrapper<WmsWarehouseReplenishmentTask>().eq("task_number", taskNumber));
        WmsWarehouseTaskOut taskOut = new WmsWarehouseTaskOut();
        String messageId = RandomStringUtils.randomNumeric(12);
        taskOut.setMessageId(messageId);// 消息识别ID
        taskOut.setOrderType(ApplyType.B.getType());// 订单类别(A工具领用 B补货出库 C出库)
        taskOut.setTaskMg(wmsWarehouseReplenishmentTask.getTaskNumber());// 任务信息
        taskOut.setGoodsType(ApplyType.B.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        taskOut.setMaterialTypeId(wmsWarehouseReplenishmentTask.getMaterialTypeId());// 物料类型ID
        taskOut.setMaterialSku(wmsWarehouseReplenishmentTask.getMaterialSku());// 物料SKU
        taskOut.setMaterialType(wmsWarehouseReplenishmentTask.getMaterialId());// 物料类型
        taskOut.setMaterialName(wmsWarehouseReplenishmentTask.getMaterialName());// 物料名称
        taskOut.setmBatch(wmsWarehouseReplenishmentTask.getmBatch());// 批次
        taskOut.setmNumber(wmsWarehouseReplenishmentTask.getmNumber());// 数量
        if (StateEnum.ZERO.getState().equals(wmsWarehouseReplenishmentTask.getSortingType())) {
            taskOut.setSortingInfo(ApplyType.A.getType());// 出仓分拣（A人工/B自动）
        } else if (StateEnum.ONE.getState().equals(wmsWarehouseReplenishmentTask.getSortingType())) {
            taskOut.setSortingInfo(ApplyType.B.getType());// 出仓分拣（A人工/B自动）
        }
        taskOut.setReqTime(new Date());// 请求时间
        taskOut.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        taskOut.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        taskOut.setResStatus(StateEnum.ONE.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        taskOut.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskOutService.save(taskOut);
        cachedThreadPool.execute(new SendTOWcs(messageId, StateEnum.ZERO));

        // 更新任务状态为出库中
        wmsWarehouseReplenishmentTask.setTaskState("2");
        final WmsWarehouseReplenishmentTaskParam wmsWarehouseReplenishmentTaskParam = new WmsWarehouseReplenishmentTaskParam();
        ToolUtil.copyProperties(wmsWarehouseReplenishmentTask, wmsWarehouseReplenishmentTaskParam);
        wmsWarehouseReplenishmentTaskService.update(wmsWarehouseReplenishmentTaskParam);
        return ResponseData.success();
    }

    // 补货出库 - 入库任务
    @SuppressWarnings("all")
    public ResponseData replenishmentInTask(String taskNumber) {
        WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.getOne(new QueryWrapper<WmsWarehouseTaskOut>().eq("task_mg", taskNumber).orderByDesc("id").last("limit 1"));
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", wmsWarehouseTaskOut.getTurnoverNumber()).orderByDesc("id").last("limit 1"));
        WmsWarehouseTaskIn wmsWarehouseTaskIn = new WmsWarehouseTaskIn();
        wmsWarehouseTaskIn.setSortingInfo(wmsWarehouseTaskOut.getSortingInfo());
        String messageIdTwo = RandomStringUtils.randomNumeric(12);
        wmsWarehouseTaskIn.setMessageId(messageIdTwo);// 消息识别ID
        wmsWarehouseTaskIn.setTaskMg(taskNumber);
        wmsWarehouseTaskIn.setSortingInfo(wmsWarehouseTaskOut.getSortingInfo());
        wmsWarehouseTaskIn.setOrderType(ApplyType.B.getType());// 订单类别(A采购入库 B入库)
        wmsWarehouseTaskIn.setTurnoverMouthQuality(wmsWarehouseTurnover.getTurnoverMouthQuantity());
        if (StateEnum.ZERO.getState().equals(wmsWarehouseTurnover.getTurnoverState())) {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.C.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        } else {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.B.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        }
        wmsWarehouseTaskIn.setTurnoverType(wmsWarehouseTurnover.getTurnoverType());// 周转箱类型(A单格口/B双格口)
        wmsWarehouseTaskIn.setTurnoverNumber(wmsWarehouseTurnover.getTurnoverNumber());// 周转箱编号
        wmsWarehouseTaskIn.settBarcode(wmsWarehouseTurnover.getBarcode());// 周转箱条码
        wmsWarehouseTaskIn.setReqTag(StateEnum.ONE.getState());//请求标记（0初始 1请求）
        wmsWarehouseTaskIn.setReqStatus(StateEnum.ONE.getState());//请求状态（0初始 1成功 2失败）
        wmsWarehouseTaskIn.setResStatus(StateEnum.ONE.getState());//结果状态（0初始 1正在执行 2任务完成 3任务失败）
        wmsWarehouseTaskIn.setReqTime(new Date());// 请求时间
        wmsWarehouseTaskIn.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskInService.save(wmsWarehouseTaskIn);
        // 1.发送请求到WMS
        cachedThreadPool.execute(new SendTOWcs(messageIdTwo, StateEnum.ONE));
        return ResponseData.success();
    }

    @SuppressWarnings("all")
    public ResponseData replenishmentInTask2(String turnoverNumber,String taskNumber) {
        WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.getOne(new QueryWrapper<WmsWarehouseTaskOut>().eq("task_mg", taskNumber).orderByDesc("id").last("limit 1"));
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", turnoverNumber).orderByDesc("id").last("limit 1"));
        WmsWarehouseTaskIn wmsWarehouseTaskIn = new WmsWarehouseTaskIn();
        String messageIdTwo = RandomStringUtils.randomNumeric(12);
        wmsWarehouseTaskIn.setMessageId(messageIdTwo);// 消息识别ID
        wmsWarehouseTaskIn.setTaskMg(taskNumber);
        wmsWarehouseTaskIn.setSortingInfo(wmsWarehouseTaskOut.getSortingInfo());
        wmsWarehouseTaskIn.setOrderType(ApplyType.B.getType());// 订单类别(A采购入库 B入库)
        wmsWarehouseTaskIn.setTurnoverMouthQuality(wmsWarehouseTurnover.getTurnoverMouthQuantity());
        if (StateEnum.ZERO.getState().equals(wmsWarehouseTurnover.getTurnoverState())) {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.C.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        } else {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.B.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        }
        wmsWarehouseTaskIn.setTurnoverType(wmsWarehouseTurnover.getTurnoverType());// 周转箱类型(A单格口/B双格口)
        wmsWarehouseTaskIn.setTurnoverNumber(wmsWarehouseTurnover.getTurnoverNumber());// 周转箱编号
        wmsWarehouseTaskIn.settBarcode(wmsWarehouseTurnover.getBarcode());// 周转箱条码
        wmsWarehouseTaskIn.setReqTag(StateEnum.ONE.getState());//请求标记（0初始 1请求）
        wmsWarehouseTaskIn.setReqStatus(StateEnum.ONE.getState());//请求状态（0初始 1成功 2失败）
        wmsWarehouseTaskIn.setResStatus(StateEnum.ONE.getState());//结果状态（0初始 1正在执行 2任务完成 3任务失败）
        wmsWarehouseTaskIn.setReqTime(new Date());// 请求时间
        wmsWarehouseTaskIn.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskInService.save(wmsWarehouseTaskIn);
        // 1.发送请求到WMS
        cachedThreadPool.execute(new SendTOWcs(messageIdTwo, StateEnum.ONE));
        return ResponseData.success();
    }

    // 入库完成回调接口
    public void claimInCallback(String messageId, String locaNumber) {
        // 更新中间表任务
        WmsWarehouseTaskIn wmsWarehouseTaskIn = wmsWarehouseTaskInService.getOne(new QueryWrapper<WmsWarehouseTaskIn>().eq("message_id", messageId));
        wmsWarehouseTaskIn.setLocaNumber(locaNumber);
        WmsWarehouseStock wmsWarehouseStock = wmsWarehouseStockService.getOne(new QueryWrapper<WmsWarehouseStock>().eq("loca_number", locaNumber));
        WmsWarehouseTurnover turnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", wmsWarehouseTaskIn.getTurnoverNumber()));
//        ToolClaimModel toolClaimModel = new ToolClaimModel();
        if (ApplyType.A.getType().equals(wmsWarehouseTaskIn.getGoodsType())) {// 工具入库
            // 1.更新入库任务完成
            updateInTask(wmsWarehouseTaskIn);
            // 2.更新周转箱信息
            updateTurnoverStock(wmsWarehouseStock, turnover);
            // 3.更新库位信息
            updateStockTurnover(wmsWarehouseStock, turnover);
        } else if (ApplyType.B.getType().equals(wmsWarehouseTaskIn.getGoodsType())) {// 备品备件入库
            // 1.更新入库任务
            updateInTask(wmsWarehouseTaskIn);
            // 2.更新周转箱信息
            updateTurnoverStock(wmsWarehouseStock, turnover);
            // 3.更新库位周转箱信息
            updateStockTurnover(wmsWarehouseStock, turnover);
        } else if (ApplyType.C.getType().equals(wmsWarehouseTaskIn.getGoodsType())) {// 空周转信息
            // 1.更新入库任务
            updateInTask(wmsWarehouseTaskIn);
            // 2.更新周转箱信息
            updateTurnoverStock(wmsWarehouseStock, turnover);
            // 3.更新库位信息
            wmsWarehouseStock.setLocaState(StateEnum.ONE.getState());// 库位状态（0空闲/1有货/2锁定）
            wmsWarehouseStock.setTurnoverId(String.valueOf(turnover.getId()));// 周转箱信息ID
            WmsWarehouseStockParam stockParam = new WmsWarehouseStockParam();
            ToolUtil.copyProperties(wmsWarehouseStock, stockParam);
            wmsWarehouseStockService.update(stockParam);
        }
    }

    // 更新库位信息
    private void updateStockTurnover(WmsWarehouseStock wmsWarehouseStock, WmsWarehouseTurnover turnover) {
        wmsWarehouseStock.setLocaState(StateEnum.ONE.getState());// 库位状态（0空闲/1有货/2锁定）
        wmsWarehouseStock.setTurnoverId(String.valueOf(turnover.getId()));// 周转箱信息ID
        WmsWarehouseStockParam stockParam = new WmsWarehouseStockParam();
        ToolUtil.copyProperties(wmsWarehouseStock, stockParam);
        wmsWarehouseStockService.update(stockParam);
    }

    // 更新周转箱信息
    private void updateTurnoverStock(WmsWarehouseStock wmsWarehouseStock, WmsWarehouseTurnover turnover) {
        turnover.setStorageStatus(StateEnum.ONE.getState());// 存放状态（1库内/2库外）
        turnover.setLocaRow(wmsWarehouseStock.getLocaRow());// 存放-排
        turnover.setLocaCol(wmsWarehouseStock.getLocaCol());// 存放-列
        turnover.setLocaLayer(wmsWarehouseStock.getLocaLayer());// 存放-层
        wmsWarehouseTurnoverService.update(turnover, new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", turnover.getTurnoverNumber()));
    }

    // 更新入库任务
    private void updateInTask(WmsWarehouseTaskIn wmsWarehouseTaskIn) {

        wmsWarehouseTaskIn.setResTag(StateEnum.TWO.getState());
        wmsWarehouseTaskIn.setResStatus(StateEnum.TWO.getState());
        WmsWarehouseTaskInParam wmsWarehouseTaskInParam = new WmsWarehouseTaskInParam();
        ToolUtil.copyProperties(wmsWarehouseTaskIn, wmsWarehouseTaskInParam);
        wmsWarehouseTaskIn.setResTag(StateEnum.TWO.getState());// 结果标记（0初始 1更新 2结束）
        wmsWarehouseTaskIn.setResStatus(StateEnum.TWO.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        WmsWarehouseTaskInParam taskInParam = new WmsWarehouseTaskInParam();
        ToolUtil.copyProperties(wmsWarehouseTaskIn, taskInParam);
        wmsWarehouseTaskInService.update(taskInParam);
    }

    public LayuiPageInfo printList() {
        WmsPrintInfoParam param = new WmsPrintInfoParam();
        param.setPrintStu(StateEnum.ZERO.getState());
        return wmsPrintInfoService.findPageBySpec(param);
    }

    public void print(String materialSerialNumber) {
        String[] materialSerialNumbers = materialSerialNumber.split(",");
        System.out.println(materialSerialNumbers.length);
        for (String number : materialSerialNumbers) {
            System.out.println(number);
            p.resetZpl();//清除
            printFawTroue(p, number); // 工具条码
            p.resetZpl();
        }
    }

    // 条码打印 - 获取采购订单信息
    public LayuiPageInfo printPurchase() {
        WmsPurchaseOrderInfoParam infoParam = new WmsPurchaseOrderInfoParam();
        return wmsPurchaseOrderInfoService.findPageBySpec2(infoParam);
    }

    // 条码打印 - 采购订单信息生成打印条码
    public void printGeneratorCode(String serialNumber, String purNumber, String printNum) {

        // 打印集合
        List<WmsPrintInfo> wmsPrintInfos = new ArrayList<>();

        // 工具集合
        List<WmsMaterialTool> materialTools = new ArrayList<>();

        int number = Integer.parseInt(printNum);
        WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getOne(new QueryWrapper<WmsPurchaseOrderInfo>().eq("pur_number", purNumber));
        if (Objects.equals(wmsPurchaseOrderInfo.getPrintNum(), "") || Objects.equals(wmsPurchaseOrderInfo.getPrintNum(), null)) {
            wmsPurchaseOrderInfo.setPrintNum(printNum);
        } else {
            Integer value = Integer.parseInt(wmsPurchaseOrderInfo.getPrintNum()) + number;
            wmsPurchaseOrderInfo.setPrintNum(String.valueOf(value));
        }
        WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam = new WmsPurchaseOrderInfoParam();
        ToolUtil.copyProperties(wmsPurchaseOrderInfo, wmsPurchaseOrderInfoParam);
        wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParam);

        String toolCard =  ConstantsContext.getToolCard();
        String[] codes = addCodeNumber(toolCard,number);
        for (int i = 0; i < number; i++) {
            // 条码信息
            WmsPrintInfo wmsPrintInfo = new WmsPrintInfo();
            wmsPrintInfo.setMaterialSerialNumber(codes[i]);// '工具编码'
            wmsPrintInfo.setOperator(serialNumber);// '操作人'
            wmsPrintInfo.setPrintType(ApplyType.B.getType());// '打印类型（A补打 B采购）'
            wmsPrintInfo.setPrintStu(StateEnum.ZERO.getState());// '打印状态（0生成 1打印 2完成 3失败 4结束）'
            wmsPrintInfo.setDataTime(new Date());// 数据时间
            wmsPrintInfos.add(wmsPrintInfo);

            // 工具信息
            WmsMaterialTool wmsMaterialTool = new WmsMaterialTool();
            wmsMaterialTool.setMaterialTypeId(wmsPurchaseOrderInfo.getMaterialTypeId());
            wmsMaterialTool.setMaterialType(wmsPurchaseOrderInfo.getType());
            wmsMaterialTool.setMaterialName(wmsPurchaseOrderInfo.getMaterialName());
            wmsMaterialTool.setMaterialSku(wmsPurchaseOrderInfo.getMaterialSku());
            wmsMaterialTool.setMaterialSerialNumber(codes[i]);
            wmsMaterialTool.setmUnit(wmsPurchaseOrderInfo.getmUnit());
            wmsMaterialTool.setMaterialState("0");
            wmsMaterialTool.setStorageState("0");
            wmsMaterialTool.setStorageAddress("");
            wmsMaterialTool.setDataState("0");
            wmsMaterialTool.setPurNumber(purNumber);
            materialTools.add(wmsMaterialTool);
        }
        wmsPrintInfoService.insertMatch(wmsPrintInfos);

        // 更新缓存
        ConstantsContext.putConstant("TOOL_CARD",codes[codes.length -1]);

        // 更新数据库
        sysConfigService.updateByCode("TOOL_CARD",codes[codes.length -1]);


        // 生成工具信息
        wmsMaterialToolService.insertTools(materialTools);






    }

    /**
     * 字符串添加
     * */
    public String[] addCodeNumber(String startCode, int number){
        String[] codes = new String[number];
        String r = startCode.replace("R", "1"); // R0000001 -> 10000001
        for (int i1 = 0; i1 < number; i1++) {
            int i = Integer.parseInt(r) + i1 + 1 ;
            String str = Integer.toString(i);
            codes[i1] = "R"+ str.substring(1);
        }
        return codes;
    }

    public LayuiPageInfo useToolInfo(String serialNumber) {
        WmsToolUseParam wmsToolUseParam = new WmsToolUseParam();
        if (!Objects.equals(serialNumber, "") || !Objects.equals(serialNumber, null)) {
            wmsToolUseParam.setOperator(serialNumber);
        }
        return wmsToolUseService.findPageBySpec(wmsToolUseParam);
    }

    public ResponseData supplement(String serialNumber, String id, String flag) {
        WmsPrintInfo wmsPrintInfo = new WmsPrintInfo();
        if (ApplyType.A.getType().equals(flag)) {
            WmsToolUse wmsToolUse = wmsToolUseService.getById(id);
            wmsPrintInfo.setMaterialSerialNumber(wmsToolUse.getMaterialSerialNumber());// '工具编码'
        } else {
            WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getById(id);
            wmsPrintInfo.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());// '工具编码'
        }
        wmsPrintInfo.setOperator(serialNumber);// '操作人'
        wmsPrintInfo.setPrintType(ApplyType.A.getType());// '打印类型（A补打 B采购）'
        wmsPrintInfo.setPrintStu(StateEnum.ZERO.getState());// '打印状态（0生成 1打印 2完成 3失败 4结束）'
        wmsPrintInfo.setDataTime(new Date());// 数据时间
        wmsPrintInfoService.save(wmsPrintInfo);
        return ResponseData.success();
    }

    public ResponseData printSupplement(ApplyType applyType) {
        List<WmsPrintInfo> infos = wmsPrintInfoService.list(new QueryWrapper<WmsPrintInfo>().eq("print_type", applyType.getType()).eq("print_stu", StateEnum.ZERO.getState()));
        for (WmsPrintInfo info : infos) {
            p.resetZpl();//清除
            printFawTroue(p, info.getMaterialSerialNumber()); // 工具条码
            info.setPrintStu("1");
            p.resetZpl();
        }
        wmsPrintInfoService.updateMatch(infos);
        return ResponseData.success();
    }

    public LayuiPageInfo printMaterial(String materialTypeId) {
        WmsMaterialToolParam wmsMaterialToolParam = new WmsMaterialToolParam();
        if (!Objects.equals(materialTypeId, "") || !Objects.equals(materialTypeId, null)) {
            wmsMaterialToolParam.setMaterialTypeId(materialTypeId);
        }
        return wmsMaterialToolService.findPageBySpec(wmsMaterialToolParam);
    }

    public ResponseData printMaterialType() {
        List<WmsMaterialType> types = wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type", StateEnum.ONE.getState()));
        return ResponseData.success(types);
    }

    // 采购入库 - 获取可接受数量的订单
    @SuppressWarnings("all")
    public LayuiPageInfo purchaseOrder() {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage<WmsPurchaseOrderInfo> page = wmsPurchaseOrderInfoService.page(pageContext, new QueryWrapper<WmsPurchaseOrderInfo>().ne("acceptable_quantity", StateEnum.ZERO.getState()));
        return LayuiPageFactory.createPageInfo(page);
    }

    // 采购入库 - 获取完成接收数量的订单
    @SuppressWarnings("all")
    public LayuiPageInfo purchaseOrderConform() {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage<WmsPurchaseOrderInfo> page = wmsPurchaseOrderInfoService.page(pageContext, new QueryWrapper<WmsPurchaseOrderInfo>().eq("acceptable_quantity", StateEnum.ZERO.getState()));
        return LayuiPageFactory.createPageInfo(page);
    }

    public ResponseData purchaseConform(String serialNumber, String purNumber) {
        WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getOne(new QueryWrapper<WmsPurchaseOrderInfo>().eq("pur_number", purNumber));
        WmsMaterialTypeResult wmsMaterialTypeResult = wmsMaterialTypeService.findById(wmsPurchaseOrderInfo.getMaterialTypeId());

        if (wmsMaterialTypeResult ==null){
             return  ResponseData.error("物料类型不存在,请先维护");
        }


        if (Objects.equals("1",wmsMaterialTypeResult.getDataState())){
            return ResponseData.error("请先配置好相应物料类型信息再执行");
        }
        // 查询 相同批次 相同物料类型的 物料信息是否存在
        WmsMaterialSparePartsParam tag = new WmsMaterialSparePartsParam();
        tag.setMaterialTypeId(wmsMaterialTypeResult.getId().toString());
        List<WmsMaterialSparePartsResult> all = wmsMaterialSparePartsService.findAllByMaterialTypeId(tag);

        boolean flag = false;
        if (all.size() >0){
            flag = all.stream().anyMatch(e -> Objects.equals(e.getMBatch(), purNumber));

        }
        if (Objects.equals("2",wmsMaterialTypeResult.getType()) && !flag){
            WmsMaterialSparePartsParam param = new WmsMaterialSparePartsParam();
            param.setMaterialTypeId(wmsMaterialTypeResult.getId().toString());
            param.setMaterialType(wmsMaterialTypeResult.getMaterialType());
            param.setMaterialName(wmsMaterialTypeResult.getMaterialName());
            param.setMaterialSku(wmsMaterialTypeResult.getMaterialSku());
            param.setMUnit(wmsMaterialTypeResult.getMUnit());
            param.setStorageState("0");
            param.setStorageAddress("");
            param.setDataState("1");
            param.setMBatch(purNumber);
            param.setMinPackageSize(wmsMaterialTypeResult.getPackageNumber());
            wmsMaterialSparePartsService.add(param);
        }

        // 1.校验库中是否有空周转箱
        String turnoverType = Objects.equals("0", wmsMaterialTypeResult.getTurnoverType()) ? "A" : Objects.equals("1", wmsMaterialTypeResult.getTurnoverType()) ? "B" : "C";
        List<WmsWarehouseTurnover> wmsWarehouseTurnover = wmsWarehouseTurnoverService.findEmptyType(turnoverType);
        if (wmsWarehouseTurnover.size() == 0) {
            return ResponseData.error("空周转箱库存不足 无法继续操作!");
        }

        // 2.更新采购入库任务 状态
        String orderId = wmsPurchaseOrderInfo.getId().toString();
        WmsWarehousePurchaseStorageTaskResult taskResult = wmsWarehousePurchaseStorageTaskService.findByOrderId(orderId);
        Optional<WmsWarehousePurchaseStorageTaskResult> taskResult1 = Optional.ofNullable(taskResult);
        boolean present = taskResult1.isPresent();

        WmsWarehousePurchaseStorageTask storageTask = new WmsWarehousePurchaseStorageTask();
        WmsPurchaseOrderInfoParam wms = new WmsPurchaseOrderInfoParam();

        // 暂停上一条
        stopLastTask();

        if (present) { // 有采购入库任务
            // 更新当前选中
            wmsPurchaseOrderInfoService.updateState("1", orderId);
            wmsWarehousePurchaseStorageTaskService.updateState("1", orderId);
        } else {
            // 更新采购订单为执行中
            wmsPurchaseOrderInfo.setArrivalState("1");
            ToolUtil.copyProperties(wmsPurchaseOrderInfo, wms);
            wmsPurchaseOrderInfoService.update(wms);

            //新建采购订单任务 状态为执行中
            storageTask.setTaskNumber(mapCodeGenerator.get(CodeProviderEnum.purchaseWarehousingCode.getProvider()).createCode(null));// 任务编号
            storageTask.setPurchaseId(String.valueOf(wmsPurchaseOrderInfo.getId()));// 采购订单信息ID
            storageTask.setOperator(serialNumber);// 操作人
            storageTask.setTaskState(StateEnum.ONE.getState());//任务状态（0初始 1执行中 2暂停中 3完成）
            storageTask.setTotalQuantity(wmsPurchaseOrderInfo.getmNumber());//总数量
            storageTask.setAcceptableQuantity(wmsPurchaseOrderInfo.getAcceptableQuantity());//可接收数量
            Integer value = Integer.parseInt(wmsPurchaseOrderInfo.getAcceptableQuantity()) - Integer.parseInt(wmsPurchaseOrderInfo.getAcceptableQuantity());
            storageTask.setReceivedQuantity(String.valueOf(value));//已接收数量
            storageTask.setGroupdQuantity(StateEnum.ZERO.getState());//已组盘数量
            wmsWarehousePurchaseStorageTaskService.save(storageTask);
        }
        return ResponseData.success();
    }

    // 更新
    private void stopLastTask() {
        wmsPurchaseOrderInfoService.stopTask();
        wmsWarehousePurchaseStorageTaskService.stopTask();
    }

    // 采购入库 - 扫描入库
    @SuppressWarnings("All")
    public ResponseData purchaseScanInTask(String serialNumber, String turnoverNumber, String taskNumber) {
        WmsWarehousePurchaseStorageTask wmsWarehousePurchaseStorageTask = wmsWarehousePurchaseStorageTaskService.getOne(new QueryWrapper<WmsWarehousePurchaseStorageTask>().eq("task_number", taskNumber));
        WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getById(wmsWarehousePurchaseStorageTask.getPurchaseId());
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("barcode", turnoverNumber));
        // 1.创建入库任务
        WmsWarehouseTaskIn wmsWarehouseTaskIn = new WmsWarehouseTaskIn();
        String messageId = RandomStringUtils.randomNumeric(12);
        wmsWarehouseTaskIn.setMessageId(messageId);// 消息识别ID
        wmsWarehouseTaskIn.setOrderType(ApplyType.B.getType());// 订单类别(A采购入库 B入库)
        wmsWarehouseTaskIn.setTaskMg(taskNumber);// 任务信息
        wmsWarehouseTaskIn.setSortingInfo("A");
        wmsWarehouseTaskIn.setTurnoverMouthQuality(wmsWarehouseTurnover.getTurnoverMouthQuantity());
        if (StateEnum.ONE.getState().equals(wmsPurchaseOrderInfo.getType())) {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.A.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        } else {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.B.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        }
        wmsWarehouseTaskIn.setTurnoverType(wmsWarehouseTurnover.getTurnoverType());// 周转箱类型(A单格口/B双格口)
        wmsWarehouseTaskIn.setTurnoverNumber(wmsWarehouseTurnover.getTurnoverNumber());// 周转箱编号
        wmsWarehouseTaskIn.settBarcode(wmsWarehouseTurnover.getBarcode());// 周转箱条码
//        Integer receiveNumber = 0;// 接收数量统计
        WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
        wmsWarehouseTurnoverBindParam.setTurnoverId(String.valueOf(wmsWarehouseTurnover.getId()));
        wmsWarehouseTaskIn.setReqTag(StateEnum.ONE.getState());//请求标记（0初始 1请求）
        wmsWarehouseTaskIn.setReqStatus(StateEnum.ONE.getState());//请求状态（0初始 1成功 2失败）
        wmsWarehouseTaskIn.setResStatus(StateEnum.ONE.getState());//结果状态（0初始 1正在执行 2任务完成 3任务失败）
        wmsWarehouseTaskIn.setReqTime(new Date());// 请求时间
        wmsWarehouseTaskIn.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskInService.save(wmsWarehouseTaskIn);
        // 2.发送入库任务WCS
        cachedThreadPool.execute(new SendTOWcs(messageId, StateEnum.ONE));
        return ResponseData.success();
    }

    // 空周转箱出库
    public ResponseData purchaseOutTask(String taskNumber) {
        WmsWarehousePurchaseStorageTask wmsWarehousePurchaseStorageTask = wmsWarehousePurchaseStorageTaskService.getOne(new QueryWrapper<WmsWarehousePurchaseStorageTask>().eq("task_number", taskNumber));
        WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getById(wmsWarehousePurchaseStorageTask.getPurchaseId());
        final WmsMaterialTypeResult materialTypeResult = wmsMaterialTypeService.findById(wmsPurchaseOrderInfo.getMaterialTypeId());

        // 1.校验库中是否有空周转箱类型,根据物料分拣类型决定出单/多格口的箱子
        String turnoverType = Objects.equals("0", materialTypeResult.getTurnoverType()) ? "A" : Objects.equals("1", materialTypeResult.getTurnoverType()) ? "B" : "C";
        String latticeNumber = Objects.equals("1", materialTypeResult.getTurnoverType()) ? "1" : "4";
        List<WmsWarehouseTurnover> wmsWarehouseTurnover = wmsWarehouseTurnoverService.findEmptyType(turnoverType);
        if (wmsWarehouseTurnover.size() == 0) {
            return ResponseData.error("空周转箱库存不足 无法继续操作!");
        }
        // 2.创建出库任务
        WmsWarehouseTaskOutParam taskOut = new WmsWarehouseTaskOutParam();
        String messageId = RandomStringUtils.randomNumeric(12);
        taskOut.setMessageId(messageId);// 消息识别ID
        taskOut.setOrderType(ApplyType.C.getType());// 订单类别(A工具领用 B补货出库 C出库)
        taskOut.setTaskMg(taskNumber);// 任务信息
        taskOut.setGoodsType(ApplyType.C.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        taskOut.setMNumber(StateEnum.ONE.getState());// 数量
        taskOut.setTurnoverType(materialTypeResult.getTurnoverType());// 周转箱类型 大 中 小
        taskOut.setMBatch("1");// 批次
        taskOut.setMaterialSku("EmptyBox"); // 物料SKU
        taskOut.setSortingInfo("A");// 出仓分拣（A人工/B自动）
        taskOut.setReqTime(new Date());// 请求时间
        taskOut.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        taskOut.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        taskOut.setResTag(StateEnum.ZERO.getState());// 结果标记（0初始 1更新 2结束）
        taskOut.setResStatus(StateEnum.ZERO.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        taskOut.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskOutService.add(taskOut);
        // 4.发送出库任务
        cachedThreadPool.execute(new SendTOWcs(messageId, StateEnum.ZERO));
        return ResponseData.success();
    }

    // 立库工具领用 - 创建入库
    public ResponseData createInTask(String messageId) {
        // 1.创建入库任务
        WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.getOne(new QueryWrapper<WmsWarehouseTaskOut>().eq("message_id", messageId));
        WmsWarehouseTaskIn wmsWarehouseTaskIn = new WmsWarehouseTaskIn();
        String messageIdTwo = RandomStringUtils.randomNumeric(12);
        wmsWarehouseTaskIn.setMessageId(messageIdTwo);// 消息识别ID
        wmsWarehouseTaskIn.setOrderType(ApplyType.B.getType());// 订单类别(A采购入库 B入库)
        wmsWarehouseTaskIn.setTaskMg(wmsWarehouseTaskOut.getTaskMg());// 任务信息
        wmsWarehouseTaskIn.setTurnoverType(wmsWarehouseTaskOut.getTurnoverType());// 周转箱类型(A单格口/B双格口)
        wmsWarehouseTaskIn.setTurnoverNumber(wmsWarehouseTaskOut.getTurnoverNumber());// 周转箱编号
        wmsWarehouseTaskIn.settBarcode(wmsWarehouseTaskOut.getBarcode());// 周转箱条码
        wmsWarehouseTaskIn.setTurnoverMouthQuality(wmsWarehouseTaskOut.getTurnoverMouthQuality());// 周转箱格口数量
        wmsWarehouseTaskIn.setSortingInfo(wmsWarehouseTaskOut.getSortingInfo()); // 入库位置
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number", wmsWarehouseTaskOut.getTurnoverNumber()));
        if (StateEnum.ZERO.getState().equals(wmsWarehouseTurnover.getTurnoverState())) {
            wmsWarehouseTaskIn.setGoodsType(ApplyType.C.getType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        } else {
            wmsWarehouseTaskIn.setGoodsType(wmsWarehouseTaskOut.getGoodsType());// 入仓货物类型（A工具/B备品备件/C空周转箱）
        }
        wmsWarehouseTaskIn.setReqTag(StateEnum.ONE.getState());//请求标记（0初始 1请求）
        wmsWarehouseTaskIn.setReqStatus(StateEnum.ONE.getState());//请求状态（0初始 1成功 2失败）
        wmsWarehouseTaskIn.setResTag(StateEnum.ZERO.getState());//结果标记（0初始 1更新 2结束）
        wmsWarehouseTaskIn.setResStatus(StateEnum.ONE.getState());//结果状态（0初始 1正在执行 2任务完成 3任务失败）
        wmsWarehouseTaskIn.setReqTime(new Date());// 请求时间
        wmsWarehouseTaskIn.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskInService.save(wmsWarehouseTaskIn);
        // 2.发送任务入库任务
        cachedThreadPool.execute(new SendTOWcs(messageIdTwo, StateEnum.ONE));
        return ResponseData.success();
    }

    public ResponseData replenishmentOutTask(String taskNumber) {
        // 1.校验库中是否有同类型的物料
        WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask = wmsWarehouseReplenishmentTaskService.getOne(new QueryWrapper<WmsWarehouseReplenishmentTask>().eq("task_number", taskNumber));
        WmsWarehouseStock wmsWarehouseStock = wmsWarehouseStockService.getOne(new QueryWrapper<WmsWarehouseStock>().eq("material_type_id", wmsWarehouseReplenishmentTask.getMaterialTypeId()).last("limit 1"));
        if (wmsWarehouseStock == null) {
            return ResponseData.error("该任务物料类型库存不足 无法继续操作!");
        }
        // 2.创建出库任务
        WmsWarehouseTaskOut taskOut = new WmsWarehouseTaskOut();
        String messageIdTwo = RandomStringUtils.randomNumeric(12);
        taskOut.setMessageId(messageIdTwo);// 消息识别ID
        taskOut.setOrderType(ApplyType.C.getType());// 订单类别(A工具领用 B补货出库 C出库)
        taskOut.setTaskMg(wmsWarehouseReplenishmentTask.getTaskNumber());// 任务信息
        taskOut.setGoodsType(ApplyType.B.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        taskOut.setMaterialTypeId(wmsWarehouseReplenishmentTask.getMaterialTypeId());// 物料类型ID
        taskOut.setMaterialSku(wmsWarehouseReplenishmentTask.getMaterialSku());// 物料SKU
        taskOut.setMaterialType(wmsWarehouseReplenishmentTask.getSortingType());// 物料类型
        taskOut.setMaterialName(wmsWarehouseReplenishmentTask.getMaterialName());// 物料名称
        taskOut.setmBatch(wmsWarehouseReplenishmentTask.getmBatch());// 批次
        taskOut.setmNumber(StateEnum.ONE.getState());// 数量
        if (StateEnum.ZERO.getState().equals(wmsWarehouseReplenishmentTask.getSortingType())) {
            taskOut.setSortingInfo(ApplyType.A.getType());// 出仓分拣（A人工/B自动）
        } else if (StateEnum.ONE.getState().equals(wmsWarehouseReplenishmentTask.getSortingType())) {
            taskOut.setSortingInfo(ApplyType.B.getType());// 出仓分拣（A人工/B自动）
        }
        taskOut.setReqTime(new Date());// 请求时间
        taskOut.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        taskOut.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        taskOut.setResStatus(StateEnum.ONE.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        taskOut.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskOutService.save(taskOut);
        // 3.出库发送任务到WCS
        cachedThreadPool.execute(new SendTOWcs(messageIdTwo, StateEnum.ZERO));
        return ResponseData.success();
    }

    public ResponseData replenishmentConformTask(String taskNumber) {
        WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask = new WmsWarehouseReplenishmentTask();
        wmsWarehouseReplenishmentTask.setTaskState(StateEnum.THREE.getState());
        wmsWarehouseReplenishmentTask.setSortingStatus(StateEnum.ONE.getState());
        wmsWarehouseReplenishmentTaskService.update(wmsWarehouseReplenishmentTask, new QueryWrapper<WmsWarehouseReplenishmentTask>().eq("task_number", taskNumber));
        return ResponseData.success();
    }

    public ResponseData findMaterialTypeAll() {
        List<WmsMaterialType> types = wmsMaterialTypeService.list();
        List<WmsMaterialType> collect = types.stream().sorted(Comparator.comparing(WmsMaterialType::getId).reversed()).collect(Collectors.toList());
        return ResponseData.success(collect);
    }

    @SuppressWarnings("all")
    public LayuiPageInfo findWarehouseList(String materialTypeId) {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = wmsWarehouseTurnoverBindService.page(pageContext, new QueryWrapper<WmsWarehouseTurnoverBind>().eq("material_type_id", materialTypeId));
        return LayuiPageFactory.createPageInfo(page);
    }

    public ResponseData replenishmentTaskInfo() {
        ToolClaimModel toolClaimModel = (ToolClaimModel) replenishmentMap.get("replenishment");
        if (StateEnum.ONE.equals(replenishmentMap.get("state"))) {// 出库中
            toolClaimModel.setState(StateEnum.ONE.getState());
        } else if (StateEnum.TWO.equals(replenishmentMap.get("state"))) {// 出库完成
            toolClaimModel.setState(StateEnum.TWO.getState());
        } else if (StateEnum.THREE.equals(replenishmentMap.get("state"))) {// 入库中
            toolClaimModel.setState(StateEnum.THREE.getState());
        } else if (StateEnum.FOUR.equals(replenishmentMap.get("state"))) {// 入库完成
            toolClaimModel.setState(StateEnum.FOUR.getState());
        }
        return ResponseData.success(toolClaimModel);
    }

    // 执行中的采购任务
    public WmsPurchaseOrderInfoResult doingTask() {
        return wmsWarehousePurchaseStorageTaskService.doingTask();
    }

    // 发送入库任务
    public void sendTask(String messageIdTwo) {
        cachedThreadPool.execute(new SendTOWcs(messageIdTwo, StateEnum.TWO));
    }

    // 发送出库任务
    public void sendTaskOut(String messageIdTwo) {
        cachedThreadPool.execute(new SendTOWcs(messageIdTwo, StateEnum.ZERO));
    }

    // 执行备件补货任务
    public ResponseData startReplenishment(String taskNumber) {
        WmsWarehouseReplenishmentTaskResult wr = wmsWarehouseReplenishmentTaskService.inExecution();
        if (wr != null) {
            return ResponseData.error(500, "当前有任务正在执行", new ArrayList<>());
        } else {
            WmsWarehouseReplenishmentTaskResult byTaskNumber = wmsWarehouseReplenishmentTaskService.findByTaskNumber(taskNumber);
            List<WmsWarehouseTurnoverBind> wmsWarehouseStocks = wmsWarehouseTurnoverBindService.list(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("material_type_id", byTaskNumber.getMaterialTypeId()));
            if (wmsWarehouseStocks.size() <= 0) {
                return ResponseData.error(500, "该任务物料类型库存不足 无法继续操作!", new ArrayList<>());
            }
            List<String> localNumbers = new ArrayList<>();
            for (WmsWarehouseTurnoverBind wmsWarehouseStock : wmsWarehouseStocks) {
                localNumbers.add(wmsWarehouseStock.getTurnoverId());
            }
            List<WmsWarehouseTurnoverBind> wmsWarehouseTurnoverBinds = wmsWarehouseTurnoverBindService.list(new QueryWrapper<WmsWarehouseTurnoverBind>().in("turnover_id", localNumbers));
            int number = 0;
            for (WmsWarehouseTurnoverBind wmsWarehouseTurnoverBind : wmsWarehouseTurnoverBinds) {
                if (!Objects.equals(wmsWarehouseTurnoverBind.getmNumber(), "") && !Objects.equals(wmsWarehouseTurnoverBind.getmNumber(), null)) {
                    number += Integer.parseInt(wmsWarehouseTurnoverBind.getmNumber());
                }
            }
            if (number < Integer.parseInt(byTaskNumber.getMNumber())) {
                return ResponseData.error(500, "该任务物料类型库存不足 无法继续操作!", new ArrayList<>());
            }
            byTaskNumber.setTaskState("1");
            WmsWarehouseReplenishmentTaskParam wms = new WmsWarehouseReplenishmentTaskParam();
            ToolUtil.copyProperties(byTaskNumber, wms);
            wmsWarehouseReplenishmentTaskService.update(wms);
        }
        return ResponseData.success();
    }

    // 发送任务到WCS
    class SendTOWcs implements Runnable {

        private String messageId;

        // ZERO:出库 ONE:入库
        private StateEnum stateEnum;


        // 出/入库周转箱类型
        Map<String, String> initMap = new HashMap<>();
        // 出/入仓货物类型 A:工具 B:备品备件 C:空
        Map<String, String> inOutType = new HashMap<>();

        // 初始化map
        public void initMap() {
            initMap.put("A", "1");
            initMap.put("B", "2");
            initMap.put("C", "3");
            inOutType.put("0", "1");
            inOutType.put("1", "2");
            inOutType.put("2", "3");
        }

        SendTOWcs(String messageId, StateEnum stateEnum) {
            this.messageId = messageId;
            this.stateEnum = stateEnum;
            initMap();
        }

        @Override
        public void run() {
            if (StateEnum.ZERO.equals(stateEnum)) { // 出库
                WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.getOne(new QueryWrapper<WmsWarehouseTaskOut>().eq("message_id", messageId));
                Map<String, Object> map = new HashMap<>();
                map.put("OutfeedId", messageId); // 消息识别id
                map.put("Type", Byte.parseByte(initMap.get(wmsWarehouseTaskOut.getGoodsType()))); // 出仓类型(A工具/B备品备件/C空周转箱)

                // 采购补货 空周转箱出库
                if (Objects.equals("C", wmsWarehouseTaskOut.getGoodsType())) {
                    map.put("BoxType", inOutType.get(wmsWarehouseTaskOut.getTurnoverType())); // 周转箱类型(A 小 B 中 C 大)  // 转换为 1 2 3
                } else {
                    map.put("BoxType", "");
                }
                map.put("LatticeType", Integer.parseInt(wmsWarehouseTaskOut.getTurnoverMouthQuality()) > 1 ? 4 : 1); // 格口类型 4 多格口 1 单个口
//                map.put("LatticeType", ""); // 格口类型 4 多格口 1 单个口
                map.put("Sku", wmsWarehouseTaskOut.getMaterialSku()); // 物料sku
                map.put("Batch", wmsWarehouseTaskOut.getmBatch()); // 批次
                map.put("Qty", Integer.parseInt(wmsWarehouseTaskOut.getmNumber())); // 数量
                map.put("SortingPosition", Objects.equals("A", wmsWarehouseTaskOut.getSortingInfo()) ? "AH1-PLA-A12" : "AH1-PLA-A50"); // 分拣工位 A人工 B自动
                log.info("The issue request parameter is{}", map);
                String str = wmsApiService.sendOutReq(map);
                log.info("The return parameter of delivery is{}", str);
                WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam = new WmsWarehouseTaskOutParam();
                ToolUtil.copyProperties(wmsWarehouseTaskOut, wmsWarehouseTaskOutParam);
                wmsWarehouseTaskOutParam.setReqTag(StateEnum.ONE.getState());// 请求标记（0初始 1请求）
                wmsWarehouseTaskOutParam.setReqStatus(StateEnum.ONE.getState());// 请求状态（0初始 1成功 2失败）
                wmsWarehouseTaskOutParam.setReqTime(new Date());
                wmsWarehouseTaskOutService.update(wmsWarehouseTaskOutParam);
            } else { // 入库
                WmsWarehouseTaskIn wmsWarehouseTaskIn = wmsWarehouseTaskInService.getOne(new QueryWrapper<WmsWarehouseTaskIn>().eq("message_id", messageId));
                Map<String, Object> map = new HashMap<>();
                map.put("InboundId", messageId); // 消息识别id
                map.put("Type", Byte.parseByte(initMap.get(wmsWarehouseTaskIn.getGoodsType()))); // 入仓类型(A工具/B备品备件/C空周转箱)
                map.put("BoxType", Integer.parseInt(initMap.get(wmsWarehouseTaskIn.getTurnoverType()))); // 周转箱类型(A 小 B 中 C 大)
                map.put("BoxCode", wmsWarehouseTaskIn.gettBarcode()); // 周转箱编号
                map.put("LatticeType", Integer.parseInt(wmsWarehouseTaskIn.getTurnoverMouthQuality()) > 1 ? 4 : 1); // 格口类型 A 多格口 B 单个口

                List<Map<String, Object>> list = new ArrayList<>();
                if (!Objects.equals("C", wmsWarehouseTaskIn.getGoodsType())) {
                    // 查询周转箱上绑定的物料信息
                    WmsWarehouseTurnoverResult turnoverInfo = wmsWarehouseTurnoverService.findByTurnoverNumber(wmsWarehouseTaskIn.getTurnoverNumber());
                    WmsWarehouseTurnoverBindParam wt = new WmsWarehouseTurnoverBindParam();
                    wt.setTurnoverId(turnoverInfo.getId().toString());
                    List<WmsWarehouseTurnoverBindResult> listTurnover = wmsWarehouseTurnoverBindService.findListTurnover(wt);
                    for (WmsWarehouseTurnoverBindResult result : listTurnover) {
                        Map<String, Object> map2 = new HashMap<>();
                        if (!Objects.equals("", result.getMaterialSku()) && !Objects.equals(null, result.getMaterialSku())) {
                            map2.put("Sku", result.getMaterialSku());
                            map2.put("Batch", result.getMBatch());
                            map2.put("Qty", Integer.parseInt(result.getMNumber()));
                            list.add(map2);
                        }
                    }
                    map.put("BoxInformation", list); // 周转箱绑定货物信息
                } else {
                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("Sku", "EmptyBox");
                    map2.put("Batch", "1");
                    map2.put("Qty", 1);
                    list.add(map2);
                    map.put("BoxInformation", list); // 空
                }
                map.put("Hits", Objects.equals("A", wmsWarehouseTaskIn.getSortingInfo()) ? "AH1-PLA-A12" : "AH1-PLA-A50"); // 分拣工位 A人工 B自动
                log.info("Warehousing request parameters{}", map);
                String str = wmsApiService.sendInReq(map);
                log.info("Warehousing request response{}", str);
                WmsWarehouseTaskInParam wmsWarehouseTaskInParam = new WmsWarehouseTaskInParam();
                ToolUtil.copyProperties(wmsWarehouseTaskIn, wmsWarehouseTaskInParam);
                wmsWarehouseTaskInParam.setReqTag(StateEnum.ONE.getState());// 请求标记（0初始 1请求）
                wmsWarehouseTaskInParam.setReqStatus(StateEnum.ONE.getState());// 请求状态（0初始 1成功 2失败）
                wmsWarehouseTaskInParam.setReqTime(new Date());
                wmsWarehouseTaskInService.update(wmsWarehouseTaskInParam);
            }
        }

    }


}
