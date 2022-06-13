package cn.stylefeng.guns.modular.onetypecabinet.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsPrintInfo;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsPrintInfoParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsPrintInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 条码打印信息表 服务类
 * </p>
 *
 * @author liwenya
 * @since 2021-11-24
 */
public interface WmsPrintInfoService extends IService<WmsPrintInfo> {

    /**
     * 新增
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    void add(WmsPrintInfoParam param);

    /**
     * 删除
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    void delete(WmsPrintInfoParam param);

    /**
     * 更新
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    void update(WmsPrintInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    WmsPrintInfoResult findBySpec(WmsPrintInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-24
     */
    List<WmsPrintInfoResult> findListBySpec(WmsPrintInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author liwenya
     * @Date 2021-11-24
     */
     LayuiPageInfo findPageBySpec(WmsPrintInfoParam param);

    WmsPrintInfoResult findLastCode();

    void insertMatch(List<WmsPrintInfo> wmsPrintInfos);
}
