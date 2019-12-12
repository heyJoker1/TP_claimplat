package com.claimplat.serviceapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.core.entity.ParameterConfig;

@Repository
public interface ParameterConfigRepository extends PagingAndSortingRepository<ParameterConfig, Long>{

	ParameterConfig findByName(String name);
}
