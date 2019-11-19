package com.claimplat.adminapi.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.claimplat.adminapi.vo.merchant.MerchantQuery;
import com.claimplat.common.enums.MerchantStatusEnum;
import com.claimplat.core.entity.Merchant;

/**
 * 分页查询商户信息
 * @author Joker
 *
 */
@Repository
public class MerchantRepositoryImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public Page<Merchant> getPage(MerchantQuery query, Pageable pageable){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Merchant> cq = cb.createQuery(Merchant.class);
		Root<Merchant> root = cq.from(Merchant.class);
		
		List<Predicate> paramList = fetchParamList(query,cb,root);
		
		Order order = cb.desc(root.get("createDeteTime"));
		cq.orderBy(order);
		cq.where(paramList.toArray(new Predicate[paramList.size()]));
		
		TypedQuery<Merchant> tq = em.createQuery(cq).setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
				.setMaxResults(pageable.getPageSize());
		List<Merchant> list = tq.getResultList();
		
		Long totalCount = queryCount(query);
		
		PageImpl<Merchant> page = new PageImpl<>(list,pageable,totalCount);
		return page;
	}

	private Long queryCount(MerchantQuery query) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Merchant> root = cq.from(Merchant.class);
		
		List<Predicate> paramList = fetchParamList(query, cb, root);
		
		cq.where(paramList.toArray(new Predicate[paramList.size()]));
		cq.select(cb.count(root));
		
		Long count = em.createQuery(cq).getSingleResult();
		return count;
	}

	private List<Predicate> fetchParamList(MerchantQuery query, CriteriaBuilder cb, Root<Merchant> root) {
		List<Predicate> paramList = new ArrayList<Predicate>();
		if(StringUtils.isNotEmpty(query.getCode())) {
			paramList.add(cb.equal(root.get("code").as(String.class), query.getCode()));
		}
		
		if(StringUtils.isNotEmpty(query.getName())) {
			paramList.add(cb.equal(root.get("name").as(String.class), query.getName()));
		}
		
		if(query.getStatus() != null) {
			paramList.add(cb.equal(root.get("status").as(MerchantStatusEnum.class), query.getStatus()));
		}
		return paramList;
	}
	
}
