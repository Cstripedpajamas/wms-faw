package cn.stylefeng.guns.modular.base.user.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import cn.stylefeng.guns.modular.base.user.entity.LoginEntity;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.utils.JudgeUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * 人员信息表控制器
 *
 * @author lhx
 * @Date 2021-11-01 13:34:38
 */
@Controller
@RequestMapping("/wmsUser")
@Api(description = "登入接口")
public class WmsUserController extends BaseController {

    private String PREFIX = "/modular/base/wmsUser";

    @Autowired
    private WmsUserService wmsUserService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsUser.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsUser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsUser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsUserParam wmsUserParam) {
        // 查看用户编号是否存在
        QueryWrapper<WmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("serial_number", wmsUserParam.getSerialNumber());
        WmsUser one = wmsUserService.getOne(wrapper);
        // 如果不为空，则说明编号存在
        if (!Objects.isNull(one)) {
            return ResponseData.error("用户编号已存在！");
        }
        this.wmsUserService.add(wmsUserParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsUserParam wmsUserParam) {
        // 查看用户编号是否存在
        QueryWrapper<WmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("serial_number", wmsUserParam.getSerialNumber());
        WmsUser one = wmsUserService.getOne(wrapper);
        // 如果不为空，则说明编号存在
        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsUserParam.getId())) {
            return ResponseData.error("用户编号已存在！");
        }
        this.wmsUserService.update(wmsUserParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsUserParam wmsUserParam) {
        // 判断是否可以删除用户
        if (!JudgeUtil.canDeleteUser(wmsUserParam.getSerialNumber())) {
            return ResponseData.error("此用户已被关联，无法删除");
        }
        this.wmsUserService.delete(wmsUserParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsUserParam wmsUserParam) {
        WmsUser detail = this.wmsUserService.getById(wmsUserParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsUserParam wmsUserParam) {
        return this.wmsUserService.findPageBySpec(wmsUserParam);
    }


    /**
     * 添加人员登入接口
     */
    @ResponseBody
    @PostMapping(value = "/login")
    @ApiOperation(value = "人员登入接口")
    public ResponseData login(@ApiParam("人员登入信息") @RequestBody String loginEntity) {
        /*
         * 登入:
         * I类柜: 标识字段 I 用户名 密码: 判断状态 用户名 密码 响应数据: 人员身份信息
         * II类柜: 标识字段 II 用户名 密码 判断状态 用户名 密码; 响应数据: 人员身份信息(不为维修人员)
         * 仓库人员: 标识字段 III 用户名 密码 判断状态 用户名 密码; 响应数据: 人员身份信息(不为维修人员)
         * */
        LoginEntity user = JSONObject.parseObject(loginEntity, LoginEntity.class);
        System.out.println(user.getState());
        if (Objects.equals("I", user.getState())) {
            WmsUserResult wm = wmsUserService.findUserInfo(user.getSerialNumber(), user.getuPwd());
            if (wm != null) {
                return ResponseData.success(wm);
            }
            else {
                return ResponseData.error("身份认证失败~");
            }

        }
        else {
            System.out.println("进来了");
        WmsUserResult wm = wmsUserService.findUserInfo2(user.getSerialNumber(), user.getuPwd());
            if (wm != null) {
                return ResponseData.success(wm);
            }
            else {
                return ResponseData.error("身份认证失败~");
            }
        }
    }
}


