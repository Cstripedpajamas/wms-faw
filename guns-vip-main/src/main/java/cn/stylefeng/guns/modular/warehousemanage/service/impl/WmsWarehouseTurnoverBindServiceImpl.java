package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehouseTurnoverBindMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverBindResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-周转箱绑定货物信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehouseTurnoverBindServiceImpl extends ServiceImpl<WmsWarehouseTurnoverBindMapper, WmsWarehouseTurnoverBind> implements WmsWarehouseTurnoverBindService {

    @Override
    public void add(WmsWarehouseTurnoverBindParam param){
        WmsWarehouseTurnoverBind entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehouseTurnoverBindParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehouseTurnoverBindParam param){
        WmsWarehouseTurnoverBind oldEntity = getOldEntity(param);
        WmsWarehouseTurnoverBind newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehouseTurnoverBindResult findBySpec(WmsWarehouseTurnoverBindParam param){
        return null;
    }

    @Override
    public List<WmsWarehouseTurnoverBindResult> findListBySpec(WmsWarehouseTurnoverBindParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehouseTurnoverBindParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsWarehouseTurnoverBindResult findByTurnoverId(WmsWarehouseTurnoverBindParam param) {
        return this.baseMapper.findByTurnoverId(param);
    }

    @Override
    public List<WmsWarehouseTurnoverBindResult> findListTurnover(WmsWarehouseTurnoverBindParam param) {
        return this.baseMapper.findListTurnover(param);
    }

    @Override
    public LayuiPageInfo findLattice(WmsWarehouseTurnoverBindParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.findLattice(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void delByTurnId(Long id) {
        this.baseMapper.delByTurnId(id);
    }

    @Override
    public WmsWarehouseTurnoverBindResult findBySKU(String materialSku) {
        return this.baseMapper.findBySKU(materialSku);
    }

    @Override
    public LayuiPageInfo findTurnoverMsg(WmsWarehouseTurnoverBindParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.findTurnoverMsg(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsWarehouseTurnoverBindParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehouseTurnoverBind getOldEntity(WmsWarehouseTurnoverBindParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehouseTurnoverBind getEntity(WmsWarehouseTurnoverBindParam param) {
        WmsWarehouseTurnoverBind entity = new WmsWarehouseTurnoverBind();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
