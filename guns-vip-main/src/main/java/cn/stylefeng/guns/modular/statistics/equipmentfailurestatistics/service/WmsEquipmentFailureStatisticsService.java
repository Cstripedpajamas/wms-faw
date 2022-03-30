package cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.entity.WmsEquipmentFailureStatistics;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.params.WmsEquipmentFailureStatisticsParam;
import cn.stylefeng.guns.modular.statistics.equipmentfailurestatistics.model.result.WmsEquipmentFailureStatisticsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 设备故障统计表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsEquipmentFailureStatisticsService extends IService<WmsEquipmentFailureStatistics> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsEquipmentFailureStatisticsParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsEquipmentFailureStatisticsParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsEquipmentFailureStatisticsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsEquipmentFailureStatisticsResult findBySpec(WmsEquipmentFailureStatisticsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsEquipmentFailureStatisticsResult> findListBySpec(WmsEquipmentFailureStatisticsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsEquipmentFailureStatisticsParam param);

}
