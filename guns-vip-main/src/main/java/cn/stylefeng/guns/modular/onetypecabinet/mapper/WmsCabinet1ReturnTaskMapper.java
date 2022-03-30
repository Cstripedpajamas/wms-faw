package cn.stylefeng.guns.modular.onetypecabinet.mapper;

import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1ReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1ReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1ReturnTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅰ类柜归还任务信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsCabinet1ReturnTaskMapper extends BaseMapper<WmsCabinet1ReturnTask> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsCabinet1ReturnTaskResult> customList(@Param("paramCondition") WmsCabinet1ReturnTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet1ReturnTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<WmsCabinet1ReturnTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet1ReturnTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet1ReturnTaskParam paramCondition);

}
