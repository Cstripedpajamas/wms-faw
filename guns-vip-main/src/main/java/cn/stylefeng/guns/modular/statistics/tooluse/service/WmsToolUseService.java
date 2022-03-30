package cn.stylefeng.guns.modular.statistics.tooluse.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.model.params.WmsToolUseParam;
import cn.stylefeng.guns.modular.statistics.tooluse.model.result.WmsToolUseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工具领用信息表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsToolUseService extends IService<WmsToolUse> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsToolUseParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsToolUseParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsToolUseParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsToolUseResult findBySpec(WmsToolUseParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsToolUseResult> findListBySpec(WmsToolUseParam param);

    List<WmsToolUseResult> findListBySpecA(WmsToolUseParam param);
    List<WmsToolUseResult> findListBySpecB(WmsToolUseParam param);
    List<WmsToolUseResult> findListBySpecC(WmsToolUseParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsToolUseParam param);

}
