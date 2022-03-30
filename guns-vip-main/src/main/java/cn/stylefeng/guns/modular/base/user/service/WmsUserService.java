package cn.stylefeng.guns.modular.base.user.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 人员信息表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsUserService extends IService<WmsUser> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsUserParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsUserParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsUserResult findBySpec(WmsUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsUserResult> findListBySpec(WmsUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsUserParam param);

    WmsUserResult findUserInfo(String serialNumber, String getuPwd);

    WmsUserResult findUserInfo2(String serialNumber, String getuPwd);

    WmsUserResult findUserIdInfo(String staffId);

    WmsUserResult findUserIdInfo2(String staffId);

    void insertListBatch(List<WmsUserParam> param);
}
