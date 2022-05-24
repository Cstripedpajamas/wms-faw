package cn.stylefeng.guns.modular.onetypeservice.service;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import cn.stylefeng.guns.modular.WebApi.WmsApiService;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.entity.WmsMaterialSpareParts;
import cn.stylefeng.guns.modular.base.materialspareparts.model.result.WmsMaterialSparePartsResult;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.materialtool.model.params.WmsMaterialToolParam;
import cn.stylefeng.guns.modular.base.materialtool.service.WmsMaterialToolService;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypecabinet.entity.*;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.*;
import cn.stylefeng.guns.modular.onetypecabinet.service.*;
import cn.stylefeng.guns.modular.onetypeservice.enums.ApplyType;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodeProviderEnum;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.onetypeservice.request.OpenEmptyDoorParam;
import cn.stylefeng.guns.modular.onetypeservice.request.ApplySpareParts;
import cn.stylefeng.guns.modular.onetypeservice.response.IntelligentCabinet1StockResponse;
import cn.stylefeng.guns.modular.onetypeservice.response.SpareParts;
import cn.stylefeng.guns.modular.onetypeservice.request.WarehouseTurnoverModify;
import cn.stylefeng.guns.modular.onetypeservice.response.ToolClaimModel;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity.WmsReturnApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params.WmsReturnApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.service.WmsReturnApplyService;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.service.WmsCabinet2CheckTaskService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service.WmsCabinet2StockService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.entity.WmsCabinet2Turnover;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service.WmsCabinet2TurnoverService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.entity.WmsCabinet2TurnoverBind;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.params.WmsCabinet2TurnoverBindParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.service.WmsCabinet2TurnoverBindService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity.WmsCabinet2UseTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.params.WmsErrorRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.service.WmsErrorRecordInfoService;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.guns.modular.utils.WebSocket.WebSocket;
import cn.stylefeng.guns.modular.warehousemanage.entity.*;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseStorageTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskInParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseReplenishmentTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverBindResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverResult;
import cn.stylefeng.guns.modular.warehousemanage.service.*;
import cn.stylefeng.guns.sys.modular.system.entity.Dict;
import cn.stylefeng.guns.sys.modular.system.entity.DictType;
import cn.stylefeng.guns.sys.modular.system.service.DictService;
import cn.stylefeng.guns.sys.modular.system.service.DictTypeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by li wen ya on 2021/11/2
 * Ⅰ类内阁服务
 *
 * 换新任务表（wmsCabinet1RenewTask）
 * 物料类型信息（wmsMaterialType）
 * 工具-物料信息（wmsMaterialTool）
 * 库存信息表（wmsIntelligentCabinet1Stock）
 * 人员信息（wmsUser）
 * 异常信息记录（wmsErrorRecordInfo）
 */
@Component
@Slf4j
public class OneTypeCabinetService {

    private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);

    @Autowired
    private DictService dictService;

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private Map<String,Code> mapCodeGenerator;

    @Autowired
    private WmsUserService wmsUserService;

    @Autowired
    private WmsMaterialToolService wmsMaterialToolService;

    @Autowired
    private WmsErrorRecordInfoService wmsErrorRecordInfoService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private WmsCabinet1CheckTaskService wmsCabinet1CheckTaskService;

    @Autowired
    private WmsCabinet1RenewTaskService wmsCabinet1RenewTaskService;

    @Autowired
    private WmsCabinet1RepairReturnTaskService wmsCabinet1RepairReturnTaskService;

    @Autowired
    private WmsCabinet1RepairTaskService wmsCabinet1RepairTaskService;

    @Autowired
    private WmsCabinet1ReturnTaskService wmsCabinet1ReturnTaskService;

    @Autowired
    private WmsIntelligentCabinet1LatticeTypeService wmsIntelligentCabinet1LatticeTypeService;

    @Autowired
    private WmsIntelligentCabinet1StockService wmsIntelligentCabinet1StockService;

    @Autowired
    private WmsReturnApplyService wmsReturnApplyService;

    @Autowired
    private WmsCabinet2CheckTaskService wmsCabinet2CheckTaskService;

    @Autowired
    private WmsCabinet2TurnoverService wmsCabinet2TurnoverService;

    @Autowired
    private WmsCabinet2StockService wmsCabinet2StockService;

    @Autowired
    private WmsCabinet2TurnoverBindService wmsCabinet2TurnoverBindService;

    @Autowired
    private WmsWarehouseTurnoverService wmsWarehouseTurnoverService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WmsMaterialSparePartsService wmsMaterialSparePartsService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @Autowired
    private WmsCabinet2UseTaskService wmsCabinet2UseTaskService;

    @Autowired
    private WmsWarehouseReplenishmentTaskService wmsWarehouseReplenishmentTaskService;

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    @Autowired
    private WmsWarehouseTaskOutService wmsWarehouseTaskOutService;

    @Autowired
    private WmsToolUseService wmsToolUseService;

    @Autowired
    private WmsWarehouseTaskInService wmsWarehouseTaskInService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WmsWarehousePurchaseStorageTaskService wmsWarehousePurchaseStorageTaskService;
    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    // 获取工具异常信息接口
    public ResponseData toolErrorContent() {
        QueryWrapper<DictType> objectQueryDictType = new QueryWrapper<>();
        objectQueryDictType.select("dict_type_id");
        objectQueryDictType.eq("code","TOOL_REPAIR");
        DictType dictType = dictTypeService.getOne(objectQueryDictType);
        QueryWrapper<Dict> objectQueryDict = new QueryWrapper<>();
        objectQueryDict.eq("dict_type_id",dictType.getDictTypeId());
        List<Dict> values = dictService.list(objectQueryDict);
        return ResponseData.success(values);
    }

    // 创建换新任务
    public String createRenewTask(String serialNumber) {
        WmsCabinet1RenewTaskParam param = new WmsCabinet1RenewTaskParam();
        String taskNumber = mapCodeGenerator.get(CodeProviderEnum.renewTaskCode.getProvider()).createCode(null);
        param.setTaskNumber(taskNumber);
        param.setOperator(serialNumber);
        param.setOperationTime(new Date());
        param.setTaskState(StateEnum.ONE.getState());
        param.setErrorTrag(StateEnum.ZERO.getState());
        param.setCreateTime(new Date());
        param.setUpdateTime(new Date());
        wmsCabinet1RenewTaskService.add(param);
        return taskNumber;
    }

    // 查找工具数据 - 根据使用状态与工具编号
    public WmsMaterialTool findToolByMaterialSerialNumber(String materialSerialNumber) {
        return wmsMaterialToolService.getOne(new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",materialSerialNumber).eq("data_state",StateEnum.ZERO.getState()));
    }

    // 打开空库门
    public ResponseData openEmptyDoor(OpenEmptyDoorParam doorParam) {
        WmsCabinet1RenewTask wmsCabinet1RenewTask = findByTaskNumber(doorParam.getTaskNumber());
        WmsMaterialTool wmsMaterialTool = findToolByMaterialSerialNumber(doorParam.getMaterialSerialNumber());
        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(wmsMaterialTool.getMaterialTypeId());
        if(checkEmpty(wmsMaterialType.getLatticeMouthType())){// 1.检查库位是否已满
            updateRenewEnd(doorParam.getTaskNumber());
            return ResponseData.error("智能柜库位已满 无空库位 无法进行换新");
        }
        if(checkSameTypeTool(wmsMaterialTool,wmsMaterialType)){// 2.检查是否有同类型的工具
            updateRenewEnd(doorParam.getTaskNumber());
            return ResponseData.error("该旧工具无同类型库存 无法进行换新");
        }
        // 3.校验是否本人操作 否异常任务结束
        if(!Objects.equals(doorParam.getSerialNumber(), wmsCabinet1RenewTask.getOperator())){
            updateRenewEnd(doorParam.getTaskNumber());
            saveErrorInfo("换新任务","登录人员与工具绑定人员不同",doorParam.getSerialNumber());
            return ResponseData.error("此任务不是同一个人操作");
        }
        // 4.查找空库位 - 根据 库位状态为空、库位类型与工具相同
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("lattice_type",Long.valueOf(wmsMaterialType.getLatticeMouthType())).eq("loca_state",StateEnum.ZERO.getState()).last("limit 1"));
        // 5.更新任务 更新工具 更新库位
        updateRenewTaskAndToolAndCabinet(wmsIntelligentCabinet1Stock,wmsMaterialTool,doorParam);
        // 6.发送打开格口消息
        Map<String,Object> mapLocaSerialNumber = getDeclensionParam(wmsIntelligentCabinet1Stock.getLocaSerialNumber());
        cachedThreadPool.execute(new OpenDoorRunnable(mapLocaSerialNumber));
        // 7.监听格口
        cachedThreadPool.execute(new ListeningDoorRunnable(doorParam.getTaskNumber(),wmsIntelligentCabinet1Stock.getLocaSerialNumber(),StateEnum.ZERO));
        // 8.删除领用工具信息
        wmsToolUseService.remove(new QueryWrapper<WmsToolUse>().eq("material_serial_number",doorParam.getMaterialSerialNumber()));

        IntelligentCabinet1StockResponse intelligentCabinet1StockResponse = new IntelligentCabinet1StockResponse();
        ToolUtil.copyProperties(wmsIntelligentCabinet1Stock,intelligentCabinet1StockResponse);

        List<String> localNum = getLocalNumberLeft();
        if(localNum.contains(wmsIntelligentCabinet1Stock.getLocaSerialNumber())){
            intelligentCabinet1StockResponse.setLeftRight(StateEnum.TWO.getState());
        } else {
            intelligentCabinet1StockResponse.setLeftRight(StateEnum.ONE.getState());
        }

        // todo 推送前台库门关闭
        Map<String, Object> mapTwo = new HashMap<>();
        mapTwo.put("code",StateEnum.ONE.getState());
        String objectTwo = JSONObject.toJSONString(mapTwo);
        WebSocket.sendMessageOfSession1(objectTwo);// 1.开 2.关

        return ResponseData.success(intelligentCabinet1StockResponse);
    }

    // 5.更新任务 更新工具 更新库位
    private void updateRenewTaskAndToolAndCabinet(WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock,
                                                  WmsMaterialTool wmsMaterialTool, OpenEmptyDoorParam doorParam) {
        // 更新任务信息
        WmsCabinet1RenewTask task = new WmsCabinet1RenewTask();
        task.setTaskState(StateEnum.TWO.getState());// 任务状态
        task.setMaterialTypeId(wmsMaterialTool.getMaterialTypeId());// 物料类型ID
        task.setMaterialName(wmsMaterialTool.getMaterialName());// 物料名称
        task.setMaterialSku(wmsMaterialTool.getMaterialSku());// 物料SKU
        task.setStorageStockId(String.valueOf(wmsIntelligentCabinet1Stock.getId()));// 存储-库存信息ID
        task.setStorageLocaSerialNumber(wmsIntelligentCabinet1Stock.getLocaSerialNumber());// 存储-库位编号
        task.setoMaterialId(String.valueOf(wmsMaterialTool.getId()));// 旧-物料信息ID
        task.setoMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());// 旧-物料编码
        task.setToolErrorContent(doorParam.getToolErrorContent());// 工具异常内容
        wmsCabinet1RenewTaskService.update(task,new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",doorParam.getTaskNumber()));
        // 更新工具状态信息
        if(!Objects.equals(doorParam.getMaterialState(),wmsMaterialTool.getMaterialState())){
            WmsMaterialTool param = new WmsMaterialTool();
            param.setMaterialState(doorParam.getMaterialState());
            wmsMaterialToolService.update(param,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",doorParam.getMaterialSerialNumber()));
        }
        // 更新库位信息
        wmsIntelligentCabinet1Stock.setLocaState(StateEnum.TWO.getState());// 库位锁定
        wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ZERO.getState());// 格口状态打开
        wmsIntelligentCabinet1Stock.setMaterialTypeId(String.valueOf(wmsMaterialTool.getMaterialTypeId()));// 库存-物料类型ID
        wmsIntelligentCabinet1Stock.setMaterialId(String.valueOf(wmsMaterialTool.getId()));// 库存-物料信息ID
        wmsIntelligentCabinet1Stock.setMaterialName(wmsMaterialTool.getMaterialName());// 物料名称
        wmsIntelligentCabinet1Stock.setMaterialSku(wmsMaterialTool.getMaterialSku());// 物料SKU
        wmsIntelligentCabinet1Stock.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());// 物料编码
        wmsIntelligentCabinet1Stock.setmNumber(StateEnum.ONE.getState());// 数量（默认 1）
        if(Objects.equals(doorParam.getMaterialState(),StateEnum.ONE.getState())){
            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ONE.getState());// 工具状态（0维修 1使用）
        } else {
            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ZERO.getState());// 工具状态（0维修 1使用）
        }
        wmsIntelligentCabinet1Stock.setCreateTime(new Date());// 数据时间
        WmsIntelligentCabinet1StockParam param = new WmsIntelligentCabinet1StockParam();
        ToolUtil.copyProperties(wmsIntelligentCabinet1Stock,param);
        wmsIntelligentCabinet1StockService.update(param);
    }

    // 获取格口参数
    private Map<String, Object> getDeclensionParam(String locaSerialNumber) {
        Map<String,String> param = new HashMap<>();
        param.put("LocationId",locaSerialNumber);
        List<Map<String,String>> list = new ArrayList<>();
        list.add(param);
        Map<String,Object> map = new HashMap<>();
        map.put("List",list);
        return map;
    }

    // 查找根据任务号换新任务
    private WmsCabinet1RenewTask findByTaskNumber(String taskNumber) {
        return wmsCabinet1RenewTaskService.getOne(new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",taskNumber));
    }

    // 取消任务
    public void updateRenewEnd(String taskNumber) {
        WmsCabinet1RenewTask task = new WmsCabinet1RenewTask();
        task.setTaskState(StateEnum.SIX.getState());
        wmsCabinet1RenewTaskService.update(task,new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",taskNumber));
    }

    // 检查是否有同类型的工具 - 根据物料类型、工具状态(使用)、格口类型
    private boolean checkSameTypeTool(WmsMaterialTool wmsMaterialTool, WmsMaterialType wmsMaterialType) {
        int countUseTool = wmsIntelligentCabinet1StockService.count(new QueryWrapper<WmsIntelligentCabinet1Stock>()
                .eq("material_type_id",wmsMaterialTool.getMaterialTypeId())//库存-物料类型ID
                .eq("tool_state",StateEnum.ONE.getState())// 工具状态（0维修 1使用）
                .eq("lattice_type",wmsMaterialType.getLatticeMouthType()));// 格口类型ID
        return countUseTool <= 0;
    }

    // 检查库位是否已满 - 根据库位状态与格口类型
    private boolean checkEmpty(String latticeType) {
        int countEmptyCabinet = wmsIntelligentCabinet1StockService.count(new QueryWrapper<WmsIntelligentCabinet1Stock>()
                .eq("loca_state",StateEnum.ZERO.getState())
                .eq("lattice_type",latticeType));
        return countEmptyCabinet <= 0;
    }

    // 打开指定库门
    public Declension openDoor(String locaSerialNumber) {
        Map<String,Object> map = getDeclensionParam(locaSerialNumber);
        return WmsApiService.openDeclension(map);
    }

    // 没有取到想要的工具回填重新打开
    public ResponseData errorDeal(String taskNumber) {
        WmsCabinet1RenewTask wmsCabinet1RenewTask = wmsCabinet1RenewTaskService.getOne(new QueryWrapper<WmsCabinet1RenewTask>()
                .eq("task_number",taskNumber).ne("error_trag",StateEnum.ONE.getState()));
        if(wmsCabinet1RenewTask != null){ // 有任务并且任务不为异常
            // 删除领用工具信息
            wmsToolUseService.remove(new QueryWrapper<WmsToolUse>().eq("material_serial_number",wmsCabinet1RenewTask.getnMaterialSerialNumber()));
            // 更新物料信息
            WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getOne(new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsCabinet1RenewTask.getnMaterialSerialNumber()));
            wmsMaterialTool.setStorageState(StateEnum.ONE.getState());// 在库内
            wmsMaterialTool.setStorageAddress(StateEnum.ONE.getState());// Ⅰ类库
            wmsMaterialTool.setMaterialState(StateEnum.TWO.getState());// 工具维修
            WmsMaterialToolParam toolParam = new WmsMaterialToolParam();
            ToolUtil.copyProperties(wmsMaterialTool,toolParam);
            wmsMaterialToolService.update(toolParam);
            // 更新格口信息
            WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = new WmsIntelligentCabinet1Stock();
            wmsIntelligentCabinet1Stock.setLocaState(StateEnum.ONE.getState());// 空闲格口
            wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ONE.getState());// 关闭格口
            wmsIntelligentCabinet1Stock.setMaterialTypeId(wmsMaterialTool.getMaterialTypeId());// 物料类型ID
            wmsIntelligentCabinet1Stock.setMaterialId(String.valueOf(wmsMaterialTool.getId()));// 物料ID
            wmsIntelligentCabinet1Stock.setMaterialName(wmsMaterialTool.getMaterialName());// 物料名称
            wmsIntelligentCabinet1Stock.setMaterialSku(wmsMaterialTool.getMaterialSku());// 物料SKU
            wmsIntelligentCabinet1Stock.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());// 物料编码
            wmsIntelligentCabinet1Stock.setmNumber(StateEnum.ONE.getState());// 数量（默认 1）
            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ZERO.getState());// 工具维修
            wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",wmsCabinet1RenewTask.getTaskLocaSerialNumber()));
            WmsMaterialType type = wmsMaterialTypeService.getById(wmsMaterialTool.getMaterialTypeId());
            // 查找有料库位
            WmsIntelligentCabinet1Stock haveMaterialCabinet = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>()
                    .eq("loca_state",StateEnum.ONE.getState()).eq("tool_state",StateEnum.ONE.getState())
                    .eq("lattice_type",type.getLatticeMouthType()).last("limit 1"));
            // todo 打开格口
            Map<String,Object> map = getDeclensionParam(haveMaterialCabinet.getLocaSerialNumber());
            cachedThreadPool.execute(new OpenDoorRunnable(map));
            // 记录异常
            saveErrorInfo("换新任务","取出的获取不符合条件",wmsCabinet1RenewTask.getOperator());
            // 更新任务
            wmsCabinet1RenewTask.setErrorTrag(StateEnum.ONE.getState()); // 异常标志
            wmsCabinet1RenewTask.setTaskState(StateEnum.FOUR.getState());// 开始取货
            wmsCabinet1RenewTask.setnMaterialId(haveMaterialCabinet.getMaterialId());
            wmsCabinet1RenewTask.setnMaterialSerialNumber(haveMaterialCabinet.getMaterialSerialNumber());
            wmsCabinet1RenewTask.setTaskStockId(String.valueOf(haveMaterialCabinet.getId()));
            wmsCabinet1RenewTask.setTaskLocaSerialNumber(haveMaterialCabinet.getLocaSerialNumber());
            WmsCabinet1RenewTaskParam renewTaskParam = new WmsCabinet1RenewTaskParam();
            ToolUtil.copyProperties(wmsCabinet1RenewTask,renewTaskParam);
            wmsCabinet1RenewTaskService.update(renewTaskParam);
            // 添加领用工具信息
            WmsToolUse wmsToolUse = new WmsToolUse();
            wmsToolUse.setOperator(wmsCabinet1RenewTask.getOperator());// 人员信息
            wmsToolUse.setMaterialTypeId(haveMaterialCabinet.getMaterialTypeId());// 物料类型ID
            wmsToolUse.setMaterialName(haveMaterialCabinet.getMaterialName());// 物料名称
            wmsToolUse.setMaterialSku(haveMaterialCabinet.getMaterialSku());// 物料SKU
            wmsToolUse.setMaterialId(haveMaterialCabinet.getMaterialId());// 物料信息ID
            wmsToolUse.setMaterialSerialNumber(haveMaterialCabinet.getMaterialSerialNumber());// 物料编码
            wmsToolUse.setDataTime(new Date());// 数据时间
            wmsToolUseService.save(wmsToolUse);
            // 更新格口信息
            haveMaterialCabinet.setLocaState(StateEnum.TWO.getState());// 格口锁定
            haveMaterialCabinet.setLatticeState(StateEnum.ZERO.getState());// 关闭格口
            WmsIntelligentCabinet1StockParam cabinet1Stock = new WmsIntelligentCabinet1StockParam();
            ToolUtil.copyProperties(haveMaterialCabinet,cabinet1Stock);
            wmsIntelligentCabinet1StockService.update(cabinet1Stock);
            cachedThreadPool.execute(new ListeningDoorRunnable(wmsCabinet1RenewTask.getTaskNumber(),haveMaterialCabinet.getLocaSerialNumber(),StateEnum.TWO));
            // 推送前台库门打开
            Map<String, Object> mapTwo = new HashMap<>();
            mapTwo.put("code",StateEnum.ONE.getState());
            String objectTwo = JSONObject.toJSONString(mapTwo);
            WebSocket.sendMessageOfSession1(objectTwo);// 1.开 2.关
            IntelligentCabinet1StockResponse intelligentCabinet1StockResponse = new IntelligentCabinet1StockResponse();
            ToolUtil.copyProperties(haveMaterialCabinet,intelligentCabinet1StockResponse);
            List<String> localNum = getLocalNumberLeft();
            if(localNum.contains(haveMaterialCabinet.getLocaSerialNumber())){
                intelligentCabinet1StockResponse.setLeftRight(StateEnum.TWO.getState());
            } else {
                intelligentCabinet1StockResponse.setLeftRight(StateEnum.ONE.getState());
            }
            return ResponseData.success(intelligentCabinet1StockResponse);
        } else {
            return ResponseData.error("任务不可重复异常操作");
        }
    }

    /**
     * 异常信息存储
     * @param errorType 异常类型
     * @param errorDescribe 异常描述
     * @param operator 操作人员
     */
    private void saveErrorInfo(String errorType,String errorDescribe,String operator){
        WmsErrorRecordInfoParam info = new WmsErrorRecordInfoParam();
        info.setErrorType(errorType); // 异常类型
        info.setErrorDescribe(errorDescribe); // 异常描述
        info.setOperator(operator); // 操作人
        info.setOperationTime(new Date()); // 操作时间
        info.setCreateTime(new Date()); // 数据时间
        wmsErrorRecordInfoService.add(info);
    }

    // 确认任务结束
    public void confirmFinishTask(String taskNumber,String autoFlag) {
        WmsCabinet1RenewTask renewTask = new WmsCabinet1RenewTask();
        renewTask.setTaskState(StateEnum.SIX.getState());
        renewTask.setAutoFlag(autoFlag);
        wmsCabinet1RenewTaskService.update(renewTask,new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",taskNumber));
    }

    // 归还 - 任务列表
    public LayuiPageInfo wmsCabinet1RepairReturnTaskList(String username) {
        WmsCabinet1ReturnTaskParam wmsCabinet1RepairReturnTaskParam = new WmsCabinet1ReturnTaskParam();
        wmsCabinet1RepairReturnTaskParam.setOperator(username);
        return wmsCabinet1ReturnTaskService.findPageBySpec(wmsCabinet1RepairReturnTaskParam);
    }

    // 归还 - 申请列表
    public LayuiPageInfo wmsReturnApplyList(String serialNumber) {
        WmsReturnApplyParam param = new WmsReturnApplyParam();
        param.setOperator(serialNumber);
        return wmsReturnApplyService.findPageBySpec(param);
    }

    // 归还 - 更新任务状态
    public void updateCabinet1ReturnTask(String taskNumber, String taskState) {
        WmsCabinet1ReturnTask task = new WmsCabinet1ReturnTask();
        task.setTaskState(taskState);
        task.setOperationTime(new Date());
        wmsCabinet1ReturnTaskService.update(task,new QueryWrapper<WmsCabinet1ReturnTask>().eq("task_number",taskNumber));
    }

    // 归还 - 确认归还逻辑处理
    public ResponseData conformCabinet1ReturnTask(String taskNumber, String serialNumber, String materialState, String materialSerialNumber) {
        WmsCabinet1ReturnTask wmsCabinet1ReturnTask = wmsCabinet1ReturnTaskService.getOne(new QueryWrapper<WmsCabinet1ReturnTask>().eq("task_number",taskNumber));
        WmsMaterialTool wmsMaterialTool = findToolByMaterialSerialNumber(materialSerialNumber);
        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(wmsMaterialTool.getMaterialTypeId());
        if(checkEmpty(wmsMaterialType.getLatticeMouthType())){// 1.检查库位是否已满
            updateCabinet1ReturnTask(taskNumber,StateEnum.FOUR.getState());
            return ResponseData.error("智能柜库位已满 无空库位 无法进行归还");
        }
        if(!Objects.equals(serialNumber, wmsCabinet1ReturnTask.getOperator())){// 2.校验是否本人操作 否异常任务结束
            updateCabinet1ReturnTask(taskNumber,StateEnum.FOUR.getState());
            saveErrorInfo("归还任务","工具绑定人员与操作人员不同",serialNumber);
            return ResponseData.error("此任务不是同一个人操作");
        }
        // 1.更新物料信息状态
        if(!Objects.equals(wmsMaterialTool.getMaterialState(),materialState)){
            wmsMaterialTool.setMaterialState(materialState);
            wmsMaterialToolService.update(wmsMaterialTool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",materialSerialNumber));
        }
        // 2.更新库位信息
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>()
                .eq("lattice_type",wmsMaterialType.getLatticeMouthType())
                .eq("loca_state",StateEnum.ZERO.getState()).last("limit 1"));
        wmsIntelligentCabinet1Stock.setLocaState(StateEnum.TWO.getState());
        wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ZERO.getState());
        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>()
                .eq("loca_serial_number",wmsIntelligentCabinet1Stock.getLocaSerialNumber()));
        // 3.更新任务信息
        WmsCabinet1ReturnTask returnTask = new WmsCabinet1ReturnTask();
        returnTask.setMaterialTypeId(String.valueOf(wmsMaterialType.getId()));// 物料类型ID
        returnTask.setMaterialName(wmsMaterialTool.getMaterialName());// 物料名称
        returnTask.setMaterialSku(wmsMaterialTool.getMaterialSku());// 物料SKU
        returnTask.setMaterialId(String.valueOf(wmsMaterialTool.getId()));// 物料信息ID
        returnTask.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());// 物料编码
        returnTask.setStockId(String.valueOf(wmsIntelligentCabinet1Stock.getId()));// 库存信息ID
        returnTask.setLocaNumber(wmsIntelligentCabinet1Stock.getLocaSerialNumber());// 库位编号
        returnTask.setTaskState(StateEnum.TWO.getState());// 任务状态(0初始 1开始 2开始存储 3存储完成 4结束 )
        wmsCabinet1ReturnTaskService.update(returnTask,new QueryWrapper<WmsCabinet1ReturnTask>().eq("task_number",taskNumber));
        // 4.打开格口
        Map<String,Object> map = getDeclensionParam(wmsIntelligentCabinet1Stock.getLocaSerialNumber());
        cachedThreadPool.execute(new OpenDoorRunnable(map));
        cachedThreadPool.execute(new ListeningDoorRunnable(taskNumber,wmsIntelligentCabinet1Stock.getLocaSerialNumber(),StateEnum.FOUR));
        // 5.删除领用工具信息
        wmsToolUseService.remove(new QueryWrapper<WmsToolUse>().eq("material_serial_number",materialSerialNumber));
        // todo 推送前台格口打开
        Map<String, Object> mapTwo = new HashMap<>();
        mapTwo.put("code",StateEnum.ONE.getState());
        String objectTwo = JSONObject.toJSONString(mapTwo);
        WebSocket.sendMessageOfSession1(objectTwo);// 1.开 2.关
        IntelligentCabinet1StockResponse intelligentCabinet1StockResponse = new IntelligentCabinet1StockResponse();
        ToolUtil.copyProperties(wmsIntelligentCabinet1Stock,intelligentCabinet1StockResponse);
        List<String> localNum = getLocalNumberLeft();
        if(localNum.contains(wmsIntelligentCabinet1Stock.getLocaSerialNumber())){
            intelligentCabinet1StockResponse.setLeftRight(StateEnum.TWO.getState());
        } else {
            intelligentCabinet1StockResponse.setLeftRight(StateEnum.ONE.getState());
        }
        return ResponseData.success(intelligentCabinet1StockResponse);
    }

    // 维修 - 获取库存中维修状态的列表
    public LayuiPageInfo wmsCabinet1RepairStateList(WmsIntelligentCabinet1StockParam param) {
        param.setToolState(StateEnum.ZERO.getState());// 维修状态的工具
        return wmsIntelligentCabinet1StockService.findPageBySpec(param);
    }

    // 维修 - 创建维修任务
    public ResponseData createRepairTask(String serialNumber,String locaSerialNumber) {
//        WmsIntelligentCabinet1Stock stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
        WmsCabinet1RepairTaskParam taskParam = new WmsCabinet1RepairTaskParam();
        String taskNumber = mapCodeGenerator.get(CodeProviderEnum.repairCode.getProvider()).createCode(null);
        taskParam.setTaskNumber(taskNumber);
//        taskParam.setMaterialTypeId(stock.getMaterialTypeId());
//        taskParam.setMaterialName(stock.getMaterialName());
//        taskParam.setMaterialSku(stock.getMaterialSku());
//        taskParam.setMaterialId(String.valueOf(stock.getMaterialId()));
//        taskParam.setMaterialSerialNumber(stock.getMaterialSerialNumber());
//        taskParam.setStockId(String.valueOf(stock.getId()));
        taskParam.setLocaNumber(locaSerialNumber);
        taskParam.setOperator(serialNumber);
//        taskParam.setOperationTime(new Date());
        taskParam.setTaskState(StateEnum.ZERO.getState());
        wmsCabinet1RepairTaskService.add(taskParam);
        return updateRepairTask(taskNumber);
    }

    private List<String> getLocalNumberLeft(){
        // "x0103","x0104","x0105","x0106","x0107","x0108","x0109","x0110"
        return Arrays.asList("x0001","x0002","x0003","x0004","x0005","x0006","x0007","x0008","x0009","x0010"
                , "x0011","x0012","x0013","x0014","x0015","x0016","x0017","x0018","x0019","x0020"
                ,"x0021","x0022","x0023","x0024","x0025","x0026","x0027","x0028","x0029","x0030"
                ,"x0031","x0032","x0033","x0034","x0035","x0036","x0037","x0038","x0039","x0040"
                ,"x0041","x0042","x0043","x0044","x0045","x0046","x0047","x0048","x0049","x0050"
                ,"x0051","x0052","x0053","x0054","x0055","x0056","x0057","x0058","x0059","x0060"
                ,"x0061","x0062","x0063","x0064","x0065","x0066","x0067","x0068","x0069","x0070"
                ,"x0071","x0072","x0073","x0074","x0075","x0076","x0077","x0078","x0079","x0080"
                ,"x0081","x0082","x0083","x0084","x0085","x0086","x0087","x0088","x0089","x0090"
                ,"x0091","x0092","x0093","x0094","x0095","x0096","x0097","x0098","x0099","x0100"
                ,"x0101","x0102");
    }

    // 维修 - 更新维修任务状态并且打开格口
    private ResponseData updateRepairTask(String taskNumber) {
        // 更新任务信息
        WmsCabinet1RepairTask repairTask = wmsCabinet1RepairTaskService.getOne(new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
        repairTask.setTaskState(StateEnum.TWO.getState());
        wmsCabinet1RepairTaskService.update(repairTask,new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
        // 更新库位信息
        WmsIntelligentCabinet1Stock cabinet1Stock = new WmsIntelligentCabinet1Stock();
        cabinet1Stock.setLocaState(StateEnum.TWO.getState());// 库位锁定
        cabinet1Stock.setLatticeState(StateEnum.ZERO.getState()); // 库位开启
        String[] locaNumbers = repairTask.getLocaNumber().split(",");
        wmsIntelligentCabinet1StockService.update(cabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().in("loca_serial_number",Arrays.asList(locaNumbers)));
        // 打开库门
        List<Map<String,String>> list = new ArrayList<>();
        for (String locaNumber : locaNumbers) {
            Map<String,String> param = new HashMap<>();
            param.put("LocationId",locaNumber);
            list.add(param);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("List",list);
        cachedThreadPool.execute(new OpenDoorRunnable(map));
        // 监听库门关闭
        List<String> localNum = getLocalNumberLeft();
        boolean left = false, right = false;
        for(String localNumber : locaNumbers){
            if(localNum.contains(localNumber)){
                right = true;
            } else {
                left = true;
            }
        }
        cachedThreadPool.execute(new ListeningDoorRepairRunnable(taskNumber,Arrays.asList(locaNumbers)));
        // todo 推送前台格口打开
        Map<String, Object> mapTwo = new HashMap<>();
        mapTwo.put("taskNumber",taskNumber);
        mapTwo.put("code",StateEnum.ONE.getState());
        if(left && right){
            mapTwo.put("leftRight",StateEnum.THREE.getState());
        } else {
            if(left){
                mapTwo.put("leftRight",StateEnum.ONE.getState());
            } else {
                mapTwo.put("leftRight",StateEnum.TWO.getState());
            }
        }
        return ResponseData.success(mapTwo);
    }

    // 维修 - 工具类型
    public LayuiPageInfo repairToolList() {
        WmsMaterialToolParam materialTool = new WmsMaterialToolParam();
        materialTool.setMaterialState(StateEnum.TWO.getState());
        return wmsMaterialToolService.findPageBySpec(materialTool);
    }

    // 维修 - 获取物料类型名称
    public String findByMaterTypeId(String materialTypeId) {
        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(materialTypeId);
        return wmsMaterialType.getMaterialType();
    }

    // 维修归还 - 任务创建
    public ResponseData createRepairGiveBack(String username) {
        WmsCabinet1RepairReturnTaskParam repairReturnTaskParam = new WmsCabinet1RepairReturnTaskParam();
        String taskNumber = mapCodeGenerator.get(CodeProviderEnum.repairReturnCode.getProvider()).createCode(null);
        repairReturnTaskParam.setTaskNumber(taskNumber);
        repairReturnTaskParam.setOperator(username);
        repairReturnTaskParam.setOperationTime(new Date());
        repairReturnTaskParam.setTaskState(StateEnum.ONE.getState());
        wmsCabinet1RepairReturnTaskService.add(repairReturnTaskParam);
        return ResponseData.success(taskNumber);
    }

    // 维修归还 - 任务取消按钮 - 结束任务
    public void cancelRepairGiveBack(String taskNumber) {
        WmsCabinet1RepairReturnTask repairReturnTask = new WmsCabinet1RepairReturnTask();
        repairReturnTask.setTaskState(StateEnum.FOUR.getState());// 任务结束
        wmsCabinet1RepairReturnTaskService.update(repairReturnTask,new QueryWrapper<WmsCabinet1RepairReturnTask>().eq("task_number",taskNumber));
    }

    // 维修归还 - 确认按钮
    public ResponseData confirmRepairGiveBack(String taskNumber, String materialSerialNumber) {
        WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getOne(new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",materialSerialNumber));
        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(wmsMaterialTool.getMaterialTypeId());
        WmsCabinet1RepairReturnTask repairReturnTask = new WmsCabinet1RepairReturnTask();
        // 更新库位 查询空库位校验
        if(checkEmpty(wmsMaterialType.getLatticeMouthType())){// 1.检查库位是否已满
            repairReturnTask.setTaskState(StateEnum.FOUR.getState());
            wmsCabinet1RepairReturnTaskService.update(repairReturnTask,new QueryWrapper<WmsCabinet1RepairReturnTask>().eq("task_number",taskNumber));
            return ResponseData.error("智能柜库位已满 无空库位 无法进行换新");
        }
        // 查找空库位 - 根据 库位状态为空、库位类型与工具相同
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("lattice_type",Long.valueOf(wmsMaterialType.getLatticeMouthType())).eq("loca_state",StateEnum.ZERO.getState()).last("limit 1"));
        // 更新任务
        repairReturnTask.setMaterialTypeId(wmsMaterialTool.getMaterialTypeId());// 物料类型ID
        repairReturnTask.setMaterialName(wmsMaterialTool.getMaterialName());// 物料名称
        repairReturnTask.setMaterialSku(wmsMaterialTool.getMaterialSku());// 物料SKU
        repairReturnTask.setMaterialId(String.valueOf(wmsMaterialTool.getId()));// 物料信息ID
        repairReturnTask.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());// 物料编码
        repairReturnTask.setStockId(String.valueOf(wmsIntelligentCabinet1Stock.getId()));// 库存信息ID
        repairReturnTask.setLocaNumber(wmsIntelligentCabinet1Stock.getLocaSerialNumber());// 库位编号
        repairReturnTask.setTaskState(StateEnum.TWO.getState());// 任务状态(0初始 1开始 2开始存储 3存储完成 4结束 )
        wmsCabinet1RepairReturnTaskService.update(repairReturnTask,new QueryWrapper<WmsCabinet1RepairReturnTask>().eq("task_number",taskNumber));
        // 更新库位信息
        wmsIntelligentCabinet1Stock.setLocaState(StateEnum.TWO.getState());// 锁定状态
        wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ZERO.getState());// 格口开启
        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",wmsIntelligentCabinet1Stock.getLocaSerialNumber()));
        // 发送开启格口
        Map<String,Object> map = getDeclensionParam(wmsIntelligentCabinet1Stock.getLocaSerialNumber());
        cachedThreadPool.execute(new OpenDoorRunnable(map));
        // 监听格口关闭指令
        cachedThreadPool.execute(new ListeningDoorRunnable(taskNumber,wmsIntelligentCabinet1Stock.getLocaSerialNumber(),StateEnum.SIX));
        // todo 推送前台格口打开
        Map<String, Object> mapTwo = new HashMap<>();
        mapTwo.put("code",StateEnum.ONE.getState());
        String objectTwo = JSONObject.toJSONString(mapTwo);
        WebSocket.sendMessageOfSession1(objectTwo);// 1.开 2.关

        IntelligentCabinet1StockResponse intelligentCabinet1StockResponse = new IntelligentCabinet1StockResponse();
        ToolUtil.copyProperties(wmsIntelligentCabinet1Stock,intelligentCabinet1StockResponse);
        List<String> localNum = getLocalNumberLeft();
        if(localNum.contains(wmsIntelligentCabinet1Stock.getLocaSerialNumber())){
            intelligentCabinet1StockResponse.setLeftRight(StateEnum.TWO.getState());
        } else {
            intelligentCabinet1StockResponse.setLeftRight(StateEnum.ONE.getState());
        }
        return ResponseData.success(intelligentCabinet1StockResponse);
    }

    // 盘点 - 任务创建
    public ResponseData createInventoryTask(String serialNumber) {
        WmsCabinet1CheckTaskParam checkTaskParam = new WmsCabinet1CheckTaskParam();
        String taskNumber = mapCodeGenerator.get(CodeProviderEnum.inventoryTaskCode.getProvider()).createCode(null);
        checkTaskParam.setTaskNumber(taskNumber);
        checkTaskParam.setTaskState(StateEnum.ONE.getState());
        checkTaskParam.setOperator(serialNumber);
        checkTaskParam.setOperationTime(new Date());
        wmsCabinet1CheckTaskService.add(checkTaskParam);
        return ResponseData.success(taskNumber);
    }

    // 盘点 - 所有格口信息
    public LayuiPageInfo inventoryCabinet() {
        return wmsIntelligentCabinet1StockService.findPageBySpec(new WmsIntelligentCabinet1StockParam());
    }

    // 盘点 - 结束任务
    public void updateInventory(String taskNumber,String state) {
        WmsCabinet1CheckTask checkTask = new WmsCabinet1CheckTask();
        checkTask.setTaskState(state);
        wmsCabinet1CheckTaskService.update(checkTask,new QueryWrapper<WmsCabinet1CheckTask>().eq("task_number",taskNumber));
    }

    // 盘点 - 打开全部格口
    public void openAllCabinet(String taskNumber) {
        updateInventory(taskNumber,StateEnum.TWO.getState());
        // todo 发送给设备打开全部格口
        List<WmsIntelligentCabinet1Stock> cabinet1StockList = wmsIntelligentCabinet1StockService.list();
        List<String> locaSerialNumberList = cabinet1StockList.stream().map(WmsIntelligentCabinet1Stock::getLocaSerialNumber).collect(Collectors.toList());
        Map<String,Object> mapOpenDoor = openDoorList(locaSerialNumberList);
        cachedThreadPool.execute(new OpenDoorRunnable(mapOpenDoor));
        // 更新数据库格口打开
        WmsIntelligentCabinet1Stock stock = new WmsIntelligentCabinet1Stock();
        stock.setLatticeState(StateEnum.ZERO.getState()); // 格口打开
        wmsIntelligentCabinet1StockService.update(stock,new QueryWrapper<>());
    }

    // 获取要打开的格口集合
    private Map<String,Object> openDoorList(List<String> listCabinet){
        Map<String,Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        for(String locaSerialNumber : listCabinet){
            Map<String,String> param = new HashMap<>();
            param.put("LocationId",locaSerialNumber);
            list.add(param);
        }
        map.put("List",list);
        return map;
    }

    // 盘点 - 关闭所有格口
    public void closeAllCabinet(String taskNumber) {
        updateInventory(taskNumber,StateEnum.FOUR.getState());
        WmsIntelligentCabinet1Stock stock = new WmsIntelligentCabinet1Stock();
        stock.setLatticeState(StateEnum.ONE.getState()); // 格口关闭
        wmsIntelligentCabinet1StockService.update(stock,new QueryWrapper<>());
    }

    // 盘点 - 获取所有工具类型
    public ResponseData queryToolType() {
        List<WmsMaterialType> list = wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type",StateEnum.ONE.getState()));
        return ResponseData.success(list);
    }

    // 盘点 - 查询所有符合的物料
    public ResponseData queryToolAll(Long materialTypeId) {
        WmsMaterialType type = wmsMaterialTypeService.getById(materialTypeId);
        List<WmsMaterialTool> list = wmsMaterialToolService.list(new QueryWrapper<WmsMaterialTool>().eq("material_type",type.getMaterialType()));
        return ResponseData.success(list);
    }

    // 盘点 - 修改库位信息
    public void updateCabinetContent(String locaSerialNumber, Long id) {
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
        WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getById(id);
        updateToolState(wmsMaterialTool, wmsIntelligentCabinet1Stock);
        wmsIntelligentCabinet1Stock.setMaterialTypeId(wmsMaterialTool.getMaterialTypeId()); // 库存-物料类型ID
        wmsIntelligentCabinet1Stock.setMaterialId(String.valueOf(wmsMaterialTool.getId())); // 库存-物料信息ID
        wmsIntelligentCabinet1Stock.setMaterialName(wmsMaterialTool.getMaterialName()); // 物料名称
        wmsIntelligentCabinet1Stock.setMaterialSku(wmsMaterialTool.getMaterialSku()); // 物料SKU
        wmsIntelligentCabinet1Stock.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber()); // 物料编码
        if(StateEnum.ONE.getState().equals(wmsMaterialTool.getMaterialState())){
            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ONE.getState()); // 工具状态（0维修 1使用）
        } else {
            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ZERO.getState()); // 工具状态（0维修 1使用）
        }
        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",wmsIntelligentCabinet1Stock.getLocaSerialNumber()));
        wmsMaterialTool.setStorageAddress(StateEnum.TWO.getState());
        wmsMaterialTool.setStorageState(StateEnum.ONE.getState());
        wmsMaterialToolService.update(wmsMaterialTool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsMaterialTool.getMaterialSerialNumber()));
    }

    // 盘点 - 清空库位数据
    public void updateInventoryCabinetEmpty(String locaSerialNumber) {
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
        if(!Objects.equals(wmsIntelligentCabinet1Stock.getMaterialSerialNumber(),"")
                && !Objects.equals(wmsIntelligentCabinet1Stock.getMaterialSerialNumber(),null)){
            WmsMaterialTool wmsMaterialTool = new WmsMaterialTool();
            wmsMaterialTool.setStorageState(StateEnum.TWO.getState());
            wmsMaterialTool.setStorageAddress(StateEnum.THREE.getState());
            wmsMaterialToolService.update(wmsMaterialTool, new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsIntelligentCabinet1Stock.getMaterialSerialNumber()));
        }
        updateCabinetEmpty(locaSerialNumber);
    }

    // 盘点 - 获取某人所有盘点任务信息
    public LayuiPageInfo inventoryTask(String serialNumber) {
        WmsCabinet1CheckTaskParam checkTaskParam = new WmsCabinet1CheckTaskParam();
        checkTaskParam.setOperator(serialNumber);
        return wmsCabinet1CheckTaskService.findPageBySpec(checkTaskParam);
    }

    // 关闭换新格口信息
    @SneakyThrows
    public ResponseData renewClose(String locaSerialNumber) {
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = new WmsIntelligentCabinet1Stock();
        wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ONE.getState());// 格口关闭
        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
        Thread.sleep(1000);
        WmsIntelligentCabinet1Stock cabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_state",StateEnum.TWO.getState()).eq("lattice_state",StateEnum.ZERO.getState()));
        return ResponseData.success(cabinet1Stock);
    }

    // PDA操作 工具盘点 扫描格口获取数据
    public ResponseData findByLocaSerialNumber(String locaSerialNumber) {
        // 1.打开格口
        Map<String,Object> map = getDeclensionParam(locaSerialNumber);
        cachedThreadPool.execute(new OpenDoorRunnable(map));
        // 2.返回格口内容
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
        return ResponseData.success(wmsIntelligentCabinet1Stock);
    }

    // PDA更新格口信息
    public void pdaUpdateCabinetContent(String locaSerialNumber, String toolNumber) {
        WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getOne(new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",toolNumber));
        updateCabinetContent(locaSerialNumber,wmsMaterialTool.getId());
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
        updateToolState(wmsMaterialTool, wmsIntelligentCabinet1Stock);
        wmsIntelligentCabinet1Stock.setMaterialTypeId(wmsMaterialTool.getMaterialTypeId()); // 库存-物料类型ID
        wmsIntelligentCabinet1Stock.setMaterialId(String.valueOf(wmsMaterialTool.getId())); // 库存-物料信息ID
        wmsIntelligentCabinet1Stock.setMaterialName(wmsMaterialTool.getMaterialName()); // 物料名称
        wmsIntelligentCabinet1Stock.setMaterialSku(wmsMaterialTool.getMaterialSku()); // 物料SKU
        wmsIntelligentCabinet1Stock.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber()); // 物料编码
        if(StateEnum.ONE.getState().equals(wmsMaterialTool.getMaterialState())){
            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ONE.getState()); // 工具状态（0维修 1使用）
        } else {
            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ZERO.getState()); // 工具状态（0维修 1使用）
        }
        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",wmsIntelligentCabinet1Stock.getLocaSerialNumber()));
        wmsMaterialTool.setStorageAddress(StateEnum.TWO.getState());
        wmsMaterialTool.setStorageState(StateEnum.ONE.getState());
        wmsMaterialToolService.update(wmsMaterialTool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsMaterialTool.getMaterialSerialNumber()));
    }

    // 更新工具状态
    private void updateToolState(WmsMaterialTool wmsMaterialTool, WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock) {
        if(!Objects.equals(wmsIntelligentCabinet1Stock.getMaterialSerialNumber(),"")
                && !Objects.equals(wmsIntelligentCabinet1Stock.getMaterialSerialNumber(),null)
                && !Objects.equals(wmsIntelligentCabinet1Stock.getMaterialSerialNumber(),wmsMaterialTool.getMaterialSerialNumber())){
            WmsMaterialTool tool = new WmsMaterialTool();
            tool.setStorageAddress(StateEnum.THREE.getState());// 不在库里不知道在哪
            tool.setStorageState(StateEnum.TWO.getState());// 库外
            wmsMaterialToolService.update(tool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsIntelligentCabinet1Stock.getMaterialSerialNumber()));
        }
    }

    // 备品备件盘点 - 任务查找
    @SuppressWarnings("all")
    public synchronized ResponseData querySparePartsTask(QueryWrapper queryWrapper) {
        List<WmsCabinet2CheckTask> checkTasks = wmsCabinet2CheckTaskService.list(queryWrapper);
        return ResponseData.success(checkTasks);
    }

    // 备品备件盘点 - 扫描数据
    public ResponseData padScanningSpare(String taskNumber) {
        WmsCabinet2CheckTask wmsCabinet2Stock = wmsCabinet2CheckTaskService.getOne(new QueryWrapper<WmsCabinet2CheckTask>().eq("task_number",taskNumber).last("limit 1"));
        if(StateEnum.FIVE.getState().equals(wmsCabinet2Stock.getTaskState())){
            return ResponseData.error("此任务一盘点完成请刷新页面");
        }
        WmsCabinet2Turnover wmsCabinet2Turnover = wmsCabinet2TurnoverService.getById(wmsCabinet2Stock.getTurnoverId());
        WmsCabinet2TurnoverBind wmsCabinet2TurnoverBind = wmsCabinet2TurnoverBindService.getOne(new QueryWrapper<WmsCabinet2TurnoverBind>().eq("turnover_id",wmsCabinet2Stock.getTurnoverId()));
        SpareParts spareParts = new SpareParts();
        spareParts.setTaskNumber(wmsCabinet2Stock.getTaskNumber());
        spareParts.setTurnoverNumber(wmsCabinet2Turnover.getTurnoverNumber());
        spareParts.setTurnoverState(wmsCabinet2Turnover.getTurnoverState());
        spareParts.setMaterialName(wmsCabinet2TurnoverBind.getMaterialName());
        spareParts.setMaterialSku(wmsCabinet2TurnoverBind.getMaterialSku());
        spareParts.setMBatch(wmsCabinet2TurnoverBind.getmBatch());
        spareParts.setMNumber(wmsCabinet2TurnoverBind.getmNumber());
        spareParts.setTurnoverId(wmsCabinet2Turnover.getId());
        return ResponseData.success(spareParts);
    }

    // 备品备件盘点 - 修改数量
    public ResponseData modifySpareParts(String number, Long turnoverId) {
        // 更新周转箱内的内容
        WmsCabinet2TurnoverBind wmsCabinet2TurnoverBind = wmsCabinet2TurnoverBindService.getOne(new QueryWrapper<WmsCabinet2TurnoverBind>().eq("turnover_id",turnoverId));
        wmsCabinet2TurnoverBind.setmNumber(number);
        WmsCabinet2TurnoverBindParam turnoverBindParam = new WmsCabinet2TurnoverBindParam();
        ToolUtil.copyProperties(wmsCabinet2TurnoverBind,turnoverBindParam);
        wmsCabinet2TurnoverBindService.update(turnoverBindParam);
        // 盘点结束
        WmsCabinet2CheckTask wmsCabinet2CheckTask = new WmsCabinet2CheckTask();
        wmsCabinet2CheckTask.setTaskState(StateEnum.FIVE.getState());
        wmsCabinet2CheckTaskService.update(wmsCabinet2CheckTask,new QueryWrapper<WmsCabinet2CheckTask>().eq("turnover_id",turnoverId));
        return ResponseData.success();
    }

    // 清除周转箱上绑定的内容
    public void cleanSpareParts(Long turnoverId) {
        // 1.更新周转箱内容
        WmsCabinet2Turnover wmsCabinet2Turnover = new WmsCabinet2Turnover();
        wmsCabinet2Turnover.setTurnoverState(StateEnum.ZERO.getState());// 周转箱空闲
        wmsCabinet2TurnoverService.update(wmsCabinet2Turnover,new QueryWrapper<WmsCabinet2Turnover>().eq("id",turnoverId));
        // 2.删除绑定表里的信息
        wmsCabinet2TurnoverBindService.remove(new QueryWrapper<WmsCabinet2TurnoverBind>().eq("turnover_id",turnoverId));
        // 3.更新任务-盘点结束
        WmsCabinet2CheckTask wmsCabinet2CheckTask = new WmsCabinet2CheckTask();
        wmsCabinet2CheckTask.setTaskState(StateEnum.FIVE.getState());
        wmsCabinet2CheckTaskService.update(wmsCabinet2CheckTask,new QueryWrapper<WmsCabinet2CheckTask>().eq("turnover_id",turnoverId));
    }

    // 立库周转箱 - 周转箱信息表
    public ResponseData warehouseTurnover(String turnoverNumber) {
        // 1. 获取周转箱的信息 2. 获取周转箱格口的信息
//        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("turnover_number",turnoverNumber));
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("barcode",turnoverNumber));
        WmsWarehouseTurnoverBindParam wms = new WmsWarehouseTurnoverBindParam();
        wms.setTurnoverId(wmsWarehouseTurnover.getId().toString());
        List<WmsWarehouseTurnoverBindResult> listBySpec = wmsWarehouseTurnoverBindService.findListBySpec(wms);
        List<WmsWarehouseTurnoverBindResult> list = new ArrayList<>();
        for (WmsWarehouseTurnoverBindResult result : listBySpec) {
            list.add(result);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("turnoverInfo",wmsWarehouseTurnover); // 周转箱信息
        map.put("latticeCode",list);
        return ResponseData.success(map);
    }

    // 立库周转箱盘点 - 清空
    public void padWarehouseTurnoverClean(String id) {
        // 清空周转箱绑定内容
        List<WmsWarehouseTurnoverBind> binds = wmsWarehouseTurnoverBindService.list(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",id));
        for(WmsWarehouseTurnoverBind bind : binds){
            bind.setGoodsType("");// 货物类型（1工具/2备品备件）
            bind.setMaterialTypeId("");// 物料类型ID
            bind.setMaterialId("");// 物料信息ID
            bind.setMaterialType("");// 物料类型
            bind.setMaterialName("");// 物料名称
            bind.setMaterialSku("");// 物料SKU
            bind.setmUnit("");// 单位
            bind.setMaterialSerialNumber("");// 物料编码
            bind.setmBatch("");//批次
            bind.setmNumber("");//数量
            bind.setLatticeState("0");
            WmsWarehouseTurnoverBindParam bindParam = new WmsWarehouseTurnoverBindParam();
            ToolUtil.copyProperties(bind,bindParam);
            wmsWarehouseTurnoverBindService.update(bindParam);
        }
        // 更新周转箱信息
        WmsWarehouseTurnoverParam wmsWarehouseTurnover = new WmsWarehouseTurnoverParam();
        WmsWarehouseTurnoverResult byId = wmsWarehouseTurnoverService.findById(id);
        byId.setTurnoverState("0");
        ToolUtil.copyProperties(byId,wmsWarehouseTurnover);
        wmsWarehouseTurnoverService.update(wmsWarehouseTurnover);
    }

    // 立库周转箱盘点 - 格口内容
    public ResponseData padWarehouseTurnoverCabinet(String id, String latticeCode) {
        WmsWarehouseTurnoverBind wmsWarehouseTurnoverBind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",id).eq("lattice_code",latticeCode));
        return ResponseData.success(wmsWarehouseTurnoverBind);
    }

    // 立库周转箱盘点 - 单格口清空
    public void padWarehouseTurnoverCabinetOneCabinet(Long id, String latticeCode) {
        WmsWarehouseTurnoverBind wmsWarehouseTurnoverBind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",id).eq("lattice_code",latticeCode));
        wmsWarehouseTurnoverBind.setGoodsType("");
        wmsWarehouseTurnoverBind.setMaterialTypeId("");
        wmsWarehouseTurnoverBind.setMaterialType("");
        wmsWarehouseTurnoverBind.setMaterialId("");
        wmsWarehouseTurnoverBind.setMaterialName("");
        wmsWarehouseTurnoverBind.setMaterialSku("");
        wmsWarehouseTurnoverBind.setmUnit("");
        wmsWarehouseTurnoverBind.setMaterialSerialNumber("");
        wmsWarehouseTurnoverBind.setmBatch("");
        wmsWarehouseTurnoverBind.setmNumber("");
        wmsWarehouseTurnoverBind.setCreateTime(new Date());
        wmsWarehouseTurnoverBind.setLatticeState("0");
        WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverParam = new WmsWarehouseTurnoverBindParam();
        ToolUtil.copyProperties(wmsWarehouseTurnoverBind,wmsWarehouseTurnoverParam);
        // 更新绑定内容
        wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverParam);

//        wmsWarehouseTurnoverBindService.remove(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",id).eq("lattice_code",latticeCode));

        // 获取周转箱信息
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getById(id);

        // 查询周转箱上格口的状态
        WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
        wmsWarehouseTurnoverBindParam.setTurnoverId(id.toString());
        List<WmsWarehouseTurnoverBindResult> listBySpec = wmsWarehouseTurnoverBindService.findListBySpec(wmsWarehouseTurnoverBindParam);
        for (WmsWarehouseTurnoverBindResult result : listBySpec) {
            if (Objects.equals("1",result.getLatticeState())){ // 有货,周转箱状态为有货
                wmsWarehouseTurnover.setTurnoverState("1");
                break;
            }
            else {
                wmsWarehouseTurnover.setTurnoverState("0");
            }
        }

        WmsWarehouseTurnoverParam turnoverParam = new WmsWarehouseTurnoverParam();
        ToolUtil.copyProperties(wmsWarehouseTurnover,turnoverParam);
        wmsWarehouseTurnoverService.update(turnoverParam);
    }

    // 立库周转箱盘点 - 单格口修改
    public ResponseData padWarehouseModify(WarehouseTurnoverModify turnoverModify) {
        WmsWarehouseTurnoverBind bind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>()
                .eq("turnover_id",turnoverModify.getId()).eq("lattice_code",turnoverModify.getLatticeCode()));
        if(StateEnum.ONE.getState().equals(turnoverModify.getTaskType())){// 工具编码更新处理
            WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getOne(new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",turnoverModify.getMaterialSerialNumber()));
            bind.setGoodsType(StateEnum.ONE.getState());
            bind.setMaterialTypeId(wmsMaterialTool.getMaterialTypeId());// 物料类型ID
            bind.setMaterialId(String.valueOf(wmsMaterialTool.getId()));// 物料信息ID
            bind.setMaterialType(wmsMaterialTool.getMaterialType());// 物料类型
            bind.setMaterialName(wmsMaterialTool.getMaterialName());// 物料名称
            bind.setMaterialSku(wmsMaterialTool.getMaterialSku());// 物料SKU
            bind.setmUnit(wmsMaterialTool.getmUnit());// 单位
            bind.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());//物料编码
            bind.setmNumber(StateEnum.ONE.getState());//数量
            bind.setLatticeState(StateEnum.ONE.getState());//状态
            bind.setMaterialSerialNumber(turnoverModify.getMaterialSerialNumber());
            WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
            ToolUtil.copyProperties(bind,wmsWarehouseTurnoverBindParam);
            wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverBindParam);
        } else {// 备品备件
            WmsMaterialSpareParts wmsMaterialSpareParts = wmsMaterialSparePartsService.getById(turnoverModify.getSparePartsId());
            bind.setGoodsType(StateEnum.TWO.getState());
            bind.setMaterialTypeId(wmsMaterialSpareParts.getMaterialTypeId());// 物料类型ID
            bind.setMaterialId(String.valueOf(wmsMaterialSpareParts.getId()));// 物料信息ID
            bind.setMaterialType(wmsMaterialSpareParts.getMaterialType());// 物料类型
            bind.setMaterialName(wmsMaterialSpareParts.getMaterialName());// 物料名称
            bind.setMaterialSku(wmsMaterialSpareParts.getMaterialSku());// 物料SKU
            bind.setmUnit(wmsMaterialSpareParts.getmUnit());// 单位
            bind.setmBatch(wmsMaterialSpareParts.getmBatch());//批次
            bind.setmNumber(turnoverModify.getNumber());//数量
            bind.setLatticeState(StateEnum.ONE.getState());//状态
            bind.setMaterialSerialNumber(turnoverModify.getMaterialSerialNumber());
            WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
            ToolUtil.copyProperties(bind,wmsWarehouseTurnoverBindParam);
            wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverBindParam);
        }
        return ResponseData.success();
    }

    // 立库周转箱组盘 - 查询格口信息
    public ResponseData padWarehouseDish(Long id, String latticeCode) {
        WmsWarehouseTurnoverBind wmsWarehouseTurnoverBind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",id).eq("lattice_code",latticeCode));
        return ResponseData.success(wmsWarehouseTurnoverBind);
    }

    // 立库周转箱组盘 - 提交绑定内容
    public ResponseData padWarehouseDishConform(WarehouseTurnoverModify modify) {
        // 获取任务
        WmsWarehousePurchaseStorageTask wmsWarehousePurchaseStorageTask = wmsWarehousePurchaseStorageTaskService.getOne(new QueryWrapper<WmsWarehousePurchaseStorageTask>().eq("task_number", modify.getTaskNumber()));
        WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getById(wmsWarehousePurchaseStorageTask.getPurchaseId());
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getById(modify.getId());
        int receiveNumber = 0;
        if ( !Objects.equals("",wmsPurchaseOrderInfo.getReceivedQuantity())){
            receiveNumber =Integer.parseInt(wmsWarehousePurchaseStorageTask.getReceivedQuantity()); // 已接收数量
        }
        WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParamC = new WmsWarehouseTurnoverBindParam();
        wmsWarehouseTurnoverBindParamC.setTurnoverId(modify.getId().toString());
        wmsWarehouseTurnover.setTurnoverState(StateEnum.ONE.getState());
        WmsWarehouseTurnoverBind wmsWarehouseTurnoverBind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",modify.getId()).eq("lattice_code",modify.getLatticeCode()));
        if(StateEnum.ONE.getState().equals(modify.getTaskType())){
            WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getOne(new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",modify.getMaterialSerialNumber()));
            if (Objects.equals("0",wmsWarehouseTurnoverBind.getmNumber())){
                wmsWarehouseTurnoverBind.setmNumber(StateEnum.ONE.getState());
                receiveNumber =receiveNumber + 1;
            }
            else {
                // 已绑定类型
                String materialTypeId = wmsWarehouseTurnoverBind.getMaterialTypeId();
                // 已绑定数量
                String number = wmsWarehouseTurnoverBind.getmNumber();
                // 查询传递的工具类型
                if (Objects.equals(materialTypeId,wmsMaterialTool.getMaterialTypeId())){
                    int i = Integer.parseInt(number);
                    int total = i +1;
                    receiveNumber =  receiveNumber + 1;
                    wmsWarehouseTurnoverBind.setmNumber(Integer.toString(total));
                }
                else {
                    return ResponseData.error("格口绑定的工具类型不一致");
                }
            }

            wmsWarehouseTurnoverBind.setGoodsType(StateEnum.ONE.getState());// 货物类型（1工具/2备品备件）
            // 查工具物料编码
            wmsWarehouseTurnoverBind.setMaterialSerialNumber(wmsMaterialTool.getMaterialSerialNumber());// 物料编码
            wmsWarehouseTurnoverBind.setMaterialTypeId(wmsMaterialTool.getMaterialTypeId());// 物料类型ID
            wmsWarehouseTurnoverBind.setMaterialId(String.valueOf(wmsMaterialTool.getId()));// 物料信息ID
            wmsWarehouseTurnoverBind.setMaterialType(wmsMaterialTool.getMaterialType());// 物料类型
            wmsWarehouseTurnoverBind.setMaterialName(wmsMaterialTool.getMaterialName());// 物料名称
            wmsWarehouseTurnoverBind.setMaterialSku(wmsMaterialTool.getMaterialSku());// 物料SKU
            wmsWarehouseTurnoverBind.setmUnit(wmsMaterialTool.getmUnit());// 单位
            wmsWarehouseTurnoverBind.setLatticeState(StateEnum.ONE.getState());
        } else {
            String sparsId = modify.getSparePartsId().toString();
            WmsMaterialSparePartsResult wmsMaterialSparePartsResult =   wmsMaterialSparePartsService.findById(sparsId);
            if (Objects.equals("0",wmsWarehouseTurnoverBind.getmNumber())){
                wmsWarehouseTurnoverBind.setmNumber(StateEnum.ONE.getState());
                receiveNumber =receiveNumber +Integer.parseInt(modify.getNumber());
                wmsWarehouseTurnoverBind.setmNumber(modify.getNumber());
            }
            else {
                // 已绑定类型
                String materialTypeId = wmsWarehouseTurnoverBind.getMaterialTypeId();
                // 已绑定数量
                String number = wmsWarehouseTurnoverBind.getmNumber();
                // 查询传递的备件类型
                if (Objects.equals(materialTypeId,wmsMaterialSparePartsResult.getMaterialTypeId()) && Objects.equals(wmsMaterialSparePartsResult.getMBatch(),wmsWarehouseTurnoverBind.getmBatch()) ){
                    int i = Integer.parseInt(number);
                    int total = i +Integer.parseInt(modify.getNumber());
                    receiveNumber =receiveNumber +Integer.parseInt(modify.getNumber());
                    wmsWarehouseTurnoverBind.setmNumber(Integer.toString(total));
                }
                else {
                    return ResponseData.error("格口绑定的备件类型或批次不一致");
                }
            }
            wmsWarehouseTurnoverBind.setGoodsType(StateEnum.TWO.getState());// 货物类型（1工具/2备品备件）
            // 查备品备件物料编码
            WmsMaterialSpareParts spareParts = wmsMaterialSparePartsService.getOne(new QueryWrapper<WmsMaterialSpareParts>().eq("id",modify.getSparePartsId()));
            wmsWarehouseTurnoverBind.setMaterialTypeId(spareParts.getMaterialTypeId());// 物料类型ID
            wmsWarehouseTurnoverBind.setMaterialId(String.valueOf(spareParts.getId()));// 物料信息ID
            wmsWarehouseTurnoverBind.setMaterialType(spareParts.getMaterialType());// 物料类型
            wmsWarehouseTurnoverBind.setMaterialName(spareParts.getMaterialName());// 物料名称
            wmsWarehouseTurnoverBind.setMaterialSku(spareParts.getMaterialSku());// 物料SKU
            wmsWarehouseTurnoverBind.setmUnit(spareParts.getmUnit());// 单位
            wmsWarehouseTurnoverBind.setmBatch(spareParts.getmBatch());// 批次
            wmsWarehouseTurnoverBind.setmNumber(wmsWarehouseTurnoverBind.getmNumber());// 数量
            wmsWarehouseTurnoverBind.setLatticeState(StateEnum.ONE.getState());
        }
        WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam = new WmsWarehouseTurnoverBindParam();
        ToolUtil.copyProperties(wmsWarehouseTurnoverBind,wmsWarehouseTurnoverBindParam);
        wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverBindParam);
        WmsWarehouseTurnoverParam turnoverParam = new WmsWarehouseTurnoverParam();
        ToolUtil.copyProperties(wmsWarehouseTurnover,turnoverParam);
        wmsWarehouseTurnoverService.update(turnoverParam);


        // 3.更新订单中信息
        if (Objects.equals(wmsPurchaseOrderInfo.getReceivedQuantity(), "") || Objects.equals(wmsPurchaseOrderInfo.getReceivedQuantity(), null)) {
            wmsPurchaseOrderInfo.setReceivedQuantity(String.valueOf(receiveNumber));// 接收数量
        } else {
            wmsPurchaseOrderInfo.setReceivedQuantity(String.valueOf(receiveNumber));// 接收数量
        }
        Integer acceptableQuantity = Integer.parseInt(wmsPurchaseOrderInfo.getmNumber()) - Integer.parseInt(wmsPurchaseOrderInfo.getReceivedQuantity());
        wmsPurchaseOrderInfo.setAcceptableQuantity(String.valueOf(acceptableQuantity));// 可接收数量
        WmsPurchaseOrderInfoParam orderInfoParam = new WmsPurchaseOrderInfoParam();
        ToolUtil.copyProperties(wmsPurchaseOrderInfo, orderInfoParam);
        wmsPurchaseOrderInfoService.update(orderInfoParam);
        // 4.更新执行任务信息
        wmsWarehousePurchaseStorageTask.setAcceptableQuantity(wmsPurchaseOrderInfo.getAcceptableQuantity());// 可接收数量
        wmsWarehousePurchaseStorageTask.setReceivedQuantity(wmsPurchaseOrderInfo.getReceivedQuantity()); // 已接收数量
        if (Objects.equals(wmsWarehousePurchaseStorageTask.getReceivedQuantity(), "") || Objects.equals(wmsWarehousePurchaseStorageTask.getReceivedQuantity(), null)) {
            wmsWarehousePurchaseStorageTask.setReceivedQuantity(StateEnum.ONE.getState());// 已接收数量
        } else {
            Integer value = Integer.valueOf(wmsWarehousePurchaseStorageTask.getReceivedQuantity());
            wmsWarehousePurchaseStorageTask.setReceivedQuantity(String.valueOf(value));// 已组盘数量
        }
        WmsWarehousePurchaseStorageTaskParam storageTaskParam = new WmsWarehousePurchaseStorageTaskParam();
        ToolUtil.copyProperties(wmsWarehousePurchaseStorageTask, storageTaskParam);
        wmsWarehousePurchaseStorageTaskService.update(storageTaskParam);
        return ResponseData.success();
    }

    // 立库周转箱组盘 - 获取所有的备品备件
    public ResponseData padWarehouseDishSparePartsData() {
        List<WmsMaterialSpareParts> sparePartsList = wmsMaterialSparePartsService.list(new QueryWrapper<WmsMaterialSpareParts>().eq("data_state",StateEnum.ZERO.getState()));
        return ResponseData.success(sparePartsList);
    }

    // 立库分拣 - 获取所有的工具分拣任务
    public ResponseData padSortingTool() {
        // 获取 出库完成、未分拣、人工分拣 的数据
        WmsWarehouseToolUseTask toolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_state",StateEnum.THREE.getState()).eq("sorting_status",StateEnum.ZERO.getState()).eq("sorting_type",StateEnum.ZERO.getState()).last("limit 1"));
        return ResponseData.success(toolUseTask);
    }

    // 立库分拣 - 获取所有的备品备件分拣任务
    public ResponseData padSortingSpare() {
        // 获取 出库完成、未分拣、人工分拣 的数据
        WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask = wmsWarehouseReplenishmentTaskService.getOne(new QueryWrapper<WmsWarehouseReplenishmentTask>().eq("task_state",StateEnum.THREE.getState()).eq("sorting_status",StateEnum.ZERO.getState()).eq("sorting_type",StateEnum.ZERO.getState()).last("limit 1"));
        return ResponseData.success(wmsWarehouseReplenishmentTask);
    }

    // 立库分拣 - 人工分拣任务校验
    public ResponseData padSortingTurnoverOne(String taskNumber,String flag) {
        if(ApplyType.A.getType().equals(flag)){
            WmsWarehouseToolUseTask toolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number",taskNumber));
            if(!StateEnum.THREE.getState().equals(toolUseTask.getTaskState())
                    && !StateEnum.ZERO.getState().equals(toolUseTask.getSortingStatus())){
                return ResponseData.error("任务未出库完成|任务分拣完成 无法操作");
            }
        } else {
            WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask = wmsWarehouseReplenishmentTaskService.getOne(new QueryWrapper<WmsWarehouseReplenishmentTask>().eq("task_state",StateEnum.THREE.getState()).eq("sorting_status",StateEnum.ZERO.getState()).eq("sorting_type",StateEnum.ZERO.getState()).last("limit 1"));
            if(!StateEnum.THREE.getState().equals(wmsWarehouseReplenishmentTask.getTaskState())
                    && !StateEnum.ZERO.getState().equals(wmsWarehouseReplenishmentTask.getSortingStatus())){
                return ResponseData.error("任务未出库完成|任务分拣完成 无法操作");
            }
        }
        return ResponseData.success();
    }

    // 立库分拣 - 根据备品备件或者工具领用任务编号获取周转箱信息
    @SuppressWarnings("all")
    public ResponseData padSortingTurnover(String taskNumber,String barCode,String flag) {
        Map<String, Object> map = new HashMap<>();


        WmsWarehouseTurnover WmsWarehouseTurnover = wmsWarehouseTurnoverService.getOne(new QueryWrapper<WmsWarehouseTurnover>().eq("barcode",barCode));
        if(ApplyType.A.getType().equals(flag)){
            WmsWarehouseToolUseTask toolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number",taskNumber));
            if(!Objects.equals(String.valueOf(WmsWarehouseTurnover.getId()),toolUseTask.getTurnoverId())){
                return ResponseData.error("周转箱类型与任务绑定不符 无法操作"); // 防错 判断是否为同一个周转箱
            }
        } else {
            WmsWarehouseReplenishmentTask wmsWarehouseReplenishmentTask = wmsWarehouseReplenishmentTaskService.getOne(new QueryWrapper<WmsWarehouseReplenishmentTask>().eq("task_state",StateEnum.THREE.getState()).eq("sorting_status",StateEnum.ZERO.getState()).eq("sorting_type",StateEnum.ZERO.getState()).last("limit 1"));
            WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.getOne(new QueryWrapper<WmsWarehouseTaskOut>().eq("task_mg",taskNumber).orderByDesc("id").last("limit 1"));
            if(!Objects.equals(barCode,wmsWarehouseTaskOut.getBarcode())){
                return ResponseData.error("周转箱类型与任务类型不符 无法操作");
            }
        }
        WmsWarehouseTurnoverBindParam wms = new WmsWarehouseTurnoverBindParam();
        wms.setTurnoverId(WmsWarehouseTurnover.getId().toString());
        List<WmsWarehouseTurnoverBindResult> listBySpec = wmsWarehouseTurnoverBindService.findListBySpec(wms);
        List<WmsWarehouseTurnoverBindResult> list = new ArrayList<>();
        for (WmsWarehouseTurnoverBindResult result : listBySpec) {
            list.add(result);
        }
        map.put("turnoverInfo",WmsWarehouseTurnover); // 周转箱信息
        map.put("latticeCode",list);
        return ResponseData.success(map);
    }

    // 立库工具分拣 - 分拣完成
    public WmsWarehouseTaskIn padSortingConform(WarehouseTurnoverModify modify) {
        WmsWarehouseTaskOut wmsWarehouseTaskOut = wmsWarehouseTaskOutService.findByTaskNumber(modify.getTaskNumber());
        WmsWarehouseTurnoverBindResult result = wmsWarehouseTurnoverBindService.findByMaterial(modify.getMaterialSerialNumber());
        // 查询出的绑定信息
        WmsWarehouseTurnoverBind bind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",modify.getId()).eq("lattice_code",result.getLatticeCode()));

        // 查询周转箱信息
        WmsWarehouseTurnover turnover = wmsWarehouseTurnoverService.getById(modify.getId());
            // 1.更新绑定信息
            updateWarehouseTurnoverBind(bind);
            //2.更新周转箱信息
            WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParamC = new WmsWarehouseTurnoverBindParam();
            wmsWarehouseTurnoverBindParamC.setTurnoverId(modify.getId().toString());
            List<WmsWarehouseTurnoverBindResult> wmsWarehouseTurnoverBindResult=wmsWarehouseTurnoverBindService.findListTurnover(wmsWarehouseTurnoverBindParamC);

            // 遍历判断数量
            if (!wmsWarehouseTurnoverBindResult.isEmpty()){
                for (WmsWarehouseTurnoverBindResult warehouseTurnoverBindResult : wmsWarehouseTurnoverBindResult) {
                    if (!Objects.equals("",warehouseTurnoverBindResult.getMNumber()) && !Objects.equals("0",warehouseTurnoverBindResult.getMNumber())){
                        turnover.setTurnoverState(StateEnum.ONE.getState());
                        break;
                    }
                    else {
                        turnover.setTurnoverState(StateEnum.ZERO.getState());
                    }
                }
            }
            else {
                turnover.setTurnoverState(StateEnum.ZERO.getState());
            }

            WmsWarehouseTurnoverParam bindParam = new WmsWarehouseTurnoverParam();
            ToolUtil.copyProperties(turnover,bindParam);
            wmsWarehouseTurnoverService.update(bindParam);
            // 4.更新任务
            WmsWarehouseToolUseTask toolUseTask = new WmsWarehouseToolUseTask();
            toolUseTask.setSortingStatus(StateEnum.ONE.getState());// 已分拣
            wmsWarehouseToolUseTaskService.update(toolUseTask,new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number",modify.getTaskNumber()));

            // 创建入库任务
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
        return wmsWarehouseTaskIn;
    }

    // 立库备件分拣 - 分拣完成
    public ResponseData padSortingConform2(WarehouseTurnoverModify modify) {
        // 查询出的绑定信息
        WmsWarehouseTurnoverBind bind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id",modify.getId()).eq("lattice_code",modify.getLatticeCode()));

        // 查询周转箱信息
        WmsWarehouseTurnover turnover = wmsWarehouseTurnoverService.getById(modify.getId());
        // 备品备件

        // 周转箱绑定数量(单个格口的数量)
        Integer value = Integer.valueOf(bind.getmNumber());
        // 分拣数量
        Integer data = Integer.valueOf(modify.getNumber());
        Integer valueNew = value - data;
        if(valueNew < 0){
            return ResponseData.error("拣选的备品备件不能大于库存数");
        }
        if(valueNew == 0){
            // 2.更新绑定信息
            updateWarehouseTurnoverBind(bind);
            // 1.更新周转箱 spareParts.getTurnoverId()
            WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam = new WmsWarehouseTurnoverParam();
            //2.更新周转箱信息
            WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParamC = new WmsWarehouseTurnoverBindParam();
            wmsWarehouseTurnoverBindParamC.setTurnoverId(modify.getId().toString());
            List<WmsWarehouseTurnoverBindResult> wmsWarehouseTurnoverBindResult=wmsWarehouseTurnoverBindService.findListTurnover(wmsWarehouseTurnoverBindParamC);

            // 遍历判断数量
            if (!wmsWarehouseTurnoverBindResult.isEmpty()){
                for (WmsWarehouseTurnoverBindResult warehouseTurnoverBindResult : wmsWarehouseTurnoverBindResult) {
                    if (!Objects.equals("",warehouseTurnoverBindResult.getMNumber()) && !Objects.equals("0",warehouseTurnoverBindResult.getMNumber())){
                        turnover.setTurnoverState(StateEnum.ONE.getState());
                        break;
                    }
                    else {
                        turnover.setTurnoverState(StateEnum.ZERO.getState());
                    }
                }
            }
            else {
                turnover.setTurnoverState(StateEnum.ZERO.getState());
            }
            ToolUtil.copyProperties(turnover,wmsWarehouseTurnoverParam);
            wmsWarehouseTurnoverService.update(wmsWarehouseTurnoverParam);


        } else {
            // 2.更新绑定信息
            bind.setmNumber(String.valueOf(valueNew));
            WmsWarehouseTurnoverBindParam bindParam = new WmsWarehouseTurnoverBindParam();
            ToolUtil.copyProperties(bind,bindParam);
            wmsWarehouseTurnoverBindService.update(bindParam);
        }

        WmsWarehouseReplenishmentTaskResult wmsWarehouseReplenishmentTaskResult =  wmsWarehouseReplenishmentTaskService.findByTaskNumber(modify.getTaskNumber());
        Integer pickNumber = Integer.parseInt(modify.getNumber()) + Integer.parseInt(wmsWarehouseReplenishmentTaskResult.getSortingNum());
        wmsWarehouseReplenishmentTaskService.updatePickNumber(modify.getTaskNumber(),pickNumber.toString());
        return ResponseData.success();
    }


    // 更新周转箱绑定信息
    private void updateWarehouseTurnoverBind(WmsWarehouseTurnoverBind bind){
        // 2.更新绑定信息
        bind.setGoodsType("");
        bind.setMaterialTypeId("");
        bind.setMaterialId("");
        bind.setMaterialType("");
        bind.setMaterialName("");
        bind.setMaterialSku("");
        bind.setmUnit("");
        bind.setMaterialSerialNumber("");
        bind.setmBatch("");
        bind.setLatticeState("0");
        bind.setmNumber(StateEnum.ZERO.getState());
        WmsWarehouseTurnoverBindParam bindParam = new WmsWarehouseTurnoverBindParam();
        ToolUtil.copyProperties(bind,bindParam);
        wmsWarehouseTurnoverBindService.update(bindParam);
    }

    // 工具申请 - 获取所有的工具物料信息
    public ResponseData padToolAll() {
        List<WmsMaterialType> tools = wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                .eq("type",StateEnum.ONE.getState()).eq("data_state",StateEnum.ZERO.getState()));
        return ResponseData.success(tools);
    }

    // 工具申请 - 提交申请
    public void padToolConform(String serialNumber, String reason, String materialTypeId) {
        WmsUseApply param = new WmsUseApply();
        param.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        param.setOperator(serialNumber);
        param.setMaterialId(materialTypeId);
        param.setmNumber(StateEnum.ONE.getState());
        param.setProcessType(ApplyType.A.getType());
        param.setProcessReason(reason);
        param.setDataTime(new Date());
        param.setDataState(StateEnum.ZERO.getState());
        wmsUseApplyService.save(param);
    }

    // 工具归还 - 提交申请
    public void padToolGiveBack(String serialNumber,String reason, String materialTypeId) {
        WmsReturnApply apply = new WmsReturnApply();
        apply.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        apply.setOperator(serialNumber);
        apply.setMaterialId(materialTypeId);
        apply.setmNumber(StateEnum.ONE.getState());
        apply.setDataTime(new Date());
        apply.setReturnReason(reason);
        apply.setDataState(StateEnum.ZERO.getState());
        wmsReturnApplyService.save(apply);
    }

    // 备品备件 - 获取所有备品备件信息
    public ResponseData padApplySpareAll() {
        List<WmsMaterialType> types = wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                .eq("type",StateEnum.TWO.getState()).eq("data_state",StateEnum.ZERO.getState()));
        return ResponseData.success(types);
    }

    // 备品备件 - 获取所有备品备件信息
    public ResponseData padApplySpareConform(ApplySpareParts apply) {
        WmsUseApply useApply = new WmsUseApply();
        useApply.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        useApply.setOperator(apply.getSerialNumber());
        useApply.setMaterialId(apply.getMaterialId());
        useApply.setmNumber(apply.getNumber());
        useApply.setProcessType(ApplyType.B.getType());
        useApply.setProcessReason(apply.getReason());
        useApply.setDataTime(new Date());
        useApply.setDataState(StateEnum.ZERO.getState());
        useApply.setScrapMaterialId(apply.getMaterialIdTwo());
        useApply.setScrapNum(apply.getNumberTwo());
        wmsUseApplyService.save(useApply);
        return ResponseData.success();
    }

    // 关闭库门
    public void repairCloseCabinet(String taskNumber) {
        WmsCabinet1RepairTask repairTask = wmsCabinet1RepairTaskService.getOne(new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
        String[] locaNumbers = repairTask.getLocaNumber().split(",");
        List<String> list = Arrays.asList(locaNumbers);
        List<WmsIntelligentCabinet1Stock> cabinet1Stocks = wmsIntelligentCabinet1StockService.list(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("lattice_state",StateEnum.ZERO.getState()).in("loca_serial_number",list));
        if(cabinet1Stocks.size() > 0){
            for (WmsIntelligentCabinet1Stock cabinet1Stock : cabinet1Stocks) {
                cabinet1Stock.setLatticeState(StateEnum.ONE.getState());
                WmsIntelligentCabinet1StockParam cabinet1StockParam = new WmsIntelligentCabinet1StockParam();
                ToolUtil.copyProperties(cabinet1Stock,cabinet1StockParam);
                wmsIntelligentCabinet1StockService.update(cabinet1StockParam);
            }
        }
    }

    public ResponseData openDoorTwo(String taskNumber) {
        // 更新任务信息
        WmsCabinet1RepairTask repairTask = wmsCabinet1RepairTaskService.getOne(new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
        // 打开库门
        String[] locaNumbers = repairTask.getLocaNumber().split(",");
        List<String> locaNums = Arrays.asList(locaNumbers);
        Map<String,Object> mapOpenDoor = openDoorList(locaNums);
        cachedThreadPool.execute(new OpenDoorRunnable(mapOpenDoor));
       return ResponseData.success();
    }


    /**
     * 业务中打开格口 发一边结束
     */
    class OpenDoorRunnable implements Runnable{

        // 库位编号
        private Map<String,Object> locaSerialNumber;

        public OpenDoorRunnable(Map<String,Object> locaSerialNumber) {
            this.locaSerialNumber = locaSerialNumber;
        }

        @Override
        public void run() {
            WmsApiService.openDeclension(locaSerialNumber);
        }
    }

    class ListeningDoorRepairRunnable implements Runnable{
        // 任务编号
        private String taskNumber;

        // 库位编号
        private List<String> locaSerialNumber;

        private List<String> closeDoor = new ArrayList<>();

        ListeningDoorRepairRunnable(String taskNumber, List<String> locaSerialNumber){
            this.taskNumber = taskNumber;
            this.locaSerialNumber = locaSerialNumber;
        }

        @SneakyThrows
        @Override
        public void run() {

            do{
                if(closeDoor.size() == locaSerialNumber.size()){
                    // 更新任务
                    WmsCabinet1RepairTask repairTask = wmsCabinet1RepairTaskService.getOne(new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
                    repairTask.setTaskState(StateEnum.FOUR.getState());
                    wmsCabinet1RepairTaskService.update(repairTask,new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
                    // todo 用 webSocket 通知前台关闭取料格口
                    Map<String, Object> map = new HashMap<>();
                    map.put("code",StateEnum.TWO.getState());
                    map.put("list",closeDoor);
                    String object = JSONObject.toJSONString(map);
                    WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                    break;
                }
                QueryWrapper<WmsIntelligentCabinet1Stock> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("lattice_state",StateEnum.ONE.getState());
                queryWrapper.in("loca_serial_number",locaSerialNumber);
                List<WmsIntelligentCabinet1Stock> list = wmsIntelligentCabinet1StockService.list(queryWrapper);
                for (WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock : list) {
                    if(!closeDoor.contains(wmsIntelligentCabinet1Stock.getLocaSerialNumber())){
                        // 更新库位
                        updateCabinetEmpty(wmsIntelligentCabinet1Stock.getLocaSerialNumber());
                        // 更新工具
                        WmsMaterialTool tool = new WmsMaterialTool();
                        tool.setStorageState(StateEnum.TWO.getState());// 工具库外
                        tool.setStorageAddress(StateEnum.THREE.getState());
                        wmsMaterialToolService.update(tool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsIntelligentCabinet1Stock.getMaterialSerialNumber()));
                        // 缓存已经关闭的格口
                        closeDoor.add(wmsIntelligentCabinet1Stock.getLocaSerialNumber());
                        // todo 用 webSocket 通知前台关闭取料格口
                        Map<String, Object> map = new HashMap<>();
                        map.put("code","");
                        map.put("list",closeDoor);
                        String object = JSONObject.toJSONString(map);
                        WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                    }
                }
                Thread.sleep(1000);
            } while (true);

        }
    }

    /**
     * 业务中监听格口关闭状态
     */
    class ListeningDoorRunnable implements Runnable{

        // 任务编号
        private String taskNumber;

        // 库位编号
        private String locaSerialNumber;

        // 标志
        private StateEnum flag;

        ListeningDoorRunnable(String taskNumber, String locaSerialNumber, StateEnum flag) {
            this.taskNumber = taskNumber;
            this.locaSerialNumber = locaSerialNumber;
            this.flag = flag;
        }

        @SneakyThrows
        @Override
        public void run() {
            do {
                Thread.sleep(200);
                WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
                if(wmsIntelligentCabinet1Stock != null){
                    if(StateEnum.ONE.getState().equals(wmsIntelligentCabinet1Stock.getLatticeState()) && StateEnum.ZERO.equals(flag)){ // 空格口-存储完成状态-打开取货格口
                        // todo 推送前台库门关闭
                        Map<String, Object> mapTwo = new HashMap<>();
                        mapTwo.put("code",StateEnum.TWO.getState());
                        String objectTwo = JSONObject.toJSONString(mapTwo);
                        WebSocket.sendMessageOfSession1(objectTwo);// 1.开 2.关
                        // 更新格口信息
                        wmsIntelligentCabinet1Stock.setLocaState(StateEnum.ONE.getState());// 格口有货
                        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
                        // 更新工具信息
                        WmsMaterialTool tool = new WmsMaterialTool();
                        tool.setStorageState(StateEnum.ONE.getState());// 在库内
                        tool.setStorageAddress(StateEnum.TWO.getState());// 仓库为I类柜
                        wmsMaterialToolService.update(tool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsIntelligentCabinet1Stock.getMaterialSerialNumber()));
                        // 打开新格口 - 查找 有料格口、可使用工具、库位类型与工具类型一致
                        WmsIntelligentCabinet1Stock haveMaterialCabinet = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>()
                                .eq("loca_state",StateEnum.ONE.getState()).eq("tool_state",StateEnum.ONE.getState())
                                .eq("lattice_type",wmsIntelligentCabinet1Stock.getLatticeType()).last("limit 1"));
                        // 更新任务信息
                        log.info("Task number is{}",taskNumber);
                        WmsCabinet1RenewTask renewTask = wmsCabinet1RenewTaskService.getOne(new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",taskNumber));
                        renewTask.setTaskState(StateEnum.FOUR.getState());// 取货开始
                        log.info("Queried inventory grid information{}",haveMaterialCabinet);
                        renewTask.setnMaterialId(haveMaterialCabinet.getMaterialId()); // 新-物料ID
                        renewTask.setnMaterialSerialNumber(haveMaterialCabinet.getMaterialSerialNumber()); // 新-物料编号
                        renewTask.setTaskStockId(String.valueOf(haveMaterialCabinet.getId())); // 新-库位ID
                        renewTask.setTaskLocaSerialNumber(haveMaterialCabinet.getLocaSerialNumber());// 新-库位编号
                        wmsCabinet1RenewTaskService.update(renewTask,new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",taskNumber));
                        // 更新格口内容
                        haveMaterialCabinet.setLocaState(StateEnum.TWO.getState()); // 状态锁定
                        haveMaterialCabinet.setLatticeState(StateEnum.ZERO.getState()); // 格口打开
                        haveMaterialCabinet.setCreateTime(new Date()); // 操作时间
                        wmsIntelligentCabinet1StockService.update(haveMaterialCabinet,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",haveMaterialCabinet.getLocaSerialNumber()));
                        // 打开格口
                        Map<String,Object> mapLocaSerialNumber = getDeclensionParam(haveMaterialCabinet.getLocaSerialNumber());
                        cachedThreadPool.execute(new OpenDoorRunnable(mapLocaSerialNumber));
                        // 添加领用工具信息
                        WmsToolUse wmsToolUse = new WmsToolUse();
                        wmsToolUse.setOperator(renewTask.getOperator());// 人员信息
                        wmsToolUse.setMaterialTypeId(haveMaterialCabinet.getMaterialTypeId());// 物料类型ID
                        wmsToolUse.setMaterialName(haveMaterialCabinet.getMaterialName());// 物料名称
                        wmsToolUse.setMaterialSku(haveMaterialCabinet.getMaterialSku());// 物料SKU
                        wmsToolUse.setMaterialId(haveMaterialCabinet.getMaterialId());// 物料信息ID
                        wmsToolUse.setMaterialSerialNumber(haveMaterialCabinet.getMaterialSerialNumber());// 物料编码
                        wmsToolUse.setDataTime(new Date());// 数据时间
                        wmsToolUseService.save(wmsToolUse);
                        // 监听格口状态
                        cachedThreadPool.execute(new ListeningDoorRunnable(taskNumber,haveMaterialCabinet.getLocaSerialNumber(),StateEnum.TWO));
                        // 用 webSocket 通知前台关闭放料同时打开取料格口 haveMaterialCabinet
                        Map<String, Object> map = new HashMap<>();
                        map.put("code",StateEnum.ONE.getState());
                        IntelligentCabinet1StockResponse intelligentCabinet1StockResponse = new IntelligentCabinet1StockResponse();
                        ToolUtil.copyProperties(haveMaterialCabinet,intelligentCabinet1StockResponse);
                        List<String> localNum = getLocalNumberLeft();
                        if(localNum.contains(wmsIntelligentCabinet1Stock.getLocaSerialNumber())){
                            intelligentCabinet1StockResponse.setLeftRight(StateEnum.TWO.getState());
                        } else {
                            intelligentCabinet1StockResponse.setLeftRight(StateEnum.ONE.getState());
                        }
                        Map<String,Object> mapObj = BeanUtil.beanToMap(intelligentCabinet1StockResponse);
                        map.put("data",mapObj);
                        String object = JSONObject.toJSONString(map);
                        WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                        break;
                    } else if(StateEnum.ONE.getState().equals(wmsIntelligentCabinet1Stock.getLatticeState()) && StateEnum.TWO.equals(flag)){// 有料格口-取出完成状态
                        // 更新物料信息
                        WmsMaterialTool tool = new WmsMaterialTool();
                        tool.setStorageState(StateEnum.TWO.getState());// 在库外
                        tool.setMaterialState(StateEnum.ONE.getState());// 工具状态使用中
                        tool.setStorageAddress(StateEnum.THREE.getState());// 不知道在哪
                        wmsMaterialToolService.update(tool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsIntelligentCabinet1Stock.getMaterialSerialNumber()));
                        // 更新任务
                        WmsCabinet1RenewTask renewTask = new WmsCabinet1RenewTask();
                        renewTask.setTaskState(StateEnum.FIVE.getState());// 取货完成
                        wmsCabinet1RenewTaskService.update(renewTask,new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",taskNumber));
                        // 更新格口信息
                        updateCabinetEmpty(locaSerialNumber);
                        // todo 用 webSocket 通知前台取料格口关闭
                        Map<String, Object> map = new HashMap<>();
                        map.put("code",StateEnum.TWO.getState());
                        String object = JSONObject.toJSONString(map);
                        WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                        break;
                    } else if(StateEnum.ONE.getState().equals(wmsIntelligentCabinet1Stock.getLatticeState()) && StateEnum.THREE.equals(flag)){// 异常格口-取出完成状态
                        // 更新任务信息
                        WmsCabinet1RenewTask renewTask = new WmsCabinet1RenewTask();
                        renewTask.setTaskState(StateEnum.FIVE.getState());// 取货完成
                        renewTask.setnMaterialId(wmsIntelligentCabinet1Stock.getMaterialId()); // 新-物料ID
                        renewTask.setnMaterialSerialNumber(wmsIntelligentCabinet1Stock.getMaterialSerialNumber()); // 新-物料编号
                        renewTask.setTaskStockId(String.valueOf(wmsIntelligentCabinet1Stock.getId())); // 新-库位ID
                        renewTask.setTaskLocaSerialNumber(wmsIntelligentCabinet1Stock.getLocaSerialNumber());// 新-库位编号
                        wmsCabinet1RenewTaskService.update(renewTask,new QueryWrapper<WmsCabinet1RenewTask>().eq("task_number",taskNumber));
                        // 更新格口为空
                        updateCabinetEmpty(locaSerialNumber);
                        // 更新物料信息
                        WmsMaterialTool tool = new WmsMaterialTool();
                        tool.setStorageState(StateEnum.TWO.getState());// 在库外
                        tool.setStorageAddress(StateEnum.THREE.getState());// 仓库为I类柜
                        wmsMaterialToolService.update(tool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsIntelligentCabinet1Stock.getMaterialSerialNumber()));
                        // todo 用 webSocket 通知前台关闭放料同时打开取料格口 haveMaterialCabinet
                        Map<String, Object> map = new HashMap<>();
                        map.put("code",StateEnum.TWO.getState());
                        String object = JSONObject.toJSONString(map);
                        WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                        break;
                    } else if(StateEnum.ONE.getState().equals(wmsIntelligentCabinet1Stock.getLatticeState()) && StateEnum.FOUR.equals(flag)){
                        WmsCabinet1ReturnTask wmsCabinet1ReturnTask = wmsCabinet1ReturnTaskService.getOne(new QueryWrapper<WmsCabinet1ReturnTask>().eq("task_number",taskNumber));
                        // 1.更新物料信息状态
                        WmsMaterialTool wmsMaterialTool = wmsMaterialToolService.getOne(new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsCabinet1ReturnTask.getMaterialSerialNumber()));
                        wmsMaterialTool.setStorageState(StateEnum.ONE.getState());// 存放状态(0初始 1库内 2库外)
                        wmsMaterialTool.setStorageAddress(StateEnum.TWO.getState());// 存放地址(1立体仓库 2Ⅰ类柜)
                        wmsMaterialToolService.update(wmsMaterialTool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",wmsCabinet1ReturnTask.getMaterialSerialNumber()));
                        // 2.更新库位信息
                        wmsIntelligentCabinet1Stock.setLocaState(StateEnum.ONE.getState()); // 库位状态(0空闲/1有货/2锁定/3盘点)
                        wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ONE.getState());// 格口状态(0开启/1关闭)
                        wmsIntelligentCabinet1Stock.setMaterialTypeId(wmsCabinet1ReturnTask.getMaterialTypeId()); // 库存-物料类型ID
                        wmsIntelligentCabinet1Stock.setMaterialId(wmsCabinet1ReturnTask.getMaterialId()); // 库存-物料信息ID
                        wmsIntelligentCabinet1Stock.setMaterialName(wmsCabinet1ReturnTask.getMaterialName()); // 物料名称
                        wmsIntelligentCabinet1Stock.setMaterialSku(wmsCabinet1ReturnTask.getMaterialSku()); // 物料SKU
                        wmsIntelligentCabinet1Stock.setMaterialSerialNumber(wmsCabinet1ReturnTask.getMaterialSerialNumber()); // 物料编码
                        wmsIntelligentCabinet1Stock.setmNumber(StateEnum.ONE.getState()); // 数量（默认 1）
                        if(Objects.equals(wmsMaterialTool.getMaterialState(),StateEnum.ONE.getState())){
                            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ONE.getState()); // 工具状态（0维修 1使用）
                        } else {
                            wmsIntelligentCabinet1Stock.setToolState(StateEnum.ZERO.getState()); // 工具状态（0维修 1使用）
                        }
                        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>()
                                .eq("loca_serial_number",locaSerialNumber));
                        // 3.更新任务信息
                        WmsCabinet1ReturnTask returnTask = new WmsCabinet1ReturnTask();
                        returnTask.setTaskState(StateEnum.FOUR.getState());// 任务状态(0初始 1开始 2开始存储 3存储完成 4结束 )
                        wmsCabinet1ReturnTaskService.update(returnTask,new QueryWrapper<WmsCabinet1ReturnTask>().eq("task_number",taskNumber));
                        // todo 用 webSocket 通知前台关闭取料格口
                        Map<String, Object> map = new HashMap<>();
                        map.put("code",StateEnum.TWO.getState());
                        String object = JSONObject.toJSONString(map);
                        WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                        break;
                    } else if(StateEnum.ONE.getState().equals(wmsIntelligentCabinet1Stock.getLatticeState()) && StateEnum.FIVE.equals(flag)){
                        // 更新任务
                        WmsCabinet1RepairTask repairTask = wmsCabinet1RepairTaskService.getOne(new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
                        repairTask.setTaskState(StateEnum.FOUR.getState());
                        wmsCabinet1RepairTaskService.update(repairTask,new QueryWrapper<WmsCabinet1RepairTask>().eq("task_number",taskNumber));
                        // 更新库位
                        updateCabinetEmpty(locaSerialNumber);
                        // 更新工具
                        WmsMaterialTool tool = new WmsMaterialTool();
                        tool.setStorageState(StateEnum.TWO.getState());// 工具库外
                        tool.setStorageAddress(StateEnum.THREE.getState());
                        wmsMaterialToolService.update(tool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",repairTask.getMaterialSerialNumber()));
                        // todo 用 webSocket 通知前台关闭取料格口
                        Map<String, Object> map = new HashMap<>();
                        map.put("code",StateEnum.TWO.getState());
                        map.put("locaSerialNumber",locaSerialNumber);
                        String object = JSONObject.toJSONString(map);
                        WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                        break;
                    } else if(StateEnum.ONE.getState().equals(wmsIntelligentCabinet1Stock.getLatticeState()) && StateEnum.SIX.equals(flag)){
                        // 更新任务
                        WmsCabinet1RepairReturnTask repairReturnTask = wmsCabinet1RepairReturnTaskService.getOne(new QueryWrapper<WmsCabinet1RepairReturnTask>().eq("task_number",taskNumber));
                        repairReturnTask.setTaskState(StateEnum.FOUR.getState());// 任务状态(0初始 1开始 2开始存储 3存储完成 4结束 )
                        wmsCabinet1RepairReturnTaskService.update(repairReturnTask,new QueryWrapper<WmsCabinet1RepairReturnTask>().eq("task_number",taskNumber));
                        // 更新库位信息
                        WmsIntelligentCabinet1Stock cabinet1Stock = new WmsIntelligentCabinet1Stock();
                        cabinet1Stock.setLocaState(StateEnum.ZERO.getState());// 库位锁定
                        cabinet1Stock.setLatticeState(StateEnum.ONE.getState()); // 库位关闭
                        cabinet1Stock.setMaterialTypeId(repairReturnTask.getMaterialTypeId());// 库存-物料类型ID
                        cabinet1Stock.setMaterialId(repairReturnTask.getMaterialId());// 库存-物料信息ID
                        cabinet1Stock.setMaterialName(repairReturnTask.getMaterialName());// 物料名称
                        cabinet1Stock.setMaterialSku(repairReturnTask.getMaterialSku());// 物料SKU
                        cabinet1Stock.setMaterialSerialNumber(repairReturnTask.getMaterialSerialNumber());// 物料编码
                        cabinet1Stock.setmNumber(StateEnum.ONE.getState());// 数量（默认 1）
                        cabinet1Stock.setToolState(StateEnum.ONE.getState());// 工具状态（0维修 1使用）
                        cabinet1Stock.setLocaState(StateEnum.ONE.getState());// 有货状态
                        cabinet1Stock.setLatticeState(StateEnum.ONE.getState());// 格口关闭
                        wmsIntelligentCabinet1StockService.update(cabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",wmsIntelligentCabinet1Stock.getLocaSerialNumber()));
                        // 更新工具信息
                        WmsMaterialTool wmsMaterialTool = new WmsMaterialTool();
                        wmsMaterialTool.setMaterialState(StateEnum.ONE.getState());// 物料状态(0新 1使用中 2维修 3报废)
                        wmsMaterialTool.setStorageState(StateEnum.ONE.getState());// 存放状态(0初始 1库内 2库外)
                        wmsMaterialTool.setStorageAddress(StateEnum.TWO.getState());// 存放地址(1立体仓库 2Ⅰ类柜)
                        wmsMaterialToolService.update(wmsMaterialTool,new QueryWrapper<WmsMaterialTool>().eq("material_serial_number",repairReturnTask.getMaterialSerialNumber()));
                        // todo 用 webSocket 通知前台关闭取料格口
                        Map<String, Object> map = new HashMap<>();
                        map.put("code",StateEnum.TWO.getState());
                        String object = JSONObject.toJSONString(map);
                        WebSocket.sendMessageOfSession1(object);// 1.开 2.关
                        break;
                    }
                } else {
                    break;
                }
            } while (true);
        }
    }

    // 将格口更新为空
    private void updateCabinetEmpty(String locaSerialNumber) {
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = new WmsIntelligentCabinet1Stock();
        wmsIntelligentCabinet1Stock.setLocaState(StateEnum.ZERO.getState());// 空闲格口
        wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ONE.getState());// 关闭格口
        wmsIntelligentCabinet1Stock.setMaterialTypeId("");// 物料类型ID
        wmsIntelligentCabinet1Stock.setMaterialId("");// 物料ID
        wmsIntelligentCabinet1Stock.setMaterialName("");// 物料名称
        wmsIntelligentCabinet1Stock.setMaterialSku("");// 物料SKU
        wmsIntelligentCabinet1Stock.setMaterialSerialNumber("");// 物料编码
        wmsIntelligentCabinet1Stock.setmNumber("");// 数量（默认 1）
        wmsIntelligentCabinet1Stock.setToolState(StateEnum.TWO.getState());// 数量（默认 1）
        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
    }

    // 格口关闭
    public String closeCabinet(String locaSerialNumber) {
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = new WmsIntelligentCabinet1Stock();
        wmsIntelligentCabinet1Stock.setLatticeState(StateEnum.ONE.getState());// 格口关闭
        wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1Stock,new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",locaSerialNumber));
        return "格口关闭成功";
    }


}
