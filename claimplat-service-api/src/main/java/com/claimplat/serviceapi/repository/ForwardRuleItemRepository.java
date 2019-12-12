package com.claimplat.serviceapi.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.core.entity.ForwardRuleItem;

@Repository
public interface ForwardRuleItemRepository extends PagingAndSortingRepository<ForwardRuleItem, Long>{
	
	List<ForwardRuleItem> findByRuleConfigId(Long ruleConfigId);
}
