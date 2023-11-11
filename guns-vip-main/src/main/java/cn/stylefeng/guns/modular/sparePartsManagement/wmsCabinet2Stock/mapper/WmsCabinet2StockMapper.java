package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.mapper;

import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.entity.WmsCabinet2Stock;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.params.WmsCabinet2StockParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.result.WmsCabinet2StockResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅱ类柜库存信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2StockMapper extends BaseMapper<WmsCabinet2Stock> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2StockResult> customList(@Param("paramCondition") WmsCabinet2StockParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet2StockParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsCabinet2StockResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2StockParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2StockParam paramCondition);

    Integer stockIsEnough(@Param("useMaterialTypeId") String useMaterialTypeId, @Param("useMaterialId") String useMaterialId, @Param("useNumber") Integer useNumber);

    WmsCabinet2StockResult findNumberInfo(@Param("useMaterialTypeId")String useMaterialTypeId, @Param("useNumber")String useNumber);

    void updateState(@Param("id")String id, @Param("state")String state);

    void updateStockById(@Param("stockID")String stockID,@Param("state")String state);

    WmsCabinet2StockResult findNullStock(String locaNumber);

    void updateTurnover(@Param("nullStockID")String nullStockID,@Param("stockState")String stockState, @Param("turnoverID")String turnoverID);

    void updateStateByTurnId(@Param("turnoverId")String turnoverId, @Param("state")String state);

    WmsCabinet2StockResult findByTurnId(@Param("turnoverId")String turnoverId);

    WmsCabinet2StockResult findNullReturnStock();

    void clearTurnMsg(@Param("stockID")String stockID, @Param("state")String state);

    String findStockNumber(@Param("stockID")String stockID);

    List<WmsCabinet2StockResult> findAll();

    WmsCabinet2StockParam findById(@Param("stockID")String stockID);

    void updateNumber(@Param("stockID")String stockID, @Param("mNumber")String mNumber);

    String findIdByLoaclNumber(@Param("locationNumber")String locationId);
}
