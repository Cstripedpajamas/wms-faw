package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseStorageTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseStorageTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehousePurchaseStorageTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 立库-采购入库任务信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehousePurchaseStorageTaskService extends IService<WmsWarehousePurchaseStorageTask> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void add(WmsWarehousePurchaseStorageTaskParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void delete(WmsWarehousePurchaseStorageTaskParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void update(WmsWarehousePurchaseStorageTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    WmsWarehousePurchaseStorageTaskResult findBySpec(WmsWarehousePurchaseStorageTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehousePurchaseStorageTaskResult> findListBySpec(WmsWarehousePurchaseStorageTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsWarehousePurchaseStorageTaskParam param);

}
