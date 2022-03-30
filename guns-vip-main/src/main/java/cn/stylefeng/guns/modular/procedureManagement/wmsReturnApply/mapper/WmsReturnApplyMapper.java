package cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.mapper;

import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity.WmsReturnApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params.WmsReturnApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.result.WmsReturnApplyResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 归还申请信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsReturnApplyMapper extends BaseMapper<WmsReturnApply> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsReturnApplyResult> customList(@Param("paramCondition") WmsReturnApplyParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsReturnApplyParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<WmsReturnApplyResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsReturnApplyParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsReturnApplyParam paramCondition);

}
