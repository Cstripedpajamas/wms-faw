package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.entity.WmsCabinet2Turnover;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.params.WmsCabinet2TurnoverParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result.WmsCabinet2TurnoverResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅱ类柜周转箱信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2TurnoverService extends IService<WmsCabinet2Turnover> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsCabinet2TurnoverParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsCabinet2TurnoverParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsCabinet2TurnoverParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsCabinet2TurnoverResult findBySpec(WmsCabinet2TurnoverParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2TurnoverResult> findListBySpec(WmsCabinet2TurnoverParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet2TurnoverParam param);

    void updateState(String turnoverID, String state);

    void updateStockLocal(String turnoverID, String row, String col, String layer, String state);

    void updateTurnState(String turnoverID, String state);

    String findTurnoverNumber(String turnoverID);

    String findIdByTurnoverNumber(String huNumber);
}
