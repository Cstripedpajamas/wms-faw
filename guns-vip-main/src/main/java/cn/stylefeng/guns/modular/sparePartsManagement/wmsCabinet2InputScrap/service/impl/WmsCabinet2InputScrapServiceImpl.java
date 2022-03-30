package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.entity.WmsCabinet2InputScrap;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.mapper.WmsCabinet2InputScrapMapper;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.params.WmsCabinet2InputScrapParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.result.WmsCabinet2InputScrapResult;
import  cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.service.WmsCabinet2InputScrapService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅱ类柜投入报废信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsCabinet2InputScrapServiceImpl extends ServiceImpl<WmsCabinet2InputScrapMapper, WmsCabinet2InputScrap> implements WmsCabinet2InputScrapService {

    @Override
    public void add(WmsCabinet2InputScrapParam param){
        WmsCabinet2InputScrap entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet2InputScrapParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet2InputScrapParam param){
        WmsCabinet2InputScrap oldEntity = getOldEntity(param);
        WmsCabinet2InputScrap newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet2InputScrapResult findBySpec(WmsCabinet2InputScrapParam param){
        return null;
    }

    @Override
    public List<WmsCabinet2InputScrapResult> findListBySpec(WmsCabinet2InputScrapParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet2InputScrapParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCabinet2InputScrapParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet2InputScrap getOldEntity(WmsCabinet2InputScrapParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet2InputScrap getEntity(WmsCabinet2InputScrapParam param) {
        WmsCabinet2InputScrap entity = new WmsCabinet2InputScrap();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
