package cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.mapper;

import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.entity.WmsEquipmentFailureStatistics;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.params.WmsEquipmentFailureStatisticsParam;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.result.WmsEquipmentFailureStatisticsResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备故障统计表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsEquipmentFailureStatisticsMapper extends BaseMapper<WmsEquipmentFailureStatistics> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsEquipmentFailureStatisticsResult> customList(@Param("paramCondition") WmsEquipmentFailureStatisticsParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsEquipmentFailureStatisticsParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsEquipmentFailureStatisticsResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsEquipmentFailureStatisticsParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsEquipmentFailureStatisticsParam paramCondition);

}
