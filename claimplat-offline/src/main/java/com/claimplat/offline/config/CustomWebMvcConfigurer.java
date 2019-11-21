package com.claimplat.offline.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.claimplat.offline.config.interceptor.RequestInterceptor;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		RequestInterceptor requestInterceptor = new RequestInterceptor();
		registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}
}
