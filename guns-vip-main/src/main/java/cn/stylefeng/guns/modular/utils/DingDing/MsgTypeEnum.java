package cn.stylefeng.guns.modular.utils.DingDing;

/**
 * @Author: ll
 * @Date: 2021/12/1 15:12
 * @Version 1.0
 */
public enum  MsgTypeEnum {
    MSG_TYPE_TEXT("text", "MSG_TYPE_TEXT"),
    MSG_TYPE_LINK("link", "MSG_TYPE_LINK"),
    MSG_TYPE_MARKDOWN("markdown", "MSG_TYPE_MARKDOWN"),
    MSG_TYPE_ACTION_CARD("actionCard", "MSG_TYPE_ACTION_CARD"),
    MSG_TYPE_FEED_CARD("feedCard", "MSG_TYPE_FEED_CARD");

    private String value;
    private String code;

    MsgTypeEnum(String value, String code) {
        this.code = code;
        this.value = value;
    }

    public static MsgTypeEnum getEnumValue(String value){
        for (MsgTypeEnum constants : values()) {
            if (constants.getValue() == value) {
                return constants;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

}
