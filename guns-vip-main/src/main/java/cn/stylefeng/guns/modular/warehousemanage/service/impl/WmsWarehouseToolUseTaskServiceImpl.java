package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseToolUseTask;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehouseToolUseTaskMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseToolUseTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseToolUseTaskResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseToolUseTaskService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-工具领用任务信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehouseToolUseTaskServiceImpl extends ServiceImpl<WmsWarehouseToolUseTaskMapper, WmsWarehouseToolUseTask> implements WmsWarehouseToolUseTaskService {

    @Override
    public void add(WmsWarehouseToolUseTaskParam param){
        WmsWarehouseToolUseTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehouseToolUseTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehouseToolUseTaskParam param){
        WmsWarehouseToolUseTask oldEntity = getOldEntity(param);
        WmsWarehouseToolUseTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehouseToolUseTaskResult findBySpec(WmsWarehouseToolUseTaskParam param){
        return null;
    }

    @Override
    public List<WmsWarehouseToolUseTaskResult> findListBySpec(WmsWarehouseToolUseTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehouseToolUseTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<WmsWarehouseToolUseTaskResult> findByTaskStateOfOperator(WmsWarehouseToolUseTaskParam param) {
        return this.baseMapper.findByTaskStateOfOperator(param);
    }

    @Override
    public void updateByTaskNumber(String toolUseTaskNumber) {
        this.baseMapper.updateByTaskNumber(toolUseTaskNumber);
    }

    private Serializable getKey(WmsWarehouseToolUseTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehouseToolUseTask getOldEntity(WmsWarehouseToolUseTaskParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehouseToolUseTask getEntity(WmsWarehouseToolUseTaskParam param) {
        WmsWarehouseToolUseTask entity = new WmsWarehouseToolUseTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
