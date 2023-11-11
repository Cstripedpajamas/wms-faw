package cn.stylefeng.guns.modular.base.materialType.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 物料类型信息表 服务类
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
public interface WmsMaterialTypeService extends IService<WmsMaterialType> {

    /**
     * 新增
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void add(WmsMaterialTypeParam param);

    /**
     * 删除
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void delete(WmsMaterialTypeParam param);

    /**
     * 更新
     *
     * @author lhx
     * @Date 2021-11-01
     */
    void update(WmsMaterialTypeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    WmsMaterialTypeResult findBySpec(WmsMaterialTypeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
    List<WmsMaterialTypeResult> findListBySpec(WmsMaterialTypeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lhx
     * @Date 2021-11-01
     */
     LayuiPageInfo findPageBySpec(WmsMaterialTypeParam param);

    WmsMaterialTypeResult findById(String sMaterialTypeId);

    WmsMaterialTypeResult findByMaterialSku(String materialSku);

    void insertListBatch(List<WmsMaterialTypeParam> param);

    List<WmsMaterialType> findAll();

    List<WmsMaterialType> findAllMaterialType();

    List<WmsMaterialType> findAllMaterialNo(WmsMaterialTypeParam param);

    List<WmsMaterialType> findMaterialRes(WmsMaterialTypeParam param);
}
