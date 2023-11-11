package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseCycleCountParam;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 机械手分拣任务表控制器
 *
 * @author chenrui
 * @Date 2021-11-23 11:51:36
 */
@Controller
@RequestMapping("/cycleCount")
@CrossOrigin
public class WmsWarehouseCycleCountController extends BaseController {

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WarehouseService warehouseService;
    /**
     * 查询列表
     *
     * @author chenrui
     * @Date 2021-11-23
     */
    @ResponseBody
    @GetMapping("/cycleCountSKUList")
    public LayuiPageInfo cycleCountSKUList(WmsWarehouseCycleCountParam wmsWarehouseCycleCountParam) {
        return this.wmsWarehouseTurnoverBindService.findSKUList(wmsWarehouseCycleCountParam);
    }


    /**
     * 盘点出库
     *
     * @author chenrui
     * @Date 2021-11-23
     */
    @ResponseBody
    @RequestMapping("/cycleCountOutbound")
    public ResponseData cycleCountOutbound(String sku, String plant, String materialType,String diBatchNo,int outboundBoxNumber,String MNumber) {
        return warehouseService.cycleCountOutWarehouse(sku,plant,materialType,diBatchNo,outboundBoxNumber,MNumber);
    }
}


