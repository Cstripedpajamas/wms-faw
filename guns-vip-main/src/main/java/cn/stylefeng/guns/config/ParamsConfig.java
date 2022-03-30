package cn.stylefeng.guns.config;

import org.apache.ibatis.annotations.Param;

/**
 * <p> 参数配置
 *
 * @author lhx
 * @version 1.0
 * @date 2021-11-04 16:12
 * @since 2021-11-04
 */

public class ParamsConfig {
    // 可用
    public final static String ENABLE = "0";
    // 禁用
    public final static String DISABLE = "1";
    // 工具
    public final static String TOOL = "1";
    // 备品备件
    public final static String SPAREPARTS = "2";

    // 订单类别-工具领用
    public final static String ORDER_TYPE_TOOL_USE = "A";
    // 订单类别-补货出库
    public final static String ORDER_TYPE_REPLENISHMENT = "B";
    // 订单类别-出库
    public final static String ORDER_TYPE_WAREHOUSE_OUT = "C";

    // 出仓货物类型-工具
    public final static String GOODS_TYPE_TOOL = "A";
    // 出仓货物类型-备品备件
    public final static String GOODS_TYPE_SPARE_PARTS = "B";
    // 出仓货物类型-空周转箱
    public final static String GOODS_TYPE_EMPTY_TURNOVER_BOX = "C";

    // 出仓分拣-人工
    public final static String SORTING_INFO_ARTIFICIALITY = "A";
    // 出仓分拣-自动
    public final static String SORTING_INFO_AUTO = "A";

    // 状态
    public final static String STATE_ZERO = "0";
    public final static String STATE_ONE = "1";
    public final static String STATE_TWO = "2";
    public final static String STATE_THREE = "3";
    public final static String STATE_FOUR = "4";
    public final static String STATE_FIVE = "5";
    public final static String STATE_SIX = "6";


}
