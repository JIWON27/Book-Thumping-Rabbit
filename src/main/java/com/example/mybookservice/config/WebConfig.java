package com.example.mybookservice.config;

import com.example.mybookservice.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(new LoginCheckInterceptor())
        .order(1)
        .addPathPatterns("/**") // 인터셉터 모든 경로 표현은 /**로 한다.
        .excludePathPatterns("/", "/add/user", "/login","/logout", "/css/**"); //화이트리스트
  }

}
