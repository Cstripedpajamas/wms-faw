package cn.stylefeng.guns.modular.statistics.sparepartsscrap.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.entity.WmsSparePartsScrap;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.params.WmsSparePartsScrapParam;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.service.WmsSparePartsScrapService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 备品备件报废信息汇总表控制器
 *
 * @author lhx
 * @Date 2021-11-01 17:05:01
 */
@Controller
@RequestMapping("/wmsSparePartsScrap")
public class WmsSparePartsScrapController extends BaseController {

    private String PREFIX = "/modular/statistics/wmsSparePartsScrap";

    @Autowired
    private WmsSparePartsScrapService wmsSparePartsScrapService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsSparePartsScrap.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsSparePartsScrap_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsSparePartsScrap_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsSparePartsScrapParam wmsSparePartsScrapParam) {
        this.wmsSparePartsScrapService.add(wmsSparePartsScrapParam);
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
    public ResponseData editItem(WmsSparePartsScrapParam wmsSparePartsScrapParam) {
        this.wmsSparePartsScrapService.update(wmsSparePartsScrapParam);
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
    public ResponseData delete(WmsSparePartsScrapParam wmsSparePartsScrapParam) {
        this.wmsSparePartsScrapService.delete(wmsSparePartsScrapParam);
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
    public ResponseData detail(WmsSparePartsScrapParam wmsSparePartsScrapParam) {
        WmsSparePartsScrap detail = this.wmsSparePartsScrapService.getById(wmsSparePartsScrapParam.getId());
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
    public LayuiPageInfo list(WmsSparePartsScrapParam wmsSparePartsScrapParam) {
        return this.wmsSparePartsScrapService.findPageBySpec(wmsSparePartsScrapParam);
    }

}


