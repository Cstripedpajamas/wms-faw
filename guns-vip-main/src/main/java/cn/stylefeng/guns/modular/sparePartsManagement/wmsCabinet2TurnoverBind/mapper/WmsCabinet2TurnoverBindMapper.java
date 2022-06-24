package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.mapper;

import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result.WmsCabinet2TurnoverResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.entity.WmsCabinet2TurnoverBind;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.params.WmsCabinet2TurnoverBindParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResultP;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 周转箱绑定货物信息关联表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2021-11-02
 */
public interface WmsCabinet2TurnoverBindMapper extends BaseMapper<WmsCabinet2TurnoverBind> {

    /**
     * 获取列表
     *
     * @author ll
     * @Date 2021-11-02
     */
    List<WmsCabinet2TurnoverBindResult> customList(@Param("paramCondition") WmsCabinet2TurnoverBindParam paramCondition);

    /**
     * 获取map列表
     *
     * @author ll
     * @Date 2021-11-02
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCabinet2TurnoverBindParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author ll
     * @Date 2021-11-02
     */
    Page<WmsCabinet2TurnoverBindResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2TurnoverBindParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author ll
     * @Date 2021-11-02
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCabinet2TurnoverBindParam paramCondition);

    void delByTurnoverID(String turnoverID);

    WmsCabinet2TurnoverBindResult findNumberInfo(@Param("matterTypeID")String matterTypeID, @Param("matterNumber")String matterNumber);

    WmsCabinet2TurnoverBindResult findByTurnId(@Param("turnoverID")String turnoverID);

    void updateNumber(@Param("turnoverID")String turnoverID,@Param("number")String number);

    WmsCabinet2TurnoverBindResult WmsCabinet2TurnoverBindResult(@Param("turnoverID")String turnoverID);

    List<WmsCabinet2TurnoverBindResultP> findBySku(@Param("materialSku")String materialSku);
}
