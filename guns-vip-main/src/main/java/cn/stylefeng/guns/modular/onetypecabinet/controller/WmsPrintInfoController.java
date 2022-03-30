package cn.stylefeng.guns.modular.onetypecabinet.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsPrintInfo;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsPrintInfoParam;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsPrintInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 条码打印信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-24 17:25:37
 */
@Controller
@RequestMapping("/wmsPrintInfo")
public class WmsPrintInfoController extends BaseController {

    private String PREFIX = "/onetypecabinet/wmsPrintInfo";

    @Autowired
    private WmsPrintInfoService wmsPrintInfoService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsPrintInfo.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsPrintInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsPrintInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsPrintInfoParam wmsPrintInfoParam) {
        this.wmsPrintInfoService.add(wmsPrintInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsPrintInfoParam wmsPrintInfoParam) {
        this.wmsPrintInfoService.update(wmsPrintInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsPrintInfoParam wmsPrintInfoParam) {
        this.wmsPrintInfoService.delete(wmsPrintInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsPrintInfoParam wmsPrintInfoParam) {
        WmsPrintInfo detail = this.wmsPrintInfoService.getById(wmsPrintInfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsPrintInfoParam wmsPrintInfoParam) {
        return this.wmsPrintInfoService.findPageBySpec(wmsPrintInfoParam);
    }

}


