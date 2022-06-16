package cn.stylefeng.guns.modular.base.packageInfo.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.base.packageInfo.mapper.WmsPackinfoMapper;
import cn.stylefeng.guns.modular.base.packageInfo.model.params.WmsPackinfoParam;
import cn.stylefeng.guns.modular.base.packageInfo.model.result.WmsPackinfoResult;
import  cn.stylefeng.guns.modular.base.packageInfo.service.WmsPackinfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 包装信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-12-14
 */
@Service
public class WmsPackinfoServiceImpl extends ServiceImpl<WmsPackinfoMapper, WmsPackinfo> implements WmsPackinfoService {

    @Override
    public void add(WmsPackinfoParam param){
        WmsPackinfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsPackinfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsPackinfoParam param){
        WmsPackinfo oldEntity = getOldEntity(param);
        WmsPackinfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsPackinfoResult findBySpec(WmsPackinfoParam param){
        return null;
    }

    @Override
    public List<WmsPackinfoResult> findListBySpec(WmsPackinfoParam param){
       return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsPackinfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsPackinfo findByMaterialTypeId(String id) {
        return this.baseMapper.findByMaterialTypeId(id);
    }

    private Serializable getKey(WmsPackinfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsPackinfo getOldEntity(WmsPackinfoParam param) {
        return this.getById(getKey(param));
    }

    private WmsPackinfo getEntity(WmsPackinfoParam param) {
        WmsPackinfo entity = new WmsPackinfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
