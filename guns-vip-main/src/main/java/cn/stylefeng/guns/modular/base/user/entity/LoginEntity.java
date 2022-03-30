package cn.stylefeng.guns.modular.base.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ll
 * @Date: 2021/11/4 16:22
 * @Version 1.0
 */

public class LoginEntity {
    private String serialNumber;
    private String uPwd;
    private String state;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "serialNumber='" + serialNumber + '\'' +
                ", uPwd='" + uPwd + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
