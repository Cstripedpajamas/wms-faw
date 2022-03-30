package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result.WmsCabinet2TurnoverResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.entity.WmsCabinet2TurnoverBind;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.params.WmsCabinet2TurnoverBindParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 周转箱绑定货物信息关联表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-02
 */
public interface WmsCabinet2TurnoverBindService extends IService<WmsCabinet2TurnoverBind> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-02
     */
    void add(WmsCabinet2TurnoverBindParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-02
     */
    void delete(WmsCabinet2TurnoverBindParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-02
     */
    void update(WmsCabinet2TurnoverBindParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-02
     */
    WmsCabinet2TurnoverBindResult findBySpec(WmsCabinet2TurnoverBindParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-02
     */
    List<WmsCabinet2TurnoverBindResult> findListBySpec(WmsCabinet2TurnoverBindParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsCabinet2TurnoverBindParam param);

    void delByTurnoverID(String turnoverID);

    WmsCabinet2TurnoverBindResult findNumberInfo(String matterTypeID, String matterNumber);

    WmsCabinet2TurnoverBindResult findByTurnId(String turnoverID);

    void updateNumber(String turnId,String number);

    WmsCabinet2TurnoverBindResult findByTurnId2(String s);
}
