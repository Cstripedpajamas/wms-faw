package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.result.WmsUseApplyResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 领用申请信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
@Transactional
public interface WmsUseApplyService extends IService<WmsUseApply> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsUseApplyParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsUseApplyParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsUseApplyParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsUseApplyResult findBySpec(WmsUseApplyParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsUseApplyResult> findListBySpec(WmsUseApplyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsUseApplyParam param);

    WmsUseApplyResult findById(String useRequestId);

}
