package cn.stylefeng.guns.modular.base.intelligentcabinet2conf.mapper;

import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.entity.WmsIntelligentCabinet2Conf;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.params.WmsIntelligentCabinet2ConfParam;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.result.WmsIntelligentCabinet2ConfResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅱ类柜物料补货阈值配置表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsIntelligentCabinet2ConfMapper extends BaseMapper<WmsIntelligentCabinet2Conf> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsIntelligentCabinet2ConfResult> customList(@Param("paramCondition") WmsIntelligentCabinet2ConfParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsIntelligentCabinet2ConfParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsIntelligentCabinet2ConfResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsIntelligentCabinet2ConfParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsIntelligentCabinet2ConfParam paramCondition);

}
