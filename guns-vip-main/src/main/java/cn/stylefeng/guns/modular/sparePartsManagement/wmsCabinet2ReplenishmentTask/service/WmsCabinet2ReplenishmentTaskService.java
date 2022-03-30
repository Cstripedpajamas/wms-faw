package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.entity.WmsCabinet2ReplenishmentTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.params.WmsCabinet2ReplenishmentTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.result.WmsCabinet2ReplenishmentTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅱ类柜补货任务信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2ReplenishmentTaskService extends IService<WmsCabinet2ReplenishmentTask> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsCabinet2ReplenishmentTaskParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsCabinet2ReplenishmentTaskParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsCabinet2ReplenishmentTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsCabinet2ReplenishmentTaskResult findBySpec(WmsCabinet2ReplenishmentTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2ReplenishmentTaskResult> findListBySpec(WmsCabinet2ReplenishmentTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet2ReplenishmentTaskParam param);

    WmsCabinet2ReplenishmentTaskResult findByTaskNumber(String taskNumber);

    void updateStateById(String id, String state);

    WmsCabinet2ReplenishmentTaskParam findById(String runningId);
}
