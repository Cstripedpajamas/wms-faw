package cn.stylefeng.guns.modular.onetypecabinet.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RepairTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RepairTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1RepairTaskService;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅰ类柜维修任务信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-01 13:54:45
 */
@Controller
@RequestMapping("/wmsCabinet1RepairTask")
public class WmsCabinet1RepairTaskController extends BaseController {

    private String PREFIX = "/onetypecabinet/wmsCabinet1RepairTask";

    @Autowired
    private Code repairCode;

    @Autowired
    private WmsCabinet1RepairTaskService wmsCabinet1RepairTaskService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet1RepairTask.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet1RepairTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet1RepairTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet1RepairTaskParam wmsCabinet1RepairTaskParam) {
        wmsCabinet1RepairTaskParam.setTaskNumber(repairCode.createCode("0001"));
        this.wmsCabinet1RepairTaskService.add(wmsCabinet1RepairTaskParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsCabinet1RepairTaskParam wmsCabinet1RepairTaskParam) {
        this.wmsCabinet1RepairTaskService.update(wmsCabinet1RepairTaskParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsCabinet1RepairTaskParam wmsCabinet1RepairTaskParam) {
        this.wmsCabinet1RepairTaskService.delete(wmsCabinet1RepairTaskParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsCabinet1RepairTaskParam wmsCabinet1RepairTaskParam) {
        WmsCabinet1RepairTask detail = this.wmsCabinet1RepairTaskService.getById(wmsCabinet1RepairTaskParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsCabinet1RepairTaskParam wmsCabinet1RepairTaskParam) {
        return this.wmsCabinet1RepairTaskService.findPageBySpec(wmsCabinet1RepairTaskParam);
    }

}


