package cn.stylefeng.guns.modular.base.materialtool.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.materialtool.model.params.WmsMaterialToolParam;
import cn.stylefeng.guns.modular.base.materialtool.service.WmsMaterialToolService;
import cn.stylefeng.guns.modular.utils.JudgeUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;


/**
 * 工具-物料信息表控制器
 *
 * @author lhx
 * @Date 2021-11-01 15:00:08
 */
@Controller
@RequestMapping("/wmsMaterialTool")
public class WmsMaterialToolController extends BaseController {

    private String PREFIX = "/modular/base/wmsMaterialTool";

    @Autowired
    private WmsMaterialToolService wmsMaterialToolService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsMaterialTool.html";
    }

    /**
     * 选择工具信息界面
     *
     * @return
     */
    @RequestMapping("/select")
    public String select() {
        return PREFIX + "/selectMaterialTool.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsMaterialTool_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsMaterialTool_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsMaterialToolParam wmsMaterialToolParam) {
        // 查看编号是否存在
        QueryWrapper<WmsMaterialTool> wrapper = new QueryWrapper<>();
        wrapper.eq("material_serial_number", wmsMaterialToolParam.getMaterialSerialNumber());
        WmsMaterialTool one = wmsMaterialToolService.getOne(wrapper);
        // 如果不为空，则说明编号存在
        if (!Objects.isNull(one)) {
            return ResponseData.error("物料编号已存在！");
        }

        this.wmsMaterialToolService.add(wmsMaterialToolParam);
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
    public ResponseData editItem(WmsMaterialToolParam wmsMaterialToolParam) {
        // 查看编号是否存在
        QueryWrapper<WmsMaterialTool> wrapper = new QueryWrapper<>();
        wrapper.eq("material_serial_number", wmsMaterialToolParam.getMaterialSerialNumber());
        WmsMaterialTool one = wmsMaterialToolService.getOne(wrapper);
        // 如果不为空，则说明编号存在
        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsMaterialToolParam.getId())) {
            return ResponseData.error("物料编号已存在！");
        }
        this.wmsMaterialToolService.update(wmsMaterialToolParam);
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
    public ResponseData delete(WmsMaterialToolParam wmsMaterialToolParam) {
        // 判断是否可以删除工具
        if (!JudgeUtil.canDeleteMaterialTool(wmsMaterialToolParam.getId())) {
            return ResponseData.error("此工具已被关联，无法删除");
        }
        this.wmsMaterialToolService.delete(wmsMaterialToolParam);
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
    public ResponseData detail(WmsMaterialToolParam wmsMaterialToolParam) {
        WmsMaterialTool detail = this.wmsMaterialToolService.getById(wmsMaterialToolParam.getId());
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
    public LayuiPageInfo list(WmsMaterialToolParam wmsMaterialToolParam) {
        return this.wmsMaterialToolService.findPageBySpec(wmsMaterialToolParam);
    }

}


