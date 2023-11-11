package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseToolUseTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseToolUseTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseToolUseTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 立库-工具领用任务信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseToolUseTaskService extends IService<WmsWarehouseToolUseTask> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void add(WmsWarehouseToolUseTaskParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void delete(WmsWarehouseToolUseTaskParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void update(WmsWarehouseToolUseTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    WmsWarehouseToolUseTaskResult findBySpec(WmsWarehouseToolUseTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseToolUseTaskResult> findListBySpec(WmsWarehouseToolUseTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsWarehouseToolUseTaskParam param);

    /**
     *  查询是否存在 为做任务
     */
    List<WmsWarehouseToolUseTaskResult> findByTaskStateOfOperator(WmsWarehouseToolUseTaskParam param);

    void updateByTaskNumber(String toolUseTaskNumber);

    WmsWarehouseToolUseTaskResult findByTaskNumber(String toolUseTaskNumber);

    WmsWarehouseToolUseTaskResult findBySortingTask(String sortingTask);

    void updatePickNumber(String taskNumber, String pickNumber);

    WmsWarehouseToolUseTaskResult inExecution();
}
