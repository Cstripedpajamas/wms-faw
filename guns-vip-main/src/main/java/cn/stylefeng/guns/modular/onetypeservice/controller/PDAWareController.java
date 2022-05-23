package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.request.ApplySpareParts;
import cn.stylefeng.guns.modular.onetypeservice.request.WarehouseTurnoverModify;
import cn.stylefeng.guns.modular.onetypeservice.response.SpareParts;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskIn;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseReplenishmentTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsSortingTaskService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseReplenishmentTaskService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
            return ResponseData.error("暂无执行中的采购任务");
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
            return ResponseData.error("无此工具信息");
        }
        return ResponseData.success(wmsMaterialTool);
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
     * 所有的工具类型信息
     */
    @RequestMapping("/apply-tool-all")
    @ResponseBody
    public ResponseData padApplyToolAll() {
        return oneTypeCabinetService.padToolAll();
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
        return oneTypeCabinetService.padApplySpareAll();
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
        LayuiPageInfo layuiPageInfo = warehouseService.claimList(serialNumber);
        final List data = layuiPageInfo.getData();
        if (layuiPageInfo.getData().size() == 0){
            return ResponseData.error("工具领用列表为空");
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
            return  ResponseData.error("暂无执行中的补货任务");
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
    public ResponseData sortingIn(String taskNumber){
        return warehouseService.replenishmentInTask(taskNumber);
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

        return ResponseData.success("接口业务暂定");
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
}
