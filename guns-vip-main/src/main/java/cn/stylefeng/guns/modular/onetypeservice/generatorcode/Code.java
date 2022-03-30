package cn.stylefeng.guns.modular.onetypeservice.generatorcode;

/**
 * Created by li wen ya on 2021/11/4
 */
public interface Code {

    /**
     * @param code 要增序的编号
     * @return 根据规则增序后的编号
     */
    String createCode(String code);

}
