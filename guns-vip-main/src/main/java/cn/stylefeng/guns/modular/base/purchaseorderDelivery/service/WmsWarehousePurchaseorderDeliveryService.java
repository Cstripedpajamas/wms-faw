package cn.stylefeng.guns.modular.base.purchaseorderDelivery.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.entity.WmsWarehousePurchaseorderDelivery;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.params.WmsWarehousePurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.result.WmsWarehousePurchaseorderDeliveryResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 发货单 服务类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
public interface WmsWarehousePurchaseorderDeliveryService extends IService<WmsWarehousePurchaseorderDelivery> {

    /**
     * 新增
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    void add(WmsWarehousePurchaseorderDeliveryParam param);

    /**
     * 删除
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    void delete(WmsWarehousePurchaseorderDeliveryParam param);

    /**
     * 更新
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    void update(WmsWarehousePurchaseorderDeliveryParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    WmsWarehousePurchaseorderDeliveryResult findBySpec(WmsWarehousePurchaseorderDeliveryParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    List<WmsWarehousePurchaseorderDeliveryResult> findListBySpec(WmsWarehousePurchaseorderDeliveryParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
     LayuiPageInfo findPageBySpec(WmsWarehousePurchaseorderDeliveryParam param);

    void insertList(List<WmsWarehousePurchaseorderDeliveryParam> param);

    WmsWarehousePurchaseorderDeliveryResult selectPurDocNo (String purDocNo,String itemNo,String purchasereqno);

}
