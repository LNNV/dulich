package com.dulich.dulich.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/user").setViewName("user-account");
        registry.addViewController("/admin/account").setViewName("admin-account");
        registry.addViewController("/admin/tour").setViewName("admin-tour");
        registry.addViewController("/admin/booked").setViewName("admin-booked");
        registry.addViewController("/admin/news").setViewName("admin-news");
        registry.addViewController("/tours").setViewName("tours");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/news").setViewName("news");
        registry.addViewController("/tour/book").setViewName("book-tour");
	}
}
