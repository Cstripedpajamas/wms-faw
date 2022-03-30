package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RepairTask;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsCabinet1RepairTaskMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RepairTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1RepairTaskResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1RepairTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅰ类柜维修任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Service
public class WmsCabinet1RepairTaskServiceImpl extends ServiceImpl<WmsCabinet1RepairTaskMapper, WmsCabinet1RepairTask> implements WmsCabinet1RepairTaskService {

    @Override
    public void add(WmsCabinet1RepairTaskParam param){
        WmsCabinet1RepairTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet1RepairTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet1RepairTaskParam param){
        WmsCabinet1RepairTask oldEntity = getOldEntity(param);
        WmsCabinet1RepairTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet1RepairTaskResult findBySpec(WmsCabinet1RepairTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet1RepairTaskResult> findListBySpec(WmsCabinet1RepairTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet1RepairTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCabinet1RepairTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet1RepairTask getOldEntity(WmsCabinet1RepairTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet1RepairTask getEntity(WmsCabinet1RepairTaskParam param) {
        WmsCabinet1RepairTask entity = new WmsCabinet1RepairTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
