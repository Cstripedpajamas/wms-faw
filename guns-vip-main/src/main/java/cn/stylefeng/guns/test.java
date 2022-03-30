package cn.stylefeng.guns;

import cn.stylefeng.guns.modular.WebApi.Entity.Declension;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName test
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2021/11/24 11:19
 * @Version 1.0
 **/
public class test {

    public static void main(String[] args) {
        String a = "x";
        int b = 1;
        List<String[]> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            for (int i1 = 1; i1 < 11; i1++) {
                for (int i2 = 1; i2 < 16; i2++) {
                    String bCode = a + addZero(b);
                    String[] str = {bCode,i+"",i1+"",i2+"","1"};
                    list.add(str);
                    b++;
                }
            }
        }
        System.out.println(list.size());
        for (String[] strings : list) {
            System.out.println(Arrays.toString(strings));
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
