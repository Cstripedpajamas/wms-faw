package cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.entity.WmsCheckDifferenceRecordInfo;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.mapper.WmsCheckDifferenceRecordInfoMapper;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.params.WmsCheckDifferenceRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.model.result.WmsCheckDifferenceRecordInfoResult;
import  cn.stylefeng.guns.modular.statistics.checkdifferencerecordinfo.service.WmsCheckDifferenceRecordInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 盘点差异记录表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsCheckDifferenceRecordInfoServiceImpl extends ServiceImpl<WmsCheckDifferenceRecordInfoMapper, WmsCheckDifferenceRecordInfo> implements WmsCheckDifferenceRecordInfoService {

    @Override
    public void add(WmsCheckDifferenceRecordInfoParam param){
        WmsCheckDifferenceRecordInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsCheckDifferenceRecordInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsCheckDifferenceRecordInfoParam param){
        WmsCheckDifferenceRecordInfo oldEntity = getOldEntity(param);
        WmsCheckDifferenceRecordInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsCheckDifferenceRecordInfoResult findBySpec(WmsCheckDifferenceRecordInfoParam param){
        return null;
    }

    @Override
    public List<WmsCheckDifferenceRecordInfoResult> findListBySpec(WmsCheckDifferenceRecordInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsCheckDifferenceRecordInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsCheckDifferenceRecordInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsCheckDifferenceRecordInfo getOldEntity(WmsCheckDifferenceRecordInfoParam param) {
        return this.getById(getKey(param));
    }

    private WmsCheckDifferenceRecordInfo getEntity(WmsCheckDifferenceRecordInfoParam param) {
        WmsCheckDifferenceRecordInfo entity = new WmsCheckDifferenceRecordInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
