package cn.stylefeng.guns.modular.WebApi.Entity;

import java.io.Serializable;

/**
 * @Author: ll
 * @Date: 2021/12/14 20:38
 * @Version 1.0
 */
public class runBatch implements Serializable {
    private Integer code;
    private String msg;
    private String data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "runBatch{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
