package com.claimplat.adminapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.adminapi.vo.merchanturl.MerchantUrlQuery;
import com.claimplat.core.entity.MerchantUrlConfig;

@Repository
public interface MerchantUrlConfigRepository extends PagingAndSortingRepository<MerchantUrlConfig, Long>{

	Page<MerchantUrlConfig> getPage(MerchantUrlQuery merchantUrlQuery, Pageable pageable);

	MerchantUrlConfig findByCode(String code);

	void deleteByCode(String code);

}
