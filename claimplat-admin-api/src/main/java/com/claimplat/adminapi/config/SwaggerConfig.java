package com.claimplat.adminapi.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.claimplat.adminapi.config.constant.SystemConstant;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Resource
	private SystemConstant systemConstant;
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(generateApiInfo())
				.enable(systemConstant.getEnableSwagger()).useDefaultResponseMessages(false).select()
				.apis(RequestHandlerSelectors.basePackage("com.claimplat.adminapi.rest")).build();
	}

	private ApiInfo generateApiInfo() {
		return new ApiInfoBuilder().title("理赔第三方中间平台系统").description("claimplat-admin-api的API文档").version("1.0.0").build();
	}
}
