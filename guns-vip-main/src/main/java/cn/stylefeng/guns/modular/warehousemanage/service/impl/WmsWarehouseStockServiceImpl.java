package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseStock;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehouseStockMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseStockParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseStockResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseStockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-仓库库存信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehouseStockServiceImpl extends ServiceImpl<WmsWarehouseStockMapper, WmsWarehouseStock> implements WmsWarehouseStockService {

    @Override
    public void add(WmsWarehouseStockParam param){
        WmsWarehouseStock entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehouseStockParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehouseStockParam param){
        WmsWarehouseStock oldEntity = getOldEntity(param);
        WmsWarehouseStock newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehouseStockResult findBySpec(WmsWarehouseStockParam param){
        return null;
    }

    @Override
    public List<WmsWarehouseStockResult> findListBySpec(WmsWarehouseStockParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehouseStockParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<WmsWarehouseStock> findByJoinOn() {
        return this.baseMapper.findByJoinOn();
    }

    @Override
    public WmsWarehouseStockResult findByTurnoverId(String id) {
        return this.baseMapper.findByTurnoverId(id);
    }

    private Serializable getKey(WmsWarehouseStockParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehouseStock getOldEntity(WmsWarehouseStockParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehouseStock getEntity(WmsWarehouseStockParam param) {
        WmsWarehouseStock entity = new WmsWarehouseStock();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
