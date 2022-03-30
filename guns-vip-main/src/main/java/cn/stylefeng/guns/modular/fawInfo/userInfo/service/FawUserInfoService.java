package cn.stylefeng.guns.modular.fawInfo.userInfo.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.entity.FawUserInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.result.FawUserInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * faw基本人员信息表 服务类
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-14
 */
public interface FawUserInfoService extends IService<FawUserInfo> {

    /**
     * 新增
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    void add(FawUserInfoParam param);

    /**
     * 删除
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    void delete(FawUserInfoParam param);

    /**
     * 更新
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    void update(FawUserInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    FawUserInfoResult findBySpec(FawUserInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
    List<FawUserInfoResult> findListBySpec(FawUserInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-14
     */
     LayuiPageInfo findPageBySpec(FawUserInfoParam param);

    void insertListBatch(List<FawUserInfoParam> param);

}
