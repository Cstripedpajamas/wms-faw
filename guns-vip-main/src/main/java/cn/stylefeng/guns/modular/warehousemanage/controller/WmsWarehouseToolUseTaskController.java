package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseToolUseTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseToolUseTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseToolUseTaskService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 立库-工具领用任务信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:08
 */
@Controller
@RequestMapping("/wmsWarehouseToolUseTask")
public class WmsWarehouseToolUseTaskController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehouseToolUseTask";

    @Autowired
    private Code toolClaimCode;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehouseToolUseTask.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehouseToolUseTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehouseToolUseTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehouseToolUseTaskParam wmsWarehouseToolUseTaskParam) {
        wmsWarehouseToolUseTaskParam.setTaskNumber(toolClaimCode.createCode("0001"));
        this.wmsWarehouseToolUseTaskService.add(wmsWarehouseToolUseTaskParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsWarehouseToolUseTaskParam wmsWarehouseToolUseTaskParam) {
        this.wmsWarehouseToolUseTaskService.update(wmsWarehouseToolUseTaskParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsWarehouseToolUseTaskParam wmsWarehouseToolUseTaskParam) {
        this.wmsWarehouseToolUseTaskService.delete(wmsWarehouseToolUseTaskParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsWarehouseToolUseTaskParam wmsWarehouseToolUseTaskParam) {
        WmsWarehouseToolUseTask detail = this.wmsWarehouseToolUseTaskService.getById(wmsWarehouseToolUseTaskParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsWarehouseToolUseTaskParam wmsWarehouseToolUseTaskParam) {
        return this.wmsWarehouseToolUseTaskService.findPageBySpec(wmsWarehouseToolUseTaskParam);
    }

}


