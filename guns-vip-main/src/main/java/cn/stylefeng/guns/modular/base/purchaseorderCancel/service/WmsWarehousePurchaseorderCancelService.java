package cn.stylefeng.guns.modular.base.purchaseorderCancel.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.entity.WmsWarehousePurchaseorderCancel;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.params.WmsWarehousePurchaseorderCancelParam;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.result.WmsWarehousePurchaseorderCancelResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单取消 服务类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
public interface WmsWarehousePurchaseorderCancelService extends IService<WmsWarehousePurchaseorderCancel> {

    /**
     * 新增
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    void add(WmsWarehousePurchaseorderCancelParam param);

    /**
     * 删除
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    void delete(WmsWarehousePurchaseorderCancelParam param);

    /**
     * 更新
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    void update(WmsWarehousePurchaseorderCancelParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    WmsWarehousePurchaseorderCancelResult findBySpec(WmsWarehousePurchaseorderCancelParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
    List<WmsWarehousePurchaseorderCancelResult> findListBySpec(WmsWarehousePurchaseorderCancelParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-20
     */
     LayuiPageInfo findPageBySpec(WmsWarehousePurchaseorderCancelParam param);

    void insertList(List<WmsWarehousePurchaseorderCancelParam> param);

}
