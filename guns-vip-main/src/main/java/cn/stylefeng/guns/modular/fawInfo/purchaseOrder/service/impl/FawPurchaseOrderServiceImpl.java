package cn.stylefeng.guns.modular.fawInfo.purchaseOrder.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.entity.FawPurchaseOrder;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.mapper.FawPurchaseOrderMapper;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.result.FawPurchaseOrderResult;
import  cn.stylefeng.guns.modular.fawInfo.purchaseOrder.service.FawPurchaseOrderService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * faw采购订单信息表 服务实现类
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
@Service
public class FawPurchaseOrderServiceImpl extends ServiceImpl<FawPurchaseOrderMapper, FawPurchaseOrder> implements FawPurchaseOrderService {

    @Override
    public void add(FawPurchaseOrderParam param){
        FawPurchaseOrder entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FawPurchaseOrderParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FawPurchaseOrderParam param){
        FawPurchaseOrder oldEntity = getOldEntity(param);
        FawPurchaseOrder newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FawPurchaseOrderResult findBySpec(FawPurchaseOrderParam param){
        return null;
    }

    @Override
    public List<FawPurchaseOrderResult> findListBySpec(FawPurchaseOrderParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(FawPurchaseOrderParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void insertListBatch(List<FawPurchaseOrderParam> param) {
        this.baseMapper.insertListBatch(param);
    }

    private Serializable getKey(FawPurchaseOrderParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private FawPurchaseOrder getOldEntity(FawPurchaseOrderParam param) {
        return this.getById(getKey(param));
    }

    private FawPurchaseOrder getEntity(FawPurchaseOrderParam param) {
        FawPurchaseOrder entity = new FawPurchaseOrder();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
