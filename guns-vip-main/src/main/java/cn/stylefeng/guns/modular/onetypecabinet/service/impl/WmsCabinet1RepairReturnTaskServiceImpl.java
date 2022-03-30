package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RepairReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsCabinet1RepairReturnTaskMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RepairReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1RepairReturnTaskResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1RepairReturnTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅰ类柜维修归还任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Service
public class WmsCabinet1RepairReturnTaskServiceImpl extends ServiceImpl<WmsCabinet1RepairReturnTaskMapper, WmsCabinet1RepairReturnTask> implements WmsCabinet1RepairReturnTaskService {

    @Override
    public void add(WmsCabinet1RepairReturnTaskParam param){
        WmsCabinet1RepairReturnTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet1RepairReturnTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet1RepairReturnTaskParam param){
        WmsCabinet1RepairReturnTask oldEntity = getOldEntity(param);
        WmsCabinet1RepairReturnTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet1RepairReturnTaskResult findBySpec(WmsCabinet1RepairReturnTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet1RepairReturnTaskResult> findListBySpec(WmsCabinet1RepairReturnTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet1RepairReturnTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCabinet1RepairReturnTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet1RepairReturnTask getOldEntity(WmsCabinet1RepairReturnTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet1RepairReturnTask getEntity(WmsCabinet1RepairReturnTaskParam param) {
        WmsCabinet1RepairReturnTask entity = new WmsCabinet1RepairReturnTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
