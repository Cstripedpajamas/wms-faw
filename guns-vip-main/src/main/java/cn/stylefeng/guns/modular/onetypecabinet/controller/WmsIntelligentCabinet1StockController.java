package cn.stylefeng.guns.modular.onetypecabinet.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1Stock;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1StockParam;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsIntelligentCabinet1StockService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Ⅰ类柜库存信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-01 13:54:45
 */
@Controller
@RequestMapping("/wmsIntelligentCabinet1Stock")
public class WmsIntelligentCabinet1StockController extends BaseController {

    private String PREFIX = "/onetypecabinet/wmsIntelligentCabinet1Stock";

    @Autowired
    private WmsIntelligentCabinet1StockService wmsIntelligentCabinet1StockService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsIntelligentCabinet1Stock.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsIntelligentCabinet1Stock_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsIntelligentCabinet1Stock_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam) {
        this.wmsIntelligentCabinet1StockService.add(wmsIntelligentCabinet1StockParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam) {
        this.wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1StockParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam) {
        this.wmsIntelligentCabinet1StockService.delete(wmsIntelligentCabinet1StockParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam) {
        WmsIntelligentCabinet1Stock detail = this.wmsIntelligentCabinet1StockService.getById(wmsIntelligentCabinet1StockParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam) {
        return this.wmsIntelligentCabinet1StockService.findPageBySpec(wmsIntelligentCabinet1StockParam);
    }

}


