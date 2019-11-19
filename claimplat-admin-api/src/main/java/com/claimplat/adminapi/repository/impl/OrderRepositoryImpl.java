package com.claimplat.adminapi.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.claimplat.adminapi.vo.order.OrderQuery;
import com.claimplat.common.enums.BusinessHandleTypeEnum;
import com.claimplat.common.enums.OrderStatusEnum;
import com.claimplat.core.entity.Order;

@Repository
public class OrderRepositoryImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public Page<Order> getPage(OrderQuery query,Pageable pageable,LocalDateTime beginDateTime,LocalDateTime endDateTime){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> root = cq.from(Order.class);
		
		List<Predicate> paramList = fetchParamList(query,cb,root,beginDateTime,endDateTime);
		javax.persistence.criteria.Order order = cb.desc(root.get("createDateTime"));
		cq.orderBy(order);
		cq.where(paramList.toArray(new Predicate[paramList.size()]));
		
		TypedQuery<Order> tq = em.createQuery(cq).setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
				.setMaxResults(pageable.getPageSize());
		List<Order> list = tq.getResultList();
		Long totalCount = queryCount(query,beginDateTime,endDateTime);
		Page<Order> page = new PageImpl<>(list,pageable,totalCount);
		return page;
	}

	private List<Predicate> fetchParamList(OrderQuery query, CriteriaBuilder cb, Root<Order> root,
			LocalDateTime beginDateTime, LocalDateTime endDateTime) {
		List<Predicate> paramList = new ArrayList<Predicate>();
		
		if(StringUtils.isNotEmpty(query.getMerchantCode())) {
			paramList.add(cb.equal(root.get("merchantCode").as(String.class), query.getMerchantCode()));
		}
		
		if(StringUtils.isNotEmpty(query.getCode())) {
			paramList.add(cb.equal(root.get("code").as(String.class), query.getCode()));
		}
		
		if(StringUtils.isNotEmpty(query.getRequestId())) {
			paramList.add(cb.equal(root.get("requestId").as(String.class), query.getRequestId()));
		}
		
		if(query.getBeginDate() != null) {
			paramList.add(cb.between(root.get("createDateTime").as(LocalDateTime.class), beginDateTime, endDateTime));
		}
		
		if(query.getStatus() != null) {
			paramList.add(cb.equal(root.get("status").as(OrderStatusEnum.class), query.getStatus()));
		}				
		
		if(query.getBusinessType() != null) {
			paramList.add(cb.equal(root.get("businessType").as(BusinessHandleTypeEnum.class), query.getBusinessType()));
		}
		return paramList;
		
	}
	
	private Long queryCount(OrderQuery query, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Order> root = cq.from(Order.class);
		
		List<Predicate> paramList = fetchParamList(query, cb, root,beginDateTime,endDateTime);
		
		cq.where(paramList.toArray(new Predicate[paramList.size()]));
		cq.select(cb.count(root));
		
		Long count = em.createQuery(cq).getSingleResult();
		return count;
	}
}
