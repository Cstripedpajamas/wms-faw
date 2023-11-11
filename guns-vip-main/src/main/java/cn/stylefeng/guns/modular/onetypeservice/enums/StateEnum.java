package cn.stylefeng.guns.modular.onetypeservice.enums;

/**
 * Created by li wen ya on 2021/11/4
 */
public enum StateEnum {

    ZERO("0"),ONE("1"),TWO("2"),THREE("3"),FOUR ("4"),FIVE("5"),SIX("6"),SEVEN("7");

    private String state;

    StateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
