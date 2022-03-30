package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.entity.WmsCabinet2InputScrap;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.params.WmsCabinet2InputScrapParam;
import cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2InputScrap.model.result.WmsCabinet2InputScrapResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Ⅱ类柜投入报废信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-11-01
 */
public interface WmsCabinet2InputScrapService extends IService<WmsCabinet2InputScrap> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-11-01
     */
    void add(WmsCabinet2InputScrapParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-11-01
     */
    void delete(WmsCabinet2InputScrapParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-11-01
     */
    void update(WmsCabinet2InputScrapParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    WmsCabinet2InputScrapResult findBySpec(WmsCabinet2InputScrapParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
    List<WmsCabinet2InputScrapResult> findListBySpec(WmsCabinet2InputScrapParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsCabinet2InputScrapParam param);

}
