package com.flance.saas.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器配置
 * @author jhf
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    TenantAppInterceptor tenantAppInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantAppInterceptor).addPathPatterns("/tenant-app/**");
    }

}
