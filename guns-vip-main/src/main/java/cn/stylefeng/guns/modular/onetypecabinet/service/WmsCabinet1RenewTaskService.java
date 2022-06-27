package cn.stylefeng.guns.modular.onetypecabinet.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsCabinet1RenewTask;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsCabinet1RenewTaskParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsCabinet1RenewTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅰ类柜换新任务信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsCabinet1RenewTaskService extends IService<WmsCabinet1RenewTask> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void add(WmsCabinet1RenewTaskParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void delete(WmsCabinet1RenewTaskParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void update(WmsCabinet1RenewTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    WmsCabinet1RenewTaskResult findBySpec(WmsCabinet1RenewTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsCabinet1RenewTaskResult> findListBySpec(WmsCabinet1RenewTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet1RenewTaskParam param);

    WmsCabinet1RenewTaskResult findByTaskNumber(String taskNumber);
}
