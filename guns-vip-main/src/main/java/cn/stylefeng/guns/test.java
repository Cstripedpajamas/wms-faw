package cn.stylefeng.guns;

import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName test 测试信息数据
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2021/11/24 11:19
 * @Version 1.0
 **/
public class test {

    public static void main(String[] args) {
        Date dateNew=new Date();
        long dateNewNow=dateNew.getTime();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Date dateNew1=new Date();
        long dateNewNow1=dateNew1.getTime();
        System.out.printf(String.valueOf(dateNewNow1-dateNewNow));
    }

    public static String addZero(int number){
        StringBuffer sb = new StringBuffer();
        if (number< 10){
           return sb.append("000").append(number).toString();
        }
        else if (number >= 100){
            return sb.append("0").append(number).toString();
        }
        return sb.append("00").append(number).toString();
    }
}
