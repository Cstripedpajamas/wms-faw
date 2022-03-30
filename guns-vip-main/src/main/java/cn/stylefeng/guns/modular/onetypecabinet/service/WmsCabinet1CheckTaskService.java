package cn.stylefeng.guns.modular.onetypecabinet.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1CheckTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1CheckTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1CheckTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅰ类柜盘点任务信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsCabinet1CheckTaskService extends IService<WmsCabinet1CheckTask> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void add(WmsCabinet1CheckTaskParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void delete(WmsCabinet1CheckTaskParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void update(WmsCabinet1CheckTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    WmsCabinet1CheckTaskResult findBySpec(WmsCabinet1CheckTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsCabinet1CheckTaskResult> findListBySpec(WmsCabinet1CheckTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet1CheckTaskParam param);

}
