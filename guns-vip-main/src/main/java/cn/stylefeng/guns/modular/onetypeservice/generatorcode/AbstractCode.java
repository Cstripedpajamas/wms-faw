package cn.stylefeng.guns.modular.onetypeservice.generatorcode;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by li wen ya on 2021/11/4
 */
public abstract class AbstractCode implements Code {

    @Override
    public String createCode(String code) {
        String prefix = prefix();
        String timeCode = timeCode();
        String order = order(code);
        return prefix + timeCode + order;
    }

    /**
     * @return 日期字符串
     */
    private String timeCode(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * @param code 要增序的code码
     * @return 返回数据
     */
    private String order(String code){
        return RandomStringUtils.randomNumeric(4);
    }

    protected abstract String prefix();

}
