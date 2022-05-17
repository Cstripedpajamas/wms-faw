package cn.stylefeng.guns.modular.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ll
 * @Date: 2021/11/3 9:35
 * @Version 1.0
 */
@Configuration
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
//    private static RestTemplate restTemplate = new RestTemplate();
    @Bean
    public static RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(30000);// 设置连接超时，单位毫秒
        requestFactory.setReadTimeout(30000);  //设置读取超时
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.getMessageConverters().clear();
        // 转为JSON
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        logger.info("Resttemplate initialization completed");
        return restTemplate;
    }
}
