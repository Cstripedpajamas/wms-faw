package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity.WmsCabinet2UseTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.params.WmsCabinet2UseTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅱ类柜领用任务信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 16:48:59
 */
@Controller
@RequestMapping("/wmsCabinet2UseTask")
public class WmsCabinet2UseTaskController extends BaseController {

    private String PREFIX = "/modular/sparePartsManagement/wmsCabinet2UseTask";

    @Autowired
    private WmsCabinet2UseTaskService wmsCabinet2UseTaskService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet2UseTask.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet2UseTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet2UseTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet2UseTaskParam wmsCabinet2UseTaskParam) {
        this.wmsCabinet2UseTaskService.add(wmsCabinet2UseTaskParam);
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
    public ResponseData editItem(WmsCabinet2UseTaskParam wmsCabinet2UseTaskParam) {
        this.wmsCabinet2UseTaskService.update(wmsCabinet2UseTaskParam);
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
    public ResponseData delete(WmsCabinet2UseTaskParam wmsCabinet2UseTaskParam) {
        this.wmsCabinet2UseTaskService.delete(wmsCabinet2UseTaskParam);
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
    public ResponseData detail(WmsCabinet2UseTaskParam wmsCabinet2UseTaskParam) {
        WmsCabinet2UseTask detail = this.wmsCabinet2UseTaskService.getById(wmsCabinet2UseTaskParam.getId());
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
    public LayuiPageInfo list(WmsCabinet2UseTaskParam wmsCabinet2UseTaskParam) {
        return this.wmsCabinet2UseTaskService.findPageBySpec(wmsCabinet2UseTaskParam);
    }

}


