package com.claimplat.serviceapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.core.entity.ForwardRuleConfig;

@Repository
public interface ForwardRuleConfigRepository extends PagingAndSortingRepository<ForwardRuleConfig, Long> {

	ForwardRuleConfig findByBusinessTypeAndMerchantCode(BusinessForwardTypeEnum businessType,String merchantCode);
}
