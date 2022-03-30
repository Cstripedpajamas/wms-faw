package cn.stylefeng.guns.modular.onetypeservice.enums;

/**
 * Created by li wen ya on 2021/11/4
 */
public enum CodeProviderEnum {

    // 任务编号生成器Bean名字
    inventoryTaskCode("inventoryTaskCode"),// 一类柜盘点任务编号生成器
    renewTaskCode("renewTaskCode"),// 一类柜换新任务生成器
    repairCode("repairCode"),// 一类柜维修任务编号生成器
    repairReturnCode("repairReturnCode"),// 一类柜维修归还任务生成器
    returnCode("returnCode"),// 一类柜归还任务编号生成器
    sparePartCode("sparePartCode"),// 备品备件补货任务
    toolClaimCode("toolClaimCode"),// 工具领用编码生成器
    turnoverBoxCode("turnoverBoxCode"),// 周转箱编号生成器
    purchaseWarehousingCode("purchaseWarehousingCode"),// 采购入库编号生成器
    processCode("processCode");// 采购入库编号生成器

    private String provider;

    CodeProviderEnum(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

}
