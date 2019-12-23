package com.claim.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.claim.task.config.interceptor.RequestInterceptor;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		RequestInterceptor requestIntercepter = new RequestInterceptor();
		registry.addInterceptor(requestIntercepter).addPathPatterns("/**");	
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}
}
