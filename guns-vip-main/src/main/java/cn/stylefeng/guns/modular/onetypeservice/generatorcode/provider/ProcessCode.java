package cn.stylefeng.guns.modular.onetypeservice.generatorcode.provider;

import cn.stylefeng.guns.modular.onetypeservice.enums.CodePrefixEnum;
import cn.stylefeng.guns.modular.onetypeservice.generatorcode.AbstractCode;
import org.springframework.stereotype.Component;

/**
 * Created by li wen ya on 2021/11/22
 * 流程单号生成器
 */
@Component(value = "processCode")
public class ProcessCode  extends AbstractCode {
    @Override
    protected String prefix() {
        return CodePrefixEnum.PROCESS_CODE.getPrefix();
    }
}
