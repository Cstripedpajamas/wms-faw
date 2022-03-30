package cn.stylefeng.guns.modular.statistics.tooluse.mapper;

import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.model.params.WmsToolUseParam;
import cn.stylefeng.guns.modular.statistics.tooluse.model.result.WmsToolUseResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工具领用信息表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsToolUseMapper extends BaseMapper<WmsToolUse> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsToolUseResult> customList(@Param("paramCondition") WmsToolUseParam paramCondition);


    List<WmsToolUseResult> customListA(@Param("paramCondition") WmsToolUseParam paramCondition);
    List<WmsToolUseResult> customListB(@Param("paramCondition") WmsToolUseParam paramCondition);
    List<WmsToolUseResult> customListC(@Param("paramCondition") WmsToolUseParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsToolUseParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsToolUseResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsToolUseParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsToolUseParam paramCondition);

}
