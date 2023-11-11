package cn.stylefeng.guns.modular.base.purchaseorderDelivery.mapper;

import cn.stylefeng.guns.modular.base.purchaseorderDelivery.entity.WmsWarehousePurchaseorderDelivery;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.params.WmsWarehousePurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.result.WmsWarehousePurchaseorderDeliveryResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发货单 Mapper 接口
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
public interface WmsWarehousePurchaseorderDeliveryMapper extends BaseMapper<WmsWarehousePurchaseorderDelivery> {

    /**
     * 获取列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    List<WmsWarehousePurchaseorderDeliveryResult> customList(@Param("paramCondition") WmsWarehousePurchaseorderDeliveryParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehousePurchaseorderDeliveryParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    Page<WmsWarehousePurchaseorderDeliveryResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseorderDeliveryParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseorderDeliveryParam paramCondition);


    void insertList(@Param("list")List<WmsWarehousePurchaseorderDeliveryParam> paramCondition);

    WmsWarehousePurchaseorderDeliveryResult selectPurDocNo(@Param("purDocNo")String purDocNo,@Param("itemNo")String itemNo,@Param("purchasereqno")String purchasereqno);
}
