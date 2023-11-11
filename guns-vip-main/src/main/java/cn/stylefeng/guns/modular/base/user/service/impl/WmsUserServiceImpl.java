package cn.stylefeng.guns.modular.base.user.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.mapper.WmsUserMapper;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.model.result.WmsUserResult;
import  cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 人员信息表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsUserServiceImpl extends ServiceImpl<WmsUserMapper, WmsUser> implements WmsUserService {

    @Override
    public void add(WmsUserParam param){
        WmsUser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsUserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsUserParam param){
        WmsUser oldEntity = getOldEntity(param);
        WmsUser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsUserResult findBySpec(WmsUserParam param){
        return null;
    }

    @Override
    public List<WmsUserResult> findListBySpec(WmsUserParam param){
        return this.baseMapper.customList(param);
    }

    public List<WmsUserResult> findListUserName(String item){
        return this.baseMapper.findListUserName(item);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsUserParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public WmsUserResult findUserInfo(String serialNumber, String getuPwd) {
        return this.baseMapper.findUserInfo(serialNumber, getuPwd);
    }

    @Override
    public WmsUserResult findUserInfo2(String serialNumber, String getuPwd) {
        return this.baseMapper.findUserInfo2(serialNumber, getuPwd);
    }

    @Override
    public WmsUserResult findUserIdInfo(String staffId) {
        return this.baseMapper.findUserIdInfo(staffId);
    }

    @Override
    public WmsUserResult findUserIdInfo2(String staffId) {
        return this.baseMapper.findUserIdInfo2(staffId);
    }

    @Override
    public WmsUserResult findUserName(String name) {
        return this.baseMapper.findUserName(name);
    }

    @Override
    public WmsUserResult findSerialNumber(String serialNumber) {
        return this.baseMapper.findSerialNumber(serialNumber);
    }

    @Override
    public void insertListBatch(List<WmsUserParam> param) {
        this.baseMapper.insertListBatch(param);
    }

    private Serializable getKey(WmsUserParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsUser getOldEntity(WmsUserParam param) {
        return this.getById(getKey(param));
    }

    private WmsUser getEntity(WmsUserParam param) {
        WmsUser entity = new WmsUser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
