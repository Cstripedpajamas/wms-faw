package cn.stylefeng.guns.modular.onetypecabinet.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1Stock;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1StockParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1StockResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅰ类柜库存信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsIntelligentCabinet1StockService extends IService<WmsIntelligentCabinet1Stock> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void add(WmsIntelligentCabinet1StockParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void delete(WmsIntelligentCabinet1StockParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void update(WmsIntelligentCabinet1StockParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    WmsIntelligentCabinet1StockResult findBySpec(WmsIntelligentCabinet1StockParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsIntelligentCabinet1StockResult> findListBySpec(WmsIntelligentCabinet1StockParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsIntelligentCabinet1StockParam param);


    /**
     * 根据库位编号查询
     */
    WmsIntelligentCabinet1StockResult findByLocaSerialNumber(WmsIntelligentCabinet1StockParam param);

}
