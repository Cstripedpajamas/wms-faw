package cn.stylefeng.guns.modular.onetypecabinet.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1LatticeType;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1LatticeTypeParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1LatticeTypeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅰ类柜格口类型信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
public interface WmsIntelligentCabinet1LatticeTypeService extends IService<WmsIntelligentCabinet1LatticeType> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void add(WmsIntelligentCabinet1LatticeTypeParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void delete(WmsIntelligentCabinet1LatticeTypeParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    void update(WmsIntelligentCabinet1LatticeTypeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    WmsIntelligentCabinet1LatticeTypeResult findBySpec(WmsIntelligentCabinet1LatticeTypeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
    List<WmsIntelligentCabinet1LatticeTypeResult> findListBySpec(WmsIntelligentCabinet1LatticeTypeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsIntelligentCabinet1LatticeTypeParam param);

}
