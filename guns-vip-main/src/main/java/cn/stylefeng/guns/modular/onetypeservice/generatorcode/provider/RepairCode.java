package cn.stylefeng.guns.modular.onetypeservice.generatorcode.provider;

import cn.stylefeng.guns.modular.onetypeservice.generatorcode.AbstractCode;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodePrefixEnum;
import org.springframework.stereotype.Component;

/**
 * Created by li wen ya on 2021/11/4
 * 一类柜维修任务编号生成器
 */
@Component(value = "repairCode")
public class RepairCode extends AbstractCode{

    @Override
    protected String prefix() {
        return CodePrefixEnum.WX_TASK_CODE.getPrefix();
    }
}
