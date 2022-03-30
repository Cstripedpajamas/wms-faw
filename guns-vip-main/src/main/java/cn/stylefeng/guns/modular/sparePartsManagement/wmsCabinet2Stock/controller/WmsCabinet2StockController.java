package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.entity.WmsCabinet2Stock;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.params.WmsCabinet2StockParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service.WmsCabinet2StockService;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.entity.WmsCabinet2Turnover;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service.WmsCabinet2TurnoverService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Ⅱ类柜库存信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 16:33:41
 */
@Controller
@RequestMapping("/wmsCabinet2Stock")
@CrossOrigin
public class WmsCabinet2StockController extends BaseController {

    private String PREFIX = "/modular/sparePartsManagement/wmsCabinet2Stock";

    @Autowired
    private WmsCabinet2StockService wmsCabinet2StockService;

    @Autowired
    private WmsCabinet2TurnoverService wmsCabinet2TurnoverService;

    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsCabinet2Stock.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsCabinet2Stock_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsCabinet2Stock_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsCabinet2StockParam wmsCabinet2StockParam) {
        this.wmsCabinet2StockService.add(wmsCabinet2StockParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsCabinet2StockParam wmsCabinet2StockParam) {
        this.wmsCabinet2StockService.update(wmsCabinet2StockParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsCabinet2StockParam wmsCabinet2StockParam) {
        this.wmsCabinet2StockService.delete(wmsCabinet2StockParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsCabinet2StockParam wmsCabinet2StockParam) {
        WmsCabinet2Stock detail = this.wmsCabinet2StockService.getById(wmsCabinet2StockParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsCabinet2StockParam wmsCabinet2StockParam) {
        return this.wmsCabinet2StockService.findPageBySpec(wmsCabinet2StockParam);
    }


    /**
     * 批量生成库位信息
     * */
    @ResponseBody
    @RequestMapping("/batchSaveMsg")
    public ResponseData batchSaveMsg(){
        String a = "x";
        int b = 1;
        List<WmsCabinet2Stock> list = new ArrayList<>();
        List<WmsCabinet2Turnover> list1 = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            for (int i1 = 1; i1 < 11; i1++) {
                for (int i2 = 1; i2 < 16; i2++) {
//                    String bCode = addZero(b);
                    String bCode = addZero2(b);
//                    WmsCabinet2Stock wmsCabinet2StockParam = new WmsCabinet2Stock();
//                    wmsCabinet2StockParam.setLocaNumber(bCode);
//                    wmsCabinet2StockParam.setLocaRow(i+"");
//                    wmsCabinet2StockParam.setLocaCol(i1+"");
//                    wmsCabinet2StockParam.setLocaLayer(i2+"");
//                    wmsCabinet2StockParam.setLocationState("1");
//                    list.add(wmsCabinet2StockParam);
                    WmsCabinet2Turnover wmsCabinet2Turnover = new WmsCabinet2Turnover();
                    wmsCabinet2Turnover.setTurnoverNumber(bCode); // 编号
                    wmsCabinet2Turnover.setBarcode(bCode); // 条码
                    wmsCabinet2Turnover.setLocaRow(i+""); // 排
                    wmsCabinet2Turnover.setLocaCol(i1+""); // 列
                    wmsCabinet2Turnover.setLocaLayer(i2+""); // 层
                    wmsCabinet2Turnover.setStorageStatus("1"); // 存放状态
                    wmsCabinet2Turnover.setTurnoverState("0"); // 空闲
                    wmsCabinet2Turnover.setDataState("0");
                    list1.add(wmsCabinet2Turnover);
                    b++;
                }
            }
        }
//        boolean b1 = wmsCabinet2StockService.saveBatch(list);
        final boolean b1 = wmsCabinet2TurnoverService.saveBatch(list1);

        return ResponseData.success(b1);
    }

    // 库位编码
    public static String addZero(int number){
        StringBuffer sb = new StringBuffer();
        if (number< 10){
            return sb.append("000").append(number).toString();
        }
        else if (number >= 100){
            return sb.append("0").append(number).toString();
        }
        return sb.append("00").append(number).toString();
    }

    // 周转箱编码
    public static String addZero2(int number){
        StringBuffer sb = new StringBuffer();
        if (number< 10){
            return sb.append("00").append(number).toString();
        }
        else if (number >= 100){
            return number+"";
        }
        return sb.append("0").append(number).toString();
    }

}


