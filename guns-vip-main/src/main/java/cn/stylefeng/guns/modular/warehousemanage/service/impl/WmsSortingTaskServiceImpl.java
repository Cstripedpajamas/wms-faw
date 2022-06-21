package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsSortingTask;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsSortingTaskMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsSortingTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsSortingTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 机械手分拣任务表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-23
 */
@Service
public class WmsSortingTaskServiceImpl extends ServiceImpl<WmsSortingTaskMapper, WmsSortingTask> implements WmsSortingTaskService {

    @Override
    public void add(WmsSortingTaskParam param){
        WmsSortingTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsSortingTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsSortingTaskParam param){
        WmsSortingTask oldEntity = getOldEntity(param);
        WmsSortingTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsSortingTaskResult findBySpec(WmsSortingTaskParam param){
        return null;
    }

    @Override
    public WmsSortingTaskResult findByTaskStateOne() {
        return this.baseMapper.findByTaskStateOne();
    }

    @Override
    public List<WmsSortingTaskResult> findListBySpec(WmsSortingTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsSortingTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<WmsSortingTaskResult> findRecentTask() {
        return this.baseMapper.findRecentTask();
    }

    @Override
    public WmsSortingTaskResult findById(String orderId) {
        return this.baseMapper.findById(orderId);
    }

    private Serializable getKey(WmsSortingTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsSortingTask getOldEntity(WmsSortingTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsSortingTask getEntity(WmsSortingTaskParam param) {
        WmsSortingTask entity = new WmsSortingTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
