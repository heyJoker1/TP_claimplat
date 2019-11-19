package com.claimplat.adminapi.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlQuery;
import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.core.entity.MerchantUrlConfig;

public class MerchantUrlConfigRepositoryImpl {

	@Autowired
	private EntityManager em;
	
	public Page<MerchantUrlConfig> getPage(MerchantUrlQuery query, Pageable pageable){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MerchantUrlConfig> cq = cb.createQuery(MerchantUrlConfig.class);
		Root<MerchantUrlConfig> root = cq.from(MerchantUrlConfig.class);
		
		List<Predicate> paramList = fetchParamList(query,cb,root);
		
		Order order = cb.desc(root.get("createDeteTime"));
		cq.orderBy(order);
		cq.where(paramList.toArray(new Predicate[paramList.size()]));
		
		TypedQuery<MerchantUrlConfig> tq = em.createQuery(cq).setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
				.setMaxResults(pageable.getPageSize());
		List<MerchantUrlConfig> list = tq.getResultList();
		
		Long totalCount = queryCount(query);
		
		PageImpl<MerchantUrlConfig> page = new PageImpl<>(list,pageable,totalCount);
		return page;
	}

	private List<Predicate> fetchParamList(MerchantUrlQuery query, CriteriaBuilder cb, Root<MerchantUrlConfig> root) {
		List<Predicate> paramList = new ArrayList<Predicate>();
		
		if(StringUtils.isNotEmpty(query.getMerchantCode())) {
			paramList.add(cb.equal(root.get("merchantCode").as(String.class), query.getMerchantCode()));
		}
		
		if(query.getBusinessType() != null) {
			paramList.add(cb.equal(root.get("businessType").as(BusinessForwardTypeEnum.class), query.getBusinessType()));
		}
		return paramList;
	}

	private Long queryCount(MerchantUrlQuery query) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<MerchantUrlConfig> root = cq.from(MerchantUrlConfig.class);
		
		List<Predicate> paramList = fetchParamList(query, cb, root);
		
		cq.where(paramList.toArray(new Predicate[paramList.size()]));
		cq.select(cb.count(root));
		
		Long count = em.createQuery(cq).getSingleResult();
		return count;
	}
}
