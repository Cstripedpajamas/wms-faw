package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehouseTurnoverMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-周转箱信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehouseTurnoverServiceImpl extends ServiceImpl<WmsWarehouseTurnoverMapper, WmsWarehouseTurnover> implements WmsWarehouseTurnoverService {

    @Override
    public void add(WmsWarehouseTurnoverParam param){
        WmsWarehouseTurnover entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehouseTurnoverParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehouseTurnoverParam param){
        WmsWarehouseTurnover oldEntity = getOldEntity(param);
        WmsWarehouseTurnover newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehouseTurnoverResult findBySpec(WmsWarehouseTurnoverParam param){
        return null;
    }

    @Override
    public List<WmsWarehouseTurnoverResult> findListBySpec(WmsWarehouseTurnoverParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehouseTurnoverParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsWarehouseTurnoverResult findByTurnoverNumber(String code) {
        return this.baseMapper.findByTurnoverNumber(code);
    }

    @Override
    public void updateLatticeNumber(String turnoverId, String i) {
         this.baseMapper.updateLatticeNumber(turnoverId,i);
    }

    @Override
    public WmsWarehouseTurnoverResult findById(String turnoverId) {
        return this.baseMapper.findById(turnoverId);
    }

    @Override
    public WmsWarehouseTurnoverResult findByBarCode(String barCode) {
        return this.baseMapper.findByBarCode(barCode);
    }

    private Serializable getKey(WmsWarehouseTurnoverParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehouseTurnover getOldEntity(WmsWarehouseTurnoverParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehouseTurnover getEntity(WmsWarehouseTurnoverParam param) {
        WmsWarehouseTurnover entity = new WmsWarehouseTurnover();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
