package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskOut;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskOutParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTaskOutService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 立库-仓库任务管理信息表-出仓控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:08
 */
@Controller
@RequestMapping("/wmsWarehouseTaskOut")
public class WmsWarehouseTaskOutController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehouseTaskOut";

    @Autowired
    private WmsWarehouseTaskOutService wmsWarehouseTaskOutService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehouseTaskOut.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehouseTaskOut_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehouseTaskOut_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam) {
        this.wmsWarehouseTaskOutService.add(wmsWarehouseTaskOutParam);
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
    public ResponseData editItem(WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam) {
        this.wmsWarehouseTaskOutService.update(wmsWarehouseTaskOutParam);
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
    public ResponseData delete(WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam) {
        this.wmsWarehouseTaskOutService.delete(wmsWarehouseTaskOutParam);
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
    public ResponseData detail(WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam) {
        WmsWarehouseTaskOut detail = this.wmsWarehouseTaskOutService.getById(wmsWarehouseTaskOutParam.getId());
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
    public LayuiPageInfo list(WmsWarehouseTaskOutParam wmsWarehouseTaskOutParam) {
        return this.wmsWarehouseTaskOutService.findPageBySpec(wmsWarehouseTaskOutParam);
    }

}


