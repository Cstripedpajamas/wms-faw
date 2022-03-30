package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.mapper;

import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.result.WmsUseApplyResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 领用申请信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsUseApplyMapper extends BaseMapper<WmsUseApply> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsUseApplyResult> customList(@Param("paramCondition") WmsUseApplyParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsUseApplyParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsUseApplyResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsUseApplyParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsUseApplyParam paramCondition);

    WmsUseApplyResult findById(@Param("id")String useRequestId);
}
