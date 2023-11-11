package cn.stylefeng.guns.modular.base.materialType.controller;

import cn.hutool.core.io.IoUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.packageInfo.model.params.WmsPackinfoParam;
import cn.stylefeng.guns.modular.base.packageInfo.model.result.WmsPackinfoResult;
import cn.stylefeng.guns.modular.base.packageInfo.service.WmsPackinfoService;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1LatticeTypeParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1LatticeTypeResult;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsIntelligentCabinet1LatticeTypeService;
import cn.stylefeng.guns.modular.utils.JudgeUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
            return ResponseData.error("物料号已存在！");
        }
        wmsMaterialTypeParam.setSource("0");
        wmsMaterialTypeParam.setDataState("0");
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
        QueryWrapper<WmsMaterialType> toolsWmsMaterialType = wrapper
                .eq("material_sku", wmsMaterialTypeParam.getMaterialSku())
                .eq("material_type","BJ")
                .eq("turnover_lattice_type","1");
        wrapper.eq("material_sku", wmsMaterialTypeParam.getMaterialSku());
        WmsMaterialType one = wmsMaterialTypeService.getOne(wrapper);
        WmsMaterialType two = wmsMaterialTypeService.getOne(toolsWmsMaterialType);
        // 如果不为空，则说明物料类型存在
        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsMaterialTypeParam.getId())) {
            return ResponseData.error("物料号已存在！");
        }
        //备品备件只能放单格口
        if (!Objects.isNull(two))
        {
            return ResponseData.error("该物料号只能配置单格口的！");
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

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("imageFile") MultipartFile file,@RequestParam("id") String id) {
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();

                String basePath=System.getProperty("user.dir");
                // 指定文件保存路径
                String savePath ="D:\\Programmer\\uploadImage";

            WmsMaterialType wmsMaterialType= this.wmsMaterialTypeService.getById(id);
            String imagePath=savePath+"\\"+fileName;
            wmsMaterialType.setImagePath(imagePath);
            WmsMaterialTypeParam wmsMaterialTypeParam=new WmsMaterialTypeParam();
            ToolUtil.copyProperties(wmsMaterialType,wmsMaterialTypeParam);
            this.wmsMaterialTypeService.update(wmsMaterialTypeParam);

                // 创建保存目录
                File saveDir = new File(savePath);
                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }

                // 保存文件
                File targetFile = new File(saveDir, fileName);

            try {
                file.transferTo(targetFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            FileInfoService fileInfoService = new FileInfoService();
//            fileInfoService.uploadFile(file);
            return "文件上传成功：" + fileName;

        }

        return "请选择要上传的文件";
    }

    @RequestMapping("/checkImage")
    @ResponseBody
    public Object checkImage(HttpServletResponse response,@RequestParam("id") String id) {

        //输出图片的文件流
        try {
            WmsMaterialType wmsMaterialType= this.wmsMaterialTypeService.getById(id);
            String imagePath= wmsMaterialType.getImagePath();
            response.setContentType("image/jpeg");
//            String filePath = "C:\\LOGBOT\\upload\\2b8a5bb3ec32745ac66fa5e91ded94b.png";
            byte[] decode = IoUtil.readBytes(new FileInputStream(imagePath));
            response.getOutputStream().write(decode);
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
        }

        return null;
    }


}


