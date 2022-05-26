package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialspareparts.model.params.WmsMaterialSparePartsParam;
import cn.stylefeng.guns.modular.base.materialspareparts.model.result.WmsMaterialSparePartsResult;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.enums.ApplyType;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.request.ApplySpareParts;
import cn.stylefeng.guns.modular.onetypeservice.request.WarehouseTurnoverModify;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.guns.modular.warehousemanage.entity.*;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseStorageTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseReplenishmentTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.service.*;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization", maxAge = 3600)
@RequestMapping("/pda/warehouse")
@Validated
public class PDAWareController {

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WmsUserService wmsUserService;
    @Autowired
    private WmsWarehouseReplenishmentTaskService wmsWarehouseReplenishmentTaskService;
    @Autowired
    private WmsSortingTaskService wmsSortingTaskService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;
    @Autowired
    private WmsWarehouseTaskOutService wmsWarehouseTaskOutService;
    @Autowired
    private WmsMaterialSparePartsService wmsMaterialSparePartsService;

    @Autowired
    private WmsWarehousePurchaseStorageTaskService wmsWarehousePurchaseStorageTaskService;

    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @Autowired
    private WmsToolUseService wmsToolUseService;

    @Autowired
    private WmsWarehouseTurnoverService wmsWarehouseTurnoverService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseData login(String serialNumber, String password){
        WmsUser wmsUser = wmsUserService.getOne(new QueryWrapper<WmsUser>().eq("serial_number",serialNumber));
        if(wmsUser == null){
            return ResponseData.error("账号不存在");
        }
        if(!Objects.equals(wmsUser.getuPwd(),password)){
            return ResponseData.error("账号密码错误");
        }
        return ResponseData.success(wmsUser.getId() + "," + wmsUser.getUserName() + "," + wmsUser.getSerialNumber());
    }

    /**
     * 采购入库任务
     */
    @RequestMapping("/doingTask")
    @ResponseBody
    public ResponseData doingTask() {
        WmsPurchaseOrderInfoResult task = warehouseService.doingTask();
        if (task == null){
            return ResponseData.error(500, "暂无执行中的采购任务", new WmsPurchaseOrderInfoResult());
        }
        return ResponseData.success(task);
    }

    /**
     * 空周转箱出库
     * 任务编号:taskNumber
     */
    @RequestMapping("/out")
    @ResponseBody
    public ResponseData purchaseOutTask(String taskNumber) {
        return warehouseService.purchaseOutTask(taskNumber);
    }

    /**
     * 周转箱信息
     * 周转箱条码:turnoverNumber
     */
    @RequestMapping("/warehouse-turnover")
    @ResponseBody
    public ResponseData padWarehouseTurnover(String turnoverNumber) {
        return oneTypeCabinetService.warehouseTurnover(turnoverNumber);
    }

    /**
     * 工具信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/dish-data")
    @ResponseBody
    public ResponseData padWarehouseDishToolData(String materialSerialNumber) {
        WmsMaterialTool wmsMaterialTool = oneTypeCabinetService.findToolByMaterialSerialNumber(materialSerialNumber);
        if (wmsMaterialTool == null) {
            return ResponseData.error(500, "无此工具信息", new WmsMaterialTool());
        }
        return ResponseData.success(wmsMaterialTool);
    }

    /**
     * 备品备件信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/dish-spareparts-data")
    @ResponseBody
    public ResponseData padWarehouseMaterialSparePartsData(String materialTypeId) {
        WmsMaterialSparePartsParam param=new WmsMaterialSparePartsParam();
        param.setMaterialTypeId(materialTypeId);
        List<WmsMaterialSparePartsResult> wmsMaterialSparePartsResultList = this.wmsMaterialSparePartsService.findAllByMaterialTypeId(param);
        if (wmsMaterialSparePartsResultList == null||wmsMaterialSparePartsResultList.isEmpty()) {
            return ResponseData.error(500, "无此类型备品备件信息", new ArrayList<>());
        }
        return ResponseData.success(wmsMaterialSparePartsResultList);
    }

    /**
     * 周转箱信息绑定
     * WarehouseTurnoverModify
     */
    @RequestMapping("/dish-bing")
    @ResponseBody
    public ResponseData padWarehouseDishConform(WarehouseTurnoverModify warehouseTurnoverModify) {
        return oneTypeCabinetService.padWarehouseDishConform(warehouseTurnoverModify);
    }

    /**
     * 提交入库
     * serialNumber 用户编号
     * turnoverNumber 周转箱条码
     * taskNumber 采购任务编号
     */
    @RequestMapping("/scan-out")
    @ResponseBody
    public ResponseData purchaseScanOutTask(String serialNumber, String turnoverNumber, String taskNumber) {
        return warehouseService.purchaseScanInTask(serialNumber, turnoverNumber, taskNumber);
    }

    /**
     * 采购入库任务 完成
     */
    @RequestMapping("/doingTaskOver")
    @ResponseBody
    public ResponseData doingTaskOver(String taskNumber) {
        WmsWarehousePurchaseStorageTask wmsWarehousePurchaseStorageTask = wmsWarehousePurchaseStorageTaskService.getOne(new QueryWrapper<WmsWarehousePurchaseStorageTask>().eq("task_number", taskNumber));
        WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getById(wmsWarehousePurchaseStorageTask.getPurchaseId());

        WmsWarehousePurchaseStorageTaskParam wmsWarehousePurchaseStorageTaskParam=new WmsWarehousePurchaseStorageTaskParam();
        wmsWarehousePurchaseStorageTaskParam.setId(wmsWarehousePurchaseStorageTask.getId());
        wmsWarehousePurchaseStorageTaskParam.setTaskState("3");
        wmsWarehousePurchaseStorageTaskService.update(wmsWarehousePurchaseStorageTaskParam);

        WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam=new WmsPurchaseOrderInfoParam();
        wmsPurchaseOrderInfoParam.setId(wmsPurchaseOrderInfo.getId());
        wmsPurchaseOrderInfoParam.setArrivalState("3");
        wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParam);

        return ResponseData.success();
    }

    /**
     * 所有的工具类型信息
     */
    @RequestMapping("/apply-tool-all")
    @ResponseBody
    public ResponseData padApplyToolAll() {
        List<WmsMaterialType> tools = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type",StateEnum.ONE.getState()).eq("data_state",StateEnum.ZERO.getState()));
        if(tools.isEmpty()){
            return ResponseData.error(500, "无工具类型", new ArrayList());
        }
        return ResponseData.success(tools);
    }

    /**
     * 工具申请
     * serialNumber  人员编号
     * reason 申请原因
     * materialTypeId 物料类型id
     */
    @RequestMapping("/apply-tool-conform")
    @ResponseBody
    public ResponseData padApplyToolConform(String serialNumber, String reason, String materialTypeId) {
        oneTypeCabinetService.padToolConform(serialNumber, reason, materialTypeId);
        return ResponseData.success();
    }

    /**
     * 所有的备品备件
     */
    @RequestMapping("/apply-spare-all")
    @ResponseBody
    public ResponseData padApplySpareAll() {
        List<WmsMaterialType> types = wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type",StateEnum.TWO.getState()).eq("data_state",StateEnum.ZERO.getState()));
        if(types.isEmpty()){
            return ResponseData.error(500, "无备品备件类型", new ArrayList());
        }
        return ResponseData.success(types);
    }

    /**
     * 备品备件申请
     */
    @RequestMapping("/apply-spare-conform")
    @ResponseBody
    public ResponseData padApplySpareConform(ApplySpareParts apply) {
        return oneTypeCabinetService.padApplySpareConform(apply);
    }

    /**
     * 归还申请
     */
    @RequestMapping("/apply-back")
    @ResponseBody
    public ResponseData padToolGiveBack(String serialNumber, String reason, String materialTypeId) {
        oneTypeCabinetService.padToolGiveBack(serialNumber, reason, materialTypeId);
        return ResponseData.success();
    }

    /**
     * 工具领用列表
     * serialNumber 人员编号
     * */
    @RequestMapping("/tool_apply_list")
    @ResponseBody
    public ResponseData toolApplyList(String serialNumber) {
        LayuiPageInfo layuiPageInfo = warehouseService.claimListA(serialNumber);
        final List<WmsWarehouseToolUseTask> data = layuiPageInfo.getData();
        if (layuiPageInfo.getData().size() == 0){
            return ResponseData.error(500, "无工具领用任务", new ArrayList());
        }
        return ResponseData.success(data);
    }

    /**
     * 工具领用
     * serialNumber 用户编号
     * taskNumber 任务编号
     * */
    @RequestMapping("/tool_apply_config")
    @ResponseBody
    public ResponseData toolApplyConfig(String serialNumber, String taskNumber) {
        return warehouseService.claimConform(serialNumber,taskNumber);
    }

    /**
     * 工具领用提交
     * WarehouseTurnoverModify
     * */
    @RequestMapping("/tool_apply_commit")
    @ResponseBody
    public ResponseData toolApplyCommit(WarehouseTurnoverModify modify) {
        if (modify.getMaterialSerialNumber() == null){
            return ResponseData.error("请先绑定工具信息");
        }
        WmsWarehouseTaskIn wmsWarehouseTaskIn = oneTypeCabinetService.padSortingConform(modify);
        warehouseService.sendTask(wmsWarehouseTaskIn.getMessageId());
        //更新任务完成
        WmsWarehouseToolUseTask toolUseTask = new WmsWarehouseToolUseTask();
        toolUseTask.setTaskState(StateEnum.THREE.getState());
        wmsWarehouseToolUseTaskService.update(toolUseTask,new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number",modify.getTaskNumber()));
        return ResponseData.success();
    }

    /**
     * 工具领用 完成提交 自动分拣
     * WarehouseTurnoverModify
     * */
    @RequestMapping("/tool_apply_commit_over")
    @ResponseBody
    public ResponseData toolApplyCommitRib(String materialSerialNumber,String taskNumber) {

        //更新任务完成
        WmsWarehouseToolUseTask toolUseTask = new WmsWarehouseToolUseTask();
        toolUseTask.setTaskState(StateEnum.THREE.getState());
        wmsWarehouseToolUseTaskService.update(toolUseTask,new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number",taskNumber));

        //添加领用工具信息
        WmsWarehouseToolUseTask wmsWarehouseToolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number", taskNumber));
        WmsToolUse wmsToolUse = new WmsToolUse();
        wmsToolUse.setOperator(wmsWarehouseToolUseTask.getOperator());// 人员信息
        wmsToolUse.setMaterialTypeId(wmsWarehouseToolUseTask.getMaterialTypeId());// 物料类型ID
        wmsToolUse.setMaterialName(wmsWarehouseToolUseTask.getMaterialName());// 物料名称
        wmsToolUse.setMaterialSku(wmsWarehouseToolUseTask.getMaterialSku());// 物料SKU
        wmsToolUse.setMaterialId(wmsWarehouseToolUseTask.getMaterialId());// 物料信息ID
        wmsToolUse.setMaterialSerialNumber(materialSerialNumber);// 物料编码
        wmsToolUse.setDataTime(new Date());// 数据时间
        wmsToolUseService.save(wmsToolUse);

        return ResponseData.success();
    }

    /**
     * 备件执行中的补货任务
     * */
    @RequestMapping("/spare_in_execution")
    @ResponseBody
    public ResponseData spareInExecution(){
        WmsWarehouseReplenishmentTaskResult wr =  wmsWarehouseReplenishmentTaskService.inExecution();
        if (wr == null){
            return ResponseData.error(500, "暂无执行中的补货任务", new WmsWarehouseReplenishmentTaskResult());
        }
        return ResponseData.success(wr);
    }

    /**
     * 备件补货出库
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/spare_out")
    @ResponseBody
    public ResponseData spareOut(String taskNumber){
        return warehouseService.replenishmentCreateOutTask(taskNumber);
    }

    /**
     * 备件补货 分拣-提交
     * WarehouseTurnoverModify
     * */
    @RequestMapping("/sorting-commit")
    @ResponseBody
    public ResponseData sortingCommit(WarehouseTurnoverModify modify){
        return oneTypeCabinetService.padSortingConform2(modify);
    }

    /**
     * 备件补货 分拣-入库
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/sorting-in")
    @ResponseBody
    public ResponseData sortingIn(String taskNumber,String turnoverNumber){
        return warehouseService.replenishmentInTask2(turnoverNumber,taskNumber);
    }

    /**
     *  备件补货 自动分拣记录
     * */
    @RequestMapping("/recentTask")
    @ResponseBody
    public ResponseData recentTask() {
        List<WmsSortingTaskResult> list = this.wmsSortingTaskService.findRecentTask();
        if (list.size() == 0){
            return ResponseData.error("暂无分拣记录");
        }
        return ResponseData.success(list);
    }

    /**
     * todo 自动分拣 提交
     * 1. 创建出库任务并执行
     *
     * 其他接口回调
     * 2. 出库完成 - 创建自动分拣任务 - 关联补货任务
     * 3. 发送分拣任务并执行
     * 4. 分拣完成 - 回调 更新周转箱信息 更新补货任务信息 更新分拣任务信息
     * 5. 创建入库任务并执行
     * 6. 入库完成 - 跟新周转箱和库位信息
     * */
    @RequestMapping("/autoSort")
    @ResponseBody
    public ResponseData autoSort(String taskNumber) {

        WmsWarehouseReplenishmentTaskResult task = wmsWarehouseReplenishmentTaskService.findByTaskNumber(taskNumber);
        if (!Objects.equals("1", task.getTaskState())) {
            return ResponseData.error("此任务未执行");
        }


        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(task.getMaterialTypeId());

        // 创建出库任务
        WmsWarehouseTaskOut out = new WmsWarehouseTaskOut();
        String messageId = RandomStringUtils.randomNumeric(12);
        out.setMessageId(messageId);// 消息识别ID
        out.setOrderType(ApplyType.A.getType());// 订单类别(A工具领用 B补货出库 C出库)
        out.setTaskMg(task.getTaskNumber());// 任务信息
        out.setGoodsType(ApplyType.A.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        out.setMaterialTypeId(task.getMaterialTypeId());// 物料类型ID
        out.setMaterialSku(wmsMaterialType.getMaterialSku());// 物料SKU
        out.setMaterialType(wmsMaterialType.getMaterialType());// 物料类型
        out.setMaterialName(task.getMaterialName());// 物料名称
        out.setmBatch(task.getMBatch()); // 批次
        out.setmNumber(task.getMNumber());// 数量
        out.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        out.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        out.setResTag(StateEnum.ZERO.getState());// 结果标记（0初始 1更新 2结束）
        out.setResStatus(StateEnum.ZERO.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        out.setSortingInfo("B");
        out.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskOutService.save(out);
        // 发送出库任务
        warehouseService.sendTaskOut(messageId);
        return ResponseData.success();
    }

    /**
     * 补货完成
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/task_finished")
    @ResponseBody
    public ResponseData taskFinished(String taskNumber) {
        return warehouseService.replenishmentConformTask(taskNumber);
    }

    /**
     * 清空格口
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/clear_latter")
    @ResponseBody
    public ResponseData clearLatter(String turnoverId,String latticeCode) {

        oneTypeCabinetService.padWarehouseTurnoverCabinetOneCabinet(Long.valueOf(turnoverId),latticeCode);

        return ResponseData.success();
    }
}
