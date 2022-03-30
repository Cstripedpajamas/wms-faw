package cn.stylefeng.guns.modular.statistics.errorrecordinfo.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.entity.WmsErrorRecordInfo;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.params.WmsErrorRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.service.WmsErrorRecordInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 异常信息记录表控制器
 *
 * @author lhx
 * @Date 2021-11-01 17:17:08
 */
@Controller
@RequestMapping("/wmsErrorRecordInfo")
public class WmsErrorRecordInfoController extends BaseController {

    private String PREFIX = "/modular/statistics/wmsErrorRecordInfo";

    @Autowired
    private WmsErrorRecordInfoService wmsErrorRecordInfoService;

    @Autowired
    private WmsUserService wmsUserService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsErrorRecordInfo.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add(Model model) {
        List<WmsUserResult> userResultList = wmsUserService.findListBySpec(new WmsUserParam());
        model.addAttribute("userResultList", userResultList);
        return PREFIX + "/wmsErrorRecordInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit(Model model) {
        List<WmsUserResult> userResultList = wmsUserService.findListBySpec(new WmsUserParam());
        model.addAttribute("userResultList", userResultList);
        return PREFIX + "/wmsErrorRecordInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsErrorRecordInfoParam wmsErrorRecordInfoParam) {
        this.wmsErrorRecordInfoService.add(wmsErrorRecordInfoParam);
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
    public ResponseData editItem(WmsErrorRecordInfoParam wmsErrorRecordInfoParam) {
        this.wmsErrorRecordInfoService.update(wmsErrorRecordInfoParam);
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
    public ResponseData delete(WmsErrorRecordInfoParam wmsErrorRecordInfoParam) {
        this.wmsErrorRecordInfoService.delete(wmsErrorRecordInfoParam);
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
    public ResponseData detail(WmsErrorRecordInfoParam wmsErrorRecordInfoParam) {
        WmsErrorRecordInfo detail = this.wmsErrorRecordInfoService.getById(wmsErrorRecordInfoParam.getId());
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
    public LayuiPageInfo list(WmsErrorRecordInfoParam wmsErrorRecordInfoParam) {
        return this.wmsErrorRecordInfoService.findPageBySpec(wmsErrorRecordInfoParam);
    }

}


