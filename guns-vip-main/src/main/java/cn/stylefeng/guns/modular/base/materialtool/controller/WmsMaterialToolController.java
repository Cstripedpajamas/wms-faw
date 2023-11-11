package cn.stylefeng.guns.modular.base.materialtool.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.materialtool.model.params.WmsMaterialToolParam;
import cn.stylefeng.guns.modular.base.materialtool.service.WmsMaterialToolService;
import cn.stylefeng.guns.modular.utils.JudgeUtil;
import cn.stylefeng.guns.sys.modular.consts.service.SysConfigService;
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
    @Autowired
    private SysConfigService sysConfigService;

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
//        // 查看编号是否存在(手动添加的时候)
//        QueryWrapper<WmsMaterialTool> wrapper = new QueryWrapper<>();
//        wrapper.eq("material_serial_number", wmsMaterialToolParam.getMaterialSerialNumber());
//        WmsMaterialTool one = wmsMaterialToolService.getOne(wrapper);
//        // 如果不为空，则说明编号存在
//        if (!Objects.isNull(one)) {
//            return ResponseData.error("物料编号已存在！");
//        }

//        String toolCard =  ConstantsContext.getToolCard();
//        String[] codes = addCodeNumber(toolCard, 1);
//        wmsMaterialToolParam.setMaterialSerialNumber(codes[0]);
        this.wmsMaterialToolService.add(wmsMaterialToolParam);
        // 更新缓存
//        ConstantsContext.putConstant("TOOL_CARD",codes[codes.length -1]);
        ConstantsContext.putConstant("TOOL_CARD",wmsMaterialToolParam.getMaterialSerialNumber());

        // 更新数据库
//        sysConfigService.updateByCode("TOOL_CARD",codes[codes.length -1]);
        sysConfigService.updateByCode("TOOL_CARD",wmsMaterialToolParam.getMaterialSerialNumber());
        return ResponseData.success();
    }

    public  String[] addCodeNumber(String startCode, int number){
        String[] codes = new String[number];
        String r = startCode.replace("R", "1"); // R0000001 -> 10000001
        for (int i1 = 0; i1 < number; i1++) {
            int i = Integer.parseInt(r) + i1 + 1 ;
            String str = Integer.toString(i);
            codes[i1] = "R"+ str.substring(1);
        }
        return codes;
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
//        QueryWrapper<WmsMaterialTool> wrapper = new QueryWrapper<>();
//        wrapper.eq("material_serial_number", wmsMaterialToolParam.getMaterialSerialNumber());
//        WmsMaterialTool one = wmsMaterialToolService.getOne(wrapper);
//        // 如果不为空，则说明编号存在
//        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsMaterialToolParam.getId())) {
//            return ResponseData.error("物料编号已存在！");
//        }
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
        System.out.println("测试");
        System.out.println(wmsMaterialToolParam);
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


    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItemFromMaterialType")
    @ResponseBody
    public ResponseData addItemFromMaterialType(WmsMaterialTypeParam typeParam) {
        // 查看编号是否存在
        QueryWrapper<WmsMaterialTool> wrapper = new QueryWrapper<>();
        wrapper.eq("material_serial_number", typeParam.getMaterialSerialNumber());
        WmsMaterialTool one = wmsMaterialToolService.getOne(wrapper);
        if (!Objects.isNull(one)) {
            return ResponseData.error("RFID条码已存在！");
        }

        // 添加物料
        WmsMaterialToolParam toolParam = new WmsMaterialToolParam();
        toolParam.setMaterialTypeId(typeParam.getId().toString());
        toolParam.setMaterialType(typeParam.getMaterialType());
        toolParam.setMaterialName(typeParam.getMaterialName());
        toolParam.setMaterialSku(typeParam.getMaterialSku());
        toolParam.setMaterialSerialNumber(typeParam.getMaterialSerialNumber());
        toolParam.setMUnit(typeParam.getMUnit());
        toolParam.setMaterialState("0");
        toolParam.setStorageState("0");
        toolParam.setDataState("0");

        this.wmsMaterialToolService.add(toolParam);
        return ResponseData.success();
    }

}


