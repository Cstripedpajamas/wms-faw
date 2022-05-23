package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsSortingTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsSortingTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 机械手分拣任务表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-23
 */
public interface WmsSortingTaskService extends IService<WmsSortingTask> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    void add(WmsSortingTaskParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    void delete(WmsSortingTaskParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    void update(WmsSortingTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    WmsSortingTaskResult findBySpec(WmsSortingTaskParam param);

    WmsSortingTaskResult findByTaskStateOne();

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-23
     */
    List<WmsSortingTaskResult> findListBySpec(WmsSortingTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-23
     */
     LayuiPageInfo findPageBySpec(WmsSortingTaskParam param);

    List<WmsSortingTaskResult> findRecentTask();
}
