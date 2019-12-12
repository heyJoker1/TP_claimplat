package com.claimplat.serviceapi.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.core.entity.MerchantUrlConfig;

@Repository
public interface MerchantUrlConfigRepository extends PagingAndSortingRepository<MerchantUrlConfig, Long>{

	List<MerchantUrlConfig> findByMerchantCodeAndBusinessType(String merchantCode,BusinessForwardTypeEnum businessType);
}
