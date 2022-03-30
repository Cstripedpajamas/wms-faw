package cn.stylefeng.guns.modular.utils;

import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.model.params.WmsIntelligentCabinet2ConfParam;
import cn.stylefeng.guns.modular.base.intelligentcabinet2conf.service.WmsIntelligentCabinet2ConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p> 用于判断的工具类
 *
 * @author lhx
 * @version 1.0
 * @date 2021-11-02 10:48
 * @since 2021-11-02
 */

@Component
public class JudgeUtil {

    @Autowired
    private WmsIntelligentCabinet2ConfService wmsIntelligentCabinet2ConfServiceTemp;
    private static WmsIntelligentCabinet2ConfService wmsIntelligentCabinet2ConfService;

    @PostConstruct
    public void init() {
        wmsIntelligentCabinet2ConfService = wmsIntelligentCabinet2ConfServiceTemp;
    }

    /**
     * 通过编号，判断是否可以删除用户
     *
     * @param serialNumber
     * @return
     */
    public static boolean canDeleteUser(String serialNumber) {
        boolean flag = true;
//        // 判断Ⅱ类柜物料补货阈值配置表是否用到用户信息
//        WmsIntelligentCabinet2ConfParam param = new WmsIntelligentCabinet2ConfParam();
//        param.setOperator(serialNumber);
//        flag = wmsIntelligentCabinet2ConfService.findListBySpec(param).isEmpty();
        return flag;
    }

    /**
     * 通过物料类型id，判断是否可以删除
     *
     * @param id
     * @return
     */
    public static boolean canDeleteMaterialType(long id) {
        boolean flag = true;
        // TODO: 2021/11/2 添加判断逻辑
        return flag;
    }

    /**
     * 通过工具id，判断是否可以删除
     *
     * @param id
     * @return
     */
    public static boolean canDeleteMaterialTool(long id) {
        boolean flag = true;
        // TODO: 2021/11/2 添加判断逻辑
        return flag;
    }

}
