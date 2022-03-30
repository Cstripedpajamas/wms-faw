package cn.stylefeng.guns.modular.onetypecabinet.mapper;

import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1LatticeType;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1LatticeTypeParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1LatticeTypeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅰ类柜格口类型信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsIntelligentCabinet1LatticeTypeMapper extends BaseMapper<WmsIntelligentCabinet1LatticeType> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsIntelligentCabinet1LatticeTypeResult> customList(@Param("paramCondition") WmsIntelligentCabinet1LatticeTypeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsIntelligentCabinet1LatticeTypeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<WmsIntelligentCabinet1LatticeTypeResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsIntelligentCabinet1LatticeTypeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsIntelligentCabinet1LatticeTypeParam paramCondition);

}
