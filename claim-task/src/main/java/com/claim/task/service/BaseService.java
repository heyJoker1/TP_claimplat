package com.claim.task.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.claim.task.constant.SystemConstant;

public class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected SystemConstant systemConstant;
	
	@Resource
	protected RestTemplate restTemplate;
}
