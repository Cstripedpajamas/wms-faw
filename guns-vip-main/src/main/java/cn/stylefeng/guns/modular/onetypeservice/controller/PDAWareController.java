package cn.stylefeng.guns.modular.onetypeservice.controller;

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
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
     * 所有的工备品备件
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

}
