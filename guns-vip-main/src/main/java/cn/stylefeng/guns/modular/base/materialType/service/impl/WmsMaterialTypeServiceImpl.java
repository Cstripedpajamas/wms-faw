package cn.stylefeng.guns.modular.base.materialType.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.mapper.WmsMaterialTypeMapper;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import  cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 物料类型信息表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsMaterialTypeServiceImpl extends ServiceImpl<WmsMaterialTypeMapper, WmsMaterialType> implements WmsMaterialTypeService {

    @Override
    public void add(WmsMaterialTypeParam param){
        WmsMaterialType entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsMaterialTypeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsMaterialTypeParam param){
        WmsMaterialType oldEntity = getOldEntity(param);
        WmsMaterialType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsMaterialTypeResult findBySpec(WmsMaterialTypeParam param){
        return null;
    }

    @Override
    public List<WmsMaterialTypeResult> findListBySpec(WmsMaterialTypeParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsMaterialTypeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsMaterialTypeResult findById(String sMaterialTypeId) {
        return this.baseMapper.findById(sMaterialTypeId);
    }

    @Override
    public WmsMaterialTypeResult findByMaterialSku(WmsMaterialTypeParam param) {
        return this.baseMapper.findByMaterialSku(param);
    }

    @Override
    public void insertListBatch(List<WmsMaterialTypeParam> param) {
        this.baseMapper.insertListBatch(param);
    }

    private Serializable getKey(WmsMaterialTypeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsMaterialType getOldEntity(WmsMaterialTypeParam param) {
        return this.getById(getKey(param));
    }

    private WmsMaterialType getEntity(WmsMaterialTypeParam param) {
        WmsMaterialType entity = new WmsMaterialType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
