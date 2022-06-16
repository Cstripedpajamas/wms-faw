package cn.stylefeng.guns.modular.base.packageInfo.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.base.packageInfo.model.params.WmsPackinfoParam;
import cn.stylefeng.guns.modular.base.packageInfo.model.result.WmsPackinfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 包装信息表 服务类
 * </p>
 *
 * @author ll
 * @since 2021-12-14
 */
public interface WmsPackinfoService extends IService<WmsPackinfo> {

    /**
     * 新增
     *
     * @author ll
     * @Date 2021-12-14
     */
    void add(WmsPackinfoParam param);

    /**
     * 删除
     *
     * @author ll
     * @Date 2021-12-14
     */
    void delete(WmsPackinfoParam param);

    /**
     * 更新
     *
     * @author ll
     * @Date 2021-12-14
     */
    void update(WmsPackinfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ll
     * @Date 2021-12-14
     */
    WmsPackinfoResult findBySpec(WmsPackinfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ll
     * @Date 2021-12-14
     */
    List<WmsPackinfoResult> findListBySpec(WmsPackinfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ll
     * @Date 2021-12-14
     */
     LayuiPageInfo findPageBySpec(WmsPackinfoParam param);

    WmsPackinfo findByMaterialTypeId(String id);
}
