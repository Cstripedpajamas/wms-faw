package cn.stylefeng.guns.modular.onetypecabinet.mapper;

import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1Stock;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1StockParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1StockResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅰ类柜库存信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsIntelligentCabinet1StockMapper extends BaseMapper<WmsIntelligentCabinet1Stock> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsIntelligentCabinet1StockResult> customList(@Param("paramCondition") WmsIntelligentCabinet1StockParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsIntelligentCabinet1StockParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<WmsIntelligentCabinet1StockResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsIntelligentCabinet1StockParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsIntelligentCabinet1StockParam paramCondition);

    //根据库位编号查询
    WmsIntelligentCabinet1StockResult findByLocaSerialNumber(@Param("paramCondition") WmsIntelligentCabinet1StockParam paramCondition);

}
