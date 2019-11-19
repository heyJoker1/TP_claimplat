package com.claimplat.adminapi.config.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConstant {
	
	@Value("${package.environment}")
	private String packageEnvironment;
	
	@Value("${swagger.enable}")
	private Boolean enableSwagger;

	public String getPackageEnvironment() {
		return packageEnvironment;
	}

	public Boolean getEnableSwagger() {
		return enableSwagger;
	}
	
}
