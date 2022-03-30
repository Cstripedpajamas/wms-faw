package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1ReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsCabinet1ReturnTaskMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1ReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1ReturnTaskResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1ReturnTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅰ类柜归还任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Service
public class WmsCabinet1ReturnTaskServiceImpl extends ServiceImpl<WmsCabinet1ReturnTaskMapper, WmsCabinet1ReturnTask> implements WmsCabinet1ReturnTaskService {

    @Override
    public void add(WmsCabinet1ReturnTaskParam param){
        WmsCabinet1ReturnTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet1ReturnTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet1ReturnTaskParam param){
        WmsCabinet1ReturnTask oldEntity = getOldEntity(param);
        WmsCabinet1ReturnTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet1ReturnTaskResult findBySpec(WmsCabinet1ReturnTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet1ReturnTaskResult> findListBySpec(WmsCabinet1ReturnTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet1ReturnTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCabinet1ReturnTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet1ReturnTask getOldEntity(WmsCabinet1ReturnTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet1ReturnTask getEntity(WmsCabinet1ReturnTaskParam param) {
        WmsCabinet1ReturnTask entity = new WmsCabinet1ReturnTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
