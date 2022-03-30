package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 立库-周转箱信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseTurnoverService extends IService<WmsWarehouseTurnover> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void add(WmsWarehouseTurnoverParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void delete(WmsWarehouseTurnoverParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void update(WmsWarehouseTurnoverParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    WmsWarehouseTurnoverResult findBySpec(WmsWarehouseTurnoverParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseTurnoverResult> findListBySpec(WmsWarehouseTurnoverParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsWarehouseTurnoverParam param);

    WmsWarehouseTurnoverResult findByTurnoverNumber(String code);

    void updateLatticeNumber(String turnoverId, String i);

    WmsWarehouseTurnoverResult findById(String turnoverId);

    WmsWarehouseTurnoverResult findByBarCode(String barCode);
}
