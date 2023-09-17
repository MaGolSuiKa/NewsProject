package com.geekaca.news.newssys.config;

import com.geekaca.news.newssys.utils.NewsConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // http://localhost/upload/20230912_15591810.png
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + NewsConstants.UPLOAD_PATH);
    }
}
