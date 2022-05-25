package com.dulich.dulich.configs;

import com.dulich.dulich.interceptor.AdminInterceptor;
import com.dulich.dulich.interceptor.LoginInterceptor;
import com.dulich.dulich.interceptor.UserInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            .addPathPatterns("/tour/book/**")
            .addPathPatterns("/admin/**")
            .addPathPatterns("/user/*")
            .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

        registry.addInterceptor(new UserInterceptor())
            .addPathPatterns("/login")
            .addPathPatterns("/admin/**")
            .addPathPatterns("/signup")
            .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

        registry.addInterceptor(new AdminInterceptor())
            .excludePathPatterns("/admin/**")
            .excludePathPatterns("/logout")
            .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
    }
}
