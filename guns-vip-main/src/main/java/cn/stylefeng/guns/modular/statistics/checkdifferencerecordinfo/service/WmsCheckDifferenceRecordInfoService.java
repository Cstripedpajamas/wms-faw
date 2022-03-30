package cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.entity.WmsCheckDifferenceRecordInfo;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.params.WmsCheckDifferenceRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.result.WmsCheckDifferenceRecordInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 盘点差异记录表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsCheckDifferenceRecordInfoService extends IService<WmsCheckDifferenceRecordInfo> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsCheckDifferenceRecordInfoParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsCheckDifferenceRecordInfoParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsCheckDifferenceRecordInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsCheckDifferenceRecordInfoResult findBySpec(WmsCheckDifferenceRecordInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsCheckDifferenceRecordInfoResult> findListBySpec(WmsCheckDifferenceRecordInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCheckDifferenceRecordInfoParam param);

}
