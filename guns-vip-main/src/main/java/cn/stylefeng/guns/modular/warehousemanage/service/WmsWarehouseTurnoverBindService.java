package cn.stylefeng.guns.modular.warehousemanage.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseTurnoverBindParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseTurnoverBindResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 立库-周转箱绑定货物信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
public interface WmsWarehouseTurnoverBindService extends IService<WmsWarehouseTurnoverBind> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void add(WmsWarehouseTurnoverBindParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void delete(WmsWarehouseTurnoverBindParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    void update(WmsWarehouseTurnoverBindParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    WmsWarehouseTurnoverBindResult findBySpec(WmsWarehouseTurnoverBindParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
    List<WmsWarehouseTurnoverBindResult> findListBySpec(WmsWarehouseTurnoverBindParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-02
     */
     LayuiPageInfo findPageBySpec(WmsWarehouseTurnoverBindParam param);

    WmsWarehouseTurnoverBindResult findByTurnoverId(WmsWarehouseTurnoverBindParam param);
    List<WmsWarehouseTurnoverBindResult> findListTurnover(WmsWarehouseTurnoverBindParam param);

    LayuiPageInfo findLattice(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam);

    void delByTurnId(Long id);

    WmsWarehouseTurnoverBindResult findBySKU(String materialSku);

    LayuiPageInfo findTurnoverMsg(WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam);

}
