package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseOrderReturn;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseOrderReturnParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehousePurchaseOrderReturnService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 立库-采购订单退还信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:07
 */
@Controller
@RequestMapping("/wmsWarehousePurchaseOrderReturn")
public class WmsWarehousePurchaseOrderReturnController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehousePurchaseOrderReturn";

    @Autowired
    private WmsWarehousePurchaseOrderReturnService wmsWarehousePurchaseOrderReturnService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehousePurchaseOrderReturn.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehousePurchaseOrderReturn_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehousePurchaseOrderReturn_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehousePurchaseOrderReturnParam wmsWarehousePurchaseOrderReturnParam) {
        wmsWarehousePurchaseOrderReturnParam.setOperator(LoginContextHolder.getContext().getUser().getAccount());
        wmsWarehousePurchaseOrderReturnParam.setOperationTime(new Date());
        wmsWarehousePurchaseOrderReturnParam.setTaskState("0");
        this.wmsWarehousePurchaseOrderReturnService.add(wmsWarehousePurchaseOrderReturnParam);
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
    public ResponseData editItem(WmsWarehousePurchaseOrderReturnParam wmsWarehousePurchaseOrderReturnParam) {
        String account = LoginContextHolder.getContext().getUser().getAccount();
        wmsWarehousePurchaseOrderReturnParam.setOperator(account);
        wmsWarehousePurchaseOrderReturnParam.setOperationTime(new Date());
        this.wmsWarehousePurchaseOrderReturnService.update(wmsWarehousePurchaseOrderReturnParam);
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
    public ResponseData delete(WmsWarehousePurchaseOrderReturnParam wmsWarehousePurchaseOrderReturnParam) {
        this.wmsWarehousePurchaseOrderReturnService.delete(wmsWarehousePurchaseOrderReturnParam);
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
    public ResponseData detail(WmsWarehousePurchaseOrderReturnParam wmsWarehousePurchaseOrderReturnParam) {
        WmsWarehousePurchaseOrderReturn detail = this.wmsWarehousePurchaseOrderReturnService.getById(wmsWarehousePurchaseOrderReturnParam.getId());
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
    public LayuiPageInfo list(WmsWarehousePurchaseOrderReturnParam wmsWarehousePurchaseOrderReturnParam) {
        return this.wmsWarehousePurchaseOrderReturnService.findPageBySpec(wmsWarehousePurchaseOrderReturnParam);
    }

}


