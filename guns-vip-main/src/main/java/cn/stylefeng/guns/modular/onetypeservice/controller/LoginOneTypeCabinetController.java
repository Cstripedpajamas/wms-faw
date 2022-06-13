package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.response.LoginResponse;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by li wen ya on 2021/11/12
 */
@RestController
@CrossOrigin
@RequestMapping("/cabinet")
@Api(description = "Ⅰ类柜登录接口")
public class LoginOneTypeCabinetController {

    public static WmsUser user = null;

    @Autowired
    private WmsUserService wmsUserService;

    // 认证人员信息 备品备件柜
    @RequestMapping(value = "/StaffId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData checkUserMsg(@RequestBody String StaffId) {
        JSONObject jsonObject = JSONObject.parseObject(StaffId);
        String staffId = jsonObject.get("StaffId").toString();
        // 判断员工标识
        WmsUser wmsUser =  wmsUserService.getOne(new QueryWrapper<WmsUser>().eq("id_info",staffId));
        if(wmsUser == null){
            return ResponseData.success("无此用户！");
        }
        user = wmsUser;
        List<LoginResponse> list = getLoginResponses();
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("list",list);
        return ResponseData.success(map);
    }

    // 获取权限
    public List<LoginResponse> getLoginResponses() {
        List<LoginResponse> list = new ArrayList<>();
        if(Objects.equals(user.getUserType(),"A")){
            user.setUserType("管理员");
            list.add(new LoginResponse("大件换新","1"));
            list.add(new LoginResponse("大件归还","2"));
            list.add(new LoginResponse("大件维修","3"));
            list.add(new LoginResponse("大件归还","4"));
            list.add(new LoginResponse("大件盘点","5"));
        } else if(Objects.equals(user.getUserType(),"B")){
            user.setUserType("维修人员");
            list.add(new LoginResponse("大件维修","3"));
            list.add(new LoginResponse("维修归还","4"));
        } else {
            user.setUserType("操作员");
            list.add(new LoginResponse("大件换新","1"));
            list.add(new LoginResponse("大件归还","2"));
        }
        return list;
    }


    @ApiOperation(value = "登录接口")
    @GetMapping("/login")
    public ResponseData login(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                              @ApiParam(value = "用户密码") @RequestParam String password){
        if(user != null){
            return ResponseData.error("已有人员在登陆不允许多人员同时登录");
        }
        WmsUser wmsUser = wmsUserService.getOne(new QueryWrapper<WmsUser>().eq("serial_number",serialNumber));
        if(wmsUser == null){
            return ResponseData.error("没有此用户");
        }
        if(!Objects.equals(wmsUser.getuPwd(),password)){
            return ResponseData.error("用户密码不对");
        }
        user = wmsUser;
        List<LoginResponse> list = getLoginResponses();
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("list",list);
        return ResponseData.success(map);
    }

    @ApiOperation(value = "获取登录信息")
    @GetMapping("/info")
    public ResponseData info(){
        if(user != null){
            return ResponseData.success(user);
        }
        return ResponseData.error("没有用登录");
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public ResponseData logout(){
        user = null;
        return ResponseData.success();
    }

}
