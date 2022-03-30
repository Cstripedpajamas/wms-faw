package cn.stylefeng.guns.modular.fawInfo.mtlInfo.mapper;

import cn.stylefeng.guns.modular.fawInfo.mtlInfo.entity.FawMtlInfo;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params.FawMtlInfoParam;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.result.FawMtlInfoResult;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * faw物料信息 Mapper 接口
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
public interface FawMtlInfoMapper extends BaseMapper<FawMtlInfo> {

    /**
     * 获取列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    List<FawMtlInfoResult> customList(@Param("paramCondition") FawMtlInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FawMtlInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    Page<FawMtlInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") FawMtlInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FawMtlInfoParam paramCondition);

    void insertListBatch(@Param("list")List<FawMtlInfoParam> paramCondition);

}
