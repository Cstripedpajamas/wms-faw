package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 领用申请信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 17:16:19
 */
@Controller
@RequestMapping("/wmsUseApply")
public class WmsUseApplyController extends BaseController {

    private String PREFIX = "/modular/procedureManagement/wmsUseApply";

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsUseApply.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsUseApply_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsUseApply_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsUseApplyParam wmsUseApplyParam) {
        this.wmsUseApplyService.add(wmsUseApplyParam);
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
    public ResponseData editItem(WmsUseApplyParam wmsUseApplyParam) {
        this.wmsUseApplyService.update(wmsUseApplyParam);
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
    public ResponseData delete(WmsUseApplyParam wmsUseApplyParam) {
        this.wmsUseApplyService.delete(wmsUseApplyParam);
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
    public ResponseData detail(WmsUseApplyParam wmsUseApplyParam) {
        WmsUseApply detail = this.wmsUseApplyService.getById(wmsUseApplyParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询指定员工列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsUseApplyParam wmsUseApplyParam) {
        return this.wmsUseApplyService.findPageBySpec(wmsUseApplyParam);
    }

    /**
     *  查询所有的列表
     * */
    @ResponseBody
    @RequestMapping("/list2")
    public LayuiPageInfo list2(WmsUseApplyParam wmsUseApplyParam) {
        return this.wmsUseApplyService.findPageBySpec2(wmsUseApplyParam);
    }

}


