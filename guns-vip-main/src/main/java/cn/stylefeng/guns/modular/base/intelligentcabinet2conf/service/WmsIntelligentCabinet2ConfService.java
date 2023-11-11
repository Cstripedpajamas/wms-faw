package cn.stylefeng.guns.modular.base.intelligentcabinet2conf.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.entity.WmsIntelligentCabinet2Conf;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.params.WmsIntelligentCabinet2ConfParam;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.result.WmsIntelligentCabinet2ConfResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅱ类柜物料补货阈值配置表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsIntelligentCabinet2ConfService extends IService<WmsIntelligentCabinet2Conf> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsIntelligentCabinet2ConfParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsIntelligentCabinet2ConfParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsIntelligentCabinet2ConfParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsIntelligentCabinet2ConfResult findBySpec(WmsIntelligentCabinet2ConfParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsIntelligentCabinet2ConfResult> findListBySpec(WmsIntelligentCabinet2ConfParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    LayuiPageInfo findPageBySpec(WmsIntelligentCabinet2ConfParam param);

    WmsIntelligentCabinet2ConfResult findBySku(String materialTypeId);
}
