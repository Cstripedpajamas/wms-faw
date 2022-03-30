package cn.stylefeng.guns.modular.onetypecabinet.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RepairReturnTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RepairReturnTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1RepairReturnTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅰ类柜维修归还任务信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsCabinet1RepairReturnTaskService extends IService<WmsCabinet1RepairReturnTask> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void add(WmsCabinet1RepairReturnTaskParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void delete(WmsCabinet1RepairReturnTaskParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void update(WmsCabinet1RepairReturnTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    WmsCabinet1RepairReturnTaskResult findBySpec(WmsCabinet1RepairReturnTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsCabinet1RepairReturnTaskResult> findListBySpec(WmsCabinet1RepairReturnTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet1RepairReturnTaskParam param);

}
