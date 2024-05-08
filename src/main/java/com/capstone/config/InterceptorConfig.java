package com.capstone.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor())
                .order(1)
                .addPathPatterns("/update/**", "/api/addReview/**")
                .excludePathPatterns(
                        "/signUp", "/login" // Login
                        , "/session-check" // MemberController
                );
    }
}

