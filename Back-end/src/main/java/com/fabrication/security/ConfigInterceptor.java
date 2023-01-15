package com.fabrication.security;


import com.fabrication.filters.TraceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Deprecated
@Component
public class ConfigInterceptor implements WebMvcConfigurer {
/*
    private final TraceInterceptor interceptor;

    @Autowired
    public ConfigInterceptor(TraceInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

 */

}
