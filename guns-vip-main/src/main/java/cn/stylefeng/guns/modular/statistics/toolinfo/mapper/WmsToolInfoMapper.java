package cn.stylefeng.guns.modular.statistics.toolinfo.mapper;

import cn.stylefeng.guns.modular.statistics.toolinfo.entity.WmsToolInfo;
import cn.stylefeng.guns.modular.statistics.toolinfo.model.params.WmsToolInfoParam;
import cn.stylefeng.guns.modular.statistics.toolinfo.model.result.WmsToolInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工具信息汇总表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsToolInfoMapper extends BaseMapper<WmsToolInfo> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsToolInfoResult> customList(@Param("paramCondition") WmsToolInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsToolInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsToolInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsToolInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsToolInfoParam paramCondition);

}
