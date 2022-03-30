package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.mapper.WmsUseApplyMapper;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.result.WmsUseApplyResult;
import  cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 领用申请信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsUseApplyServiceImpl extends ServiceImpl<WmsUseApplyMapper, WmsUseApply> implements WmsUseApplyService {

    @Override
    public void add(WmsUseApplyParam param){
        WmsUseApply entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsUseApplyParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsUseApplyParam param){
        WmsUseApply oldEntity = getOldEntity(param);
        WmsUseApply newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsUseApplyResult findBySpec(WmsUseApplyParam param){
        return null;
    }

    @Override
    public List<WmsUseApplyResult> findListBySpec(WmsUseApplyParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsUseApplyParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsUseApplyResult findById(String useRequestId) {
        return this.baseMapper.findById(useRequestId);
    }

    private Serializable getKey(WmsUseApplyParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsUseApply getOldEntity(WmsUseApplyParam param) {
        return this.getById(getKey(param));
    }

    private WmsUseApply getEntity(WmsUseApplyParam param) {
        WmsUseApply entity = new WmsUseApply();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
