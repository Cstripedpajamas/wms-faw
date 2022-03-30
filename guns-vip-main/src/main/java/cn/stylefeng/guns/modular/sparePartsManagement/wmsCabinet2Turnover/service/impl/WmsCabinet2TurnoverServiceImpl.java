package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.entity.WmsCabinet2Turnover;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.mapper.WmsCabinet2TurnoverMapper;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.params.WmsCabinet2TurnoverParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.model.result.WmsCabinet2TurnoverResult;
import  cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2Turnover.service.WmsCabinet2TurnoverService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Ⅱ类柜周转箱信息表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Service
public class WmsCabinet2TurnoverServiceImpl extends ServiceImpl<WmsCabinet2TurnoverMapper, WmsCabinet2Turnover> implements WmsCabinet2TurnoverService {

    @Override
    public void add(WmsCabinet2TurnoverParam param){
        WmsCabinet2Turnover entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCabinet2TurnoverParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCabinet2TurnoverParam param){
        WmsCabinet2Turnover oldEntity = getOldEntity(param);
        WmsCabinet2Turnover newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCabinet2TurnoverResult findBySpec(WmsCabinet2TurnoverParam param){
        return null;
    }

    @Override
    public List<WmsCabinet2TurnoverResult> findListBySpec(WmsCabinet2TurnoverParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCabinet2TurnoverParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void updateState(String turnoverID, String state) {
          baseMapper.updateState(turnoverID, state);
    }

    @Override
    public void updateStockLocal(String turnoverID, String row, String col, String layer, String state) {
        baseMapper.updateStockLocal(turnoverID, row,col,layer,state);
    }

    @Override
    public void updateTurnState(String turnoverID, String state) {
        baseMapper.updateTurnState(turnoverID,state);
    }

    @Override
    public String findTurnoverNumber(String turnoverID) {
        return baseMapper.findTurnoverNumber(turnoverID);
    }

    @Override
    public String findIdByTurnoverNumber(String huNumber) {
        return baseMapper.findIdByTurnoverNumber(huNumber);
    }

    private Serializable getKey(WmsCabinet2TurnoverParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCabinet2Turnover getOldEntity(WmsCabinet2TurnoverParam param) {
        return this.getById(getKey(param));
    }

    private WmsCabinet2Turnover getEntity(WmsCabinet2TurnoverParam param) {
        WmsCabinet2Turnover entity = new WmsCabinet2Turnover();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
