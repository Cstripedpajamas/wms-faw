package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.afterturn.easypoi.entity.vo.MapExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.Code;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseStock;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam.Type;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverResult;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;


/**
 * 立库-周转箱信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:08
 */
@Controller
@RequestMapping("/wmsWarehouseTurnover")
public class WmsWarehouseTurnoverController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehouseTurnover";

    @Autowired
    private Code turnoverBoxCode;

    @Autowired
    private WmsWarehouseTurnoverService wmsWarehouseTurnoverService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehouseTurnover.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehouseTurnover_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehouseTurnover_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam) {
        String code = turnoverBoxCode.createCode("0001");
        wmsWarehouseTurnoverParam.setTurnoverNumber(code);
        this.wmsWarehouseTurnoverService.add(wmsWarehouseTurnoverParam);

        // 添加周转箱之后,根据格口的数量创建周转箱的绑定的格口信息
        WmsWarehouseTurnoverResult listBySpec = wmsWarehouseTurnoverService.findByTurnoverNumber(code);
        String number = wmsWarehouseTurnoverParam.getTurnoverMouthQuantity();
         int i = Integer.parseInt(number);
        for (int i1 = 1; i1 <= i; i1++) {
            WmsWarehouseTurnoverBindParam w = new WmsWarehouseTurnoverBindParam();
            w.setTurnoverId(listBySpec.getId().toString());
            w.setLatticeCode((Type.A000.toString()+i1));
            wmsWarehouseTurnoverBindService.add(w);
        }
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam) {
        this.wmsWarehouseTurnoverService.update(wmsWarehouseTurnoverParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam) {
        this.wmsWarehouseTurnoverService.delete(wmsWarehouseTurnoverParam);

        // 删除周转箱绑定的格口信息
        this.wmsWarehouseTurnoverBindService.delByTurnId(wmsWarehouseTurnoverParam.getId());
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam) {
        WmsWarehouseTurnover detail = this.wmsWarehouseTurnoverService.getById(wmsWarehouseTurnoverParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam) {
        return this.wmsWarehouseTurnoverService.findPageBySpec(wmsWarehouseTurnoverParam);
    }


    /**
     * 模板导出
     */
    @RequestMapping("/exportOut")
    public void exportOut(ModelMap modelMap, HttpServletRequest request,
                          HttpServletResponse response) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("周转箱类型(A 小 B 中 C 大)", "turnoverType"));
        entity.add(new ExcelExportEntity("条码信息", "barcode"));
        entity.add(new ExcelExportEntity("存放状态(1 库内 2 库外)", "storageStatus"));
        entity.add(new ExcelExportEntity("存放-排", "locaRow"));
        entity.add(new ExcelExportEntity("存放-列", "locaCol"));
        entity.add(new ExcelExportEntity("存放-层", "locaLayer"));
        entity.add(new ExcelExportEntity("周转箱状态(0 空闲 1 有货)", "turnoverState"));
        entity.add(new ExcelExportEntity("格口数量", "affWarehouse"));
        entity.add(new ExcelExportEntity("归属仓库(0 备品备件 1 工具)", "turnoverMouthQuantity"));
        ExportParams params = new ExportParams("周转箱信息", "周转箱信息", ExcelType.XSSF);
        modelMap.put(MapExcelConstants.MAP_LIST, new ArrayList<>());
        modelMap.put(MapExcelConstants.ENTITY_LIST, entity);
        modelMap.put(MapExcelConstants.PARAMS, params);
        modelMap.put(MapExcelConstants.FILE_NAME, "周转箱信息");
        PoiBaseView.render(modelMap, request, response, MapExcelConstants.EASYPOI_MAP_EXCEL_VIEW);

    }

    /**
     * 导入
     */
    @RequestMapping("/exportIn")
    @ResponseBody
    public ResponseData exportIn(@RequestPart("file") MultipartFile file, HttpServletRequest request) throws IOException {
        List<WmsWarehouseTurnover> list = new ArrayList<>();
        XSSFWorkbook sheets = new XSSFWorkbook(file.getInputStream());
        //获取sheet
        XSSFSheet sheet = sheets.getSheet(sheets.getSheetName(0));
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 2; i < rows; i++) {
            //获取列数
            XSSFRow row = sheet.getRow(i);
            WmsWarehouseTurnover wmsWarehouseStock = new WmsWarehouseTurnover();
            String code = turnoverBoxCode.createCode("0001");
            wmsWarehouseStock.setTurnoverNumber(code); // 周转箱编号
            wmsWarehouseStock.setTurnoverType(row.getCell(0).toString()); // 周转箱类型
            wmsWarehouseStock.setBarcode(row.getCell(1).toString()); // 条码信息
            wmsWarehouseStock.setStorageStatus(row.getCell(2).toString()); // 存放状态
            wmsWarehouseStock.setLocaRow(row.getCell(3).toString().split("\\.")[0]); // 地址-排
            wmsWarehouseStock.setLocaCol(row.getCell(4).toString().split("\\.")[0]); // 地址-列
            wmsWarehouseStock.setLocaLayer(row.getCell(5).toString().split("\\.")[0]); // 地址-层
            wmsWarehouseStock.setTurnoverState(row.getCell(6).toString().split("\\.")[0]); // 周转箱状态
            wmsWarehouseStock.setTurnoverMouthQuantity(row.getCell(7).toString().split("\\.")[0]); // 格口数量
            wmsWarehouseStock.setAffWarehouse(row.getCell(8).toString().split("\\.")[0]); // 归属仓库
            wmsWarehouseStock.setDataState("0"); // 数据状态
            wmsWarehouseStock.setCreateTime(new Date());
            list.add(wmsWarehouseStock);
        }

        wmsWarehouseTurnoverService.saveBatch(list);

        return  ResponseData.success();
    }

}


