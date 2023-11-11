package cn.stylefeng.guns.modular.fawPurchase0rder.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderDelivery;
import cn.stylefeng.guns.modular.fawPurchase0rder.mapper.FawmallPurchaseorderDeliveryMapper;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.result.FawmallPurchaseorderDeliveryResult;
import cn.stylefeng.guns.modular.fawPurchase0rder.service.FawmallPurchaseorderDeliveryService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * faw商城取消采购订单 服务实现类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
@Service
public class FawmallPurchaseorderDeliveryServiceImpl extends ServiceImpl<FawmallPurchaseorderDeliveryMapper, FawmallPurchaseorderDelivery> implements FawmallPurchaseorderDeliveryService {

    @Override
    public void add(FawmallPurchaseorderDeliveryParam param){
        FawmallPurchaseorderDelivery entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FawmallPurchaseorderDeliveryParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FawmallPurchaseorderDeliveryParam param){
        FawmallPurchaseorderDelivery oldEntity = getOldEntity(param);
        FawmallPurchaseorderDelivery newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FawmallPurchaseorderDeliveryResult findBySpec(FawmallPurchaseorderDeliveryParam param){
        return null;
    }

    @Override
    public List<FawmallPurchaseorderDeliveryResult> findListBySpec(FawmallPurchaseorderDeliveryParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(FawmallPurchaseorderDeliveryParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(FawmallPurchaseorderDeliveryParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private FawmallPurchaseorderDelivery getOldEntity(FawmallPurchaseorderDeliveryParam param) {
        return this.getById(getKey(param));
    }

    private FawmallPurchaseorderDelivery getEntity(FawmallPurchaseorderDeliveryParam param) {
        FawmallPurchaseorderDelivery entity = new FawmallPurchaseorderDelivery();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public void insertListBatch(List<FawmallPurchaseorderDeliveryParam> param) {
        this.baseMapper.insertListBatch(param);
    }

}
