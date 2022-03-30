package cn.stylefeng.guns.modular.warehousemanage.mapper;

import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskOut;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskOutParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTaskOutResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 立库-仓库任务管理信息表-出仓 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseTaskOutMapper extends BaseMapper<WmsWarehouseTaskOut> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseTaskOutResult> customList(@Param("paramCondition") WmsWarehouseTaskOutParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehouseTaskOutParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<WmsWarehouseTaskOutResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseTaskOutParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseTaskOutParam paramCondition);

}
