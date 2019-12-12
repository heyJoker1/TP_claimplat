package com.claimplat.serviceapi.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConstant {
	
	@Value("${package.environment}")
	private String packageEnvironment;

	public String getPackageEnvironment() {
		return packageEnvironment;
	}
	
	
}
