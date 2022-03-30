package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.params.WmsCabinet2CheckTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.service.WmsCabinet2CheckTaskService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅱ类柜盘点任务信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 17:01:14
 */
@Controller
@RequestMapping("/wmsCabinet2CheckTask")
public class WmsCabinet2CheckTaskController extends BaseController {

    private String PREFIX = "/modular/sparePartsManagement/wmsCabinet2CheckTask";

    @Autowired
    private WmsCabinet2CheckTaskService wmsCabinet2CheckTaskService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet2CheckTask.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet2CheckTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet2CheckTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet2CheckTaskParam wmsCabinet2CheckTaskParam) {
        this.wmsCabinet2CheckTaskService.add(wmsCabinet2CheckTaskParam);
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
    public ResponseData editItem(WmsCabinet2CheckTaskParam wmsCabinet2CheckTaskParam) {
        this.wmsCabinet2CheckTaskService.update(wmsCabinet2CheckTaskParam);
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
    public ResponseData delete(WmsCabinet2CheckTaskParam wmsCabinet2CheckTaskParam) {
        this.wmsCabinet2CheckTaskService.delete(wmsCabinet2CheckTaskParam);
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
    public ResponseData detail(WmsCabinet2CheckTaskParam wmsCabinet2CheckTaskParam) {
        WmsCabinet2CheckTask detail = this.wmsCabinet2CheckTaskService.getById(wmsCabinet2CheckTaskParam.getId());
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
    public LayuiPageInfo list(WmsCabinet2CheckTaskParam wmsCabinet2CheckTaskParam) {
        return this.wmsCabinet2CheckTaskService.findPageBySpec(wmsCabinet2CheckTaskParam);
    }

}


