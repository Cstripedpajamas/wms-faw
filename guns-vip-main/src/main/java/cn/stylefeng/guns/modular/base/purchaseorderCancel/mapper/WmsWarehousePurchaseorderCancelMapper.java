package cn.stylefeng.guns.modular.base.purchaseorderCancel.mapper;

import cn.stylefeng.guns.modular.base.purchaseorderCancel.entity.WmsWarehousePurchaseorderCancel;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.params.WmsWarehousePurchaseorderCancelParam;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.result.WmsWarehousePurchaseorderCancelResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单取消 Mapper 接口
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
public interface WmsWarehousePurchaseorderCancelMapper extends BaseMapper<WmsWarehousePurchaseorderCancel> {

    /**
     * 获取列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    List<WmsWarehousePurchaseorderCancelResult> customList(@Param("paramCondition") WmsWarehousePurchaseorderCancelParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehousePurchaseorderCancelParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    Page<WmsWarehousePurchaseorderCancelResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseorderCancelParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseorderCancelParam paramCondition);

    void insertList(@Param("list")List<WmsWarehousePurchaseorderCancelParam> paramCondition);
}
