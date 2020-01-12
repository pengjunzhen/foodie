package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * @author pengjunzhen
 * @description 跨域配置
 * @date 2020/1/12 21:14
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        // 1、添加cors 配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("*");

        // 设置是否发送cookie 信息
        config.setAllowCredentials(true);

        // 设置允许请求的方式
        config.setAllowedMethods(Collections.singletonList("*"));

        // 设置允许的header
        config.setAllowedHeaders(Collections.singletonList("*"));

        // 2、为 url 添加映射地址
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3、返回重新定义好的corsSource
        return new CorsFilter(corsSource);

    }
}
