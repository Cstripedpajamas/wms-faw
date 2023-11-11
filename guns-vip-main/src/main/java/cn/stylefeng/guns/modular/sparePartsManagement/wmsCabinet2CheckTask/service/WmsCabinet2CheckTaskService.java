package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.params.WmsCabinet2CheckTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.result.WmsCabinet2CheckTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅱ类柜盘点任务信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2CheckTaskService extends IService<WmsCabinet2CheckTask> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsCabinet2CheckTaskParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsCabinet2CheckTaskParam param);


    void deleteVue();
    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsCabinet2CheckTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsCabinet2CheckTaskResult findBySpec(WmsCabinet2CheckTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2CheckTaskResult> findListBySpec(WmsCabinet2CheckTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet2CheckTaskParam param);

    void addAll(List<WmsCabinet2CheckTaskParam> list);

    List<WmsCabinet2CheckTaskResult> findByTurnoverId(String turnoverID);

    void updateStateById(String runningId, String state);

    Integer isFinishTask(String taskNumber);

    void updateAllState(String taskNumber,String state);

    WmsCabinet2CheckTaskResult findById(String runningId);
}
