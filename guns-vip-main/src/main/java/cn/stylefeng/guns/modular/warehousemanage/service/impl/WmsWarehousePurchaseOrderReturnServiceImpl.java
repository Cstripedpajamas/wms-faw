package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseOrderReturn;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehousePurchaseOrderReturnMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseOrderReturnParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehousePurchaseOrderReturnResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehousePurchaseOrderReturnService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-采购订单退还信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehousePurchaseOrderReturnServiceImpl extends ServiceImpl<WmsWarehousePurchaseOrderReturnMapper, WmsWarehousePurchaseOrderReturn> implements WmsWarehousePurchaseOrderReturnService {

    @Override
    public void add(WmsWarehousePurchaseOrderReturnParam param){
        WmsWarehousePurchaseOrderReturn entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehousePurchaseOrderReturnParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehousePurchaseOrderReturnParam param){
        WmsWarehousePurchaseOrderReturn oldEntity = getOldEntity(param);
        WmsWarehousePurchaseOrderReturn newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehousePurchaseOrderReturnResult findBySpec(WmsWarehousePurchaseOrderReturnParam param){
        return null;
    }

    @Override
    public List<WmsWarehousePurchaseOrderReturnResult> findListBySpec(WmsWarehousePurchaseOrderReturnParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehousePurchaseOrderReturnParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsWarehousePurchaseOrderReturnParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehousePurchaseOrderReturn getOldEntity(WmsWarehousePurchaseOrderReturnParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehousePurchaseOrderReturn getEntity(WmsWarehousePurchaseOrderReturnParam param) {
        WmsWarehousePurchaseOrderReturn entity = new WmsWarehousePurchaseOrderReturn();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
