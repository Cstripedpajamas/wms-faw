package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1LatticeType;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsIntelligentCabinet1LatticeTypeMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1LatticeTypeParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1LatticeTypeResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsIntelligentCabinet1LatticeTypeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅰ类柜格口类型信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Service
public class WmsIntelligentCabinet1LatticeTypeServiceImpl extends ServiceImpl<WmsIntelligentCabinet1LatticeTypeMapper, WmsIntelligentCabinet1LatticeType> implements WmsIntelligentCabinet1LatticeTypeService {

    @Override
    public void add(WmsIntelligentCabinet1LatticeTypeParam param){
        WmsIntelligentCabinet1LatticeType entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsIntelligentCabinet1LatticeTypeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsIntelligentCabinet1LatticeTypeParam param){
        WmsIntelligentCabinet1LatticeType oldEntity = getOldEntity(param);
        WmsIntelligentCabinet1LatticeType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsIntelligentCabinet1LatticeTypeResult findBySpec(WmsIntelligentCabinet1LatticeTypeParam param){
        return null;
    }

    @Override
    public List<WmsIntelligentCabinet1LatticeTypeResult> findListBySpec(WmsIntelligentCabinet1LatticeTypeParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsIntelligentCabinet1LatticeTypeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsIntelligentCabinet1LatticeTypeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsIntelligentCabinet1LatticeType getOldEntity(WmsIntelligentCabinet1LatticeTypeParam param) {
        return this.getById(getKey(param));
    }

    private WmsIntelligentCabinet1LatticeType getEntity(WmsIntelligentCabinet1LatticeTypeParam param) {
        WmsIntelligentCabinet1LatticeType entity = new WmsIntelligentCabinet1LatticeType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
