package cn.stylefeng.guns.modular.fawInfo.purchaseOrder.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.entity.FawPurchaseOrder;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.result.FawPurchaseOrderResult;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * faw采购订单信息表 服务类
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
public interface FawPurchaseOrderService extends IService<FawPurchaseOrder> {

    /**
     * 新增
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    void add(FawPurchaseOrderParam param);

    /**
     * 删除
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    void delete(FawPurchaseOrderParam param);

    /**
     * 更新
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    void update(FawPurchaseOrderParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    FawPurchaseOrderResult findBySpec(FawPurchaseOrderParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    List<FawPurchaseOrderResult> findListBySpec(FawPurchaseOrderParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
     LayuiPageInfo findPageBySpec(FawPurchaseOrderParam param);

    void insertListBatch(List<FawPurchaseOrderParam> param);

}
