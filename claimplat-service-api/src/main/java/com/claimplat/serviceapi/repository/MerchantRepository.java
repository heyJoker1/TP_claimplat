package com.claimplat.serviceapi.repository;

import org.springframework.stereotype.Repository;

import com.claimplat.common.enums.ThridpartTypeEnum;
import com.claimplat.core.entity.Merchant;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface MerchantRepository extends PagingAndSortingRepository<Merchant,Long>{
	
	Merchant findByCode(String merchantCode);
	
	Merchant findByThridpartType(ThridpartTypeEnum thridpartType);
}
