package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseOrderReturn;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseOrderReturnParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehousePurchaseOrderReturnResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 立库-采购订单退还信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehousePurchaseOrderReturnService extends IService<WmsWarehousePurchaseOrderReturn> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void add(WmsWarehousePurchaseOrderReturnParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void delete(WmsWarehousePurchaseOrderReturnParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void update(WmsWarehousePurchaseOrderReturnParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    WmsWarehousePurchaseOrderReturnResult findBySpec(WmsWarehousePurchaseOrderReturnParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehousePurchaseOrderReturnResult> findListBySpec(WmsWarehousePurchaseOrderReturnParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsWarehousePurchaseOrderReturnParam param);

}
