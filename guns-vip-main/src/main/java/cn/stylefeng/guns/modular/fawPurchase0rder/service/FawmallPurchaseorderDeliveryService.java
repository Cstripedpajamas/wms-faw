package cn.stylefeng.guns.modular.fawPurchase0rder.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderDelivery;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.result.FawmallPurchaseorderDeliveryResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * faw商城取消采购订单 服务类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
public interface FawmallPurchaseorderDeliveryService extends IService<FawmallPurchaseorderDelivery> {

    /**
     * 新增
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    void add(FawmallPurchaseorderDeliveryParam param);

    /**
     * 删除
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    void delete(FawmallPurchaseorderDeliveryParam param);

    /**
     * 更新
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    void update(FawmallPurchaseorderDeliveryParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    FawmallPurchaseorderDeliveryResult findBySpec(FawmallPurchaseorderDeliveryParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    List<FawmallPurchaseorderDeliveryResult> findListBySpec(FawmallPurchaseorderDeliveryParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
     LayuiPageInfo findPageBySpec(FawmallPurchaseorderDeliveryParam param);

    void insertListBatch(List<FawmallPurchaseorderDeliveryParam> param);
}
