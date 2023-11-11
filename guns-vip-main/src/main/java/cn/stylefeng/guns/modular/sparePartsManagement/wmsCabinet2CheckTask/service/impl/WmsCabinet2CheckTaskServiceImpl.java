package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.entity.WmsCabinet2CheckTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.mapper.WmsCabinet2CheckTaskMapper;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.params.WmsCabinet2CheckTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.model.result.WmsCabinet2CheckTaskResult;
import  cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2CheckTask.service.WmsCabinet2CheckTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅱ类柜盘点任务信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsCabinet2CheckTaskServiceImpl extends ServiceImpl<WmsCabinet2CheckTaskMapper, WmsCabinet2CheckTask> implements WmsCabinet2CheckTaskService {

    @Override
    public void add(WmsCabinet2CheckTaskParam param){
        WmsCabinet2CheckTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet2CheckTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void deleteVue(){
        this.baseMapper.deleteVue();
    }

    @Override
    public void update(WmsCabinet2CheckTaskParam param){
        WmsCabinet2CheckTask oldEntity = getOldEntity(param);
        WmsCabinet2CheckTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet2CheckTaskResult findBySpec(WmsCabinet2CheckTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet2CheckTaskResult> findListBySpec(WmsCabinet2CheckTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet2CheckTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void addAll(List<WmsCabinet2CheckTaskParam> list) {
        this.baseMapper.addAll(list);
    }

    @Override
    public List<WmsCabinet2CheckTaskResult> findByTurnoverId(String turnoverID) {
        return this.baseMapper.findByTurnoverId(turnoverID);
    }

    @Override
    public void updateStateById(String runningId, String state) {
        this.baseMapper.updateStateById(runningId,state);
    }

    @Override
    public Integer isFinishTask(String taskNumber) {
       return this.baseMapper.isFinishTask(taskNumber);
    }

    @Override
    public void updateAllState(String taskNumber,String state) {
        this.baseMapper.updateAllState(taskNumber,state);
    }

    @Override
    public WmsCabinet2CheckTaskResult findById(String runningId) {
        return  this.baseMapper.findById(runningId);
    }

    private Serializable getKey(WmsCabinet2CheckTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet2CheckTask getOldEntity(WmsCabinet2CheckTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet2CheckTask getEntity(WmsCabinet2CheckTaskParam param) {
        WmsCabinet2CheckTask entity = new WmsCabinet2CheckTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
