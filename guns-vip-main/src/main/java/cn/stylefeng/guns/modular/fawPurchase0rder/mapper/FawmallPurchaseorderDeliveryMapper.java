package cn.stylefeng.guns.modular.fawPurchase0rder.mapper;

import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderDelivery;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.result.FawmallPurchaseorderDeliveryResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * faw商城取消采购订单 Mapper 接口
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
public interface FawmallPurchaseorderDeliveryMapper extends BaseMapper<FawmallPurchaseorderDelivery> {

    /**
     * 获取列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    List<FawmallPurchaseorderDeliveryResult> customList(@Param("paramCondition") FawmallPurchaseorderDeliveryParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FawmallPurchaseorderDeliveryParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    Page<FawmallPurchaseorderDeliveryResult> customPageList(@Param("page") Page page, @Param("paramCondition") FawmallPurchaseorderDeliveryParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FawmallPurchaseorderDeliveryParam paramCondition);

    void insertListBatch(@Param("list")List<FawmallPurchaseorderDeliveryParam> paramCondition);
}
