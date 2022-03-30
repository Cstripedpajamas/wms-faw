package cn.stylefeng.guns.modular.onetypeservice.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by li wen ya on 2021/11/12
 */
@Data
public class LoginResponse implements Serializable{

    private static final long serialVersionUID=1L;

    public LoginResponse() {
    }

    public LoginResponse(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private String value;

}
