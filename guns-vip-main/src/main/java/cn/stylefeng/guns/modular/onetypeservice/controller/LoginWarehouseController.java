package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.response.LoginResponse;
import cn.stylefeng.guns.modular.onetypeservice.response.LoginWarehouseResponse;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
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
@RequestMapping("/warehouse")
@Api(description = "仓库登录接口")
public class LoginWarehouseController {

    private WmsUser user = null;

    @Autowired
    private WmsUserService wmsUserService;

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
        List<LoginWarehouseResponse> list = new ArrayList<>();
        if(Objects.equals(wmsUser.getUserType(),"A")){
            user = wmsUser;
            user.setUserType("管理员");
//            list.add(new LoginWarehouseResponse("工具领用","1","main/toolAccessPage/step1"));
            list.add(new LoginWarehouseResponse("采购入库","2","main/purchaseWarehousingPage/step1"));
            list.add(new LoginWarehouseResponse("备品备件补货出库","4","main/replenishmentPage/step1"));
            list.add(new LoginWarehouseResponse("工具条码打印","5","main/printerPage/step1"));
            list.add(new LoginWarehouseResponse("入库","3","main/instoragePage"));
            list.add(new LoginWarehouseResponse("出库","6","main/outstoragePage"));
        } else {
           return ResponseData.error("您不是管理员,无法登入");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("list",list);
        return ResponseData.success(map);
    }

    @ApiOperation(value = "获取登录信息")
    @GetMapping("/info")
    public ResponseData info(){
        return ResponseData.success(user);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public ResponseData logout(){
        user = null;
        return ResponseData.success();
    }

}
