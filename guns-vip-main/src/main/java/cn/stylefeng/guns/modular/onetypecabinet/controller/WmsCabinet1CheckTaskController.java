package cn.stylefeng.guns.modular.onetypecabinet.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1CheckTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1CheckTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1CheckTaskService;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅰ类柜盘点任务信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-01 13:54:45
 */
@Controller
@RequestMapping("/wmsCabinet1CheckTask")
public class WmsCabinet1CheckTaskController extends BaseController {

    private String PREFIX = "/onetypecabinet/wmsCabinet1CheckTask";

    @Autowired
    private WmsCabinet1CheckTaskService wmsCabinet1CheckTaskService;

    @Autowired
    private Code inventoryTaskCode;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet1CheckTask.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet1CheckTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet1CheckTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet1CheckTaskParam wmsCabinet1CheckTaskParam) {
        wmsCabinet1CheckTaskParam.setTaskNumber(inventoryTaskCode.createCode("0001"));
        this.wmsCabinet1CheckTaskService.add(wmsCabinet1CheckTaskParam);
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
    public ResponseData editItem(WmsCabinet1CheckTaskParam wmsCabinet1CheckTaskParam) {
        this.wmsCabinet1CheckTaskService.update(wmsCabinet1CheckTaskParam);
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
    public ResponseData delete(WmsCabinet1CheckTaskParam wmsCabinet1CheckTaskParam) {
        this.wmsCabinet1CheckTaskService.delete(wmsCabinet1CheckTaskParam);
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
    public ResponseData detail(WmsCabinet1CheckTaskParam wmsCabinet1CheckTaskParam) {
        WmsCabinet1CheckTask detail = this.wmsCabinet1CheckTaskService.getById(wmsCabinet1CheckTaskParam.getId());
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
    public LayuiPageInfo list(WmsCabinet1CheckTaskParam wmsCabinet1CheckTaskParam) {
        return this.wmsCabinet1CheckTaskService.findPageBySpec(wmsCabinet1CheckTaskParam);
    }

}


