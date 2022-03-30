package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseReplenishmentTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseReplenishmentTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseReplenishmentTaskService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 立库-备品备件补货任务信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:08
 */
@Controller
@RequestMapping("/wmsWarehouseReplenishmentTask")
public class WmsWarehouseReplenishmentTaskController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehouseReplenishmentTask";

    @Autowired
    private Code sparePartCode;

    @Autowired
    private WmsWarehouseReplenishmentTaskService wmsWarehouseReplenishmentTaskService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehouseReplenishmentTask.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehouseReplenishmentTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehouseReplenishmentTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehouseReplenishmentTaskParam wmsWarehouseReplenishmentTaskParam) {
        wmsWarehouseReplenishmentTaskParam.setTaskNumber(sparePartCode.createCode("0001"));
        this.wmsWarehouseReplenishmentTaskService.add(wmsWarehouseReplenishmentTaskParam);
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
    public ResponseData editItem(WmsWarehouseReplenishmentTaskParam wmsWarehouseReplenishmentTaskParam) {
        this.wmsWarehouseReplenishmentTaskService.update(wmsWarehouseReplenishmentTaskParam);
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
    public ResponseData delete(WmsWarehouseReplenishmentTaskParam wmsWarehouseReplenishmentTaskParam) {
        this.wmsWarehouseReplenishmentTaskService.delete(wmsWarehouseReplenishmentTaskParam);
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
    public ResponseData detail(WmsWarehouseReplenishmentTaskParam wmsWarehouseReplenishmentTaskParam) {
        WmsWarehouseReplenishmentTask detail = this.wmsWarehouseReplenishmentTaskService.getById(wmsWarehouseReplenishmentTaskParam.getId());
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
    public LayuiPageInfo list(WmsWarehouseReplenishmentTaskParam wmsWarehouseReplenishmentTaskParam) {
        return this.wmsWarehouseReplenishmentTaskService.findPageBySpec(wmsWarehouseReplenishmentTaskParam);
    }

}


