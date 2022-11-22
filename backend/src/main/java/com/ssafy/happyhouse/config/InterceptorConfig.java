package com.ssafy.happyhouse.config;

import com.ssafy.happyhouse.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //인터셉터 등록
    @Autowired
    LoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //해당 요청 인터셉트
		registry.addInterceptor(interceptor).addPathPatterns("/trade/**");
    }
}
