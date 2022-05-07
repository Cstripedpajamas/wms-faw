package cn.stylefeng.guns.modular.onetypeservice.monitor;

import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.entity.WmsMaterialSpareParts;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.materialtool.service.WmsMaterialToolService;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1ReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1ReturnTaskService;
import cn.stylefeng.guns.modular.onetypeservice.enums.ApplyType;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodeProviderEnum;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity.WmsReturnApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params.WmsReturnApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.service.WmsReturnApplyService;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity.WmsCabinet2UseTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseToolUseTask;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseToolUseTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by li wen ya on 2021/11/22
 * 通过 webService 获取申请任务的状态
 */
@Component
public class ListeningApply implements CommandLineRunner {

    private static boolean kg=true;

    @Autowired
    private WmsReturnApplyService wmsReturnApplyService;

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    @Autowired
    private WmsCabinet1ReturnTaskService wmsCabinet1ReturnTaskService;

    @Autowired
    private WmsCabinet2UseTaskService wmsCabinet2UseTaskService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @Autowired
    private WmsMaterialSparePartsService wmsMaterialSparePartsService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private Map<String,Code> mapCodeGenerator;

    @Override
    public void run(String... args) throws Exception {
        while (kg) {
            List<WmsReturnApply> returnApplies = wmsReturnApplyService.list(new QueryWrapper<WmsReturnApply>().eq("data_state", StateEnum.TWO.getState()));
            if (returnApplies.size() > 0) {
                for (WmsReturnApply apply : returnApplies) {
                    // 1.更新申请任务
                    WmsReturnApplyParam applyParam = new WmsReturnApplyParam();
                    ToolUtil.copyProperties(apply, applyParam);
                    applyParam.setDataState(StateEnum.THREE.getState());
                    wmsReturnApplyService.update(applyParam);
                    // 2.创建执行任务
                    WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(apply.getMaterialId());
                    WmsCabinet1ReturnTask returnTask = new WmsCabinet1ReturnTask();
                    returnTask.setTaskNumber(mapCodeGenerator.get(CodeProviderEnum.returnCode.getProvider()).createCode(null));//任务编号
                    returnTask.setReturnRequestId(String.valueOf(apply.getId()));//归还申请ID
                    returnTask.setProcessNumber(apply.getProcessNumber());//流程单号
                    returnTask.setMaterialTypeId(apply.getMaterialId());//物料类型ID
                    returnTask.setMaterialName(wmsMaterialType.getMaterialName());//物料名称
                    returnTask.setMaterialSku(wmsMaterialType.getMaterialSku());//物料SKU
                    returnTask.setMaterialId(apply.getMaterialId());//物料信息ID
//                    returnTask.setMaterialSerialNumber(wmsMaterialType.getMaterialSerialNumber());//物料编码
                    returnTask.setOperator(apply.getOperator());//操作人
                    returnTask.setTaskState(StateEnum.ZERO.getState());//任务状态(0初始 1开始 2开始存储 3存储完成 4结束)
                    wmsCabinet1ReturnTaskService.save(returnTask);
                }
            }
            List<WmsUseApply> wmsUseApplies = wmsUseApplyService.list(new QueryWrapper<WmsUseApply>().eq("data_state", StateEnum.TWO.getState()));
            if (wmsUseApplies.size() > 0) {
                for (WmsUseApply apply : wmsUseApplies) {
                    // 1.更新申请任务
                    WmsUseApplyParam useApplyParam = new WmsUseApplyParam();
                    ToolUtil.copyProperties(apply, useApplyParam);
                    useApplyParam.setDataState(StateEnum.THREE.getState());
                    wmsUseApplyService.update(useApplyParam);
                    // 2.创建执行任务
                    if(ApplyType.A.getType().equals(apply.getProcessType())){// 工具申请
                        // 创建工具领用任务
                        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(apply.getMaterialId());
                        WmsWarehouseToolUseTask toolUseTask = new WmsWarehouseToolUseTask();
                        toolUseTask.setTaskNumber(mapCodeGenerator.get(CodeProviderEnum.toolClaimCode.getProvider()).createCode(null));//任务编号
                        toolUseTask.setUseRequestId(String.valueOf(apply.getId()));//领用申请ID
                        toolUseTask.setProcessNumber(apply.getProcessNumber());//流程单号
                        toolUseTask.setMaterialTypeId(apply.getMaterialId());//物料类型ID
                        toolUseTask.setMaterialName(wmsMaterialType.getMaterialName());//物料名称
                        toolUseTask.setMaterialSku(wmsMaterialType.getMaterialSku());//物料SKU
                        toolUseTask.setOperator(apply.getOperator());//操作人
                        toolUseTask.setTaskState(StateEnum.ZERO.getState());//任务状态（0初始 1开始 2出库中 3完成 ）
                        toolUseTask.setInterfaceState(StateEnum.ZERO.getState());//接口状态（0初始/1调用）
                        //分拣类型（0人工 1自动)
                        if (wmsMaterialType.getSortType().equals("1")) {
                            toolUseTask.setSortingType(StateEnum.ONE.getState());
                        }else {
                            toolUseTask.setSortingType(StateEnum.ZERO.getState());
                        }
//                        toolUseTask.setSortingType(StateEnum.ZERO.getState());//分拣类型（0人工 1自动)
                        toolUseTask.setSortingStatus(StateEnum.ZERO.getState());//分拣状态（0未分拣 1已分拣）
                        wmsWarehouseToolUseTaskService.save(toolUseTask);
                    } else if(ApplyType.B.getType().equals(apply.getProcessType())){// 备品备件申请
                        // 创建备品备件领用任务
                        WmsCabinet2UseTask useTask = new WmsCabinet2UseTask();
                        useTask.setTaskNumber(mapCodeGenerator.get(CodeProviderEnum.sparePartCode.getProvider()).createCode(null));//任务编号
                        useTask.setUseRequestId(String.valueOf(apply.getId()));//领用申请ID
                        useTask.setProcessNumber(apply.getProcessNumber());//流程单号
                        useTask.setsMaterialTypeId(apply.getScrapMaterialId());//报废-物料类型ID
                        useTask.setsMaterialId(apply.getScrapMaterialId());//物料信息ID
//                        useTask.setsNumber(apply.getScrapNum());//报废数量
                        useTask.setsNumber("0");//报废数量
                        useTask.setUseMaterialTypeId(apply.getMaterialId());//领用-物料类型ID
                        useTask.setUseMaterialId(apply.getMaterialId());//物料信息ID
                        useTask.setUseNumber(apply.getmNumber());//领用数量
                        useTask.setOperator(apply.getOperator());//操作人
                        useTask.setTaskState(StateEnum.ZERO.getState());//任务状态(0初始 1开始投入 2投入完成 3开始出库 4出库完成 5取货完成 6结束)
                        useTask.setInterfaceState(StateEnum.ZERO.getState());//接口状态（0初始/1调用）
                        wmsCabinet2UseTaskService.save(useTask);
                    }
                }
            }
            Thread.sleep(5000);
        }
    }
}
