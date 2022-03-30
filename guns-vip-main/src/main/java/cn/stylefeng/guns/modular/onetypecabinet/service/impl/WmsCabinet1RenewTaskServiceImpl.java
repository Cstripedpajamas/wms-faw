package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RenewTask;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsCabinet1RenewTaskMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RenewTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1RenewTaskResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsCabinet1RenewTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅰ类柜换新任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Service
public class WmsCabinet1RenewTaskServiceImpl extends ServiceImpl<WmsCabinet1RenewTaskMapper, WmsCabinet1RenewTask> implements WmsCabinet1RenewTaskService {

    @Override
    public void add(WmsCabinet1RenewTaskParam param){
        WmsCabinet1RenewTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet1RenewTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet1RenewTaskParam param){
        WmsCabinet1RenewTask oldEntity = getOldEntity(param);
        WmsCabinet1RenewTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet1RenewTaskResult findBySpec(WmsCabinet1RenewTaskParam param){
        return null;
    }

    @Override
    public List<WmsCabinet1RenewTaskResult> findListBySpec(WmsCabinet1RenewTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet1RenewTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCabinet1RenewTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet1RenewTask getOldEntity(WmsCabinet1RenewTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet1RenewTask getEntity(WmsCabinet1RenewTaskParam param) {
        WmsCabinet1RenewTask entity = new WmsCabinet1RenewTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
