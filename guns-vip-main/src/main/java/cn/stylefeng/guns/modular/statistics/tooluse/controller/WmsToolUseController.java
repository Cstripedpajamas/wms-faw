package cn.stylefeng.guns.modular.statistics.tooluse.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.model.params.WmsToolUseParam;
import cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 工具领用信息表控制器
 *
 * @author lhx
 * @Date 2021-11-01 17:09:03
 */
@Controller
@RequestMapping("/wmsToolUse")
public class WmsToolUseController extends BaseController {

    private String PREFIX = "/modular/statistics/wmsToolUse";

    @Autowired
    private WmsToolUseService wmsToolUseService;

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
        return PREFIX + "/wmsToolUse.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add(Model model) {
        List<WmsUserResult> userResultList = wmsUserService.findListBySpec(new WmsUserParam());
        model.addAttribute("userResultList", userResultList);
        return PREFIX + "/wmsToolUse_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit(Model model) {
        List<WmsUserResult> userResultList = wmsUserService.findListBySpec(new WmsUserParam());
        model.addAttribute("userResultList", userResultList);
        return PREFIX + "/wmsToolUse_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsToolUseParam wmsToolUseParam) {
        this.wmsToolUseService.add(wmsToolUseParam);
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
    public ResponseData editItem(WmsToolUseParam wmsToolUseParam) {
        this.wmsToolUseService.update(wmsToolUseParam);
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
    public ResponseData delete(WmsToolUseParam wmsToolUseParam) {
        this.wmsToolUseService.delete(wmsToolUseParam);
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
    public ResponseData detail(WmsToolUseParam wmsToolUseParam) {
        WmsToolUse detail = this.wmsToolUseService.getById(wmsToolUseParam.getId());
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
    public LayuiPageInfo list(WmsToolUseParam wmsToolUseParam) {
        return this.wmsToolUseService.findPageBySpec(wmsToolUseParam);
    }

}


