package cn.stylefeng.guns.modular.statistics.errorrecordinfo.mapper;

import cn.stylefeng.guns.modular.statistics.errorrecordinfo.entity.WmsErrorRecordInfo;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.params.WmsErrorRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.result.WmsErrorRecordInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 异常信息记录表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsErrorRecordInfoMapper extends BaseMapper<WmsErrorRecordInfo> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsErrorRecordInfoResult> customList(@Param("paramCondition") WmsErrorRecordInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsErrorRecordInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsErrorRecordInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsErrorRecordInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsErrorRecordInfoParam paramCondition);

}
