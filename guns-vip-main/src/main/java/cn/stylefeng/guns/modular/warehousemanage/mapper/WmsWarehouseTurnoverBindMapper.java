package cn.stylefeng.guns.modular.warehousemanage.mapper;

import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.BatchEnt;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverBindResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 立库-周转箱绑定货物信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseTurnoverBindMapper extends BaseMapper<WmsWarehouseTurnoverBind> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseTurnoverBindResult> customList(@Param("paramCondition") WmsWarehouseTurnoverBindParam paramCondition);
    List<WmsWarehouseTurnoverBindResult> findListTurnover(@Param("paramCondition") WmsWarehouseTurnoverBindParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsWarehouseTurnoverBindParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<WmsWarehouseTurnoverBindResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseTurnoverBindParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsWarehouseTurnoverBindParam paramCondition);

    WmsWarehouseTurnoverBindResult findByTurnoverId(@Param("paramCondition") WmsWarehouseTurnoverBindParam paramCondition);

    Page<WmsWarehouseTurnoverBindResult> findLattice(@Param("page") Page page,@Param("paramCondition")WmsWarehouseTurnoverBindParam param);

    void delByTurnId(@Param("id")Long id);

    WmsWarehouseTurnoverBindResult findBySKU(@Param("materialSku")String materialSku);

    Page<WmsWarehouseTurnoverBindResult> findTurnoverMsg(@Param("page") Page pageContext, @Param("paramCondition") WmsMaterialTypeResult param);

    WmsWarehouseTurnoverBindResult findByMaterial(@Param("materialSerialNumber")String materialSerialNumber);

    BatchEnt findBatch(@Param("materialSku")String materialSku, @Param("number")int number);
}
