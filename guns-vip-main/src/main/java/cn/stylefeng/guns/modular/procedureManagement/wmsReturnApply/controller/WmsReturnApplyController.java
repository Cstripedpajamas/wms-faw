package cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity.WmsReturnApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params.WmsReturnApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.service.WmsReturnApplyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 归还申请信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 17:25:01
 */
@Controller
@RequestMapping("/wmsReturnApply")
public class WmsReturnApplyController extends BaseController {

    private String PREFIX = "/modular/procedureManagement/wmsReturnApply";

    @Autowired
    private WmsReturnApplyService wmsReturnApplyService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsReturnApply.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsReturnApply_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsReturnApply_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsReturnApplyParam wmsReturnApplyParam) {
        this.wmsReturnApplyService.add(wmsReturnApplyParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsReturnApplyParam wmsReturnApplyParam) {
        this.wmsReturnApplyService.update(wmsReturnApplyParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsReturnApplyParam wmsReturnApplyParam) {
        this.wmsReturnApplyService.delete(wmsReturnApplyParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsReturnApplyParam wmsReturnApplyParam) {
        WmsReturnApply detail = this.wmsReturnApplyService.getById(wmsReturnApplyParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsReturnApplyParam wmsReturnApplyParam) {
        return this.wmsReturnApplyService.findPageBySpec(wmsReturnApplyParam);
    }

}


