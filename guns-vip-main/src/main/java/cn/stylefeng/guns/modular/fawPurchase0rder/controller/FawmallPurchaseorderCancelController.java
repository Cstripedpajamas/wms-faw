package cn.stylefeng.guns.modular.fawPurchase0rder.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderCancel;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderCancelParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.service.FawmallPurchaseorderCancelService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * faw商城发货单控制器
 *
 * @author 邢玉祥
 * @Date 2023-03-22 10:55:50
 */
@Controller
@RequestMapping("/fawmallPurchaseorderCancel")
public class FawmallPurchaseorderCancelController extends BaseController {

    private String PREFIX = "/fawPurchase0rder/fawmallPurchaseorderCancel";

    @Autowired
    private FawmallPurchaseorderCancelService fawmallPurchaseorderCancelService;

    /**
     * 跳转到主页面
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/fawmallPurchaseorderCancel.html";
    }

    /**
     * 新增页面
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/fawmallPurchaseorderCancel_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/fawmallPurchaseorderCancel_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(FawmallPurchaseorderCancelParam fawmallPurchaseorderCancelParam) {
        this.fawmallPurchaseorderCancelService.add(fawmallPurchaseorderCancelParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(FawmallPurchaseorderCancelParam fawmallPurchaseorderCancelParam) {
        this.fawmallPurchaseorderCancelService.update(fawmallPurchaseorderCancelParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(FawmallPurchaseorderCancelParam fawmallPurchaseorderCancelParam) {
        this.fawmallPurchaseorderCancelService.delete(fawmallPurchaseorderCancelParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FawmallPurchaseorderCancelParam fawmallPurchaseorderCancelParam) {
        FawmallPurchaseorderCancel detail = this.fawmallPurchaseorderCancelService.getById(fawmallPurchaseorderCancelParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 邢玉祥
     * @Date 2023-03-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(FawmallPurchaseorderCancelParam fawmallPurchaseorderCancelParam) {
        return this.fawmallPurchaseorderCancelService.findPageBySpec(fawmallPurchaseorderCancelParam);
    }
}


