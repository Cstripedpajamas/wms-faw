package cn.stylefeng.guns.modular.fawInfo.userInfo.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.entity.FawUserInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.mapper.FawUserInfoMapper;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.result.FawUserInfoResult;
import  cn.stylefeng.guns.modular.fawInfo.userInfo.service.FawUserInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * faw基本人员信息表 服务实现类
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-14
 */
@Service
public class FawUserInfoServiceImpl extends ServiceImpl<FawUserInfoMapper, FawUserInfo> implements FawUserInfoService {

    @Override
    public void add(FawUserInfoParam param){
        FawUserInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FawUserInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FawUserInfoParam param){
        FawUserInfo oldEntity = getOldEntity(param);
        FawUserInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FawUserInfoResult findBySpec(FawUserInfoParam param){
        return null;
    }

    @Override
    public List<FawUserInfoResult> findListBySpec(FawUserInfoParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(FawUserInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void insertListBatch(List<FawUserInfoParam> param) {
        this.baseMapper.insertListBatch(param);
    }

    private Serializable getKey(FawUserInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private FawUserInfo getOldEntity(FawUserInfoParam param) {
        return this.getById(getKey(param));
    }

    private FawUserInfo getEntity(FawUserInfoParam param) {
        FawUserInfo entity = new FawUserInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
