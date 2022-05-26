package cn.stylefeng.guns;

import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String ki="2022-05-26T02:43:48";
        try {
            System.out.printf(sdf.parse(ki).toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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
