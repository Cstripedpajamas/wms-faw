package cn.stylefeng.guns.threads;

import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1ReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1ReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1ReturnTaskService;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodeProviderEnum;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity.WmsReturnApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params.WmsReturnApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.service.WmsReturnApplyService;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
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
 * @ClassName GuiHuanThread
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/5/16 11:25
 * @Version 1.0
 **/
@Component
public class GuiHuanAppliesThread {

    private final static Logger logger = LoggerFactory.getLogger(GuiHuanAppliesThread.class);

    public static GuiHuanAppliesThread thread;

    @Autowired
    private WmsReturnApplyService wmsReturnApplyService;

    @Autowired
    private WmsCabinet1ReturnTaskService wmsCabinet1ReturnTaskService;

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
            logger.info("Task Thread- Return application");
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void runThreadMain(){
        List<WmsReturnApply> returnApplies = thread.wmsReturnApplyService.list(new QueryWrapper<WmsReturnApply>().eq("data_state", StateEnum.TWO.getState()));
        if (returnApplies.size() > 0) {
            for (WmsReturnApply apply : returnApplies) {
                // 1.更新申请任务
                WmsReturnApplyParam applyParam = new WmsReturnApplyParam();
                ToolUtil.copyProperties(apply, applyParam);
                applyParam.setDataState(StateEnum.THREE.getState());
                thread.wmsReturnApplyService.update(applyParam);
                // 2.创建执行任务
                WmsMaterialType wmsMaterialType = thread.wmsMaterialTypeService.getById(apply.getMaterialId());
                WmsCabinet1ReturnTask returnTask = new WmsCabinet1ReturnTask();
                //任务编号
                returnTask.setTaskNumber(thread.mapCodeGenerator.get(CodeProviderEnum.returnCode.getProvider()).createCode(null));
                //归还申请ID
                returnTask.setReturnRequestId(String.valueOf(apply.getId()));
                //流程单号
                returnTask.setProcessNumber(apply.getProcessNumber());
                //物料类型ID
                returnTask.setMaterialTypeId(apply.getMaterialId());
                //物料名称
                returnTask.setMaterialName(wmsMaterialType.getMaterialName());
                //物料SKU
                returnTask.setMaterialSku(wmsMaterialType.getMaterialSku());
                //物料信息ID
                returnTask.setMaterialId(apply.getMaterialId());
                //物料编码
//                returnTask.setMaterialSerialNumber(wmsMaterialType.getMaterialSerialNumber());

                returnTask.setToolErrorContent(apply.getReturnReason());
                //操作人
                returnTask.setOperator(apply.getOperator());
                //任务状态(0初始 1开始 2开始存储 3存储完成 4结束)
                returnTask.setTaskState(StateEnum.ZERO.getState());
                WmsCabinet1ReturnTaskParam wms = new WmsCabinet1ReturnTaskParam();
                ToolUtil.copyProperties(returnTask,wms);
                thread.wmsCabinet1ReturnTaskService.add(wms);
            }
        }
    }

}
