package cn.stylefeng.guns.modular.base.packageInfo.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.base.packageInfo.model.params.WmsPackinfoParam;
import cn.stylefeng.guns.modular.base.packageInfo.service.WmsPackinfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 包装信息表控制器
 *
 * @author ll
 * @Date 2021-12-14 16:54:08
 */
@Controller
@RequestMapping("/wmsPackinfo")
public class WmsPackinfoController extends BaseController {

    private String PREFIX = "/modular/base/packageInfo";

    @Autowired
    private WmsPackinfoService wmsPackinfoService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-12-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsPackinfo.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-12-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsPackinfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-12-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsPackinfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-12-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsPackinfoParam wmsPackinfoParam) {
        this.wmsPackinfoService.add(wmsPackinfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ll
     * @Date 2021-12-14
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsPackinfoParam wmsPackinfoParam) {
        this.wmsPackinfoService.update(wmsPackinfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ll
     * @Date 2021-12-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsPackinfoParam wmsPackinfoParam) {
        this.wmsPackinfoService.delete(wmsPackinfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ll
     * @Date 2021-12-14
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsPackinfoParam wmsPackinfoParam) {
        WmsPackinfo detail = this.wmsPackinfoService.getById(wmsPackinfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author ll
     * @Date 2021-12-14
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsPackinfoParam wmsPackinfoParam) {
        return this.wmsPackinfoService.findPageBySpec(wmsPackinfoParam);
    }

}


