package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity.WmsCabinet2UseTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.mapper.WmsCabinet2UseTaskMapper;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.params.WmsCabinet2UseTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.result.WmsCabinet2UseTaskResult;
import  cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service.WmsCabinet2UseTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅱ类柜领用任务信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsCabinet2UseTaskServiceImpl extends ServiceImpl<WmsCabinet2UseTaskMapper, WmsCabinet2UseTask> implements WmsCabinet2UseTaskService {

    @Override
    public void add(WmsCabinet2UseTaskParam param){
        WmsCabinet2UseTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet2UseTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet2UseTaskParam param){
        WmsCabinet2UseTask oldEntity = getOldEntity(param);
        WmsCabinet2UseTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet2UseTaskResult findBySpec(WmsCabinet2UseTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet2UseTaskResult> findListBySpec(WmsCabinet2UseTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet2UseTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsCabinet2UseTaskResult findById(String id) {
        return this.baseMapper.findById(id);
    }

    @Override
    public void updateState(String id, String i) {
        this.baseMapper.updateState(id,i);
    }

    @Override
    public void updateScropNumber(String orderId, String scrapCount) {
        this.baseMapper.updateScropNumber(orderId,scrapCount);
    }

    @Override
    public void updateStockMsgById(String runningId, String stockId, String locaNumber, String state) {
        this.baseMapper.updateStockMsgById(runningId,stockId,locaNumber,state);
    }

    @Override
    public LayuiPageInfo findPageBySpec2(WmsCabinet2UseTaskParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList2(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCabinet2UseTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet2UseTask getOldEntity(WmsCabinet2UseTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet2UseTask getEntity(WmsCabinet2UseTaskParam param) {
        WmsCabinet2UseTask entity = new WmsCabinet2UseTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
