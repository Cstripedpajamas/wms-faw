package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.entity.WmsCabinet2InputScrap;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.params.WmsCabinet2InputScrapParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.service.WmsCabinet2InputScrapService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅱ类柜投入报废信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 16:42:26
 */
@Controller
@RequestMapping("/wmsCabinet2InputScrap")
public class WmsCabinet2InputScrapController extends BaseController {

    private String PREFIX = "/modular/sparePartsManagement/wmsCabinet2InputScrap";

    @Autowired
    private WmsCabinet2InputScrapService wmsCabinet2InputScrapService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet2InputScrap.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet2InputScrap_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet2InputScrap_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet2InputScrapParam wmsCabinet2InputScrapParam) {
        this.wmsCabinet2InputScrapService.add(wmsCabinet2InputScrapParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsCabinet2InputScrapParam wmsCabinet2InputScrapParam) {
        this.wmsCabinet2InputScrapService.update(wmsCabinet2InputScrapParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsCabinet2InputScrapParam wmsCabinet2InputScrapParam) {
        this.wmsCabinet2InputScrapService.delete(wmsCabinet2InputScrapParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsCabinet2InputScrapParam wmsCabinet2InputScrapParam) {
        WmsCabinet2InputScrap detail = this.wmsCabinet2InputScrapService.getById(wmsCabinet2InputScrapParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsCabinet2InputScrapParam wmsCabinet2InputScrapParam) {
        return this.wmsCabinet2InputScrapService.findPageBySpec(wmsCabinet2InputScrapParam);
    }

}


