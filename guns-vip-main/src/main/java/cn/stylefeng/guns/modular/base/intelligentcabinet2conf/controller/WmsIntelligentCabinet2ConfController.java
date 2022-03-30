package cn.stylefeng.guns.modular.base.intelligentcabinet2conf.controller;

import cn.stylefeng.guns.base.auth.context.LoginContext;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.entity.WmsIntelligentCabinet2Conf;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.params.WmsIntelligentCabinet2ConfParam;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.service.WmsIntelligentCabinet2ConfService;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;


/**
 * Ⅱ类柜物料补货阈值配置表控制器
 *
 * @author lhx
 * @Date 2021-11-01 15:14:51
 */
@Controller
@RequestMapping("/wmsIntelligentCabinet2Conf")
public class WmsIntelligentCabinet2ConfController extends BaseController {

    private String PREFIX = "/modular/base/wmsIntelligentCabinet2Conf";

    @Autowired
    private WmsIntelligentCabinet2ConfService wmsIntelligentCabinet2ConfService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsIntelligentCabinet2Conf.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsIntelligentCabinet2Conf_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsIntelligentCabinet2Conf_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsIntelligentCabinet2ConfParam wmsIntelligentCabinet2ConfParam) {
        // 查看物料编号是否存在
        QueryWrapper<WmsIntelligentCabinet2Conf> wrapper = new QueryWrapper<>();
        wrapper.eq("material_type_id", wmsIntelligentCabinet2ConfParam.getMaterialTypeId());
        WmsIntelligentCabinet2Conf one = wmsIntelligentCabinet2ConfService.getOne(wrapper);
        // 如果不为空，则说明编号存在
        if (!Objects.isNull(one)) {
            return ResponseData.error("此物料阈值已存在！");
        }
        wmsIntelligentCabinet2ConfParam.setOperator(LoginContextHolder.getContext().getUser().getName());
        this.wmsIntelligentCabinet2ConfService.add(wmsIntelligentCabinet2ConfParam);
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
    public ResponseData editItem(WmsIntelligentCabinet2ConfParam wmsIntelligentCabinet2ConfParam) {
        // 查看物料编号是否存在
        QueryWrapper<WmsIntelligentCabinet2Conf> wrapper = new QueryWrapper<>();
        wrapper.eq("material_type_id", wmsIntelligentCabinet2ConfParam.getMaterialTypeId());
        WmsIntelligentCabinet2Conf one = wmsIntelligentCabinet2ConfService.getOne(wrapper);
        // 如果不为空，则说明编号存在
        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsIntelligentCabinet2ConfParam.getId())) {
            return ResponseData.error("此物料阈值已存在！");
        }
        this.wmsIntelligentCabinet2ConfService.update(wmsIntelligentCabinet2ConfParam);
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
    public ResponseData delete(WmsIntelligentCabinet2ConfParam wmsIntelligentCabinet2ConfParam) {
        this.wmsIntelligentCabinet2ConfService.delete(wmsIntelligentCabinet2ConfParam);
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
    public ResponseData detail(WmsIntelligentCabinet2ConfParam wmsIntelligentCabinet2ConfParam) {
        WmsIntelligentCabinet2Conf detail = this.wmsIntelligentCabinet2ConfService.getById(wmsIntelligentCabinet2ConfParam.getId());
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
    public LayuiPageInfo list(WmsIntelligentCabinet2ConfParam wmsIntelligentCabinet2ConfParam) {
        return this.wmsIntelligentCabinet2ConfService.findPageBySpec(wmsIntelligentCabinet2ConfParam);
    }

}


