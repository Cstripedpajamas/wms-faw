package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskIn;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehouseTaskInMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskInParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTaskInResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTaskInService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-仓库任务管理信息表-入仓 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehouseTaskInServiceImpl extends ServiceImpl<WmsWarehouseTaskInMapper, WmsWarehouseTaskIn> implements WmsWarehouseTaskInService {

    @Override
    public void add(WmsWarehouseTaskInParam param){
        WmsWarehouseTaskIn entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehouseTaskInParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehouseTaskInParam param){
        WmsWarehouseTaskIn oldEntity = getOldEntity(param);
        WmsWarehouseTaskIn newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehouseTaskInResult findBySpec(WmsWarehouseTaskInParam param){
        return null;
    }

    @Override
    public List<WmsWarehouseTaskInResult> findListBySpec(WmsWarehouseTaskInParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehouseTaskInParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsWarehouseTaskInParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehouseTaskIn getOldEntity(WmsWarehouseTaskInParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehouseTaskIn getEntity(WmsWarehouseTaskInParam param) {
        WmsWarehouseTaskIn entity = new WmsWarehouseTaskIn();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
