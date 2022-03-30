package cn.stylefeng.guns.modular.warehousemanage.mapper;

import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseOrderReturn;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseOrderReturnParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehousePurchaseOrderReturnResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 立库-采购订单退还信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehousePurchaseOrderReturnMapper extends BaseMapper<WmsWarehousePurchaseOrderReturn> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehousePurchaseOrderReturnResult> customList(@Param("paramCondition") WmsWarehousePurchaseOrderReturnParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehousePurchaseOrderReturnParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<WmsWarehousePurchaseOrderReturnResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseOrderReturnParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseOrderReturnParam paramCondition);

}
