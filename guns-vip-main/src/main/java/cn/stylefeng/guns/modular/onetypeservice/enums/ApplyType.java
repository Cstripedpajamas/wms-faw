package cn.stylefeng.guns.modular.onetypeservice.enums;

/**
 * Created by li wen ya on 2021/11/12
 * 申请类型
 */
public enum ApplyType {

    A("A"),
    B("B"),
    C("C");

    private String type;

    ApplyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
