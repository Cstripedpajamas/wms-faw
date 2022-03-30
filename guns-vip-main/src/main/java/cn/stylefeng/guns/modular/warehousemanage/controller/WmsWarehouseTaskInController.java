package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskIn;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskInParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTaskInService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 立库-仓库任务管理信息表-入仓控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:08
 */
@Controller
@RequestMapping("/wmsWarehouseTaskIn")
public class WmsWarehouseTaskInController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehouseTaskIn";

    @Autowired
    private WmsWarehouseTaskInService wmsWarehouseTaskInService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehouseTaskIn.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehouseTaskIn_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehouseTaskIn_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehouseTaskInParam wmsWarehouseTaskInParam) {
        this.wmsWarehouseTaskInService.add(wmsWarehouseTaskInParam);
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
    public ResponseData editItem(WmsWarehouseTaskInParam wmsWarehouseTaskInParam) {
        this.wmsWarehouseTaskInService.update(wmsWarehouseTaskInParam);
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
    public ResponseData delete(WmsWarehouseTaskInParam wmsWarehouseTaskInParam) {
        this.wmsWarehouseTaskInService.delete(wmsWarehouseTaskInParam);
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
    public ResponseData detail(WmsWarehouseTaskInParam wmsWarehouseTaskInParam) {
        WmsWarehouseTaskIn detail = this.wmsWarehouseTaskInService.getById(wmsWarehouseTaskInParam.getId());
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
    public LayuiPageInfo list(WmsWarehouseTaskInParam wmsWarehouseTaskInParam) {
        return this.wmsWarehouseTaskInService.findPageBySpec(wmsWarehouseTaskInParam);
    }

}


