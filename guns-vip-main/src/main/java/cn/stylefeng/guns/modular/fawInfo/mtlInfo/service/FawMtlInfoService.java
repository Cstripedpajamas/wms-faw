package cn.stylefeng.guns.modular.fawInfo.mtlInfo.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.entity.FawMtlInfo;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params.FawMtlInfoParam;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.result.FawMtlInfoResult;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * faw物料信息 服务类
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
public interface FawMtlInfoService extends IService<FawMtlInfo> {

    /**
     * 新增
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    void add(FawMtlInfoParam param);

    /**
     * 删除
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    void delete(FawMtlInfoParam param);

    /**
     * 更新
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    void update(FawMtlInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    FawMtlInfoResult findBySpec(FawMtlInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
    List<FawMtlInfoResult> findListBySpec(FawMtlInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fubenhao
     * @Date 2022-03-28
     */
     LayuiPageInfo findPageBySpec(FawMtlInfoParam param);

    void insertListBatch(List<FawMtlInfoParam> param);

}
