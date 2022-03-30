package cn.stylefeng.guns.modular.statistics.sparepartsscrap.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.entity.WmsSparePartsScrap;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.params.WmsSparePartsScrapParam;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.result.WmsSparePartsScrapResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 备品备件报废信息汇总表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsSparePartsScrapService extends IService<WmsSparePartsScrap> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsSparePartsScrapParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsSparePartsScrapParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsSparePartsScrapParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsSparePartsScrapResult findBySpec(WmsSparePartsScrapParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsSparePartsScrapResult> findListBySpec(WmsSparePartsScrapParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsSparePartsScrapParam param);

}
