package com.claimplat.serviceapi.componentforward;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.common.exception.BusinessException;
import com.claimplat.core.entity.MerchantUrlConfig;
import com.claimplat.serviceapi.constant.SystemConstant;
import com.claimplat.serviceapi.repository.ForwardRuleConfigRepository;
import com.claimplat.serviceapi.repository.ForwardRuleItemRepository;
import com.claimplat.serviceapi.repository.MerchantUrlConfigRepository;
import com.claimplat.serviceapi.repository.OrderRepository;
import com.claimplat.serviceapi.repository.ParameterConfigRepository;

public abstract class AbstractComponentForwarder implements ComponentForwarder{

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	protected RestTemplate restTemplate;
	
	@Resource
	protected RestTemplate restTemplateWithProxy;
	
	@Resource
	protected RedisTemplate redisTemplate;
	
	@Resource
	protected SystemConstant systemConstant;
	
	@Resource
	protected OrderRepository orderRepository;
	
	@Resource
	protected ForwardRuleConfigRepository forwardRuleConfigRepository;
	
	@Resource
	protected ForwardRuleItemRepository forwardRuleItemRepository;
	
	@Resource
	protected MerchantUrlConfigRepository merchantUrlConfigRepository;
	
	@Resource
	protected ParameterConfigRepository parameterConfigRepository;
	
	protected MerchantUrlConfig fetchUrlConfig(String merchantCode,BusinessForwardTypeEnum businessType) {
		List<MerchantUrlConfig> urlConfigList = merchantUrlConfigRepository.findByMerchantCodeAndBusinessType(merchantCode, businessType);
		
		if(urlConfigList == null || urlConfigList.isEmpty()) {
			throw new BusinessException("业务系统接口地址信息无法找到");
		}
		
		MerchantUrlConfig urlConfig = urlConfigList.get(0);
		
		if(StringUtils.isEmpty(urlConfig.getUrl())) {
			throw new BusinessException("业务配置数据存在错误");
		}
		
		return urlConfig;
	}
}
