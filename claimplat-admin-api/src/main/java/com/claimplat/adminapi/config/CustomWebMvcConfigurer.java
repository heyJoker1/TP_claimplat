package com.claimplat.adminapi.config;

import java.util.ArrayList;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.claimplat.adminapi.config.interceptor.LoginInterceptor;
import com.claimplat.adminapi.config.interceptor.RequestInterceptor;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer{

	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		RequestInterceptor requestInterceptor = new RequestInterceptor();
		ArrayList<String> swaggerList = new ArrayList<String>();
		swaggerList.add("/swagger*/**");
		swaggerList.add("/webjars/**");
		swaggerList.add("/v2/**");
		registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
		registry.addInterceptor(new LoginInterceptor(redisTemplate)).excludePathPatterns("/user/**","/out/**","/swagger*/**/","webjars/**","/v2/**");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}
}
