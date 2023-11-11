package cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.entity.WmsEquipmentFailureStatistics;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.params.WmsEquipmentFailureStatisticsParam;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.service.WmsEquipmentFailureStatisticsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 设备故障统计表控制器
 *
 * @author lhx
 * @Date 2021-11-01 17:13:27
 */
@Controller
@RequestMapping("/wmsEquipmentFailureStatistics")
public class WmsEquipmentFailureStatisticsController extends BaseController {

    private String PREFIX = "/modular/statistics/wmsEquipmentFailureStatistics";

    @Autowired
    private WmsEquipmentFailureStatisticsService wmsEquipmentFailureStatisticsService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsEquipmentFailureStatistics.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsEquipmentFailureStatistics_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsEquipmentFailureStatistics_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsEquipmentFailureStatisticsParam wmsEquipmentFailureStatisticsParam) {
        this.wmsEquipmentFailureStatisticsService.add(wmsEquipmentFailureStatisticsParam);
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
    public ResponseData editItem(WmsEquipmentFailureStatisticsParam wmsEquipmentFailureStatisticsParam) {
        this.wmsEquipmentFailureStatisticsService.update(wmsEquipmentFailureStatisticsParam);
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
    public ResponseData delete(WmsEquipmentFailureStatisticsParam wmsEquipmentFailureStatisticsParam) {
        this.wmsEquipmentFailureStatisticsService.delete(wmsEquipmentFailureStatisticsParam);
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
    public ResponseData detail(WmsEquipmentFailureStatisticsParam wmsEquipmentFailureStatisticsParam) {
        WmsEquipmentFailureStatistics detail = this.wmsEquipmentFailureStatisticsService.getById(wmsEquipmentFailureStatisticsParam.getId());
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
    public LayuiPageInfo list(WmsEquipmentFailureStatisticsParam wmsEquipmentFailureStatisticsParam) {
        return this.wmsEquipmentFailureStatisticsService.findPageBySpec(wmsEquipmentFailureStatisticsParam);
    }


    /**
     * 查询维修列表
     *
     * @author 王盼宇
     * @Date 2023-07-22
     */
    @ResponseBody
    @RequestMapping("/RepairList")
    public LayuiPageInfo RepairList(WmsEquipmentFailureStatisticsParam wmsEquipmentFailureStatisticsParam) {
        return this.wmsEquipmentFailureStatisticsService.findPageBySpec(wmsEquipmentFailureStatisticsParam);
    }

}


