package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.entity.WmsCabinet2Stock;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.mapper.WmsCabinet2StockMapper;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.params.WmsCabinet2StockParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.model.result.WmsCabinet2StockResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Stock.service.WmsCabinet2StockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅱ类柜库存信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsCabinet2StockServiceImpl extends ServiceImpl<WmsCabinet2StockMapper, WmsCabinet2Stock> implements WmsCabinet2StockService {

    @Override
    public void add(WmsCabinet2StockParam param) {
        WmsCabinet2Stock entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet2StockParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet2StockParam param) {
        WmsCabinet2Stock oldEntity = getOldEntity(param);
        WmsCabinet2Stock newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet2StockResult findBySpec(WmsCabinet2StockParam param) {
        return null;
    }

    @Override
    public List<WmsCabinet2StockResult> findListBySpec(WmsCabinet2StockParam param) {
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet2StockParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public Integer stockIsEnough(String useMaterialTypeId, String useMaterialId, Integer useNumber) {
        return this.baseMapper.stockIsEnough(useMaterialTypeId, useMaterialId, useNumber);
    }

    @Override
    public WmsCabinet2StockResult findNumberInfo(String useMaterialTypeId, String useNumber) {
        return this.baseMapper.findNumberInfo(useMaterialTypeId,useNumber);
    }

    @Override
    public void updateState(String id, String state) {
         this.baseMapper.updateState(id,state);
    }

    @Override
    public void updateStockById(String stockID,String state) {
        this.baseMapper.updateStockById(stockID,state);
    }

    @Override
    public WmsCabinet2StockResult findNullStock() {
      return   this.baseMapper.findNullStock();
    }

    @Override
    public void updateTurnover(String nullStockID,String stockState, String turnoverID) {
        this.baseMapper.updateTurnover(nullStockID,stockState,turnoverID);
    }

    @Override
    public void updateStateByTurnId(String turnoverId, String state) {
        this.baseMapper.updateStateByTurnId(turnoverId,state);
    }

    @Override
    public WmsCabinet2StockResult findByTurnId(String turnoverId) {
        return this.baseMapper.findByTurnId(turnoverId);
    }

    @Override
    public WmsCabinet2StockResult findNullReturnStock() {
        return this.baseMapper.findNullReturnStock();
    }

    @Override
    public void clearTurnMsg(String stockID, String state) {
        this.baseMapper.clearTurnMsg(stockID,state);
    }

    @Override
    public String findStockNumber(String stockID) {
        return this.baseMapper.findStockNumber(stockID);
    }

    @Override
    public List<WmsCabinet2StockResult> findAll() {
        return this.baseMapper.findAll();
    }

    @Override
    public WmsCabinet2StockParam findById(String stockID) {
        return this.baseMapper.findById(stockID);
    }

    @Override
    public void updateNumber(String stockID, String mNumber) {
        this.baseMapper.updateNumber(stockID,mNumber);
    }

    @Override
    public String findIdByLoaclNumber(String locationId) {
        return this.baseMapper.findIdByLoaclNumber(locationId);
    }

    private Serializable getKey(WmsCabinet2StockParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet2Stock getOldEntity(WmsCabinet2StockParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet2Stock getEntity(WmsCabinet2StockParam param) {
        WmsCabinet2Stock entity = new WmsCabinet2Stock();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
