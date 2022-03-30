package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.response.LoginResponse;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by li wen ya on 2021/11/17
 */
@RestController
@CrossOrigin
@RequestMapping("/pda")
@Api(description = "PDA登录")
public class LoginPDAController {

    @Autowired
    private WmsUserService wmsUserService;

    @ApiOperation(value = "PDA登录接口")
    @GetMapping("/login")
    public ResponseData login(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                              @ApiParam(value = "用户密码") @RequestParam String password){
        WmsUser wmsUser = wmsUserService.getOne(new QueryWrapper<WmsUser>().eq("serial_number",serialNumber));
        if(wmsUser == null){
            return ResponseData.error("没有此用户");
        }
        if(!Objects.equals(wmsUser.getuPwd(),password)){
            return ResponseData.error("用户密码不对");
        }
        return ResponseData.success(wmsUser);
    }


}
