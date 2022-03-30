package cn.stylefeng.guns.modular.statistics.errorrecordinfo.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.entity.WmsErrorRecordInfo;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.params.WmsErrorRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.result.WmsErrorRecordInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 异常信息记录表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsErrorRecordInfoService extends IService<WmsErrorRecordInfo> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsErrorRecordInfoParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsErrorRecordInfoParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsErrorRecordInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsErrorRecordInfoResult findBySpec(WmsErrorRecordInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsErrorRecordInfoResult> findListBySpec(WmsErrorRecordInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsErrorRecordInfoParam param);

}
