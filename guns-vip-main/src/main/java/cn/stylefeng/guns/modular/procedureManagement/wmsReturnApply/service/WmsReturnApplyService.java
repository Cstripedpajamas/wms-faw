package cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.entity.WmsReturnApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.params.WmsReturnApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsReturnApply.model.result.WmsReturnApplyResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 归还申请信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsReturnApplyService extends IService<WmsReturnApply> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsReturnApplyParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsReturnApplyParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsReturnApplyParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsReturnApplyResult findBySpec(WmsReturnApplyParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsReturnApplyResult> findListBySpec(WmsReturnApplyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsReturnApplyParam param);

}
