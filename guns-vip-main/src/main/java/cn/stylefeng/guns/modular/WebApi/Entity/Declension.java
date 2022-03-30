package cn.stylefeng.guns.modular.WebApi.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Author: ll
 * @Date: 2021/11/3 13:43
 * @Version 1.0
 */
@ApiModel(value = "智能柜返回结果")
public class Declension implements Serializable {
    @ApiModelProperty(value = "返回结果")
    private String result;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "库位编号集合")
    private String[] locationIdList;

    @Override
    public String toString() {
        return "Declension{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", locationIdList=" + Arrays.toString(locationIdList) +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getLocationIdList() {
        return locationIdList;
    }

    public void setLocationIdList(String[] locationIdList) {
        this.locationIdList = locationIdList;
    }
}
