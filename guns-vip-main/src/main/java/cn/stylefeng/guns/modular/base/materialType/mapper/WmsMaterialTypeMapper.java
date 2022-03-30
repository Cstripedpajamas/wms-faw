package cn.stylefeng.guns.modular.base.materialType.mapper;

import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params.FawMtlInfoParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 物料类型信息表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsMaterialTypeMapper extends BaseMapper<WmsMaterialType> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsMaterialTypeResult> customList(@Param("paramCondition") WmsMaterialTypeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsMaterialTypeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsMaterialTypeResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsMaterialTypeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsMaterialTypeParam paramCondition);

    WmsMaterialTypeResult findById(@Param("id")String sMaterialTypeId);

    WmsMaterialTypeResult findByMaterialSku(@Param("paramCondition") WmsMaterialTypeParam paramCondition);

    void insertListBatch(@Param("list")List<WmsMaterialTypeParam> paramCondition);
}
