package cn.stylefeng.guns.modular.base.redirect;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
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
import cn.stylefeng.guns.modular.utils.JudgeUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ApplyController
 * @Description 申请
 * @Author ASD-FuBenHao
 * @Date 2022/5/30 16:22
 * @Version 1.0
 **/
@Controller
@RequestMapping("/apply")
@CrossOrigin
public class ApplyController extends BaseController {

    private String PREFIX = "/modular/base";

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    @Autowired
    private Map<String, Code> mapCodeGenerator;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private WmsReturnApplyService wmsReturnApplyService;


    /**
     * 工具申请
     */
    @RequestMapping("/toolApply")
    public String toolApply() {
        return PREFIX + "/tool_apply.html";
    }

    /**
     * 备品备件申请
     */
    @RequestMapping("/spareApply")
    public String spareApply() {
        return PREFIX + "/spare_apply.html";
    }

    /**
     * 工具归还申请
     */
    @RequestMapping("/toolReturnApply")
    public String toolReturnApply() {
        return PREFIX + "/tool_return_apply.html";
    }

    /**
     * 获取申请人员
     */
    @RequestMapping("/user")
    @ResponseBody
    public ResponseData getUserInfo() {
        LoginUser user = LoginContextHolder.getContext().getUser();
        return ResponseData.success(user.getId());
    }

    /**
     * 新增接口
     */
    @RequestMapping("/addTool")
    @ResponseBody
    public ResponseData addItem(WmsUseApplyParam wmsUseApplyParam) {
        WmsUseApplyParam param = new WmsUseApplyParam();
        param.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        param.setOperator(wmsUseApplyParam.getOperator());
        param.setMaterialId(wmsUseApplyParam.getMaterialId());
        param.setMNumber(StateEnum.ONE.getState());
        param.setProcessType(ApplyType.A.getType());
        param.setProcessReason(wmsUseApplyParam.getProcessReason());
        param.setDataTime(new Date());
        param.setDataState(StateEnum.ZERO.getState());
        this.wmsUseApplyService.add(param);
        return ResponseData.success();
    }

    /**
     * 获取物料类型，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForMaterialType")
    public JSONObject listSelectForMaterialType() {
        List<WmsMaterialType> tools = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type",StateEnum.ONE.getState()).eq("data_state",StateEnum.ZERO.getState()));
        JSONObject object = new JSONObject();
        if (!tools.isEmpty()){
            for (WmsMaterialType wmsMaterialTypeResult : tools) {
                object.put(String.valueOf(wmsMaterialTypeResult.getId()),wmsMaterialTypeResult.getMaterialName()+","+wmsMaterialTypeResult.getMaterialType()+","+wmsMaterialTypeResult.getMaterialSku());
            }
        }
        return object;
    }

    /**
     * 获取物料类型，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForMaterialTypeSpare")
    public JSONObject listSelectForMaterialTypeSpare() {
        List<WmsMaterialType> types = wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type",StateEnum.TWO.getState()).eq("data_state",StateEnum.ZERO.getState()));
        JSONObject object = new JSONObject();
        if (!types.isEmpty()){
            for (WmsMaterialType wmsMaterialTypeResult : types) {
                object.put(String.valueOf(wmsMaterialTypeResult.getId()),wmsMaterialTypeResult.getMaterialName()+","+wmsMaterialTypeResult.getMaterialType()+","+wmsMaterialTypeResult.getMaterialSku());
            }
        }
        return object;
    }

    /**
     * 新增接口
     */
    @RequestMapping("/addSpare")
    @ResponseBody
    public ResponseData addSpare(WmsUseApplyParam wmsUseApplyParam) {
        WmsUseApply useApply = new WmsUseApply();
        useApply.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        useApply.setOperator(wmsUseApplyParam.getOperator());
        useApply.setMaterialId(wmsUseApplyParam.getMaterialId());
        useApply.setmNumber(wmsUseApplyParam.getMNumber());
        useApply.setProcessType(ApplyType.B.getType());
        useApply.setProcessReason(wmsUseApplyParam.getProcessReason());
        useApply.setDataTime(new Date());
        useApply.setDataState(StateEnum.ZERO.getState());
        useApply.setScrapMaterialId(wmsUseApplyParam.getScrapMaterialId());
        useApply.setScrapNum(wmsUseApplyParam.getScrapNum());
        wmsUseApplyService.save(useApply);
        return ResponseData.success();
    }

    /**
     * 新增接口
     */
    @RequestMapping("/addToolReturn")
    @ResponseBody
    public ResponseData addToolReturn(WmsReturnApplyParam wmsReturnApplyParam) {
        WmsReturnApply apply = new WmsReturnApply();
        apply.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        apply.setOperator(wmsReturnApplyParam.getOperator());
        apply.setMaterialId(wmsReturnApplyParam.getMaterialId());
        apply.setmNumber(StateEnum.ONE.getState());
        apply.setDataTime(new Date());
        apply.setReturnReason(wmsReturnApplyParam.getReturnReason());
        apply.setDataState(StateEnum.ZERO.getState());
        wmsReturnApplyService.save(apply);
        return ResponseData.success();
    }

}
