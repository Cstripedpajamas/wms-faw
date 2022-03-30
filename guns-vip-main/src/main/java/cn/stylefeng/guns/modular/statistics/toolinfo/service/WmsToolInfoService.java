package cn.stylefeng.guns.modular.statistics.toolinfo.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.toolinfo.entity.WmsToolInfo;
import cn.stylefeng.guns.modular.statistics.toolinfo.model.params.WmsToolInfoParam;
import cn.stylefeng.guns.modular.statistics.toolinfo.model.result.WmsToolInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工具信息汇总表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsToolInfoService extends IService<WmsToolInfo> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsToolInfoParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsToolInfoParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsToolInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsToolInfoResult findBySpec(WmsToolInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsToolInfoResult> findListBySpec(WmsToolInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsToolInfoParam param);

}
