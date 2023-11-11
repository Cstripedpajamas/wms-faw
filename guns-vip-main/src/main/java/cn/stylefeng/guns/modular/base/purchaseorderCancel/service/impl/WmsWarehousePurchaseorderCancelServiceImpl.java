package cn.stylefeng.guns.modular.base.purchaseorderCancel.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.entity.WmsWarehousePurchaseorderCancel;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.mapper.WmsWarehousePurchaseorderCancelMapper;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.params.WmsWarehousePurchaseorderCancelParam;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.result.WmsWarehousePurchaseorderCancelResult;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.service.WmsWarehousePurchaseorderCancelService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 订单取消 服务实现类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-20
 */
@Service
public class WmsWarehousePurchaseorderCancelServiceImpl extends ServiceImpl<WmsWarehousePurchaseorderCancelMapper, WmsWarehousePurchaseorderCancel> implements WmsWarehousePurchaseorderCancelService {

    @Override
    public void add(WmsWarehousePurchaseorderCancelParam param){
        WmsWarehousePurchaseorderCancel entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsWarehousePurchaseorderCancelParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsWarehousePurchaseorderCancelParam param){
        WmsWarehousePurchaseorderCancel oldEntity = getOldEntity(param);
        WmsWarehousePurchaseorderCancel newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsWarehousePurchaseorderCancelResult findBySpec(WmsWarehousePurchaseorderCancelParam param){
        return null;
    }

    @Override
    public List<WmsWarehousePurchaseorderCancelResult> findListBySpec(WmsWarehousePurchaseorderCancelParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsWarehousePurchaseorderCancelParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsWarehousePurchaseorderCancelParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsWarehousePurchaseorderCancel getOldEntity(WmsWarehousePurchaseorderCancelParam param) {
        return this.getById(getKey(param));
    }

    private WmsWarehousePurchaseorderCancel getEntity(WmsWarehousePurchaseorderCancelParam param) {
        WmsWarehousePurchaseorderCancel entity = new WmsWarehousePurchaseorderCancel();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public void insertList(List<WmsWarehousePurchaseorderCancelParam> param) {
        this.baseMapper.insertList(param);
    }
}
