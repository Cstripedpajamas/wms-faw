package cn.stylefeng.guns.modular.warehousemanage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskOut;
import cn.stylefeng.guns.modular.warehousemanage.mapper.WmsWarehouseTaskOutMapper;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskOutParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTaskOutResult;
import  cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTaskOutService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 立库-仓库任务管理信息表-出仓 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Service
public class WmsWarehouseTaskOutServiceImpl extends ServiceImpl<WmsWarehouseTaskOutMapper, WmsWarehouseTaskOut> implements WmsWarehouseTaskOutService {

    @Override
    public void add(WmsWarehouseTaskOutParam param){
        WmsWarehouseTaskOut entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehouseTaskOutParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehouseTaskOutParam param){
        WmsWarehouseTaskOut oldEntity = getOldEntity(param);
        WmsWarehouseTaskOut newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehouseTaskOutResult findBySpec(WmsWarehouseTaskOutParam param){
        return null;
    }

    @Override
    public List<WmsWarehouseTaskOutResult> findListBySpec(WmsWarehouseTaskOutParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehouseTaskOutParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsWarehouseTaskOut findByTaskNumber(String taskNumber) {
        return this.baseMapper.findByTaskNumber(taskNumber);
    }

    private Serializable getKey(WmsWarehouseTaskOutParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehouseTaskOut getOldEntity(WmsWarehouseTaskOutParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehouseTaskOut getEntity(WmsWarehouseTaskOutParam param) {
        WmsWarehouseTaskOut entity = new WmsWarehouseTaskOut();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
