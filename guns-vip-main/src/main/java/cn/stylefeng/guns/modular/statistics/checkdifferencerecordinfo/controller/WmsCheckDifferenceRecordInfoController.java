package cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.entity.WmsCheckDifferenceRecordInfo;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.params.WmsCheckDifferenceRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.service.WmsCheckDifferenceRecordInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 盘点差异记录表控制器
 *
 * @author lhx
 * @Date 2021-11-01 17:21:22
 */
@Controller
@RequestMapping("/wmsCheckDifferenceRecordInfo")
public class WmsCheckDifferenceRecordInfoController extends BaseController {

    private String PREFIX = "/modular/statistics/wmsCheckDifferenceRecordInfo";

    @Autowired
    private WmsCheckDifferenceRecordInfoService wmsCheckDifferenceRecordInfoService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCheckDifferenceRecordInfo.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCheckDifferenceRecordInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCheckDifferenceRecordInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCheckDifferenceRecordInfoParam wmsCheckDifferenceRecordInfoParam) {
        this.wmsCheckDifferenceRecordInfoService.add(wmsCheckDifferenceRecordInfoParam);
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
    public ResponseData editItem(WmsCheckDifferenceRecordInfoParam wmsCheckDifferenceRecordInfoParam) {
        this.wmsCheckDifferenceRecordInfoService.update(wmsCheckDifferenceRecordInfoParam);
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
    public ResponseData delete(WmsCheckDifferenceRecordInfoParam wmsCheckDifferenceRecordInfoParam) {
        this.wmsCheckDifferenceRecordInfoService.delete(wmsCheckDifferenceRecordInfoParam);
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
    public ResponseData detail(WmsCheckDifferenceRecordInfoParam wmsCheckDifferenceRecordInfoParam) {
        WmsCheckDifferenceRecordInfo detail = this.wmsCheckDifferenceRecordInfoService.getById(wmsCheckDifferenceRecordInfoParam.getId());
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
    public LayuiPageInfo list(WmsCheckDifferenceRecordInfoParam wmsCheckDifferenceRecordInfoParam) {
        return this.wmsCheckDifferenceRecordInfoService.findPageBySpec(wmsCheckDifferenceRecordInfoParam);
    }

}


