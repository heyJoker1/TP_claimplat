package com.claimplat.adminapi.repository;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.claimplat.adminapi.vo.order.OrderQuery;
import com.claimplat.core.entity.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>{

	Page<Order> getPage(OrderQuery orderQuery, Pageable pageable, LocalDateTime beginDateTime,LocalDateTime endDateTime);

	Order findByCode(String code);

}
