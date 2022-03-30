package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.entity.WmsCabinet2ReplenishmentTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.params.WmsCabinet2ReplenishmentTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.service.WmsCabinet2ReplenishmentTaskService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅱ类柜补货任务信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 16:55:57
 */
@Controller
@RequestMapping("/wmsCabinet2ReplenishmentTask")
public class WmsCabinet2ReplenishmentTaskController extends BaseController {

    private String PREFIX = "/modular/sparePartsManagement/wmsCabinet2ReplenishmentTask";

    @Autowired
    private WmsCabinet2ReplenishmentTaskService wmsCabinet2ReplenishmentTaskService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet2ReplenishmentTask.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet2ReplenishmentTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet2ReplenishmentTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet2ReplenishmentTaskParam wmsCabinet2ReplenishmentTaskParam) {
        this.wmsCabinet2ReplenishmentTaskService.add(wmsCabinet2ReplenishmentTaskParam);
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
    public ResponseData editItem(WmsCabinet2ReplenishmentTaskParam wmsCabinet2ReplenishmentTaskParam) {
        this.wmsCabinet2ReplenishmentTaskService.update(wmsCabinet2ReplenishmentTaskParam);
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
    public ResponseData delete(WmsCabinet2ReplenishmentTaskParam wmsCabinet2ReplenishmentTaskParam) {
        this.wmsCabinet2ReplenishmentTaskService.delete(wmsCabinet2ReplenishmentTaskParam);
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
    public ResponseData detail(WmsCabinet2ReplenishmentTaskParam wmsCabinet2ReplenishmentTaskParam) {
        WmsCabinet2ReplenishmentTask detail = this.wmsCabinet2ReplenishmentTaskService.getById(wmsCabinet2ReplenishmentTaskParam.getId());
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
    public LayuiPageInfo list(WmsCabinet2ReplenishmentTaskParam wmsCabinet2ReplenishmentTaskParam) {
        return this.wmsCabinet2ReplenishmentTaskService.findPageBySpec(wmsCabinet2ReplenishmentTaskParam);
    }

}


