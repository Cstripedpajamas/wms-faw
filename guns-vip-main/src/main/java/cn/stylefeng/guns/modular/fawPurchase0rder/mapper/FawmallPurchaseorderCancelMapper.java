package cn.stylefeng.guns.modular.fawPurchase0rder.mapper;

import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderCancel;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderCancelParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.result.FawmallPurchaseorderCancelResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * faw商城发货单 Mapper 接口
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
public interface FawmallPurchaseorderCancelMapper extends BaseMapper<FawmallPurchaseorderCancel> {

    /**
     * 获取列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    List<FawmallPurchaseorderCancelResult> customList(@Param("paramCondition") FawmallPurchaseorderCancelParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FawmallPurchaseorderCancelParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    Page<FawmallPurchaseorderCancelResult> customPageList(@Param("page") Page page, @Param("paramCondition") FawmallPurchaseorderCancelParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FawmallPurchaseorderCancelParam paramCondition);

    void insertListBatch(@Param("list")List<FawmallPurchaseorderCancelParam> paramCondition);
}
