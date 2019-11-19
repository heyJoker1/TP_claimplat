package com.claimplat.adminapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.adminapi.service.ParameterConfigService;

import io.swagger.annotations.Api;

@Api(value = "商户管理")
@RestController("/parameterConfig")
public class ParameterConfigRest {
	
	@Autowired
	private ParameterConfigService parameterConfigService;
	
	 
} 
