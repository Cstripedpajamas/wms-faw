package cn.stylefeng.guns.modular.warehousemanage.mapper;

import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseReplenishmentTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseReplenishmentTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseReplenishmentTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 立库-备品备件补货任务信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseReplenishmentTaskMapper extends BaseMapper<WmsWarehouseReplenishmentTask> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseReplenishmentTaskResult> customList(@Param("paramCondition") WmsWarehouseReplenishmentTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehouseReplenishmentTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<WmsWarehouseReplenishmentTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseReplenishmentTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseReplenishmentTaskParam paramCondition);

    WmsWarehouseReplenishmentTaskResult findByTaskNumber(@Param("taskNumber")String taskNumber);

    void updatePickNumber(@Param("taskNumber")String taskNumber, @Param("pickNumber")String pickNumber);
}
