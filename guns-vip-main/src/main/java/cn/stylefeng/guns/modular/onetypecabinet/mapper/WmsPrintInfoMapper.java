package cn.stylefeng.guns.modular.onetypecabinet.mapper;

import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsPrintInfo;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsPrintInfoParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsPrintInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 条码打印信息表 Mapper 接口
 * </p>
 *
 * @author liwenya
 * @since 2021-11-24
 */
public interface WmsPrintInfoMapper extends BaseMapper<WmsPrintInfo> {

    /**
     * 获取列表
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    List<WmsPrintInfoResult> customList(@Param("paramCondition") WmsPrintInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsPrintInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    Page<WmsPrintInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsPrintInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsPrintInfoParam paramCondition);

    WmsPrintInfoResult findLastCode();

    void insertMatch(@Param("list")List<WmsPrintInfo> wmsPrintInfos);
}
