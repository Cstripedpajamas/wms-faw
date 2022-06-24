package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result.WmsCabinet2TurnoverResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.entity.WmsCabinet2TurnoverBind;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.mapper.WmsCabinet2TurnoverBindMapper;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.params.WmsCabinet2TurnoverBindParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResult;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result.WmsCabinet2TurnoverBindResultP;
import  cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.service.WmsCabinet2TurnoverBindService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 周转箱绑定货物信息关联表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-02
 */
@Service
public class WmsCabinet2TurnoverBindServiceImpl extends ServiceImpl<WmsCabinet2TurnoverBindMapper, WmsCabinet2TurnoverBind> implements WmsCabinet2TurnoverBindService {

    @Override
    public void add(WmsCabinet2TurnoverBindParam param){
        WmsCabinet2TurnoverBind entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet2TurnoverBindParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet2TurnoverBindParam param){
        WmsCabinet2TurnoverBind oldEntity = getOldEntity(param);
        WmsCabinet2TurnoverBind newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet2TurnoverBindResult findBySpec(WmsCabinet2TurnoverBindParam param){
        return null;
    }

    @Override
    public List<WmsCabinet2TurnoverBindResult> findListBySpec(WmsCabinet2TurnoverBindParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet2TurnoverBindParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void delByTurnoverID(String turnoverID) {
        this.baseMapper.delByTurnoverID(turnoverID);
    }

    @Override
    public WmsCabinet2TurnoverBindResult findNumberInfo(String matterTypeID, String matterNumber) {
        return this.baseMapper.findNumberInfo(matterTypeID,matterNumber);
    }

    @Override
    public WmsCabinet2TurnoverBindResult findByTurnId(String turnoverID) {
        return this.baseMapper.findByTurnId(turnoverID);
    }

    @Override
    public void updateNumber(String turnoverID,String number) {
        this.baseMapper.updateNumber(turnoverID,number);
    }

    @Override
    public WmsCabinet2TurnoverBindResult findByTurnId2(String turnoverID) {
        return this.baseMapper.WmsCabinet2TurnoverBindResult(turnoverID);
    }

    @Override
    public List<WmsCabinet2TurnoverBindResultP> findBySku(String materialSku) {
        return this.baseMapper.findBySku(materialSku);
    }

    private Serializable getKey(WmsCabinet2TurnoverBindParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet2TurnoverBind getOldEntity(WmsCabinet2TurnoverBindParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet2TurnoverBind getEntity(WmsCabinet2TurnoverBindParam param) {
        WmsCabinet2TurnoverBind entity = new WmsCabinet2TurnoverBind();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
