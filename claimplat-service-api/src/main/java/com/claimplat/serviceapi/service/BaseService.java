package com.claimplat.serviceapi.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.claimplat.common.bean.request.BaseHandleRequest;
import com.claimplat.common.bean.request.BasePlatfromHandleRequest;
import com.claimplat.common.constant.ReturnCode;
import com.claimplat.common.enums.MerchantStatusEnum;
import com.claimplat.common.enums.ThridpartTypeEnum;
import com.claimplat.common.exception.ValidateException;
import com.claimplat.core.entity.Merchant;
import com.claimplat.serviceapi.componentforward.factory.ComponentForwarderFactory;
import com.claimplat.serviceapi.constant.SystemConstant;
import com.claimplat.serviceapi.repository.MerchantRepository;
import com.claimplat.serviceapi.repository.OrderRepository;

public class BaseService {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	protected MerchantRepository merchantRepository;
	
	@Resource
	protected SystemConstant systemConstant;
	
	@Resource
	protected OrderRepository orderRepository;
	
	@Resource
	protected ComponentForwarderFactory componentForwarderFactory;
	
	public Merchant validateAndFetchMerchant(BaseHandleRequest handleRequest) {
		BasePlatfromHandleRequest request = (BasePlatfromHandleRequest) handleRequest;
		if(StringUtils.isEmpty(request.getMerchantCode())) {
			throw new IllegalArgumentException("商户号不能为空");
		}
		
		Merchant merchant = merchantRepository.findByCode(request.getMerchantCode());
		if(merchant == null) {
			throw new ValidateException(ReturnCode.REC_2001.getCode(),ReturnCode.REC_2001.getName());
		}
		if(merchant.getStatus() == MerchantStatusEnum.FORBIDDEN) {
			throw new ValidateException(ReturnCode.REC_2002.getCode(),ReturnCode.REC_2002.getName());
		}
		
		return merchant;
	}
	
	public Merchant validateAndFetchMerchant(ThridpartTypeEnum thridpartType) {
		if(thridpartType == null) {
			throw new IllegalArgumentException("无法标识商户信息");
		}
		
		Merchant merchant = merchantRepository.findByThridpartType(thridpartType);
		if(merchant == null) {
			throw new ValidateException(ReturnCode.REC_2001.getCode(),ReturnCode.REC_2001.getName());
		}
		if(merchant.getStatus() == MerchantStatusEnum.FORBIDDEN) {
			throw new ValidateException(ReturnCode.REC_2002.getCode(),ReturnCode.REC_2002.getName());
		}
		
		return merchant;
	}
}
