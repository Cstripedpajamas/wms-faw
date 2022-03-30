package cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.mapper;

import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.entity.WmsCheckDifferenceRecordInfo;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.params.WmsCheckDifferenceRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.result.WmsCheckDifferenceRecordInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 盘点差异记录表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsCheckDifferenceRecordInfoMapper extends BaseMapper<WmsCheckDifferenceRecordInfo> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsCheckDifferenceRecordInfoResult> customList(@Param("paramCondition") WmsCheckDifferenceRecordInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsCheckDifferenceRecordInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsCheckDifferenceRecordInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsCheckDifferenceRecordInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsCheckDifferenceRecordInfoParam paramCondition);

}
