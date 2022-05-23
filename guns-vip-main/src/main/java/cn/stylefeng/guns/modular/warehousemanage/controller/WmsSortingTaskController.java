package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsSortingTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsSortingTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsSortingTaskService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 机械手分拣任务表控制器
 *
 * @author liwenya
 * @Date 2021-11-23 11:51:36
 */
@Controller
@RequestMapping("/wmsSortingTask")
@CrossOrigin
public class WmsSortingTaskController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsSortingTask";

    @Autowired
    private WmsSortingTaskService wmsSortingTaskService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsSortingTask.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsSortingTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsSortingTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsSortingTaskParam wmsSortingTaskParam) {
        this.wmsSortingTaskService.add(wmsSortingTaskParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsSortingTaskParam wmsSortingTaskParam) {
        this.wmsSortingTaskService.update(wmsSortingTaskParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsSortingTaskParam wmsSortingTaskParam) {
        this.wmsSortingTaskService.delete(wmsSortingTaskParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsSortingTaskParam wmsSortingTaskParam) {
        WmsSortingTask detail = this.wmsSortingTaskService.getById(wmsSortingTaskParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsSortingTaskParam wmsSortingTaskParam) {
        return this.wmsSortingTaskService.findPageBySpec(wmsSortingTaskParam);
    }

    @ResponseBody
    @RequestMapping("/recentTask")
    public ResponseData recentTask() {
        List<WmsSortingTaskResult> list = this.wmsSortingTaskService.findRecentTask();
        return ResponseData.success(list);
    }

}


