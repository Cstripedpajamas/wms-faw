package cn.stylefeng.guns.threads;

import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1ReturnTaskService;
import cn.stylefeng.guns.modular.onetypeservice.enums.ApplyType;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodeProviderEnum;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UseAppliesThread
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/5/16 11:25
 * @Version 1.0
 **/
@Component
public class UseAppliesThread{

    private final static Logger logger = LoggerFactory.getLogger(UseAppliesThread.class);

    public static UseAppliesThread thread;

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    @Autowired
    private WmsCabinet2UseTaskService wmsCabinet2UseTaskService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private Map<String, Code> mapCodeGenerator;


    @PostConstruct
    public void init() {
        thread = this;
    }

    public static void startThread() {
        while (true){
            runThreadMain();
            logger.info("线程运行- 领用申请");
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void runThreadMain(){
        List<WmsUseApply> wmsUseApplies = thread.wmsUseApplyService.list(new QueryWrapper<WmsUseApply>().eq("data_state", StateEnum.TWO.getState()));
        if (wmsUseApplies.size() > 0) {
            for (WmsUseApply apply : wmsUseApplies) {
                // 1.更新申请任务
                WmsUseApplyParam useApplyParam = new WmsUseApplyParam();
                ToolUtil.copyProperties(apply, useApplyParam);
                useApplyParam.setDataState(StateEnum.THREE.getState());
                thread.wmsUseApplyService.update(useApplyParam);
                // 2.创建执行任务
                // 工具申请
                if(ApplyType.A.getType().equals(apply.getProcessType())){
                    // 创建工具领用任务
                    WmsMaterialType wmsMaterialType = thread.wmsMaterialTypeService.getById(apply.getMaterialId());
                    WmsWarehouseToolUseTask toolUseTask = new WmsWarehouseToolUseTask();
                    //任务编号
                    toolUseTask.setTaskNumber(thread.mapCodeGenerator.get(CodeProviderEnum.toolClaimCode.getProvider()).createCode(null));
                    //领用申请ID
                    toolUseTask.setUseRequestId(String.valueOf(apply.getId()));
                    //流程单号
                    toolUseTask.setProcessNumber(apply.getProcessNumber());
                    //物料类型ID
                    toolUseTask.setMaterialTypeId(apply.getMaterialId());
                    //物料名称
                    toolUseTask.setMaterialName(wmsMaterialType.getMaterialName());
                    //物料SKU
                    toolUseTask.setMaterialSku(wmsMaterialType.getMaterialSku());
                    //操作人
                    toolUseTask.setOperator(apply.getOperator());
                    //任务状态（0初始 1开始 2出库中 3完成 ）
                    toolUseTask.setTaskState(StateEnum.ZERO.getState());
                    //接口状态（0初始/1调用）
                    toolUseTask.setInterfaceState(StateEnum.ZERO.getState());
                    //分拣类型（0人工 1自动)
                    if (wmsMaterialType.getSortType().equals("1")) {
                        toolUseTask.setSortingType(StateEnum.ONE.getState());
                    }else {
                        toolUseTask.setSortingType(StateEnum.ZERO.getState());
                    }
//                        toolUseTask.setSortingType(StateEnum.ZERO.getState());//分拣类型（0人工 1自动)
                    //分拣状态（0未分拣 1已分拣）
                    toolUseTask.setSortingStatus(StateEnum.ZERO.getState());
                    thread.wmsWarehouseToolUseTaskService.save(toolUseTask);
                }
                // 备品备件申请
                else if(ApplyType.B.getType().equals(apply.getProcessType())){
                    // 创建备品备件领用任务
                    WmsCabinet2UseTask useTask = new WmsCabinet2UseTask();
                    //任务编号
                    useTask.setTaskNumber(thread.mapCodeGenerator.get(CodeProviderEnum.sparePartCode.getProvider()).createCode(null));
                    //领用申请ID
                    useTask.setUseRequestId(String.valueOf(apply.getId()));
                    //流程单号
                    useTask.setProcessNumber(apply.getProcessNumber());
                    //报废-物料类型ID
                    useTask.setsMaterialTypeId(apply.getScrapMaterialId());
                    //物料信息ID
                    useTask.setsMaterialId(apply.getScrapMaterialId());
//                        useTask.setsNumber(apply.getScrapNum());//报废数量
                    //报废数量
                    useTask.setsNumber("0");
                    //领用-物料类型ID
                    useTask.setUseMaterialTypeId(apply.getMaterialId());
                    //物料信息ID
                    useTask.setUseMaterialId(apply.getMaterialId());
                    //领用数量
                    useTask.setUseNumber(apply.getmNumber());
                    //操作人
                    useTask.setOperator(apply.getOperator());
                    //任务状态(0初始 1开始投入 2投入完成 3开始出库 4出库完成 5取货完成 6结束)
                    useTask.setTaskState(StateEnum.ZERO.getState());
                    //接口状态（0初始/1调用）
                    useTask.setInterfaceState(StateEnum.ZERO.getState());
                    thread.wmsCabinet2UseTaskService.save(useTask);
                }
            }
        }
    }

}
