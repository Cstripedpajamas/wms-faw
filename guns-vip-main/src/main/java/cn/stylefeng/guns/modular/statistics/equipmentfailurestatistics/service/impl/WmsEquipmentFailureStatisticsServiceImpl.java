package cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.entity.WmsEquipmentFailureStatistics;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.mapper.WmsEquipmentFailureStatisticsMapper;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.params.WmsEquipmentFailureStatisticsParam;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.result.WmsEquipmentFailureStatisticsResult;
import  cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.service.WmsEquipmentFailureStatisticsService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备故障统计表 服务实现类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Service
public class WmsEquipmentFailureStatisticsServiceImpl extends ServiceImpl<WmsEquipmentFailureStatisticsMapper, WmsEquipmentFailureStatistics> implements WmsEquipmentFailureStatisticsService {

    @Override
    public void add(WmsEquipmentFailureStatisticsParam param){
        WmsEquipmentFailureStatistics entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WmsEquipmentFailureStatisticsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WmsEquipmentFailureStatisticsParam param){
        WmsEquipmentFailureStatistics oldEntity = getOldEntity(param);
        WmsEquipmentFailureStatistics newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WmsEquipmentFailureStatisticsResult findBySpec(WmsEquipmentFailureStatisticsParam param){
        return null;
    }

    @Override
    public List<WmsEquipmentFailureStatisticsResult> findListBySpec(WmsEquipmentFailureStatisticsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WmsEquipmentFailureStatisticsParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WmsEquipmentFailureStatisticsParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WmsEquipmentFailureStatistics getOldEntity(WmsEquipmentFailureStatisticsParam param) {
        return this.getById(getKey(param));
    }

    private WmsEquipmentFailureStatistics getEntity(WmsEquipmentFailureStatisticsParam param) {
        WmsEquipmentFailureStatistics entity = new WmsEquipmentFailureStatistics();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
