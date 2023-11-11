package cn.stylefeng.guns.modular.base.purchaseorderinfo.mapper;

import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购订单信息表 Mapper 接口
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsPurchaseOrderInfoMapper extends BaseMapper<WmsPurchaseOrderInfo> {

    /**
     * 获取列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsPurchaseOrderInfoResult> customList(@Param("paramCondition") WmsPurchaseOrderInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WmsPurchaseOrderInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<WmsPurchaseOrderInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") WmsPurchaseOrderInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WmsPurchaseOrderInfoParam paramCondition);

    void insertListBatch(@Param("list")List<WmsPurchaseOrderInfoParam> paramCondition);

    void updateState(@Param("state")String state,@Param("orderId")String orderId);

    void stopTask();

    IPage customPageList2(@Param("page")Page pageContext,@Param("paramCondition") WmsPurchaseOrderInfoParam param);

    void updatePurdocnoState(@Param("purDocNO")String purDocNO,
    @Param("itemNO")String itemNO,@Param("state")String state);

    void updatePurdocno(@Param("client")String client,@Param("purDocItemNo")String purDocItemNo,
                        @Param("buyListStrDes")String buyListStrDes,@Param("storeLocation")String storeLocation,@Param("mNumber")String mNumber,@Param("purDocNO")String purDocNO, @Param("itemNO")String itemNO,@Param("materialsku")String materialsku, @Param("quantity")String quantity, @Param("arrivaltime")Date arrivaltime, @Param("purchasereqno")String purchasereqno, @Param("updatetime")Date updatetime, @Param("stockbillid")String stockbillid, @Param("statedesc")String statedesc, @Param("arrivalstate")String arrivalstate, @Param("createdby")String createdby, @Param("typeid")String typeid, @Param("type")String type, @Param("materialType")String materialType, @Param("materialName")String materialName, @Param("mUnit")String mUnit,@Param("purNumber")String purNumber);

    WmsPurchaseOrderInfoResult selectPurdocno(@Param("purDocNO")String purDocNO,@Param("itemNO")String itemNO);
}
