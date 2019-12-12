package com.claimplat.serviceapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.core.entity.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>{

	Order findByCode(String code);
}
