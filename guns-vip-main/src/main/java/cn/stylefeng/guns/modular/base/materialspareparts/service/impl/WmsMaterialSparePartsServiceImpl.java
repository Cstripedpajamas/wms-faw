package cn.stylefeng.guns.modular.base.materialspareparts.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialspareparts.entity.WmsMaterialSpareParts;
import cn.stylefeng.guns.modular.base.materialspareparts.mapper.WmsMaterialSparePartsMapper;
import cn.stylefeng.guns.modular.base.materialspareparts.model.params.WmsMaterialSparePartsParam;
import cn.stylefeng.guns.modular.base.materialspareparts.model.result.WmsMaterialSparePartsResult;
import  cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 备品备件-物料信息表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsMaterialSparePartsServiceImpl extends ServiceImpl<WmsMaterialSparePartsMapper, WmsMaterialSpareParts> implements WmsMaterialSparePartsService {

    @Override
    public void add(WmsMaterialSparePartsParam param){
        WmsMaterialSpareParts entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsMaterialSparePartsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsMaterialSparePartsParam param){
        WmsMaterialSpareParts oldEntity = getOldEntity(param);
        WmsMaterialSpareParts newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsMaterialSparePartsResult findBySpec(WmsMaterialSparePartsParam param){
        return null;
    }

    @Override
    public List<WmsMaterialSparePartsResult> findListBySpec(WmsMaterialSparePartsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsMaterialSparePartsParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<WmsMaterialSparePartsResult> findAll() {
        return this.baseMapper.findAll();
    }

    @Override
    public List<WmsMaterialSparePartsResult> findAllByMaterialTypeId(WmsMaterialSparePartsParam param) {
        return this.baseMapper.findAllByMaterialTypeId(param);
    }

    private Serializable getKey(WmsMaterialSparePartsParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsMaterialSpareParts getOldEntity(WmsMaterialSparePartsParam param) {
        return this.getById(getKey(param));
    }

    private WmsMaterialSpareParts getEntity(WmsMaterialSparePartsParam param) {
        WmsMaterialSpareParts entity = new WmsMaterialSpareParts();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
