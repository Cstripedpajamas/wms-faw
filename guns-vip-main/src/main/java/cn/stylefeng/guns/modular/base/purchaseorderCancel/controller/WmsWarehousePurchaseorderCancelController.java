package cn.stylefeng.guns.modular.base.purchaseorderCancel.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.entity.WmsWarehousePurchaseorderCancel;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.params.WmsWarehousePurchaseorderCancelParam;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.service.WmsWarehousePurchaseorderCancelService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 订单取消控制器
 *
 * @author 邢玉祥
 * @Date 2023-03-20 14:50:41
 */
@Controller
@RequestMapping("/wmsWarehousePurchaseorderCancel")
public class WmsWarehousePurchaseorderCancelController extends BaseController {

    private String PREFIX = "/modular/base/wmsWarehousePurchaseorderCancel";

    @Autowired
    private WmsWarehousePurchaseorderCancelService wmsWarehousePurchaseorderCancelService;

    /**
     * 跳转到主页面
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehousePurchaseorderCancel.html";
    }

    /**
     * 新增页面
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehousePurchaseorderCancel_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehousePurchaseorderCancel_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehousePurchaseorderCancelParam wmsWarehousePurchaseorderCancelParam) {
        this.wmsWarehousePurchaseorderCancelService.add(wmsWarehousePurchaseorderCancelParam);
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
    public ResponseData editItem(WmsWarehousePurchaseorderCancelParam wmsWarehousePurchaseorderCancelParam) {
        this.wmsWarehousePurchaseorderCancelService.update(wmsWarehousePurchaseorderCancelParam);
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
    public ResponseData delete(WmsWarehousePurchaseorderCancelParam wmsWarehousePurchaseorderCancelParam) {
        this.wmsWarehousePurchaseorderCancelService.delete(wmsWarehousePurchaseorderCancelParam);
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
    public ResponseData detail(WmsWarehousePurchaseorderCancelParam wmsWarehousePurchaseorderCancelParam) {
        WmsWarehousePurchaseorderCancel detail = this.wmsWarehousePurchaseorderCancelService.getById(wmsWarehousePurchaseorderCancelParam.getId());
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
    public LayuiPageInfo list(WmsWarehousePurchaseorderCancelParam wmsWarehousePurchaseorderCancelParam) {
        return this.wmsWarehousePurchaseorderCancelService.findPageBySpec(wmsWarehousePurchaseorderCancelParam);
    }

}


