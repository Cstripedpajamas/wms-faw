package cn.stylefeng.guns.modular.base.materialtool.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.materialtool.mapper.WmsMaterialToolMapper;
import cn.stylefeng.guns.modular.base.materialtool.model.params.WmsMaterialToolParam;
import cn.stylefeng.guns.modular.base.materialtool.model.result.WmsMaterialToolResult;
import  cn.stylefeng.guns.modular.base.materialtool.service.WmsMaterialToolService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 工具-物料信息表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsMaterialToolServiceImpl extends ServiceImpl<WmsMaterialToolMapper, WmsMaterialTool> implements WmsMaterialToolService {

    @Override
    public void add(WmsMaterialToolParam param){
        WmsMaterialTool entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsMaterialToolParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsMaterialToolParam param){
        WmsMaterialTool oldEntity = getOldEntity(param);
        WmsMaterialTool newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsMaterialToolResult findBySpec(WmsMaterialToolParam param){
        return null;
    }

    @Override
    public List<WmsMaterialToolResult> findListBySpec(WmsMaterialToolParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsMaterialToolParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsMaterialToolParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsMaterialTool getOldEntity(WmsMaterialToolParam param) {
        return this.getById(getKey(param));
    }

    private WmsMaterialTool getEntity(WmsMaterialToolParam param) {
        WmsMaterialTool entity = new WmsMaterialTool();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
