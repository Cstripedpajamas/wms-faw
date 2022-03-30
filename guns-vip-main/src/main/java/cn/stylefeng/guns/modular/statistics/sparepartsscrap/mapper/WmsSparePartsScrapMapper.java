package cn.stylefeng.guns.modular.statistics.sparepartsscrap.mapper;

import cn.stylefeng.guns.modular.statistics.sparepartsscrap.entity.WmsSparePartsScrap;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.params.WmsSparePartsScrapParam;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.result.WmsSparePartsScrapResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 备品备件报废信息汇总表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsSparePartsScrapMapper extends BaseMapper<WmsSparePartsScrap> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsSparePartsScrapResult> customList(@Param("paramCondition") WmsSparePartsScrapParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsSparePartsScrapParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsSparePartsScrapResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsSparePartsScrapParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsSparePartsScrapParam paramCondition);

}
