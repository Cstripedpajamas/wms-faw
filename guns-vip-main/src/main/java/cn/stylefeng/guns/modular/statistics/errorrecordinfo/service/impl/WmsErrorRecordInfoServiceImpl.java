package cn.stylefeng.guns.modular.statistics.errorrecordinfo.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.entity.WmsErrorRecordInfo;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.mapper.WmsErrorRecordInfoMapper;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.params.WmsErrorRecordInfoParam;
import cn.stylefeng.guns.modular.statistics.errorrecordinfo.model.result.WmsErrorRecordInfoResult;
import  cn.stylefeng.guns.modular.statistics.errorrecordinfo.service.WmsErrorRecordInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 异常信息记录表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsErrorRecordInfoServiceImpl extends ServiceImpl<WmsErrorRecordInfoMapper, WmsErrorRecordInfo> implements WmsErrorRecordInfoService {

    @Override
    public void add(WmsErrorRecordInfoParam param){
        WmsErrorRecordInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsErrorRecordInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsErrorRecordInfoParam param){
        WmsErrorRecordInfo oldEntity = getOldEntity(param);
        WmsErrorRecordInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsErrorRecordInfoResult findBySpec(WmsErrorRecordInfoParam param){
        return null;
    }

    @Override
    public List<WmsErrorRecordInfoResult> findListBySpec(WmsErrorRecordInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsErrorRecordInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsErrorRecordInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsErrorRecordInfo getOldEntity(WmsErrorRecordInfoParam param) {
        return this.getById(getKey(param));
    }

    private WmsErrorRecordInfo getEntity(WmsErrorRecordInfoParam param) {
        WmsErrorRecordInfo entity = new WmsErrorRecordInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
