package cn.stylefeng.guns.modular.onetypeservice.generatorcode.provider;

import cn.stylefeng.guns.modular.onetypeservice.generatorcode.AbstractCode;
import cn.stylefeng.guns.modular.onetypeservice.enums.CodePrefixEnum;
import org.springframework.stereotype.Component;

/**
 * Created by li wen ya on 2021/11/4
 * 备品备件补货任务
 */
@Component(value = "sparePartCode")
public class SparePartCode extends AbstractCode {
    @Override
    protected String prefix() {
        return CodePrefixEnum.BPBJ_TASK_CODE.getPrefix();
    }
}
