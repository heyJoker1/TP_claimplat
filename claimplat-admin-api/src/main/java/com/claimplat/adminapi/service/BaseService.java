package com.claimplat.adminapi.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.claimplat.adminapi.config.constant.SystemConstant;

public class BaseService {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected SystemConstant ststemConstant;
	
	@Resource
	protected RedisTemplate	redisTemplate;
}
