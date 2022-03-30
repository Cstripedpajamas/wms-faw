package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.entity.WmsCabinet2UseTask;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.params.WmsCabinet2UseTaskParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2UseTask.model.result.WmsCabinet2UseTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅱ类柜领用任务信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2UseTaskService extends IService<WmsCabinet2UseTask> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsCabinet2UseTaskParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsCabinet2UseTaskParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsCabinet2UseTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsCabinet2UseTaskResult findBySpec(WmsCabinet2UseTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2UseTaskResult> findListBySpec(WmsCabinet2UseTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet2UseTaskParam param);

    WmsCabinet2UseTaskResult findById(String id);

    void updateState(String id, String i);

    void updateScropNumber(String orderId, String scrapCount);

    void updateStockMsgById(String runningId, String stockId, String locaNumber, String state);

    LayuiPageInfo findPageBySpec2(WmsCabinet2UseTaskParam wmsUserParam);
}
