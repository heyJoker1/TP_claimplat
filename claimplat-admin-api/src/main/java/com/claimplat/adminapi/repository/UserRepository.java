package com.claimplat.adminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.core.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	User findByName(String userName);

	User findByIdEquals(Long userId);

}
