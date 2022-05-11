package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseReplenishmentTask;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehouseReplenishmentTaskMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseReplenishmentTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseReplenishmentTaskResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseReplenishmentTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-备品备件补货任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehouseReplenishmentTaskServiceImpl extends ServiceImpl<WmsWarehouseReplenishmentTaskMapper, WmsWarehouseReplenishmentTask> implements WmsWarehouseReplenishmentTaskService {

    @Override
    public void add(WmsWarehouseReplenishmentTaskParam param){
        WmsWarehouseReplenishmentTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehouseReplenishmentTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehouseReplenishmentTaskParam param){
        WmsWarehouseReplenishmentTask oldEntity = getOldEntity(param);
        WmsWarehouseReplenishmentTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehouseReplenishmentTaskResult findBySpec(WmsWarehouseReplenishmentTaskParam param){
        return null;
    }

    @Override
    public List<WmsWarehouseReplenishmentTaskResult> findListBySpec(WmsWarehouseReplenishmentTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehouseReplenishmentTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsWarehouseReplenishmentTaskResult findByTaskNumber(String taskNumber) {
        return this.baseMapper.findByTaskNumber(taskNumber);
    }

    @Override
    public void updatePickNumber(String taskNumber, String pickNumber) {
        this.baseMapper.updatePickNumber(taskNumber,pickNumber);
    }

    private Serializable getKey(WmsWarehouseReplenishmentTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehouseReplenishmentTask getOldEntity(WmsWarehouseReplenishmentTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehouseReplenishmentTask getEntity(WmsWarehouseReplenishmentTaskParam param) {
        WmsWarehouseReplenishmentTask entity = new WmsWarehouseReplenishmentTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
