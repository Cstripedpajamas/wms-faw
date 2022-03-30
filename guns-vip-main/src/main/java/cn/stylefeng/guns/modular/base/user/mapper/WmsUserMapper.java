package cn.stylefeng.guns.modular.base.user.mapper;

import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人员信息表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsUserMapper extends BaseMapper<WmsUser> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsUserResult> customList(@Param("paramCondition") WmsUserParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsUserParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsUserResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsUserParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsUserParam paramCondition);

    WmsUserResult findUserInfo(@Param("number")String serialNumber, @Param("pwd")String getuPwd);

    WmsUserResult findUserInfo2(@Param("number")String serialNumber, @Param("pwd")String getuPwd);

    WmsUserResult findUserIdInfo(@Param("staffId")String staffId);

    WmsUserResult findUserIdInfo2(@Param("staffId")String staffId);

    void insertListBatch(@Param("list")List<WmsUserParam> paramCondition);
}
