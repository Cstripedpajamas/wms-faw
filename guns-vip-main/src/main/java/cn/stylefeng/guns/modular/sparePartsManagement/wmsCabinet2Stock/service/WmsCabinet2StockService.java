package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.entity.WmsCabinet2Stock;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.params.WmsCabinet2StockParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.result.WmsCabinet2StockResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅱ类柜库存信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2StockService extends IService<WmsCabinet2Stock> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsCabinet2StockParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsCabinet2StockParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsCabinet2StockParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsCabinet2StockResult findBySpec(WmsCabinet2StockParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2StockResult> findListBySpec(WmsCabinet2StockParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    LayuiPageInfo findPageBySpec(WmsCabinet2StockParam param);

    Integer stockIsEnough(String useMaterialTypeId, String useMaterialId, Integer useNumber);

    WmsCabinet2StockResult findNumberInfo(String useMaterialTypeId, String useNumber);

    void updateState(String id, String s);

    void updateStockById(String stockID,String state);

    WmsCabinet2StockResult findNullStock(String locaNumber);

    void updateTurnover(String toString,String stockState, String turnoverID);

    void updateStateByTurnId(String turnoverId, String state);

    WmsCabinet2StockResult findByTurnId(String turnoverId);

    WmsCabinet2StockResult findNullReturnStock();

    void clearTurnMsg(String stockID, String state);

    String findStockNumber(String stockID);

    List<WmsCabinet2StockResult> findAll();

    WmsCabinet2StockParam findById(String stockID);

    void updateNumber(String stockId, String mNumber);

    String findIdByLoaclNumber(String locationId);
}
