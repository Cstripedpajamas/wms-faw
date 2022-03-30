package cn.stylefeng.guns.modular.onetypeservice.generatorcode.provider;

import cn.stylefeng.guns.modular.onetypeservice.generatorcode.AbstractCode;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodePrefixEnum;
import org.springframework.stereotype.Component;

/**
 * Created by li wen ya on 2021/11/4
 * 周转箱编号生成器
 */
@Component(value = "turnoverBoxCode")
public class TurnoverBoxCode extends AbstractCode {
    @Override
    protected String prefix() {
        return CodePrefixEnum.ZZX_TASK_CODE.getPrefix();
    }
}
