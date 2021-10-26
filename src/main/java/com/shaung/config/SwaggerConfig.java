package com.shaung.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @Auther:shuang
 * @Date:2021/3/12 - 03 - 12 - 20:16:53
 * @Description: vue_cli
 * @versin:1.0
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
//必须存在 扫描api controller层
@ComponentScan(basePackages = {"com.shaung.controller"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.wsmyskxz.springboot.Controller"))
//                .paths(PathSelectors.any())
//                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("shuang","暂无","985641643@qq.com");
        return new ApiInfoBuilder()
                .title("诚信商城API接口")
                .description("API接口")
//                .termsOfServiceUrl("http://www.xxx.com/")
                .contact(contact)
                .version("1.0")
                .build();
    }

}

