package cn.stylefeng.guns.modular.base.intelligentcabinet2conf.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.entity.WmsIntelligentCabinet2Conf;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.mapper.WmsIntelligentCabinet2ConfMapper;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.params.WmsIntelligentCabinet2ConfParam;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.result.WmsIntelligentCabinet2ConfResult;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.service.WmsIntelligentCabinet2ConfService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅱ类柜物料补货阈值配置表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsIntelligentCabinet2ConfServiceImpl extends ServiceImpl<WmsIntelligentCabinet2ConfMapper, WmsIntelligentCabinet2Conf> implements WmsIntelligentCabinet2ConfService {

    @Override
    public void add(WmsIntelligentCabinet2ConfParam param) {
        WmsIntelligentCabinet2Conf entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsIntelligentCabinet2ConfParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsIntelligentCabinet2ConfParam param) {
        WmsIntelligentCabinet2Conf oldEntity = getOldEntity(param);
        WmsIntelligentCabinet2Conf newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsIntelligentCabinet2ConfResult findBySpec(WmsIntelligentCabinet2ConfParam param) {
        return null;
    }

    @Override
    public List<WmsIntelligentCabinet2ConfResult> findListBySpec(WmsIntelligentCabinet2ConfParam param) {
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsIntelligentCabinet2ConfParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsIntelligentCabinet2ConfResult findBySku(String materialTypeId) {
        return this.baseMapper.findBySku(materialTypeId);
    }

    private Serializable getKey(WmsIntelligentCabinet2ConfParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsIntelligentCabinet2Conf getOldEntity(WmsIntelligentCabinet2ConfParam param) {
        return this.getById(getKey(param));
    }

    private WmsIntelligentCabinet2Conf getEntity(WmsIntelligentCabinet2ConfParam param) {
        WmsIntelligentCabinet2Conf entity = new WmsIntelligentCabinet2Conf();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
