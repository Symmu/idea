package com.shaung.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther:shuang
 * @Date:2021/3/12 - 03 - 12 - 20:35:34
 * @Description: vue_cli
 * @versin:1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations ("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations ("classpath:/META-INF/resources/webjars/");
    }

}
