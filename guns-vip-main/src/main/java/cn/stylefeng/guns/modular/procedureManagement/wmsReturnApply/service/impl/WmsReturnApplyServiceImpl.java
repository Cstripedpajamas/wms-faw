package cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity.WmsReturnApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.mapper.WmsReturnApplyMapper;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params.WmsReturnApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.result.WmsReturnApplyResult;
import  cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.service.WmsReturnApplyService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 归还申请信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsReturnApplyServiceImpl extends ServiceImpl<WmsReturnApplyMapper, WmsReturnApply> implements WmsReturnApplyService {

    @Override
    public void add(WmsReturnApplyParam param){
        WmsReturnApply entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsReturnApplyParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsReturnApplyParam param){
        WmsReturnApply oldEntity = getOldEntity(param);
        WmsReturnApply newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsReturnApplyResult findBySpec(WmsReturnApplyParam param){
        return null;
    }

    @Override
    public List<WmsReturnApplyResult> findListBySpec(WmsReturnApplyParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsReturnApplyParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsReturnApplyParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsReturnApply getOldEntity(WmsReturnApplyParam param) {
        return this.getById(getKey(param));
    }

    private WmsReturnApply getEntity(WmsReturnApplyParam param) {
        WmsReturnApply entity = new WmsReturnApply();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
