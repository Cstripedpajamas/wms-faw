package cn.stylefeng.guns.modular.warehousemanage.mapper;

import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 立库-周转箱信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseTurnoverMapper extends BaseMapper<WmsWarehouseTurnover> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseTurnoverResult> customList(@Param("paramCondition") WmsWarehouseTurnoverParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehouseTurnoverParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<WmsWarehouseTurnoverResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseTurnoverParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseTurnoverParam paramCondition);

    WmsWarehouseTurnoverResult findByTurnoverNumber(@Param("code")String code);

    void updateLatticeNumber(@Param("turnoverId")String turnoverId, @Param("number")String number);

    WmsWarehouseTurnoverResult findById(@Param("turnoverId")String turnoverId);

    WmsWarehouseTurnoverResult findByBarCode(@Param("barCode")String barCode);
}
