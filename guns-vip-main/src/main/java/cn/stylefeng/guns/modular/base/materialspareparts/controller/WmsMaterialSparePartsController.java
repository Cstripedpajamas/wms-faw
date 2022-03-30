package cn.stylefeng.guns.modular.base.materialspareparts.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialspareparts.entity.WmsMaterialSpareParts;
import cn.stylefeng.guns.modular.base.materialspareparts.model.params.WmsMaterialSparePartsParam;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 备品备件-物料信息表控制器
 *
 * @author lhx
 * @Date 2021-11-01 15:04:21
 */
@Controller
@RequestMapping("/wmsMaterialSpareParts")
public class WmsMaterialSparePartsController extends BaseController {

    private String PREFIX = "/modular/base/wmsMaterialSpareParts";

    @Autowired
    private WmsMaterialSparePartsService wmsMaterialSparePartsService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsMaterialSpareParts.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsMaterialSpareParts_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsMaterialSpareParts_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsMaterialSparePartsParam wmsMaterialSparePartsParam) {
        this.wmsMaterialSparePartsService.add(wmsMaterialSparePartsParam);
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
    public ResponseData editItem(WmsMaterialSparePartsParam wmsMaterialSparePartsParam) {
        this.wmsMaterialSparePartsService.update(wmsMaterialSparePartsParam);
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
    public ResponseData delete(WmsMaterialSparePartsParam wmsMaterialSparePartsParam) {
        this.wmsMaterialSparePartsService.delete(wmsMaterialSparePartsParam);
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
    public ResponseData detail(WmsMaterialSparePartsParam wmsMaterialSparePartsParam) {
        WmsMaterialSpareParts detail = this.wmsMaterialSparePartsService.getById(wmsMaterialSparePartsParam.getId());
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
    public LayuiPageInfo list(WmsMaterialSparePartsParam wmsMaterialSparePartsParam) {
        return this.wmsMaterialSparePartsService.findPageBySpec(wmsMaterialSparePartsParam);
    }

}


