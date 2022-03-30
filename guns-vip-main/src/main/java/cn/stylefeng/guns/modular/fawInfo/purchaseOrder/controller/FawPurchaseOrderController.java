package cn.stylefeng.guns.modular.fawInfo.purchaseOrder.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.entity.FawPurchaseOrder;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.service.FawPurchaseOrderService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * faw采购订单信息表控制器
 *
 * @author fubenhao
 * @Date 2022-03-28 14:26:23
 */
@Controller
@RequestMapping("/fawPurchaseOrder")
public class FawPurchaseOrderController extends BaseController {

    @Autowired
    private FawPurchaseOrderService fawPurchaseOrderService;

    /**
     * 新增接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(FawPurchaseOrderParam fawPurchaseOrderParam) {
        this.fawPurchaseOrderService.add(fawPurchaseOrderParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(FawPurchaseOrderParam fawPurchaseOrderParam) {
        this.fawPurchaseOrderService.update(fawPurchaseOrderParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(FawPurchaseOrderParam fawPurchaseOrderParam) {
        this.fawPurchaseOrderService.delete(fawPurchaseOrderParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FawPurchaseOrderParam fawPurchaseOrderParam) {
        FawPurchaseOrder detail = this.fawPurchaseOrderService.getById(fawPurchaseOrderParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(FawPurchaseOrderParam fawPurchaseOrderParam) {
        return this.fawPurchaseOrderService.findPageBySpec(fawPurchaseOrderParam);
    }

}


