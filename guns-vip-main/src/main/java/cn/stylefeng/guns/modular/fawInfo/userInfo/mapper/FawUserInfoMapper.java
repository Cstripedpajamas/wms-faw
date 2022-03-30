package cn.stylefeng.guns.modular.fawInfo.userInfo.mapper;

import cn.stylefeng.guns.modular.fawInfo.userInfo.entity.FawUserInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.result.FawUserInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * faw基本人员信息表 Mapper 接口
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-14
 */
public interface FawUserInfoMapper extends BaseMapper<FawUserInfo> {

    /**
     * 获取列表
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    List<FawUserInfoResult> customList(@Param("paramCondition") FawUserInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FawUserInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    Page<FawUserInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") FawUserInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FawUserInfoParam paramCondition);

    void insertListBatch(@Param("list")List<FawUserInfoParam> paramCondition);

}
