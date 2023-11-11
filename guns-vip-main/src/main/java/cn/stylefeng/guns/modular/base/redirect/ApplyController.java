package cn.stylefeng.guns.modular.base.redirect;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
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
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.entity.WmsCabinet2Stock;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service.WmsCabinet2StockService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseToolUseTask;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseToolUseTaskService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private WmsUserService wmsUserService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;


    @Autowired
    private WmsCabinet2StockService wmsCabinet2StockService;

    /**
     * 工具申请
     */
    @RequestMapping("/toolApply")
    public String toolApply() {
        return PREFIX + "/tool_apply.html";
    }

    /**
     * 工具申请
     */
    @RequestMapping("/toolApplyNumber")
    public String toolApplyNumber() {
        return PREFIX + "/tool_applyNumber.html";
    }

    /**
     * 申请
     */
    @RequestMapping("/applicationPage")
    public String applicationPage() {
        return PREFIX + "/application_page.html";
    }

    /**
     * 备品备件申请
     */
    @RequestMapping("/spareApply")
    public String spareApply() {
        return PREFIX + "/spare_apply.html";
    }



    /**
     * 技术发展部领用
     */
    @RequestMapping("/technologyApply")
    public String technologyApply() {
        return PREFIX + "/technology_apply.html";
    }


    /**
     * 技术发展部领用（二类柜）
     */
    @RequestMapping("/technologyApplyII")
    public String technologyApplyII() {
        return PREFIX + "/technology_apply_II.html";
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
        return ResponseData.success(user.getAccount());
    }

    /**
     * 获取审批人员，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectApprovedBy")
    public ResponseData listSelectApprovedBy() {
        System.out.println("、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、");
        LoginUser user = LoginContextHolder.getContext().getUser();
        List<Map<String,String>> data = new ArrayList<>();
        WmsUserResult userName =wmsUserService.findUserName(user.getName());
        Set<String> tools = wmsUserService.findListUserName(userName.getWorkTeam()).stream().map(m -> m.getUserName()).collect(Collectors.toSet());
//        Set<String> toolsS = wmsUserService.findListUserName(userName.getWorkTeam()).stream().map(m -> m.getSerialNumber()).collect(Collectors.toSet());
        System.out.println(tools);
        if (!tools.isEmpty()){
            System.out.println("////////////////////////////////////////////////////////////////");
            int i = 1;
            for (String key : tools) {
                WmsUserResult wmsUserResult= wmsUserService.findUserName(key);
                if (wmsUserResult!=null){
                    HashMap<String,String> map = new HashMap<>();
                    map.put("id",String.valueOf(wmsUserResult.getSerialNumber()));
                    map.put("approvedBy",key);
                    data.add(map);
                }
            }
        };
        return ResponseData.success(data);
    }

    /**
     * 获取审批人员，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectApprovedBySerialNumber")
    public ResponseData listSelectApprovedBySerialNumber(String operator) {

//        LoginUser user = LoginContextHolder.getContext().getUser();
        List<Map<String,String>> data = new ArrayList<>();

//        WmsUser user =wmsUserService.getOne(new QueryWrapper<WmsUser>().eq("",operator));
        WmsUserResult userName =wmsUserService.findSerialNumber(operator);
        String str=userName.getWorkTeam();
        List<WmsUserResult> aa=wmsUserService.findListUserName(userName.getWorkTeam());
        Set<String> tools = wmsUserService.findListUserName(userName.getWorkTeam()).stream().map(m -> m.getUserName()).collect(Collectors.toSet());
//        Set<String> toolsS = wmsUserService.findListUserName(userName.getWorkTeam()).stream().map(m -> m.getSerialNumber()).collect(Collectors.toSet());
        System.out.println(tools);
        if (!tools.isEmpty()){
            int i = 1;
            for (String key : tools) {
                WmsUserResult wmsUserResult= wmsUserService.findUserName(key);
                if (wmsUserResult!=null){
                    HashMap<String,String> map = new HashMap<>();
                    map.put("id",String.valueOf(wmsUserResult.getSerialNumber()));
                    map.put("approvedBy",key);
                    data.add(map);
                }
            }
        };
        return ResponseData.success(data);
    }

    /**
     * 新增接口
     */
    @RequestMapping("/addTool")
    @ResponseBody
    public ResponseData addItem(WmsUseApplyParam wmsUseApplyParam) throws ApiException {
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//        OapiGettokenRequest req = new OapiGettokenRequest();
//        req.setAppkey("dingotsmhynm0kuntqmp");
//        req.setAppsecret("S_1wYkvX93FI5NME5jytYvuOhUkqI3TTUynXZBkJUeT1HXvrkE-_jGw_M_q8mYWS");
//        req.setHttpMethod("GET");
//        OapiGettokenResponse rsp = client.execute(req);
//        System.out.println(rsp.getAccessToken());
//
//        DingTalkClient clientTwo = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getuserinfo");
//        OapiV2UserGetuserinfoRequest reqTwo = new OapiV2UserGetuserinfoRequest();
//        String accessToken=rsp.getAccessToken();
//        OapiV2UserGetuserinfoResponse rspTwo = client.execute(reqTwo, accessToken);
//        reqTwo.setCode(wmsUseApplyParam.getApplyCode());

        WmsUseApplyParam param = new WmsUseApplyParam();
        param.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        param.setOperator(wmsUseApplyParam.getOperator());
        param.setMaterialId(wmsUseApplyParam.getMaterialId());
        param.setApprovedBy(wmsUseApplyParam.getApprovedBy());
        param.setMNumber(wmsUseApplyParam.getMNumber());
        param.setProcessType(ApplyType.A.getType());
        param.setProcessReason(wmsUseApplyParam.getProcessReason());
        param.setDataTime(new Date());
        param.setDataState(StateEnum.ZERO.getState());

//        王盼宇修改于20230518，修改内容：领用申请表增加四个字段，用户申请领用时自己填写
        param.setJieSan(wmsUseApplyParam.getJieSan());
        param.setPostDate(wmsUseApplyParam.getPostDate());
        param.setOrdNO(wmsUseApplyParam.getOrdNO());
        param.setCostCenter("R060020");
//        param.setBusArea("6010104");
        param.setBusArea("60101");
        param.setMainAssetNo(wmsUseApplyParam.getMainAssetNo());

        List<WmsUser> toolsWmsUser = this.wmsUserService.list(
                new QueryWrapper<WmsUser>()
                .eq("serial_number",wmsUseApplyParam.getOperator())
                .eq("data_state","0")
                .eq("user_type","D")
        );
        List<WmsMaterialType> toolsWmsMaterialType = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                .eq("id",wmsUseApplyParam.getMaterialId())
                .eq("data_state",StateEnum.ZERO.getState())
                .eq("material_type","BJ")
        );
        if (!toolsWmsMaterialType.isEmpty() && !toolsWmsUser.isEmpty()){
//            return "您的权限不支持领取改物品";
            return ResponseData.error("申请物料类型不匹配!");
        }
            this.wmsUseApplyService.add(param);
            return ResponseData.success();
    }


    /**
     * 新增接口（技术发展部申请领用）
     */
    @RequestMapping("/addTechnology")
    @ResponseBody
    public ResponseData addTechnology(WmsUseApplyParam wmsUseApplyParam) throws ApiException {

//        王盼宇新增于20231008
        WmsUseApplyParam param = new WmsUseApplyParam();
        param.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        param.setOperator(wmsUseApplyParam.getOperator());
        param.setMaterialId(wmsUseApplyParam.getMaterialId());
        param.setApprovedBy(wmsUseApplyParam.getApprovedBy());
        param.setMNumber(wmsUseApplyParam.getMNumber());
        param.setProcessType(ApplyType.A.getType());
        param.setProcessReason(wmsUseApplyParam.getProcessReason());
        param.setDataTime(new Date());
        param.setDataState(StateEnum.ZERO.getState());

        param.setJieSan(wmsUseApplyParam.getJieSan());
        param.setPostDate(wmsUseApplyParam.getPostDate());
        param.setOrdNO(wmsUseApplyParam.getOrdNO());
        param.setCostCenter("Z28");
//        param.setBusArea("6010104");
        param.setBusArea("6");
        param.setMainAssetNo(wmsUseApplyParam.getMainAssetNo());

        List<WmsUser> toolsWmsUser = this.wmsUserService.list(
                new QueryWrapper<WmsUser>()
                        .eq("serial_number",wmsUseApplyParam.getOperator())
                        .eq("data_state","0")
                        .eq("user_type","D")
        );
        List<WmsMaterialType> toolsWmsMaterialType = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                .eq("id",wmsUseApplyParam.getMaterialId())
                .eq("data_state",StateEnum.ZERO.getState())
                .eq("material_type","BJ")
        );
        if (!toolsWmsMaterialType.isEmpty() && !toolsWmsUser.isEmpty()){
//            return "您的权限不支持领取改物品";
            return ResponseData.error("申请物料类型不匹配!");
        }
        this.wmsUseApplyService.add(param);
        return ResponseData.success();
    }

    /**
     * 新增接口
     */
    @RequestMapping("/addToolNumber")
    @ResponseBody
    public ResponseData addItemNumber(WmsUseApplyParam wmsUseApplyParam) throws ApiException {
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//        OapiGettokenRequest req = new OapiGettokenRequest();
//        req.setAppkey("dingotsmhynm0kuntqmp");
//        req.setAppsecret("S_1wYkvX93FI5NME5jytYvuOhUkqI3TTUynXZBkJUeT1HXvrkE-_jGw_M_q8mYWS");
//        req.setHttpMethod("GET");
//        OapiGettokenResponse rsp = client.execute(req);
//        System.out.println(rsp.getAccessToken());
//
//        DingTalkClient clientTwo = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getuserinfo");
//        OapiV2UserGetuserinfoRequest reqTwo = new OapiV2UserGetuserinfoRequest();
//        String accessToken=rsp.getAccessToken();
//        OapiV2UserGetuserinfoResponse rspTwo = client.execute(reqTwo, accessToken);
//        reqTwo.setCode(wmsUseApplyParam.getApplyCode());

        WmsUseApplyParam param = new WmsUseApplyParam();
        param.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        param.setOperator(wmsUseApplyParam.getOperator());
        param.setMaterialId(wmsUseApplyParam.getMaterialId());
        param.setMNumber(wmsUseApplyParam.getMNumber());
        param.setProcessType(ApplyType.A.getType());
        param.setProcessReason(wmsUseApplyParam.getProcessReason());
        param.setDataTime(new Date());
        param.setDataState(StateEnum.ZERO.getState());

//        王盼宇修改于20230518，修改内容：领用申请表增加四个字段，用户申请领用时自己填写
        param.setJieSan(wmsUseApplyParam.getJieSan());
        param.setPostDate(wmsUseApplyParam.getPostDate());
        param.setOrdNO(wmsUseApplyParam.getOrdNO());
        param.setCostCenter("R060020");
//        param.setBusArea("6010104");
        param.setBusArea("60101");
        param.setMainAssetNo(wmsUseApplyParam.getMainAssetNo());

        List<WmsUser> toolsWmsUser = this.wmsUserService.list(
                new QueryWrapper<WmsUser>()
                        .eq("serial_number",wmsUseApplyParam.getOperator())
                        .eq("data_state","0")
                        .eq("user_type","D")
        );
        List<WmsMaterialType> toolsWmsMaterialType = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                .eq("id",wmsUseApplyParam.getMaterialId())
                .eq("data_state",StateEnum.ZERO.getState())
                .eq("material_type","BJ")
        );
        if (!toolsWmsMaterialType.isEmpty() && !toolsWmsUser.isEmpty()){
//            return "您的权限不支持领取改物品";
            return ResponseData.error("申请物料类型不匹配!");
        }
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
                object.put(String.valueOf(wmsMaterialTypeResult.getId()),wmsMaterialTypeResult.getMaterialName()+","+wmsMaterialTypeResult.getMaterialName()+","+wmsMaterialTypeResult.getMaterialSku());
            }
        }
        return object;
    }

    /**
     * 获取工厂，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForplant")
    public ResponseData listSelectForplant(String sku,String type) {
        List<WmsMaterialType> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                ).stream().collect(Collectors.toList());
        WmsMaterialType wmsMaterialType = tools.get(0);

        int totalMNumber=0;
        if (type!=null&&type.equals("II")){
            List<WmsCabinet2Stock> wmsCabinet2StockList=this.wmsCabinet2StockService.list(new QueryWrapper<WmsCabinet2Stock>().eq("material_sku",sku));

            for (WmsCabinet2Stock stock : wmsCabinet2StockList){
                totalMNumber+=Integer.parseInt(stock.getmNumber());
            }
        }else{
            List<WmsWarehouseTurnoverBind> wmsWarehouseTurnoverBindList= this.wmsWarehouseTurnoverBindService.list(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("material_sku",sku));

            for (WmsWarehouseTurnoverBind bind : wmsWarehouseTurnoverBindList){
                totalMNumber+=Integer.parseInt(bind.getmNumber());
            }
        }

        int useTotalMNumber=0;
        int useApplyNumber=0;
        List<WmsUseApply> wmsUseApplyList=this.wmsUseApplyService.list(
                new QueryWrapper<WmsUseApply>()
                .eq("material_id",wmsMaterialType.getId()));
        for (WmsUseApply bind : wmsUseApplyList){
            if (Integer.parseInt(bind.getDataState())<=1){
                useApplyNumber+=Integer.parseInt(bind.getmNumber());
            }
        }
        int toolUseTask=0;
        List<WmsWarehouseToolUseTask> wmsWarehouseToolUseTaskList =this.wmsWarehouseToolUseTaskService.list(
                new QueryWrapper<WmsWarehouseToolUseTask>()
                        .eq("material_sku",sku));
        for (WmsWarehouseToolUseTask bind : wmsWarehouseToolUseTaskList){
            if (Integer.parseInt(bind.getTaskState())<3){
                toolUseTask+=Integer.parseInt(bind.getmNumber());
            }
        }
        useTotalMNumber=useApplyNumber+toolUseTask;

        WmsMaterialTypeParam wm=new WmsMaterialTypeParam();
        ToolUtil.copyProperties(wmsMaterialType, wm);
        wm.setTotalMNumber(String.valueOf(totalMNumber));
        wm.setUseTotalMNumber(String.valueOf(useTotalMNumber));

        return ResponseData.success(wm);
    }




    /**
     * 获取工厂，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForplant-vue")
    public ResponseData listSelectForplantVue(String sku) {
        Set<String> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                ).stream().map(m -> m.getPlant()).collect(Collectors.toSet());
        List<Map<String,String>> res = new ArrayList<>();
        if (!tools.isEmpty()){
            int i = 1;
            for (String key : tools) {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",String.valueOf(i));
                map.put("plant",key);
                res.add(map);
            }
        }
        return ResponseData.success(res);
    }

    /**
     * 获取物料类型，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectFormaterialtype")
    public JSONObject listSelectFormaterialtype(String sku, String plant) {
        Set<String> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                        .eq("sizes",plant)
                ).stream().map(m -> m.getMaterialType()).collect(Collectors.toSet());
        JSONObject object = new JSONObject();
        if (!tools.isEmpty()){
            for (String key : tools) {
                object.put(key, key);
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
    @RequestMapping("/listSelectFormaterialtype-vue")
    public ResponseData listSelectFormaterialtypeVue(String sku, String plant) {
        Set<String> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                        .eq("plant",plant)
                ).stream().map(m -> m.getMaterialType()).collect(Collectors.toSet());
        List<Map<String,String>> res = new ArrayList<>();
        if (!tools.isEmpty()){
            int i = 1;
            for (String key : tools) {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",String.valueOf(i));
                map.put("material_sku",key);
//                map.put("plant",key);
                res.add(map);
            }
        }
        return ResponseData.success(res);
    }

    /**
     * 获取批次，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForDibatch")
    public JSONObject listSelectForDibatch(String sku, String plant, String materialType) {
        Set<String> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                        .eq("sizes",plant)
                        .eq("material_type",materialType)
                ).stream().map(m -> m.getDiBatchNo()).collect(Collectors.toSet());
        JSONObject object = new JSONObject();
        if (!tools.isEmpty()){
            for (String key : tools) {
                object.put(key, key);
            }
        }
        return object;
    }

    /**
     * 获取批次，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForDibatch-vue")
    public ResponseData listSelectForDibatchVue(String sku, String plant, String materialType) {
        Set<String> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                        .eq("plant",plant)
                        .eq("material_type",materialType)
                ).stream().map(m -> m.getDiBatchNo()).collect(Collectors.toSet());
        List<Map<String,String>> res = new ArrayList<>();
        if (!tools.isEmpty()){
            int i = 1;
            for (String key : tools) {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",String.valueOf(i));
//                map.put("material_sku",key);
//                map.put("plant",key);
                map.put("diBatch",key);
                res.add(map);
            }
        }
        return ResponseData.success(res);
    }

    /**
     * 获取批次，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForName-vue")
    public ResponseData listSelectForNameVue(String sku, String plant, String materialType, String diBatch) {
        List<WmsMaterialType> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                        .eq("plant",plant)
                        .eq("material_type",materialType)
                        .eq("di_batchNo",diBatch)
                ).stream().collect(Collectors.toList());
        Map<String,String> res = new HashMap<>();
        if (!tools.isEmpty()){
            res.put("materialName", tools.get(0).getMaterialName());
            res.put("materialId", tools.get(0).getId().toString());
            res.put("mUnit", tools.get(0).getmUnit());
        }
        return ResponseData.success(res);
    }

    /**
     * 获取批次，显示到select下拉框
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelectForName")
    public ResponseData listSelectForName(String sku, String plant, String materialType, String diBatch) {
        List<WmsMaterialType> tools =
                this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                        .eq("material_sku",sku)
                        .eq("sizes",plant)
                        .eq("material_type",materialType)
                        .eq("di_batchNo",diBatch)
                ).stream().collect(Collectors.toList());
        return ResponseData.success(tools.get(0));
    }

    /**
     * 新增接口
     */
    @RequestMapping("/addTechnologyII")
    @ResponseBody
    public ResponseData addTechnologyII(WmsUseApplyParam wmsUseApplyParam) {
        WmsUseApply useApply = new WmsUseApply();
        useApply.setProcessNumber(mapCodeGenerator.get(CodeProviderEnum.processCode.getProvider()).createCode(null));
        useApply.setOperator(wmsUseApplyParam.getOperator());
        useApply.setMaterialId(wmsUseApplyParam.getMaterialId());
        useApply.setApprovedBy(wmsUseApplyParam.getApprovedBy());
        useApply.setmNumber(wmsUseApplyParam.getMNumber());
        useApply.setProcessType(ApplyType.B.getType());
        useApply.setProcessReason(wmsUseApplyParam.getProcessReason());
        useApply.setDataTime(new Date());
        useApply.setDataState(StateEnum.ZERO.getState());
        useApply.setScrapMaterialId(wmsUseApplyParam.getScrapMaterialId());
        useApply.setScrapNum(wmsUseApplyParam.getScrapNum());
        //        王盼宇修改于20230604，修改内容：领用申请表增加四个字段，用户申请领用时自己填写
        useApply.setPostDate(wmsUseApplyParam.getPostDate());
        useApply.setOrdNO(wmsUseApplyParam.getOrdNO());
        useApply.setCostCenter("Z28");
//        useApply.setBusArea("6010104");
        useApply.setBusArea("6");
        useApply.setJieSan(true);
        useApply.setMainAssetNo(wmsUseApplyParam.getMainAssetNo());

        wmsUseApplyService.save(useApply);
        return ResponseData.success();
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
        useApply.setApprovedBy(wmsUseApplyParam.getApprovedBy());
        useApply.setmNumber(wmsUseApplyParam.getMNumber());
        useApply.setProcessType(ApplyType.B.getType());
        useApply.setProcessReason(wmsUseApplyParam.getProcessReason());
        useApply.setDataTime(new Date());
        useApply.setDataState(StateEnum.ZERO.getState());
        useApply.setScrapMaterialId(wmsUseApplyParam.getScrapMaterialId());
        useApply.setScrapNum(wmsUseApplyParam.getScrapNum());
        //        王盼宇修改于20230604，修改内容：领用申请表增加四个字段，用户申请领用时自己填写
        useApply.setPostDate(wmsUseApplyParam.getPostDate());
        useApply.setOrdNO(wmsUseApplyParam.getOrdNO());
        useApply.setCostCenter("R060020");
//        useApply.setBusArea("6010104");
        useApply.setBusArea("60101");
        useApply.setJieSan(true);
        useApply.setMainAssetNo(wmsUseApplyParam.getMainAssetNo());

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
        apply.setDataState(StateEnum.TWO.getState());
        wmsReturnApplyService.save(apply);
        return ResponseData.success();
    }

}
