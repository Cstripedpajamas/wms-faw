package cn.stylefeng.guns.modular.fawInfo.mtlInfo.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.entity.FawMtlInfo;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.mapper.FawMtlInfoMapper;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params.FawMtlInfoParam;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.result.FawMtlInfoResult;
import  cn.stylefeng.guns.modular.fawInfo.mtlInfo.service.FawMtlInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * faw物料信息 服务实现类
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-28
 */
@Service
public class FawMtlInfoServiceImpl extends ServiceImpl<FawMtlInfoMapper, FawMtlInfo> implements FawMtlInfoService {

    @Override
    public void add(FawMtlInfoParam param){
        FawMtlInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FawMtlInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FawMtlInfoParam param){
        FawMtlInfo oldEntity = getOldEntity(param);
        FawMtlInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FawMtlInfoResult findBySpec(FawMtlInfoParam param){
        return null;
    }

    @Override
    public List<FawMtlInfoResult> findListBySpec(FawMtlInfoParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(FawMtlInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void insertListBatch(List<FawMtlInfoParam> param) {
        this.baseMapper.insertListBatch(param);
    }

    private Serializable getKey(FawMtlInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private FawMtlInfo getOldEntity(FawMtlInfoParam param) {
        return this.getById(getKey(param));
    }

    private FawMtlInfo getEntity(FawMtlInfoParam param) {
        FawMtlInfo entity = new FawMtlInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
