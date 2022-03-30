package cn.stylefeng.guns.modular.onetypeservice.response;

import lombok.Data;

/**
 * Created by li wen ya on 2021/11/24
 */
@Data
public class LoginWarehouseResponse {

    private static final long serialVersionUID=1L;

    public LoginWarehouseResponse() {
    }

    public LoginWarehouseResponse(String name, String value,String route) {
        this.name = name;
        this.value = value;
        this.route = route;
    }

    private String name;

    private String value;

    private String route;

}
