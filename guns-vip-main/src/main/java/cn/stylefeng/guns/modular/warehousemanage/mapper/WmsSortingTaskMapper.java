package cn.stylefeng.guns.modular.warehousemanage.mapper;

import cn.stylefeng.guns.modular.warehousemanage.entity.WmsSortingTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsSortingTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机械手分拣任务表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-23
 */
public interface WmsSortingTaskMapper extends BaseMapper<WmsSortingTask> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    List<WmsSortingTaskResult> customList(@Param("paramCondition") WmsSortingTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsSortingTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    Page<WmsSortingTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsSortingTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsSortingTaskParam paramCondition);

    WmsSortingTaskResult findByTaskStateOne();

    List<WmsSortingTaskResult> findRecentTask();
}
