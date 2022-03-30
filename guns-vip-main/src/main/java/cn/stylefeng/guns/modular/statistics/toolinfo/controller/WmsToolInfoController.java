package cn.stylefeng.guns.modular.statistics.toolinfo.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.toolinfo.entity.WmsToolInfo;
import cn.stylefeng.guns.modular.statistics.toolinfo.model.params.WmsToolInfoParam;
import cn.stylefeng.guns.modular.statistics.toolinfo.service.WmsToolInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 工具信息汇总表控制器
 *
 * @author lhx
 * @Date 2021-11-01 16:59:46
 */
@Controller
@RequestMapping("/wmsToolInfo")
public class WmsToolInfoController extends BaseController {

    private String PREFIX = "/modular/statistics/wmsToolInfo";

    @Autowired
    private WmsToolInfoService wmsToolInfoService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsToolInfo.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsToolInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsToolInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsToolInfoParam wmsToolInfoParam) {
        this.wmsToolInfoService.add(wmsToolInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsToolInfoParam wmsToolInfoParam) {
        this.wmsToolInfoService.update(wmsToolInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsToolInfoParam wmsToolInfoParam) {
        this.wmsToolInfoService.delete(wmsToolInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsToolInfoParam wmsToolInfoParam) {
        WmsToolInfo detail = this.wmsToolInfoService.getById(wmsToolInfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsToolInfoParam wmsToolInfoParam) {
        return this.wmsToolInfoService.findPageBySpec(wmsToolInfoParam);
    }

}


