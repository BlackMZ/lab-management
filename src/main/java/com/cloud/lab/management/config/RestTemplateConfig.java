package com.cloud.lab.management.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

/**
 * TestTemplate 配置
 */
@Configuration
@ConditionalOnClass(value = {RestTemplate.class, HttpClient.class})
public class RestTemplateConfig {

    /**
     * 连接超时默认2s
     */
    private static final int CONNECT_TIME_OUT = 5000;
    /**
     * 读取超时默认30s
     */
    private static final int READ_TIME_OUT = 5000;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //单位为ms
        factory.setReadTimeout(READ_TIME_OUT);
        //单位为ms
        factory.setConnectTimeout(CONNECT_TIME_OUT);
        return factory;
    }
}
