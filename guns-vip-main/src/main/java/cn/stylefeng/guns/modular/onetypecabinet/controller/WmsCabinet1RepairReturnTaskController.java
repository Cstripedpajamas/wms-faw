package cn.stylefeng.guns.modular.onetypecabinet.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RepairReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RepairReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1RepairReturnTaskService;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅰ类柜维修归还任务信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-01 13:54:45
 */
@Controller
@RequestMapping("/wmsCabinet1RepairReturnTask")
public class WmsCabinet1RepairReturnTaskController extends BaseController {

    private String PREFIX = "/onetypecabinet/wmsCabinet1RepairReturnTask";

    @Autowired
    private Code repairReturnCode;

    @Autowired
    private WmsCabinet1RepairReturnTaskService wmsCabinet1RepairReturnTaskService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet1RepairReturnTask.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet1RepairReturnTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet1RepairReturnTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet1RepairReturnTaskParam wmsCabinet1RepairReturnTaskParam) {
        wmsCabinet1RepairReturnTaskParam.setTaskNumber(repairReturnCode.createCode("0001"));
        this.wmsCabinet1RepairReturnTaskService.add(wmsCabinet1RepairReturnTaskParam);
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
    public ResponseData editItem(WmsCabinet1RepairReturnTaskParam wmsCabinet1RepairReturnTaskParam) {
        this.wmsCabinet1RepairReturnTaskService.update(wmsCabinet1RepairReturnTaskParam);
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
    public ResponseData delete(WmsCabinet1RepairReturnTaskParam wmsCabinet1RepairReturnTaskParam) {
        this.wmsCabinet1RepairReturnTaskService.delete(wmsCabinet1RepairReturnTaskParam);
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
    public ResponseData detail(WmsCabinet1RepairReturnTaskParam wmsCabinet1RepairReturnTaskParam) {
        WmsCabinet1RepairReturnTask detail = this.wmsCabinet1RepairReturnTaskService.getById(wmsCabinet1RepairReturnTaskParam.getId());
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
    public LayuiPageInfo list(WmsCabinet1RepairReturnTaskParam wmsCabinet1RepairReturnTaskParam) {
        return this.wmsCabinet1RepairReturnTaskService.findPageBySpec(wmsCabinet1RepairReturnTaskParam);
    }

}


