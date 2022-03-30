package cn.stylefeng.guns.modular.statistics.toolinfo.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.toolinfo.entity.WmsToolInfo;
import cn.stylefeng.guns.modular.statistics.toolinfo.mapper.WmsToolInfoMapper;
import cn.stylefeng.guns.modular.statistics.toolinfo.model.params.WmsToolInfoParam;
import cn.stylefeng.guns.modular.statistics.toolinfo.model.result.WmsToolInfoResult;
import  cn.stylefeng.guns.modular.statistics.toolinfo.service.WmsToolInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 工具信息汇总表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsToolInfoServiceImpl extends ServiceImpl<WmsToolInfoMapper, WmsToolInfo> implements WmsToolInfoService {

    @Override
    public void add(WmsToolInfoParam param){
        WmsToolInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsToolInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsToolInfoParam param){
        WmsToolInfo oldEntity = getOldEntity(param);
        WmsToolInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsToolInfoResult findBySpec(WmsToolInfoParam param){
        return null;
    }

    @Override
    public List<WmsToolInfoResult> findListBySpec(WmsToolInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsToolInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsToolInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsToolInfo getOldEntity(WmsToolInfoParam param) {
        return this.getById(getKey(param));
    }

    private WmsToolInfo getEntity(WmsToolInfoParam param) {
        WmsToolInfo entity = new WmsToolInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
