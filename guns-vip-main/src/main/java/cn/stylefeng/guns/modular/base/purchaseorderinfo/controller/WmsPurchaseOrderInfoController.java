package cn.stylefeng.guns.modular.base.purchaseorderinfo.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.entity.WmsIntelligentCabinet2Conf;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;


/**
 * 采购订单信息表控制器
 *
 * @author lhx
 * @Date 2021-11-01 15:08:30
 */
@Controller
@RequestMapping("/wmsPurchaseOrderInfo")
public class WmsPurchaseOrderInfoController extends BaseController {

    private String PREFIX = "/modular/base/wmsPurchaseOrderInfo";

    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsPurchaseOrderInfo.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsPurchaseOrderInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsPurchaseOrderInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        // 查看采购单号是否存在
        QueryWrapper<WmsPurchaseOrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pur_number", wmsPurchaseOrderInfoParam.getPurNumber());
        WmsPurchaseOrderInfo one = wmsPurchaseOrderInfoService.getOne(wrapper);
        // 如果不为空，则说明采购单号存在
        if (!Objects.isNull(one)) {
            return ResponseData.error("此采购单号已存在！");
        }
        wmsPurchaseOrderInfoParam.setReceivedQuantity("0");
        wmsPurchaseOrderInfoParam.setAcceptableQuantity(wmsPurchaseOrderInfoParam.getMNumber());
        this.wmsPurchaseOrderInfoService.add(wmsPurchaseOrderInfoParam);
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
    public ResponseData editItem(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        // 查看采购单号是否存在
        QueryWrapper<WmsPurchaseOrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pur_number", wmsPurchaseOrderInfoParam.getPurNumber());
        WmsPurchaseOrderInfo one = wmsPurchaseOrderInfoService.getOne(wrapper);
        // 如果不为空，则说明采购单号存在
        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsPurchaseOrderInfoParam.getId())) {
            return ResponseData.error("此采购单号已存在！");
        }
        this.wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParam);
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
    public ResponseData delete(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        this.wmsPurchaseOrderInfoService.delete(wmsPurchaseOrderInfoParam);
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
    public ResponseData detail(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        WmsPurchaseOrderInfo detail = this.wmsPurchaseOrderInfoService.getById(wmsPurchaseOrderInfoParam.getId());
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
    public LayuiPageInfo list(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        return this.wmsPurchaseOrderInfoService.findPageBySpec(wmsPurchaseOrderInfoParam);
    }

    /**
     * 查询列表出退还意外的数据
     */
    @ResponseBody
    @RequestMapping("/contentionList")
    public ResponseData contentionList() {
        List<WmsPurchaseOrderInfo> list = this.wmsPurchaseOrderInfoService.list(new QueryWrapper<WmsPurchaseOrderInfo>().ne("arrival_state",3));
        return ResponseData.success(list);
    }

}


