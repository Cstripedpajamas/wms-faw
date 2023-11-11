package cn.stylefeng.guns.modular.base.purchaseorderDelivery.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.entity.WmsWarehousePurchaseorderDelivery;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.params.WmsWarehousePurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.WmsWarehousePurchaseorderDeliveryService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 发货单控制器
 *
 * @author 邢玉祥
 * @Date 2023-03-20 14:51:14
 */
@Controller
@RequestMapping("/wmsWarehousePurchaseorderDelivery")
public class WmsWarehousePurchaseorderDeliveryController extends BaseController {

    private String PREFIX = "/modular/base/wmsWarehousePurchaseorderDelivery";

    @Autowired
    private WmsWarehousePurchaseorderDeliveryService wmsWarehousePurchaseorderDeliveryService;

    /**
     * 跳转到主页面
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehousePurchaseorderDelivery.html";
    }

    /**
     * 新增页面
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehousePurchaseorderDelivery_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehousePurchaseorderDelivery_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehousePurchaseorderDeliveryParam wmsWarehousePurchaseorderDeliveryParam) {

        wmsWarehousePurchaseorderDeliveryParam.setCreateTime(new Date());
        List<WmsWarehousePurchaseorderDeliveryParam> wmsWarehousePurchaseorderParamList = new ArrayList<>();
        wmsWarehousePurchaseorderParamList.add(wmsWarehousePurchaseorderDeliveryParam);
//        this.wmsWarehousePurchaseorderDeliveryService.add(wmsWarehousePurchaseorderDeliveryParam);
        this.wmsWarehousePurchaseorderDeliveryService.insertList(wmsWarehousePurchaseorderParamList);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsWarehousePurchaseorderDeliveryParam wmsWarehousePurchaseorderDeliveryParam) {
        this.wmsWarehousePurchaseorderDeliveryService.update(wmsWarehousePurchaseorderDeliveryParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsWarehousePurchaseorderDeliveryParam wmsWarehousePurchaseorderDeliveryParam) {
        this.wmsWarehousePurchaseorderDeliveryService.delete(wmsWarehousePurchaseorderDeliveryParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsWarehousePurchaseorderDeliveryParam wmsWarehousePurchaseorderDeliveryParam) {
        WmsWarehousePurchaseorderDelivery detail = this.wmsWarehousePurchaseorderDeliveryService.getById(wmsWarehousePurchaseorderDeliveryParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsWarehousePurchaseorderDeliveryParam wmsWarehousePurchaseorderDeliveryParam) {
        return this.wmsWarehousePurchaseorderDeliveryService.findPageBySpec(wmsWarehousePurchaseorderDeliveryParam);
    }

}


