package cn.stylefeng.guns.modular.onetypeservice.generatorcode.provider;

import cn.stylefeng.guns.modular.onetypeservice.generatorcode.AbstractCode;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodePrefixEnum;
import org.springframework.stereotype.Component;

/**
 * Created by li wen ya on 2021/11/4]
 * 工具领用编码生成器
 */
@Component(value = "toolClaimCode")
public class ToolClaimCode extends AbstractCode{

    @Override
    protected String prefix() {
        return CodePrefixEnum.GJLY_TASK_CODE.getPrefix();
    }
}
