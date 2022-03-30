package cn.stylefeng.guns.modular.onetypeservice.enums;

/**
 * Created by li wen ya on 2021/11/4
 */
public enum CodePrefixEnum {

    PD_TASK_CODE("PD"),// Ⅰ类柜盘点任务
    HX_TASK_CODE("HX"),// Ⅰ类柜换新任务
    WXGH_TASK_CODE("WG"),// Ⅰ类柜维修归还任务
    WX_TASK_CODE ("WX"),// Ⅰ类柜维修任务
    GH_TASK_CODE("GH"),// Ⅰ类柜归还任务
    CG_TASK_CODE("CG"),// 采购入库
    BPBJ_TASK_CODE("BJ"),// 备品备件补货任务
    GJLY_TASK_CODE("LY"),// 工具领用
    ZZX_TASK_CODE("ZZ"),// 周转箱
    PROCESS_CODE("LC");// 流程单号

    private String prefix;

    CodePrefixEnum(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

}
