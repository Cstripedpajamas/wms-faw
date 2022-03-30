package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1Stock;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsIntelligentCabinet1StockMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1StockParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1StockResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsIntelligentCabinet1StockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅰ类柜库存信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Service
public class WmsIntelligentCabinet1StockServiceImpl extends ServiceImpl<WmsIntelligentCabinet1StockMapper, WmsIntelligentCabinet1Stock> implements WmsIntelligentCabinet1StockService {

    @Override
    public void add(WmsIntelligentCabinet1StockParam param){
        WmsIntelligentCabinet1Stock entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsIntelligentCabinet1StockParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsIntelligentCabinet1StockParam param){
        WmsIntelligentCabinet1Stock oldEntity = getOldEntity(param);
        WmsIntelligentCabinet1Stock newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsIntelligentCabinet1StockResult findBySpec(WmsIntelligentCabinet1StockParam param){
        return null;
    }

    @Override
    public List<WmsIntelligentCabinet1StockResult> findListBySpec(WmsIntelligentCabinet1StockParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsIntelligentCabinet1StockParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsIntelligentCabinet1StockResult findByLocaSerialNumber(WmsIntelligentCabinet1StockParam param) {
        return this.baseMapper.findByLocaSerialNumber(param);
    }

    private Serializable getKey(WmsIntelligentCabinet1StockParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsIntelligentCabinet1Stock getOldEntity(WmsIntelligentCabinet1StockParam param) {
        return this.getById(getKey(param));
    }

    private WmsIntelligentCabinet1Stock getEntity(WmsIntelligentCabinet1StockParam param) {
        WmsIntelligentCabinet1Stock entity = new WmsIntelligentCabinet1Stock();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
