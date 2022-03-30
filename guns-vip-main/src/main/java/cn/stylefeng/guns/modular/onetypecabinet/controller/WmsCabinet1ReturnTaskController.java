package cn.stylefeng.guns.modular.onetypecabinet.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1ReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1ReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1ReturnTaskService;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅰ类柜归还任务信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-01 13:54:45
 */
@Controller
@RequestMapping("/wmsCabinet1ReturnTask")
public class WmsCabinet1ReturnTaskController extends BaseController {

    private String PREFIX = "/onetypecabinet/wmsCabinet1ReturnTask";

    @Autowired
    private Code returnCode;

    @Autowired
    private WmsCabinet1ReturnTaskService wmsCabinet1ReturnTaskService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet1ReturnTask.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet1ReturnTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet1ReturnTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet1ReturnTaskParam wmsCabinet1ReturnTaskParam) {
        wmsCabinet1ReturnTaskParam.setTaskNumber(returnCode.createCode("0001"));
        this.wmsCabinet1ReturnTaskService.add(wmsCabinet1ReturnTaskParam);
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
    public ResponseData editItem(WmsCabinet1ReturnTaskParam wmsCabinet1ReturnTaskParam) {
        this.wmsCabinet1ReturnTaskService.update(wmsCabinet1ReturnTaskParam);
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
    public ResponseData delete(WmsCabinet1ReturnTaskParam wmsCabinet1ReturnTaskParam) {
        this.wmsCabinet1ReturnTaskService.delete(wmsCabinet1ReturnTaskParam);
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
    public ResponseData detail(WmsCabinet1ReturnTaskParam wmsCabinet1ReturnTaskParam) {
        WmsCabinet1ReturnTask detail = this.wmsCabinet1ReturnTaskService.getById(wmsCabinet1ReturnTaskParam.getId());
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
    public LayuiPageInfo list(WmsCabinet1ReturnTaskParam wmsCabinet1ReturnTaskParam) {
        return this.wmsCabinet1ReturnTaskService.findPageBySpec(wmsCabinet1ReturnTaskParam);
    }

}


