package com.heyou.springboot.config;

import com.heyou.springboot.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器配置
 * @author heyou
 * @date 2019-11-21 17:15
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //将组件注册在容器中
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer(){
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源； *.css,*.js
                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/user/login","/static/**","/webjars/**");
                // /**  表示拦截所有路径下的所有请求
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/user/**");
            }
        };
    }
}
