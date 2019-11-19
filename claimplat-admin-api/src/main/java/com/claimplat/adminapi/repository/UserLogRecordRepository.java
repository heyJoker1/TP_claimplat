package com.claimplat.adminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.core.entity.UserLogRecord;

@Repository
public interface UserLogRecordRepository extends PagingAndSortingRepository<UserLogRecord, Long>{

}
