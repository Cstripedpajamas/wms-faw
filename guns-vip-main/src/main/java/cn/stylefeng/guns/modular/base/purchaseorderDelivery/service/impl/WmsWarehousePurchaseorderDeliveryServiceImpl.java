package cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.entity.WmsWarehousePurchaseorderDelivery;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.mapper.WmsWarehousePurchaseorderDeliveryMapper;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.params.WmsWarehousePurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.result.WmsWarehousePurchaseorderDeliveryResult;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.WmsWarehousePurchaseorderDeliveryService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 发货单 服务实现类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
@Service
public class WmsWarehousePurchaseorderDeliveryServiceImpl extends ServiceImpl<WmsWarehousePurchaseorderDeliveryMapper, WmsWarehousePurchaseorderDelivery> implements WmsWarehousePurchaseorderDeliveryService {

    @Override
    public void add(WmsWarehousePurchaseorderDeliveryParam param){
        WmsWarehousePurchaseorderDelivery entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehousePurchaseorderDeliveryParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehousePurchaseorderDeliveryParam param){
        WmsWarehousePurchaseorderDelivery oldEntity = getOldEntity(param);
        WmsWarehousePurchaseorderDelivery newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehousePurchaseorderDeliveryResult findBySpec(WmsWarehousePurchaseorderDeliveryParam param){
        return null;
    }

    @Override
    public List<WmsWarehousePurchaseorderDeliveryResult> findListBySpec(WmsWarehousePurchaseorderDeliveryParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehousePurchaseorderDeliveryParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsWarehousePurchaseorderDeliveryParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehousePurchaseorderDelivery getOldEntity(WmsWarehousePurchaseorderDeliveryParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehousePurchaseorderDelivery getEntity(WmsWarehousePurchaseorderDeliveryParam param) {
        WmsWarehousePurchaseorderDelivery entity = new WmsWarehousePurchaseorderDelivery();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public void insertList(List<WmsWarehousePurchaseorderDeliveryParam> param) {
        this.baseMapper.insertList(param);
    }

    public WmsWarehousePurchaseorderDeliveryResult selectPurDocNo(String purDocNo,String itemNo,String purchasereqno) {
        return this.baseMapper.selectPurDocNo(purDocNo, itemNo,purchasereqno);
    }
}
