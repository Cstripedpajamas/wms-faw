package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.entity.WmsCabinet2ReplenishmentTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.mapper.WmsCabinet2ReplenishmentTaskMapper;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.params.WmsCabinet2ReplenishmentTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.model.result.WmsCabinet2ReplenishmentTaskResult;
import  cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2ReplenishmentTask.service.WmsCabinet2ReplenishmentTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅱ类柜补货任务信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsCabinet2ReplenishmentTaskServiceImpl extends ServiceImpl<WmsCabinet2ReplenishmentTaskMapper, WmsCabinet2ReplenishmentTask> implements WmsCabinet2ReplenishmentTaskService {

    @Override
    public void add(WmsCabinet2ReplenishmentTaskParam param){
        WmsCabinet2ReplenishmentTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet2ReplenishmentTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet2ReplenishmentTaskParam param){
        WmsCabinet2ReplenishmentTask oldEntity = getOldEntity(param);
        WmsCabinet2ReplenishmentTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet2ReplenishmentTaskResult findBySpec(WmsCabinet2ReplenishmentTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet2ReplenishmentTaskResult> findListBySpec(WmsCabinet2ReplenishmentTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet2ReplenishmentTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsCabinet2ReplenishmentTaskResult findByTaskNumber(String taskNumber) {
        return this.baseMapper.findByTaskNumber(taskNumber);
    }

    @Override
    public void updateStateById(String id, String state) {
       this.baseMapper.updateStateById(id,state);
    }

    @Override
    public WmsCabinet2ReplenishmentTaskParam findById(String runningId) {
        return this.baseMapper.findById(runningId);
    }

    private Serializable getKey(WmsCabinet2ReplenishmentTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet2ReplenishmentTask getOldEntity(WmsCabinet2ReplenishmentTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet2ReplenishmentTask getEntity(WmsCabinet2ReplenishmentTaskParam param) {
        WmsCabinet2ReplenishmentTask entity = new WmsCabinet2ReplenishmentTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
