package com.steady.steadyback.config;

import com.steady.steadyback.util.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1) //인터셉터 체인
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/users", "/users/find_id", "/users/find_pw", "users/logout", "/css/**", "/*.ico", "/error");
    }

}
