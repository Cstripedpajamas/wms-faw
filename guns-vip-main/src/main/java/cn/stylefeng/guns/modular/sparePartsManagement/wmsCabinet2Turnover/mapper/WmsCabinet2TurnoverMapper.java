package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.mapper;

import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.entity.WmsCabinet2Turnover;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.params.WmsCabinet2TurnoverParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result.WmsCabinet2TurnoverResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅱ类柜周转箱信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2TurnoverMapper extends BaseMapper<WmsCabinet2Turnover> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2TurnoverResult> customList(@Param("paramCondition") WmsCabinet2TurnoverParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet2TurnoverParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsCabinet2TurnoverResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2TurnoverParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2TurnoverParam paramCondition);

    void updateState(@Param("turnoverID")String turnoverID, @Param("state")String state);

    void updateStockLocal(@Param("turnoverID")String turnoverID, @Param("row")String row, @Param("col")String col, @Param("layer")String layer, @Param("state")String state);

    void updateTurnState(@Param("turnoverID")String turnoverID, @Param("state")String state);

    String findTurnoverNumber(@Param("turnoverID")String turnoverID);

    String findIdByTurnoverNumber(@Param("turnoverNumber")String huNumber);
}
