package com.songsong.music.config;

import com.songsong.music.common.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // interceptor 적용
                .excludePathPatterns( // interceptor 제외
                        "/",
                        "/favicon.ico",
                        "/assets/**",
                        "/pages/login",
                        "/pages/signup",
                        "/auth/**",
                        "/users/**"
                );

    }
}

