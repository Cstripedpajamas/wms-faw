package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehousePurchaseStorageTask;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehousePurchaseStorageTaskMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehousePurchaseStorageTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehousePurchaseStorageTaskResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehousePurchaseStorageTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-采购入库任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehousePurchaseStorageTaskServiceImpl extends ServiceImpl<WmsWarehousePurchaseStorageTaskMapper, WmsWarehousePurchaseStorageTask> implements WmsWarehousePurchaseStorageTaskService {

    @Override
    public void add(WmsWarehousePurchaseStorageTaskParam param){
        WmsWarehousePurchaseStorageTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehousePurchaseStorageTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehousePurchaseStorageTaskParam param){
        WmsWarehousePurchaseStorageTask oldEntity = getOldEntity(param);
        WmsWarehousePurchaseStorageTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehousePurchaseStorageTaskResult findBySpec(WmsWarehousePurchaseStorageTaskParam param){
        return null;
    }

    @Override
    public List<WmsWarehousePurchaseStorageTaskResult> findListBySpec(WmsWarehousePurchaseStorageTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehousePurchaseStorageTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsWarehousePurchaseStorageTaskResult findByOrderId(String purNumber) {
        return this.baseMapper.findByOrderId(purNumber);
    }

    @Override
    public void updateState(String state,String OrderId) {
        this.baseMapper.updateState(state,OrderId);
    }

    @Override
    public void updateStateById(String runningId, String state) {
        this.baseMapper.updateStateById(runningId,state);
    }

    @Override
    public void stopTask() {
        baseMapper.stopTask();
    }

    @Override
    public WmsPurchaseOrderInfoResult doingTask() {
        return this.baseMapper.doingTask();
    }

    private Serializable getKey(WmsWarehousePurchaseStorageTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehousePurchaseStorageTask getOldEntity(WmsWarehousePurchaseStorageTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehousePurchaseStorageTask getEntity(WmsWarehousePurchaseStorageTaskParam param) {
        WmsWarehousePurchaseStorageTask entity = new WmsWarehousePurchaseStorageTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
