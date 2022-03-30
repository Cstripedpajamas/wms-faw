package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.mapper;

import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.entity.WmsCabinet2InputScrap;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.params.WmsCabinet2InputScrapParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.result.WmsCabinet2InputScrapResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅱ类柜投入报废信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2InputScrapMapper extends BaseMapper<WmsCabinet2InputScrap> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2InputScrapResult> customList(@Param("paramCondition") WmsCabinet2InputScrapParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet2InputScrapParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsCabinet2InputScrapResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2InputScrapParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2InputScrapParam paramCondition);

}
