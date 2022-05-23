package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseReplenishmentTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseReplenishmentTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseReplenishmentTaskResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 立库-备品备件补货任务信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseReplenishmentTaskService extends IService<WmsWarehouseReplenishmentTask> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void add(WmsWarehouseReplenishmentTaskParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void delete(WmsWarehouseReplenishmentTaskParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void update(WmsWarehouseReplenishmentTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    WmsWarehouseReplenishmentTaskResult findBySpec(WmsWarehouseReplenishmentTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseReplenishmentTaskResult> findListBySpec(WmsWarehouseReplenishmentTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsWarehouseReplenishmentTaskParam param);

    WmsWarehouseReplenishmentTaskResult findByTaskNumber(String taskNumber);

    void updatePickNumber(String taskNumber, String pickNumber);

    void stopTask();

    LayuiPageInfo findList(WmsWarehouseReplenishmentTaskParam param);

    WmsWarehouseReplenishmentTaskResult inExecution();
}
