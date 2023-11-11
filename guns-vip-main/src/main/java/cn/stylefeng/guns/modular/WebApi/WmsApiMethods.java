package cn.stylefeng.guns.modular.WebApi;

import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.controller.LoginOneTypeCabinetController;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.response.LoginResponse;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.sparePartsManagement.requestMsg.RequestMsg;
import cn.stylefeng.guns.modular.utils.WebSocket.WebSocket;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseToolUseTask;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseToolUseTaskService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ll
 * @Date: 2021/11/5 14:34
 * @Version 1.0
 */
@Component
public class WmsApiMethods {
    @Autowired
    private  WmsUserService wmsUserService;

    @Autowired
    private LoginOneTypeCabinetController loginOneTypeCabinetController;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;
    public static  WmsApiMethods wmsApiMethods;
    @PostConstruct
    public void init(){
        wmsApiMethods = this;
        wmsApiMethods.wmsUserService = this.wmsUserService;
    }
    // 人脸认证 工具柜
    static void I(String StaffId){
        JSONObject jsonObject = JSONObject.parseObject(StaffId);
        String staffId = jsonObject.get("StaffId").toString();
        // 判断员工标识
        WmsUser wmsUser =  wmsApiMethods.wmsUserService.getOne(new QueryWrapper<WmsUser>().eq("id_info",staffId).eq("data_state", StateEnum.ZERO.getState()));
        if(wmsUser == null){
            WebSocket.sendMessageOfSession1(JSONObject.toJSONString(ResponseData.error("人员认证失败")));
        } else {
            LoginOneTypeCabinetController.user = wmsUser;
            List<LoginResponse> list = wmsApiMethods.loginOneTypeCabinetController.getLoginResponses();
            Map<String,Object> map = new HashMap<>();
            map.put("user",wmsUser);
            map.put("list",list);
            WebSocket.sendMessageOfSession1(JSONObject.toJSONString(ResponseData.success(map)));
        }
    }


    // 人脸认证 备品备件
    public static void II(String StaffId){
    JSONObject jsonObject = JSONObject.parseObject(StaffId);
    String staffId = jsonObject.get("StaffId").toString();
    WmsUserResult wmsUserResult =  wmsApiMethods.wmsUserService.findUserIdInfo2(staffId);
        if (wmsUserResult !=null){
            // 保存人员信息
            RequestMsg.saveUserInfo(wmsUserResult); // 保存登录人员信息
            List list = RequestMsg.tsUserInfo(wmsUserResult); // 人员权限
            Map<String, Object> map = new HashMap<>();
            map.put("userInfo", wmsUserResult);
            map.put("list", list);
            WebSocket.sendMessageOfSession2(JSONObject.toJSONString(ResponseData.success(map)));
    }
        else {
        WebSocket.sendMessageOfSession2(JSONObject.toJSONString(ResponseData.error("人员认证失败")));
    }
    }

    public static void ASRSAutoExeTask(String StaffId){
        try{
            System.out.println(StaffId);
            JSONObject jsonObject = JSONObject.parseObject(StaffId);
            String staffId = jsonObject.get("StaffId").toString();
            toolApplyList(staffId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据角色Number查询任务信息
    public static void toolApplyList(String serialNumber) {
        List<WmsWarehouseToolUseTask> data = wmsApiMethods.wmsWarehouseToolUseTaskService.list(new
               QueryWrapper<WmsWarehouseToolUseTask>()
               .eq("operator",serialNumber)
               .eq("task_state",0)
        );
        if(data == null || data.isEmpty()){
            return;
        }
        wmsApiMethods.warehouseService.claimConform(serialNumber, data.get(0).getTaskNumber());
    }
}
