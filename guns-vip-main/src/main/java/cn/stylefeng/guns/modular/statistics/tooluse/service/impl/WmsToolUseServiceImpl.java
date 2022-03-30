package cn.stylefeng.guns.modular.statistics.tooluse.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.mapper.WmsToolUseMapper;
import cn.stylefeng.guns.modular.statistics.tooluse.model.params.WmsToolUseParam;
import cn.stylefeng.guns.modular.statistics.tooluse.model.result.WmsToolUseResult;
import  cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 工具领用信息表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsToolUseServiceImpl extends ServiceImpl<WmsToolUseMapper, WmsToolUse> implements WmsToolUseService {

    @Override
    public void add(WmsToolUseParam param){
        WmsToolUse entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsToolUseParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsToolUseParam param){
        WmsToolUse oldEntity = getOldEntity(param);
        WmsToolUse newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsToolUseResult findBySpec(WmsToolUseParam param){
        return null;
    }

    @Override
    public List<WmsToolUseResult> findListBySpec(WmsToolUseParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public List<WmsToolUseResult> findListBySpecA(WmsToolUseParam param) {
        return this.baseMapper.customListA(param);
    }

    @Override
    public List<WmsToolUseResult> findListBySpecB(WmsToolUseParam param) {
        return this.baseMapper.customListB(param);
    }

    @Override
    public List<WmsToolUseResult> findListBySpecC(WmsToolUseParam param) {
        return this.baseMapper.customListC(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsToolUseParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsToolUseParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsToolUse getOldEntity(WmsToolUseParam param) {
        return this.getById(getKey(param));
    }

    private WmsToolUse getEntity(WmsToolUseParam param) {
        WmsToolUse entity = new WmsToolUse();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
