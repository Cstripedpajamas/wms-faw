package cn.stylefeng.guns.modular.warehousemanage.mapper;

import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseStorageTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseStorageTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehousePurchaseStorageTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 立库-采购入库任务信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehousePurchaseStorageTaskMapper extends BaseMapper<WmsWarehousePurchaseStorageTask> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehousePurchaseStorageTaskResult> customList(@Param("paramCondition") WmsWarehousePurchaseStorageTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehousePurchaseStorageTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<WmsWarehousePurchaseStorageTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseStorageTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehousePurchaseStorageTaskParam paramCondition);

}
