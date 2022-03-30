package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.entity.WmsCabinet2Turnover;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.params.WmsCabinet2TurnoverParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service.WmsCabinet2TurnoverService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅱ类柜周转箱信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 16:23:36
 */
@Controller
@RequestMapping("/wmsCabinet2Turnover")
public class WmsCabinet2TurnoverController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(WmsCabinet2TurnoverController.class);
    private String PREFIX = "/modular/sparePartsManagement/wmsCabinet2Turnover";

    @Autowired
    private WmsCabinet2TurnoverService wmsCabinet2TurnoverService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        logger.error("登入次数{}",55);
        return PREFIX + "/wmsCabinet2Turnover.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet2Turnover_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet2Turnover_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet2TurnoverParam wmsCabinet2TurnoverParam) {
        this.wmsCabinet2TurnoverService.add(wmsCabinet2TurnoverParam);
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
    public ResponseData editItem(WmsCabinet2TurnoverParam wmsCabinet2TurnoverParam) {
        this.wmsCabinet2TurnoverService.update(wmsCabinet2TurnoverParam);
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
    public ResponseData delete(WmsCabinet2TurnoverParam wmsCabinet2TurnoverParam) {
        this.wmsCabinet2TurnoverService.delete(wmsCabinet2TurnoverParam);
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
    public ResponseData detail(WmsCabinet2TurnoverParam wmsCabinet2TurnoverParam) {
        WmsCabinet2Turnover detail = this.wmsCabinet2TurnoverService.getById(wmsCabinet2TurnoverParam.getId());
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
    public LayuiPageInfo list(WmsCabinet2TurnoverParam wmsCabinet2TurnoverParam) {
        return this.wmsCabinet2TurnoverService.findPageBySpec(wmsCabinet2TurnoverParam);
    }

}


