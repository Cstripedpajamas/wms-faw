package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTaskIn;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTaskInParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTaskInResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 立库-仓库任务管理信息表-入仓 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseTaskInService extends IService<WmsWarehouseTaskIn> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void add(WmsWarehouseTaskInParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void delete(WmsWarehouseTaskInParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void update(WmsWarehouseTaskInParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    WmsWarehouseTaskInResult findBySpec(WmsWarehouseTaskInParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseTaskInResult> findListBySpec(WmsWarehouseTaskInParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsWarehouseTaskInParam param);

}
