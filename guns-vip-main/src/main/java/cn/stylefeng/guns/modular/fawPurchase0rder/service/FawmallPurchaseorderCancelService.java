package cn.stylefeng.guns.modular.fawPurchase0rder.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderCancel;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderCancelParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.result.FawmallPurchaseorderCancelResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * faw商城发货单 服务类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
public interface FawmallPurchaseorderCancelService extends IService<FawmallPurchaseorderCancel> {

    /**
     * 新增
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    void add(FawmallPurchaseorderCancelParam param);

    /**
     * 删除
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    void delete(FawmallPurchaseorderCancelParam param);

    /**
     * 更新
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    void update(FawmallPurchaseorderCancelParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    FawmallPurchaseorderCancelResult findBySpec(FawmallPurchaseorderCancelParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
    List<FawmallPurchaseorderCancelResult> findListBySpec(FawmallPurchaseorderCancelParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 邢玉祥
     * @Date 2023-03-21
     */
     LayuiPageInfo findPageBySpec(FawmallPurchaseorderCancelParam param);

    void insertListBatch(List<FawmallPurchaseorderCancelParam> param);
}
