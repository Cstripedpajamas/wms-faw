package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.mapper;

import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.params.WmsCabinet2CheckTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.result.WmsCabinet2CheckTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅱ类柜盘点任务信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2CheckTaskMapper extends BaseMapper<WmsCabinet2CheckTask> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2CheckTaskResult> customList(@Param("paramCondition") WmsCabinet2CheckTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet2CheckTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsCabinet2CheckTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2CheckTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2CheckTaskParam paramCondition);

    void addAll(@Param("list")List<WmsCabinet2CheckTaskParam> list);

    List<WmsCabinet2CheckTaskResult> findByTurnoverId(@Param("turnoverID")String turnoverID);

    void updateStateById(@Param("runningId")String runningId, @Param("state")String state);

    Integer isFinishTask(@Param("taskNumber")String taskNumber);

    void updateAllState(@Param("taskNumber")String taskNumber,@Param("state")String state);

    WmsCabinet2CheckTaskResult findById(@Param("id")String runningId);
}
