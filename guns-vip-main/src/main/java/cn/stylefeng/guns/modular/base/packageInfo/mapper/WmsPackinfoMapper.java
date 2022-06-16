package cn.stylefeng.guns.modular.base.packageInfo.mapper;

import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.base.packageInfo.model.params.WmsPackinfoParam;
import cn.stylefeng.guns.modular.base.packageInfo.model.result.WmsPackinfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 包装信息表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-12-14
 */
public interface WmsPackinfoMapper extends BaseMapper<WmsPackinfo> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-12-14
     */
    List<WmsPackinfoResult> customList(@Param("paramCondition") WmsPackinfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-12-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsPackinfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-12-14
     */
    Page<WmsPackinfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsPackinfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-12-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsPackinfoParam paramCondition);

    WmsPackinfo findByMaterialTypeId(@Param("id")String id);
}
