package cn.stylefeng.guns.modular.onetypecabinet.mapper;

import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1CheckTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1CheckTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1CheckTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅰ类柜盘点任务信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsCabinet1CheckTaskMapper extends BaseMapper<WmsCabinet1CheckTask> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsCabinet1CheckTaskResult> customList(@Param("paramCondition") WmsCabinet1CheckTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet1CheckTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<WmsCabinet1CheckTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet1CheckTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet1CheckTaskParam paramCondition);

}
