package com.claimplat.adminapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.claimplat.adminapi.vo.merchant.MerchantQuery;
import com.claimplat.core.entity.Merchant;

/**
 * 商户信息管理的查改增
 * @author Joker
 *
 */
@Repository
public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long>{

	/**分页查询商户信息
	 * @param merQuery
	 * @param pageable
	 * @return
	 */
	Page<Merchant> getPage(MerchantQuery merQuery, Pageable pageable);

	/**查询商户详情
	 * @param code
	 * @return
	 */
	Merchant findByCode(String code);
	
	/**
	 * 修改商户信息
	 * @param name
	 * @param code
	 * @return
	 */
	Long countByNameAndCodeNot(String name, String code);

	/**
	 * 新增商户信息
	 * @param name
	 * @return
	 */
	Long countByName(String name);

	

}
