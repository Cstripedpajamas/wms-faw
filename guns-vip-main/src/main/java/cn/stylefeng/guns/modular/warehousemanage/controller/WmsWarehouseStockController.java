package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.afterturn.easypoi.entity.vo.MapExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseStock;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseStockParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseStockService;
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


/**
 * 立库-仓库库存信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:08
 */
@Controller
@RequestMapping("/wmsWarehouseStock")
public class WmsWarehouseStockController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehouseStock";

    @Autowired
    private WmsWarehouseStockService wmsWarehouseStockService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehouseStock.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehouseStock_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehouseStock_edit.html";
    }

    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehouseStockParam wmsWarehouseStockParam) {
        this.wmsWarehouseStockService.add(wmsWarehouseStockParam);
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
    public ResponseData editItem(WmsWarehouseStockParam wmsWarehouseStockParam) {
        this.wmsWarehouseStockService.update(wmsWarehouseStockParam);
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
    public ResponseData delete(WmsWarehouseStockParam wmsWarehouseStockParam) {
        this.wmsWarehouseStockService.delete(wmsWarehouseStockParam);
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
    public ResponseData detail(WmsWarehouseStockParam wmsWarehouseStockParam) {
        WmsWarehouseStock detail = this.wmsWarehouseStockService.getById(wmsWarehouseStockParam.getId());
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
    public LayuiPageInfo list(WmsWarehouseStockParam wmsWarehouseStockParam) {
        return this.wmsWarehouseStockService.findPageBySpec(wmsWarehouseStockParam);
    }


    /**
     * 模板导出
     */
    @RequestMapping("/exportOut")
    public void exportOut(ModelMap modelMap, HttpServletRequest request,
                          HttpServletResponse response) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("库位编号", "locaNumber"));
        entity.add(new ExcelExportEntity("巷道", "roadway"));
        entity.add(new ExcelExportEntity("地址-排", "locaRow"));
        entity.add(new ExcelExportEntity("地址-列", "locaCol"));
        entity.add(new ExcelExportEntity("地址-层", "locaLayer"));
//        entity.add(new ExcelExportEntity("库位设备码", "locaEquipment"));
        entity.add(new ExcelExportEntity("库位状态(0 空闲 1 有货)", "locaState"));
//        entity.add(new ExcelExportEntity("备注", "mark"));
//        entity.add(new ExcelExportEntity("备用字段", "spareField"));
        entity.add(new ExcelExportEntity("数据时间", "createTime"));

        ExportParams params = new ExportParams("仓库库存", "仓库库存", ExcelType.XSSF);
        modelMap.put(MapExcelConstants.MAP_LIST, new ArrayList<>());
        modelMap.put(MapExcelConstants.ENTITY_LIST, entity);
        modelMap.put(MapExcelConstants.PARAMS, params);
        modelMap.put(MapExcelConstants.FILE_NAME, "仓库库存");
        PoiBaseView.render(modelMap, request, response, MapExcelConstants.EASYPOI_MAP_EXCEL_VIEW);

    }

    /**
     * 导入
     */
    @RequestMapping("/exportIn")
    @ResponseBody
    public ResponseData exportIn(@RequestPart("file") MultipartFile file, HttpServletRequest request) throws IOException {
        List<WmsWarehouseStock> list = new ArrayList<>();
        XSSFWorkbook sheets = new XSSFWorkbook(file.getInputStream());
        //获取sheet
        XSSFSheet sheet = sheets.getSheet(sheets.getSheetName(0));
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 2; i < rows; i++) {
            //获取列数
            XSSFRow row = sheet.getRow(i);
            WmsWarehouseStock wmsWarehouseStock = new WmsWarehouseStock();
            wmsWarehouseStock.setLocaNumber(row.getCell(0).toString()); // 库位编号
            wmsWarehouseStock.setRoadway(row.getCell(1).toString()); // 巷道
            wmsWarehouseStock.setLocaRow(row.getCell(2).toString().split("\\.")[0]); // 地址-排
            wmsWarehouseStock.setLocaCol(row.getCell(3).toString().split("\\.")[0]); // 地址-列
            wmsWarehouseStock.setLocaLayer(row.getCell(4).toString().split("\\.")[0]); // 地址-层
//            wmsWarehouseStock.setLocaEquipment(row.getCell(5).toString()); // 库位设备码
            wmsWarehouseStock.setLocaState(row.getCell(5).toString()); // 库位状态
//            wmsWarehouseStock.setMark(row.getCell(6).toString()); // 备注
            Cell createStart = row.getCell(6);
            if(HSSFDateUtil.isCellDateFormatted(createStart)){
                Date d = createStart.getDateCellValue();
                wmsWarehouseStock.setCreateTime(d);
            }
            list.add(wmsWarehouseStock);
        }

        wmsWarehouseStockService.saveBatch(list);

        return  ResponseData.success();
    }


}


