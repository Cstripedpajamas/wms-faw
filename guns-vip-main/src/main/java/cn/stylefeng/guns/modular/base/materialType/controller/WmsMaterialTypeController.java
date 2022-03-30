package cn.stylefeng.guns.modular.base.materialType.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.base.packageInfo.model.params.WmsPackinfoParam;
import cn.stylefeng.guns.modular.base.packageInfo.model.result.WmsPackinfoResult;
import cn.stylefeng.guns.modular.base.packageInfo.service.WmsPackinfoService;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1LatticeTypeParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1LatticeTypeResult;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsIntelligentCabinet1LatticeTypeService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.utils.JudgeUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;


/**
 * 物料类型信息表控制器
 *
 * @author lhx
 * @Date 2021-11-01 14:54:13
 */
@Controller
@RequestMapping("/wmsMaterialType")
public class WmsMaterialTypeController extends BaseController {

    private String PREFIX = "/modular/base/wmsMaterialType";

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private WmsIntelligentCabinet1LatticeTypeService wmsIntelligentCabinet1LatticeTypeService;

    @Autowired
    private WmsPackinfoService wmsPackinfoService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsMaterialType.html";
    }

    /**
     * 弹出选择物料类型的界面
     *
     * @return
     */
    @RequestMapping("/select")
    public String select(String type, Model model) {
        model.addAttribute("type", type);
        return PREFIX + "/selectMaterialType.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add(Model model) {
        List<WmsIntelligentCabinet1LatticeTypeResult> latticeTypeResultList = wmsIntelligentCabinet1LatticeTypeService.findListBySpec(new WmsIntelligentCabinet1LatticeTypeParam());
        model.addAttribute("latticeTypeResultList", latticeTypeResultList);
        List<WmsPackinfoResult> listBySpec = wmsPackinfoService.findListBySpec(new WmsPackinfoParam());
        model.addAttribute("wmsPackInfoResultList", listBySpec);
        return PREFIX + "/wmsMaterialType_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit(Model model) {
        List<WmsIntelligentCabinet1LatticeTypeResult> latticeTypeResultList = wmsIntelligentCabinet1LatticeTypeService.findListBySpec(new WmsIntelligentCabinet1LatticeTypeParam());
        model.addAttribute("latticeTypeResultList", latticeTypeResultList);
        List<WmsPackinfoResult> listBySpec = wmsPackinfoService.findListBySpec(new WmsPackinfoParam());
        model.addAttribute("wmsPackInfoResultList", listBySpec);
        return PREFIX + "/wmsMaterialType_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsMaterialTypeParam wmsMaterialTypeParam) {
        //判断物料类型是否存在
        QueryWrapper<WmsMaterialType> wrapper = new QueryWrapper<>();
        wrapper.eq("material_sku", wmsMaterialTypeParam.getMaterialSku());
        WmsMaterialType one = wmsMaterialTypeService.getOne(wrapper);
        // 如果不为空，则说明物料类型存在
        if (!Objects.isNull(one)) {
            return ResponseData.error("物料SKU已存在！");
        }
        this.wmsMaterialTypeService.add(wmsMaterialTypeParam);
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
    public ResponseData editItem(WmsMaterialTypeParam wmsMaterialTypeParam) {
        //判断物料类型是否存在
        QueryWrapper<WmsMaterialType> wrapper = new QueryWrapper<>();
        wrapper.eq("material_sku", wmsMaterialTypeParam.getMaterialSku());
        WmsMaterialType one = wmsMaterialTypeService.getOne(wrapper);
        // 如果不为空，则说明物料类型存在
        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsMaterialTypeParam.getId())) {
            return ResponseData.error("物料SKU已存在！");
        }
        this.wmsMaterialTypeService.update(wmsMaterialTypeParam);
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
    public ResponseData delete(WmsMaterialTypeParam wmsMaterialTypeParam) {
        // 判断是否可以删除
        if (!JudgeUtil.canDeleteMaterialType(wmsMaterialTypeParam.getId())) {
            return ResponseData.error("此物料类型已被关联，无法删除");
        }
        this.wmsMaterialTypeService.delete(wmsMaterialTypeParam);
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
    public ResponseData detail(WmsMaterialTypeParam wmsMaterialTypeParam) {
        WmsMaterialType detail = this.wmsMaterialTypeService.getById(wmsMaterialTypeParam.getId());
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
    public LayuiPageInfo list(WmsMaterialTypeParam wmsMaterialTypeParam) {
        return this.wmsMaterialTypeService.findPageBySpec(wmsMaterialTypeParam);
    }

}


