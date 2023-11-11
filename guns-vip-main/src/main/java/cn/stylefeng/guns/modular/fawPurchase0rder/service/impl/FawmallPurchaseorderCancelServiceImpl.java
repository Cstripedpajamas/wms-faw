package cn.stylefeng.guns.modular.fawPurchase0rder.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawPurchase0rder.entity.FawmallPurchaseorderCancel;
import cn.stylefeng.guns.modular.fawPurchase0rder.mapper.FawmallPurchaseorderCancelMapper;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderCancelParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.result.FawmallPurchaseorderCancelResult;
import cn.stylefeng.guns.modular.fawPurchase0rder.service.FawmallPurchaseorderCancelService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * faw商城发货单 服务实现类
 * </p>
 *
 * @author 邢玉祥
 * @since 2023-03-21
 */
@Service
public class FawmallPurchaseorderCancelServiceImpl extends ServiceImpl<FawmallPurchaseorderCancelMapper, FawmallPurchaseorderCancel> implements FawmallPurchaseorderCancelService {

    @Override
    public void add(FawmallPurchaseorderCancelParam param){
        FawmallPurchaseorderCancel entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FawmallPurchaseorderCancelParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FawmallPurchaseorderCancelParam param){
        FawmallPurchaseorderCancel oldEntity = getOldEntity(param);
        FawmallPurchaseorderCancel newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FawmallPurchaseorderCancelResult findBySpec(FawmallPurchaseorderCancelParam param){
        return null;
    }

    @Override
    public List<FawmallPurchaseorderCancelResult> findListBySpec(FawmallPurchaseorderCancelParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(FawmallPurchaseorderCancelParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(FawmallPurchaseorderCancelParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private FawmallPurchaseorderCancel getOldEntity(FawmallPurchaseorderCancelParam param) {
        return this.getById(getKey(param));
    }

    private FawmallPurchaseorderCancel getEntity(FawmallPurchaseorderCancelParam param) {
        FawmallPurchaseorderCancel entity = new FawmallPurchaseorderCancel();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public void insertListBatch(List<FawmallPurchaseorderCancelParam> param) {
        this.baseMapper.insertListBatch(param);
    }

}
