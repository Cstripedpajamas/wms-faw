package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1CheckTask;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsCabinet1CheckTaskMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1CheckTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1CheckTaskResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1CheckTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅰ类柜盘点任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Service
public class WmsCabinet1CheckTaskServiceImpl extends ServiceImpl<WmsCabinet1CheckTaskMapper, WmsCabinet1CheckTask> implements WmsCabinet1CheckTaskService {

    @Override
    public void add(WmsCabinet1CheckTaskParam param){
        WmsCabinet1CheckTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet1CheckTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet1CheckTaskParam param){
        WmsCabinet1CheckTask oldEntity = getOldEntity(param);
        WmsCabinet1CheckTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet1CheckTaskResult findBySpec(WmsCabinet1CheckTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet1CheckTaskResult> findListBySpec(WmsCabinet1CheckTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet1CheckTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCabinet1CheckTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet1CheckTask getOldEntity(WmsCabinet1CheckTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet1CheckTask getEntity(WmsCabinet1CheckTaskParam param) {
        WmsCabinet1CheckTask entity = new WmsCabinet1CheckTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
