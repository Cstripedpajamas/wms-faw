package cn.stylefeng.guns.modular.onetypecabinet.mapper;

import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RepairReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RepairReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1RepairReturnTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Ⅰ类柜维修归还任务信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsCabinet1RepairReturnTaskMapper extends BaseMapper<WmsCabinet1RepairReturnTask> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsCabinet1RepairReturnTaskResult> customList(@Param("paramCondition") WmsCabinet1RepairReturnTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet1RepairReturnTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<WmsCabinet1RepairReturnTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet1RepairReturnTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet1RepairReturnTaskParam paramCondition);

}
