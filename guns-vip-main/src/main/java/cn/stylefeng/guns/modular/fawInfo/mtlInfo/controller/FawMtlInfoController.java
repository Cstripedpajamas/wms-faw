package cn.stylefeng.guns.modular.fawInfo.mtlInfo.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.entity.FawMtlInfo;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params.FawMtlInfoParam;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.service.FawMtlInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * faw物料信息控制器
 *
 * @author fubenhao
 * @Date 2022-03-28 17:05:43
 */
@Controller
@RequestMapping("/fawMtlInfo")
public class FawMtlInfoController extends BaseController {

    @Autowired
    private FawMtlInfoService fawMtlInfoService;

    /**
     * 新增接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(FawMtlInfoParam fawMtlInfoParam) {
        this.fawMtlInfoService.add(fawMtlInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(FawMtlInfoParam fawMtlInfoParam) {
        this.fawMtlInfoService.update(fawMtlInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(FawMtlInfoParam fawMtlInfoParam) {
        this.fawMtlInfoService.delete(fawMtlInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FawMtlInfoParam fawMtlInfoParam) {
        FawMtlInfo detail = this.fawMtlInfoService.getById(fawMtlInfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(FawMtlInfoParam fawMtlInfoParam) {
        return this.fawMtlInfoService.findPageBySpec(fawMtlInfoParam);
    }

}


