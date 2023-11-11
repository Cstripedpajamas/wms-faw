package cn.stylefeng.guns.modular.fawPurchase0rder.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderDelivery;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.service.FawmallPurchaseorderDeliveryService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * faw商城取消采购订单控制器
 *
 * @author 邢玉祥
 * @Date 2023-03-21 13:46:59
 */
@Controller
@RequestMapping("/fawmallPurchaseorderDelivery")
public class FawmallPurchaseorderDeliveryController extends BaseController {

    private String PREFIX = "/fawPurchase0rder/fawmallPurchaseorderDelivery";

    @Autowired
    private FawmallPurchaseorderDeliveryService fawmallPurchaseorderDeliveryService;

    /**
     * 跳转到主页面
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/fawmallPurchaseorderDelivery.html";
    }

    /**
     * 新增页面
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/fawmallPurchaseorderDelivery_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/fawmallPurchaseorderDelivery_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(FawmallPurchaseorderDeliveryParam fawmallPurchaseorderDeliveryParam) {
        this.fawmallPurchaseorderDeliveryService.add(fawmallPurchaseorderDeliveryParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(FawmallPurchaseorderDeliveryParam fawmallPurchaseorderDeliveryParam) {
        this.fawmallPurchaseorderDeliveryService.update(fawmallPurchaseorderDeliveryParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(FawmallPurchaseorderDeliveryParam fawmallPurchaseorderDeliveryParam) {
        this.fawmallPurchaseorderDeliveryService.delete(fawmallPurchaseorderDeliveryParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FawmallPurchaseorderDeliveryParam fawmallPurchaseorderDeliveryParam) {
        FawmallPurchaseorderDelivery detail = this.fawmallPurchaseorderDeliveryService.getById(fawmallPurchaseorderDeliveryParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(FawmallPurchaseorderDeliveryParam fawmallPurchaseorderDeliveryParam) {
        return this.fawmallPurchaseorderDeliveryService.findPageBySpec(fawmallPurchaseorderDeliveryParam);
    }
}


