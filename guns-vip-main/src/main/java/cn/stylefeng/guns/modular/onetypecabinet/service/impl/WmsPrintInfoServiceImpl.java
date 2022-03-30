package cn.stylefeng.guns.modular.onetypecabinet.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsPrintInfo;
import cn.stylefeng.guns.modular.onetypecabinet.mapper.WmsPrintInfoMapper;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsPrintInfoParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsPrintInfoResult;
import  cn.stylefeng.guns.modular.onetypecabinet.service.WmsPrintInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 条码打印信息表 服务实现类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-24
 */
@Service
public class WmsPrintInfoServiceImpl extends ServiceImpl<WmsPrintInfoMapper, WmsPrintInfo> implements WmsPrintInfoService {

    @Override
    public void add(WmsPrintInfoParam param){
        WmsPrintInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsPrintInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsPrintInfoParam param){
        WmsPrintInfo oldEntity = getOldEntity(param);
        WmsPrintInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsPrintInfoResult findBySpec(WmsPrintInfoParam param){
        return null;
    }

    @Override
    public List<WmsPrintInfoResult> findListBySpec(WmsPrintInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsPrintInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsPrintInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsPrintInfo getOldEntity(WmsPrintInfoParam param) {
        return this.getById(getKey(param));
    }

    private WmsPrintInfo getEntity(WmsPrintInfoParam param) {
        WmsPrintInfo entity = new WmsPrintInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
