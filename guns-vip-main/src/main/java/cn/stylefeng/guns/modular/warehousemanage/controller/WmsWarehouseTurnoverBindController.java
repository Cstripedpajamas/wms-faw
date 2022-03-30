package cn.stylefeng.guns.modular.warehousemanage.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam.Type;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverBindResult;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 立库-周转箱绑定货物信息表控制器
 *
 * @author liwenya
 * @Date 2021-11-02 08:58:08
 */
@Controller
@RequestMapping("/wmsWarehouseTurnoverBind")
public class WmsWarehouseTurnoverBindController extends BaseController {

    private String PREFIX = "/warehousemanage/wmsWarehouseTurnoverBind";

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WmsWarehouseTurnoverService wmsWarehouseTurnoverService;

    /**
     * 跳转到主页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsWarehouseTurnoverBind.html";
    }

    /**
     * 新增页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsWarehouseTurnoverBind_add.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsWarehouseTurnoverBind_edit.html";
    }

    /**
     * 编辑页面
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/details")
    public String details() {
        return PREFIX + "/wmsWarehouseTurnoverBind_detail.html";
    }


    /**
     * 新增接口
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam) {
        // 判断格口数量,生成顺序格口编号,并更新周转箱数量
        LayuiPageInfo lattice = wmsWarehouseTurnoverBindService.findLattice(wmsWarehouseTurnoverBindParam);
         int size = lattice.getData().size();
         if (size>11){ // 格口数量限制
             return ResponseData.error("格口数目过多");
         }
         wmsWarehouseTurnoverBindParam.setTurnoverId(wmsWarehouseTurnoverBindParam.getId().toString());
         wmsWarehouseTurnoverBindParam.setLatticeCode(Type.A000.toString()+(size + 1)); // 新增格口编号
        this.wmsWarehouseTurnoverBindService.add(wmsWarehouseTurnoverBindParam); // 添加格口

        // 更新周转箱上的格口数量
        this.wmsWarehouseTurnoverService.updateLatticeNumber(wmsWarehouseTurnoverBindParam.getTurnoverId(),(size + 1) +"");

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
    public ResponseData editItem(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam) {
        this.wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverBindParam);
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
    public ResponseData delete(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam) {
        this.wmsWarehouseTurnoverBindService.delete(wmsWarehouseTurnoverBindParam);
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
    public ResponseData detail(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam) {
        WmsWarehouseTurnoverBind detail = this.wmsWarehouseTurnoverBindService.getById(wmsWarehouseTurnoverBindParam.getId());
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
    public LayuiPageInfo list(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam) {
        return this.wmsWarehouseTurnoverBindService.findPageBySpec(wmsWarehouseTurnoverBindParam);
    }


    /**
     * 查询周转箱上绑定的格口信息
     * */
    @ResponseBody
    @RequestMapping("/findLattice")
    public LayuiPageInfo findLattice(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam) {
        return this.wmsWarehouseTurnoverBindService.findLattice(wmsWarehouseTurnoverBindParam);
    }

}


