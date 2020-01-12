package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pengjunzhen
 * @description Swagger2配置类
 * @date 2020/1/5 20:09
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 配置swagger2核心配置 docket
     * http://localhost:8088/swagger-ui.html 原路径
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("电商平台接口api") // 文档标题
                .contact(new Contact("imooc",
                        "https://www.imooc.com",
                        "pengjunzhenwork@163.com")) // 联系人信息
                .description("api文档")   // 详细信息
                .version("1.0.0")   // 文档版本号
                .termsOfServiceUrl("http://www.imooc.com") // 网站地址
                .build();
    }
}
