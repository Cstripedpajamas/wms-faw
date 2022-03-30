package cn.stylefeng.guns.modular.fawInfo.userInfo.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.entity.FawUserInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import cn.stylefeng.guns.modular.fawInfo.userInfo.service.FawUserInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * faw基本人员信息表控制器
 *
 * @author fubenhao
 * @Date 2022-03-14 10:44:19
 */
@Controller
@RequestMapping("/fawUserInfo")
public class FawUserInfoController extends BaseController {

    @Autowired
    private FawUserInfoService fawUserInfoService;

    /**
     * 新增接口
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(FawUserInfoParam fawUserInfoParam) {
        this.fawUserInfoService.add(fawUserInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(FawUserInfoParam fawUserInfoParam) {
        this.fawUserInfoService.update(fawUserInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(FawUserInfoParam fawUserInfoParam) {
        this.fawUserInfoService.delete(fawUserInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FawUserInfoParam fawUserInfoParam) {
        FawUserInfo detail = this.fawUserInfoService.getById(fawUserInfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(FawUserInfoParam fawUserInfoParam) {
        return this.fawUserInfoService.findPageBySpec(fawUserInfoParam);
    }

}


