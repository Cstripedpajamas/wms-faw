package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.mapper;

import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity.WmsCabinet2UseTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.params.WmsCabinet2UseTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.result.WmsCabinet2UseTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅱ类柜领用任务信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2UseTaskMapper extends BaseMapper<WmsCabinet2UseTask> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2UseTaskResult> customList(@Param("paramCondition") WmsCabinet2UseTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet2UseTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsCabinet2UseTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2UseTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2UseTaskParam paramCondition);

    WmsCabinet2UseTaskResult findById(@Param("id")String id);

    void updateState(@Param("id")String id, @Param("state")String state);

    void updateScropNumber(@Param("taskId")String orderId, @Param("scrapNumber")String scrapCount);

    void updateStockMsgById(@Param("runningId")String runningId, @Param("stockId")String stockId, @Param("locaNumber")String locaNumber, @Param("state")String state);

    Page<WmsCabinet2UseTaskResult> customPageList2(@Param("page") Page page, @Param("paramCondition") WmsCabinet2UseTaskParam param);

}
