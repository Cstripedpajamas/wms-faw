package cn.stylefeng.guns.modular.fawInfo.purchaseOrder.mapper;

import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.entity.FawPurchaseOrder;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.result.FawPurchaseOrderResult;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * faw采购订单信息表 Mapper 接口
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
public interface FawPurchaseOrderMapper extends BaseMapper<FawPurchaseOrder> {

    /**
     * 获取列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    List<FawPurchaseOrderResult> customList(@Param("paramCondition") FawPurchaseOrderParam paramCondition);

    /**
     * 获取map列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FawPurchaseOrderParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    Page<FawPurchaseOrderResult> customPageList(@Param("page") Page page, @Param("paramCondition") FawPurchaseOrderParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FawPurchaseOrderParam paramCondition);

    void insertListBatch(@Param("list")List<FawPurchaseOrderParam> paramCondition);

}
