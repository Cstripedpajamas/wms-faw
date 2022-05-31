package cn.stylefeng.guns.threads;

import cn.stylefeng.guns.modular.WebApi.Entity.BpmSendBody2Entity;
import cn.stylefeng.guns.modular.WebApi.Entity.BpmSendBodyEntity;
import cn.stylefeng.guns.modular.WebApi.Entity.BpmSendHeaderEntity;
import cn.stylefeng.guns.modular.WebApi.WmsApiService;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.fawInfo.userInfo.entity.FawUserInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.result.FawUserInfoResult;
import cn.stylefeng.guns.modular.fawInfo.userInfo.service.FawUserInfoService;
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
import java.util.ArrayList;
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

    @Autowired
    private WmsApiService wmsApiService;

    @Autowired
    private FawUserInfoService fawUserInfoService;



    @PostConstruct
    public void init() {
        thread = this;
    }

    public static void startThread() {
        while (true){
            runThreadMain();
//            runThreadMainA();
//            runThreadMainB();
            logger.info("Task Thread- Requisition application");
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * [查询BPM 处理进度]
     * @author       : [ASD-FuBenHao]
     * @version      : [v1.0]
     * @createTime   : [2022/5/31 14:17]
     **/
    private static void runThreadMainB(){
        List<WmsUseApply> wmsUseApplies = thread.wmsUseApplyService.list(new QueryWrapper<WmsUseApply>().eq("data_state", StateEnum.ONE.getState()));
        if (wmsUseApplies.size() > 0) {
            for (WmsUseApply apply : wmsUseApplies) {
                WmsUseApplyParam useApplyParam = new WmsUseApplyParam();
                ToolUtil.copyProperties(apply, useApplyParam);
                int rs=findBpmRs(apply);
                if (rs==1){
                    useApplyParam.setDataState(StateEnum.TWO.getState());
                    thread.wmsUseApplyService.update(useApplyParam);
                }
                if (rs==2){
                    useApplyParam.setDataState(StateEnum.THREE.getState());
                    thread.wmsUseApplyService.update(useApplyParam);
                }
            }
        }
    }

    /**
     * [提交BPM申请]
     * @author       : [ASD-FuBenHao]
     * @version      : [v1.0]
     * @createTime   : [2022/5/31 13:49]
     **/
    private static void runThreadMainA(){
        List<WmsUseApply> wmsUseApplies = thread.wmsUseApplyService.list(new QueryWrapper<WmsUseApply>().eq("data_state", StateEnum.ZERO.getState()));
        if (wmsUseApplies.size() > 0) {
            for (WmsUseApply apply : wmsUseApplies) {
                if (sendBpmRs(apply)){
                    WmsUseApplyParam useApplyParam = new WmsUseApplyParam();
                    ToolUtil.copyProperties(apply, useApplyParam);
                    useApplyParam.setDataState(StateEnum.ONE.getState());
                    thread.wmsUseApplyService.update(useApplyParam);
                }
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

    private static int findBpmRs(WmsUseApply apply){
        int rs=0;
        BpmSendHeaderEntity bpmSendHeaderEntity=new BpmSendHeaderEntity();
        bpmSendHeaderEntity.setReceiver("JF_BPM");
        bpmSendHeaderEntity.setSender("JF_TWMS");
        bpmSendHeaderEntity.setTransID("1");
        bpmSendHeaderEntity.setCount("1");
        bpmSendHeaderEntity.setMessageID("1");
        bpmSendHeaderEntity.setComment("1");
        bpmSendHeaderEntity.setInterfaceID("TWMS-BPM-001");
        BpmSendBody2Entity bpmSendBody2Entity=new BpmSendBody2Entity();
        bpmSendBody2Entity.setPageNo(1);
        bpmSendBody2Entity.setPageSize(3);
        bpmSendBody2Entity.setEmployeeId(apply.getOperator());
        List<Map<String, String>> mapList=thread.wmsApiService.queryBpm(bpmSendHeaderEntity,bpmSendBody2Entity);
        if (mapList!=null){
            if (!mapList.isEmpty()){
                for (Map<String, String> stringStringMap : mapList) {
                    String processNo=stringStringMap.get("processNo");
                    String status=stringStringMap.get("status");
                    if (processNo.equals(apply.getProcessNumber())){
                        if (status!=null){
                            if (status.equals("COMPLETED")){
                                rs=1;
                            }
                            if (status.equals("REJECT")){
                                rs=2;
                            }
                        }
                    }
                }
            }
        }
        return rs;
    }

    private static boolean sendBpmRs(WmsUseApply apply){
        WmsMaterialType wmsMaterialType = thread.wmsMaterialTypeService.getById(apply.getMaterialId());
        List<FawUserInfo> fawUserInfoResultList = thread.fawUserInfoService.list(new QueryWrapper<FawUserInfo>().eq("faw_cla_of_pos","10").or().eq("faw_cla_of_pos","9"));
        List<String> stringList=new ArrayList<>();
        if (!fawUserInfoResultList.isEmpty()){
            for (FawUserInfo fawUserInfo : fawUserInfoResultList) {
                stringList.add(fawUserInfo.getEmployeeId());
            }
        }
        String stringFromList = String.join(",", stringList);
        BpmSendHeaderEntity bpmSendHeaderEntity=new BpmSendHeaderEntity();
        bpmSendHeaderEntity.setReceiver("JF_BPM");
        bpmSendHeaderEntity.setSender("JF_TWMS");
        bpmSendHeaderEntity.setTransID("1");
        bpmSendHeaderEntity.setCount("1");
        bpmSendHeaderEntity.setMessageID("1");
        bpmSendHeaderEntity.setComment("1");
        bpmSendHeaderEntity.setInterfaceID("TWMS-BPM-002");
        int applyType=1;
        if(ApplyType.B.getType().equals(apply.getProcessType())){
            applyType=2;
        }
        BpmSendBodyEntity bpmSendBodyEntity=new BpmSendBodyEntity();
        bpmSendBodyEntity.setApplyType(applyType);
        bpmSendBodyEntity.setMaterialName(wmsMaterialType.getMaterialName());
        bpmSendBodyEntity.setMaterialNumber(wmsMaterialType.getMaterialType());
        bpmSendBodyEntity.setApplyReason(apply.getProcessReason());
        bpmSendBodyEntity.setEmployeeId(apply.getOperator());
        bpmSendBodyEntity.setMaterialQuantity(Integer.valueOf(apply.getmNumber()));
        bpmSendBodyEntity.setProcessNo(apply.getProcessNumber());
        bpmSendBodyEntity.setMaterialSku(wmsMaterialType.getMaterialSku());
        bpmSendBodyEntity.setApprover(stringFromList);
        return thread.wmsApiService.sendBpm(bpmSendHeaderEntity,bpmSendBodyEntity);
    }
}
