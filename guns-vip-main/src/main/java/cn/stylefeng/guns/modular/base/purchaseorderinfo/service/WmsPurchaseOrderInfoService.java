package cn.stylefeng.guns.modular.base.purchaseorderinfo.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 采购订单信息表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsPurchaseOrderInfoService extends IService<WmsPurchaseOrderInfo> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsPurchaseOrderInfoParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsPurchaseOrderInfoParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsPurchaseOrderInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsPurchaseOrderInfoResult findBySpec(WmsPurchaseOrderInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsPurchaseOrderInfoResult> findListBySpec(WmsPurchaseOrderInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsPurchaseOrderInfoParam param);

    void insertListBatch(List<WmsPurchaseOrderInfoParam> param);

}
