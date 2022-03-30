package cn.stylefeng.guns.modular.statistics.sparepartsscrap.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.entity.WmsSparePartsScrap;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.mapper.WmsSparePartsScrapMapper;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.params.WmsSparePartsScrapParam;
import cn.stylefeng.guns.modular.statistics.sparepartsscrap.model.result.WmsSparePartsScrapResult;
import  cn.stylefeng.guns.modular.statistics.sparepartsscrap.service.WmsSparePartsScrapService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 备品备件报废信息汇总表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsSparePartsScrapServiceImpl extends ServiceImpl<WmsSparePartsScrapMapper, WmsSparePartsScrap> implements WmsSparePartsScrapService {

    @Override
    public void add(WmsSparePartsScrapParam param){
        WmsSparePartsScrap entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsSparePartsScrapParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsSparePartsScrapParam param){
        WmsSparePartsScrap oldEntity = getOldEntity(param);
        WmsSparePartsScrap newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsSparePartsScrapResult findBySpec(WmsSparePartsScrapParam param){
        return null;
    }

    @Override
    public List<WmsSparePartsScrapResult> findListBySpec(WmsSparePartsScrapParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsSparePartsScrapParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsSparePartsScrapParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsSparePartsScrap getOldEntity(WmsSparePartsScrapParam param) {
        return this.getById(getKey(param));
    }

    private WmsSparePartsScrap getEntity(WmsSparePartsScrapParam param) {
        WmsSparePartsScrap entity = new WmsSparePartsScrap();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
