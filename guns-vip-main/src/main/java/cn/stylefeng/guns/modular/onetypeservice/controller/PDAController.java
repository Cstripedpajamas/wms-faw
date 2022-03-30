package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.modular.onetypeservice.request.ApplySpareParts;
import cn.stylefeng.guns.modular.onetypeservice.response.SpareParts;
import cn.stylefeng.guns.modular.onetypeservice.request.WarehouseTurnoverModify;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by li wen ya on 2021/11/17
 */
@RestController
@CrossOrigin
@RequestMapping("/pda")
@Api(description = "PDA逻辑接口")
public class PDAController {

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;

    @GetMapping("/tool")
    @ApiOperation(value = "工具-PDA：1.扫描格口获取信息")
    public ResponseData padScanningTool(@ApiParam(value = "格口编号") @RequestParam String locaSerialNumber){
        return oneTypeCabinetService.findByLocaSerialNumber(locaSerialNumber.trim());
    }

    @GetMapping("/tool-modify")
    @ApiOperation(value = "工具-PDA：2.修改指定格口内容")
    public ResponseData padModifyCabinet(@ApiParam(value = "格口编号") @RequestParam String locaSerialNumber,
                                      @ApiParam(value = "物料信息ID") @RequestParam String toolNumber){
        oneTypeCabinetService.pdaUpdateCabinetContent(locaSerialNumber,toolNumber);
        return ResponseData.success();
    }

    @GetMapping("/spare-start")
    @ApiOperation(value = "备品备件盘点-PDA：1.1获取所有未盘点的任务")
    public ResponseData padSparePartsStart(){
        QueryWrapper<WmsCabinet2CheckTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_state", StateEnum.TWO.getState());
        return oneTypeCabinetService.querySparePartsTask(queryWrapper);
    }

    @GetMapping("/spare-end")
    @ApiOperation(value = "备品备件盘点-PDA：1.2获取所有已盘点的任务")
    public ResponseData padSparePartsEnd(){
        QueryWrapper<WmsCabinet2CheckTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_state", StateEnum.FIVE.getState());
        return oneTypeCabinetService.querySparePartsTask(queryWrapper);
    }

    @GetMapping("/spare-data")
    @ApiOperation(value = "备品备件盘点-PDA：2.获取选中任务的具体信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK",response = SpareParts.class)
    })
    public ResponseData padSpareParts(@ApiParam(value = "任务编号") @RequestParam String taskNumber){
        return oneTypeCabinetService.padScanningSpare(taskNumber);
    }

    @GetMapping("/spare-clean")
    @ApiOperation(value = "备品备件盘点-PDA：3.周转箱上绑定的数据")
    public ResponseData padCleanSpareParts(@ApiParam(value = "周转箱ID") @RequestParam Long turnoverId){
        oneTypeCabinetService.cleanSpareParts(turnoverId);
        return ResponseData.success();
    }

    @GetMapping("/spare-modify")
    @ApiOperation(value = "备品备件盘点-PDA：4.获取选中任务的具体信息")
    public ResponseData pdaModifySpareParts(@ApiParam(value = "修改数量") @RequestParam String number,
                               @ApiParam(value = "周转箱ID") @RequestParam Long turnoverId){
        return oneTypeCabinetService.modifySpareParts(number,turnoverId);
    }

    @GetMapping("/warehouse-turnover")
    @ApiOperation(value = "立库盘点-PDA：1.扫码获取周转箱信息")
    public ResponseData padWarehouseTurnover(@ApiParam(value = "周转箱编号") @RequestParam String turnoverNumber){
        return oneTypeCabinetService.warehouseTurnover(turnoverNumber);
    }

    @GetMapping("/warehouse-clean-all")
    @ApiOperation(value = "立库盘点-PDA：2.清空周转箱")
    public ResponseData padWarehouseTurnoverClean(@ApiParam(value = "周转箱ID") @RequestParam String id){
        oneTypeCabinetService.padWarehouseTurnoverClean(id);
        return ResponseData.success();
    }

    @GetMapping("/warehouse-data")
    @ApiOperation(value = "立库盘点-PDA：3.周转箱格口内容")
    public ResponseData padWarehouseTurnoverCabinet(@ApiParam(value = "周转箱ID") @RequestParam String id,
                                                    @ApiParam(value = "格口编号") @RequestParam String latticeCode){
        return oneTypeCabinetService.padWarehouseTurnoverCabinet(id,latticeCode);
    }

    @GetMapping("/warehouse-clean-one")
    @ApiOperation(value = "立库盘点-PDA：4.单格口清空")
    public ResponseData padWarehouseTurnoverCabinetOne(@ApiParam(value = "周转箱ID") @RequestParam Long id,
                                                       @ApiParam(value = "格口编号") @RequestParam String latticeCode){
        oneTypeCabinetService.padWarehouseTurnoverCabinetOneCabinet(id,latticeCode);
        return ResponseData.success();
    }

    @GetMapping("/warehouse-modify")
    @ApiOperation(value = "立库盘点-PDA：5.单格口修改")
    public ResponseData padWarehouseTurnoverModify(WarehouseTurnoverModify turnoverModify){
        return oneTypeCabinetService.padWarehouseModify(turnoverModify);
    }

    @GetMapping("/dish-query")
    @ApiOperation(value = "立库组盘-PDA：1.调用立库盘点-PDA(1)；2.组盘查询格口货物类型")
    public ResponseData padWarehouseDishQuery(@ApiParam(value = "周转箱ID") @RequestParam Long id,
                                              @ApiParam(value = "格口编号") @RequestParam String latticeCode){
        return oneTypeCabinetService.padWarehouseDish(id,latticeCode);
    }

    @GetMapping("/dish-data")
    @ApiOperation(value = "立库组盘-PDA：3.1 查找工具数据")
    public ResponseData padWarehouseDishToolData(@ApiParam(value = "工具编号") @RequestParam String materialSerialNumber){
        WmsMaterialTool wmsMaterialTool = oneTypeCabinetService.findToolByMaterialSerialNumber(materialSerialNumber);
        return ResponseData.success(wmsMaterialTool);
    }

    @GetMapping("/dish-spare-data")
    @ApiOperation(value = "立库组盘-PDA：3.2 查找备品备件数据")
    public ResponseData padWarehouseDishSparePartsData(){
        return oneTypeCabinetService.padWarehouseDishSparePartsData();
    }

    @GetMapping("/dish-bing")
    @ApiOperation(value = "立库组盘-PDA：4.立即绑定")
    public ResponseData padWarehouseDishConform(WarehouseTurnoverModify warehouseTurnoverModify){
        return oneTypeCabinetService.padWarehouseDishConform(warehouseTurnoverModify);
    }

    @GetMapping("/sorting-tool")
    @ApiOperation(value = "立库分拣-PDA：1.1获取工具分拣任务")
    public ResponseData padSortingTool(){
        return oneTypeCabinetService.padSortingTool();
    }

    @GetMapping("/sorting-spare")
    @ApiOperation(value = "立库分拣-PDA：1.2获取备品备件分拣任务")
    public ResponseData padSortingSpare(){
        return oneTypeCabinetService.padSortingSpare();
    }

    @GetMapping("/sorting-turnover-one")
    @ApiOperation(value = "立库分拣-PDA：2. 提交检查任务是否可分拣")
    public ResponseData padSortingTurnoverOne(@ApiParam(value = "任务编号") @RequestParam String taskNumber,
                                              @ApiParam(value = "任务标识（A:工具 B:备品备件）") @RequestParam String flag){
        return oneTypeCabinetService.padSortingTurnoverOne(taskNumber,flag);
    }

    @GetMapping("/sorting-turnover")
    @ApiOperation(value = "立库分拣-PDA：3 获取周转箱信息")
    public ResponseData padSortingTurnover(@ApiParam(value = "任务编号") @RequestParam String taskNumber,
                                           @ApiParam(value = "周转箱编号") @RequestParam String turnoverNumber,
                                           @ApiParam(value = "任务标识（A:工具 B:备品备件）") @RequestParam String flag){
        return oneTypeCabinetService.padSortingTurnover(taskNumber,turnoverNumber.trim(),flag);
    }

    @GetMapping("/sorting-cabinet")
    @ApiOperation(value = "立库分拣-PDA：4.获取指定格口信息")
    public ResponseData padSortingCabinet(@ApiParam(value = "周转箱ID") @RequestParam Long id,
                                          @ApiParam(value = "格口编号") @RequestParam String latticeCode){
        return oneTypeCabinetService.padWarehouseDish(id,latticeCode);
    }

    @GetMapping("/sorting-conform")
    @ApiOperation(value = "立库分拣-PDA：5.分拣完成")
    public ResponseData padSortingConform(WarehouseTurnoverModify modify){
        return oneTypeCabinetService.padSortingConform(modify);
    }

    @GetMapping("/apply-tool-all")
    @ApiOperation(value = "工具申请-PDA：1.获取所有的工具信息")
    public ResponseData padApplyToolAll(){
        return oneTypeCabinetService.padToolAll();
    }

    @GetMapping("/apply-tool-conform")
    @ApiOperation(value = "工具申请-PDA：2.工具申请提交")
    public ResponseData padApplyToolConform(@ApiParam(value = "人员编号") @RequestParam String serialNumber,
                                       @ApiParam(value = "申请原因") @RequestParam String reason,
                                       @ApiParam(value = "物料类型Id") @RequestParam String materialTypeId){
        oneTypeCabinetService.padToolConform(serialNumber,reason,materialTypeId);
        return ResponseData.success();
    }

    @GetMapping("/apply-back")
    @ApiOperation(value = "工具归还-PDA：1.调用工具申请(1),2.工具归还申请")
    public ResponseData padToolGiveBack(@ApiParam(value = "人员编号") @RequestParam String serialNumber,
                                        @ApiParam(value = "申请原因") @RequestParam String reason,
                                        @ApiParam(value = "物料类型ID") @RequestParam String materialTypeId){
        oneTypeCabinetService.padToolGiveBack(serialNumber,reason,materialTypeId);
        return ResponseData.success();
    }

    @GetMapping("/apply-spare-all")
    @ApiOperation(value = "备品备件申请-PDA：1.获取所有的备品备件信息")
    public ResponseData padApplySpareAll(){
        return oneTypeCabinetService.padApplySpareAll();
    }

    @GetMapping("/apply-spare-conform")
    @ApiOperation(value = "备品备件申请-PDA：2.备品备件提交")
    public ResponseData padApplySpareConform(ApplySpareParts apply){
        return oneTypeCabinetService.padApplySpareConform(apply);
    }

}
