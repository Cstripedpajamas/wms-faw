package cn.stylefeng.guns.modular.base.materialspareparts.mapper;

import cn.stylefeng.guns.modular.base.materialspareparts.entity.WmsMaterialSpareParts;
import cn.stylefeng.guns.modular.base.materialspareparts.model.params.WmsMaterialSparePartsParam;
import cn.stylefeng.guns.modular.base.materialspareparts.model.result.WmsMaterialSparePartsResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 备品备件-物料信息表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsMaterialSparePartsMapper extends BaseMapper<WmsMaterialSpareParts> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsMaterialSparePartsResult> customList(@Param("paramCondition") WmsMaterialSparePartsParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsMaterialSparePartsParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsMaterialSparePartsResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsMaterialSparePartsParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsMaterialSparePartsParam paramCondition);

    List<WmsMaterialSparePartsResult> findAll();

    List<WmsMaterialSparePartsResult> findAllByMaterialTypeId(@Param("paramCondition") WmsMaterialSparePartsParam paramCondition);

    WmsMaterialSparePartsResult findById(@Param("sparsId")String sparsId);
}
