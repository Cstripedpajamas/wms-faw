package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.mapper;

import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.entity.WmsCabinet2ReplenishmentTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.params.WmsCabinet2ReplenishmentTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.result.WmsCabinet2ReplenishmentTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅱ类柜补货任务信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2ReplenishmentTaskMapper extends BaseMapper<WmsCabinet2ReplenishmentTask> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2ReplenishmentTaskResult> customList(@Param("paramCondition") WmsCabinet2ReplenishmentTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet2ReplenishmentTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsCabinet2ReplenishmentTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2ReplenishmentTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2ReplenishmentTaskParam paramCondition);

    WmsCabinet2ReplenishmentTaskResult findByTaskNumber(@Param("taskNumber") String taskNumber);

    void updateStateById(@Param("id")String id, @Param("state")String state);

    WmsCabinet2ReplenishmentTaskParam findById(@Param("id")String id);
}
