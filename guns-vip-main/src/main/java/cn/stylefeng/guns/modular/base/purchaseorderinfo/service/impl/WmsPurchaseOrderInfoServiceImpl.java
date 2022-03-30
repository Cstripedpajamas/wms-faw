package cn.stylefeng.guns.modular.base.purchaseorderinfo.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.mapper.WmsPurchaseOrderInfoMapper;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import  cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 采购订单信息表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsPurchaseOrderInfoServiceImpl extends ServiceImpl<WmsPurchaseOrderInfoMapper, WmsPurchaseOrderInfo> implements WmsPurchaseOrderInfoService {

    @Override
    public void add(WmsPurchaseOrderInfoParam param){
        WmsPurchaseOrderInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsPurchaseOrderInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsPurchaseOrderInfoParam param){
        WmsPurchaseOrderInfo oldEntity = getOldEntity(param);
        WmsPurchaseOrderInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsPurchaseOrderInfoResult findBySpec(WmsPurchaseOrderInfoParam param){
        return null;
    }

    @Override
    public List<WmsPurchaseOrderInfoResult> findListBySpec(WmsPurchaseOrderInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsPurchaseOrderInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void insertListBatch(List<WmsPurchaseOrderInfoParam> param) {
        this.baseMapper.insertListBatch(param);
    }

    private Serializable getKey(WmsPurchaseOrderInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsPurchaseOrderInfo getOldEntity(WmsPurchaseOrderInfoParam param) {
        return this.getById(getKey(param));
    }

    private WmsPurchaseOrderInfo getEntity(WmsPurchaseOrderInfoParam param) {
        WmsPurchaseOrderInfo entity = new WmsPurchaseOrderInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
