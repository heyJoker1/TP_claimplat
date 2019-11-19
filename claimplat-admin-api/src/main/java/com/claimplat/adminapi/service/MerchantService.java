package com.claimplat.adminapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claimplat.adminapi.repository.MerchantRepository;
import com.claimplat.adminapi.util.CodeUtil;
import com.claimplat.adminapi.vo.PageInfoVo;
import com.claimplat.adminapi.vo.merchant.MerchantAdd;
import com.claimplat.adminapi.vo.merchant.MerchantQuery;
import com.claimplat.adminapi.vo.merchant.MerchantUpdate;
import com.claimplat.adminapi.vo.merchant.MerchantVo;
import com.claimplat.common.exception.BusinessException;
import com.claimplat.core.entity.Merchant;

/**
 * 商户信息管理的查改增
 * @author Joker
 *
 */
@Service
@Transactional
public class MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	
	/**
	 * 分页查询商户信息
	 * @param merQuery
	 * @return
	 */
	public PageInfoVo<MerchantVo> getMerchantList(MerchantQuery merQuery) {
		Pageable pageable = PageRequest.of(merQuery.getPageIndex(), merQuery.getPageSize());
		if(pageable.getPageSize() > 20) {
			throw new IllegalStateException("页面容量不能超过20!");
		}
		if(pageable.getPageNumber() < 0) {
			throw new IllegalStateException("页码不能为负数!");
		}
		
		Page<Merchant> page = merchantRepository.getPage(merQuery,pageable);
		List<Merchant> merchants = page.getContent();
		List<MerchantVo> merchantVos = new ArrayList<MerchantVo>();
		for(Merchant merchant : merchants) {
			MerchantVo merchantVo = new MerchantVo();
			merchantVo.setCode(merchant.getCode());
			merchantVo.setName(merchant.getName());
			merchantVo.setAddress(merchant.getAddress());
			merchantVo.setAppKey(merchant.getAppKey());
			merchantVo.setAppSecret(merchant.getAppSecret());
			merchantVo.setEmail(merchant.getEmail());
			merchantVo.setRemark(merchant.getRemark());
			merchantVo.setStatus(merchant.getStatus());
			merchantVo.setStatusName(merchant.getStatus().getName());
			merchantVo.setTelephone(merchant.getTelephone());
			merchantVos.add(merchantVo);
		}
		PageInfoVo<MerchantVo> pageInfoVo = new PageInfoVo<MerchantVo>(page.getTotalElements(), page.getNumber(), page.getSize(), merchantVos);		
		return pageInfoVo;
	}

	/**
	 * 查询商户详情
	 * @param code
	 * @return
	 */
	public Merchant getMerchantDetails(String code) {
		if(StringUtils.isEmpty(code)) {
			throw new IllegalArgumentException("商户code不能为空!");
		}
		return merchantRepository.findByCode(code);
	}

	/**
	 * 修改商户信息
	 * @param merUpdate
	 */
	public void updateMerchant(MerchantUpdate merUpdate) {
		if(StringUtils.isEmpty(merUpdate.getName())) {
			throw new IllegalArgumentException("商户名不能为空！");
		}
		if(StringUtils.isEmpty(merUpdate.getEmail())) {
			throw new IllegalArgumentException("联系邮箱不能为空!");
		}
		if(StringUtils.isEmpty(merUpdate.getAddress())) {
			throw new IllegalArgumentException("公司地址不能为空!");
		}
		if(merUpdate.getStatus() == null) {
			throw new IllegalArgumentException("商户状态不能为空!");
		}
		
		Merchant merchant = merchantRepository.findByCode(merUpdate.getCode());
		if(merchant == null) {
			throw new IllegalStateException("找不到商户信息!");
		}
		
		Long count = merchantRepository.countByNameAndCodeNot(merUpdate.getName(),merUpdate.getCode());
		if(count > 0) {
			throw new IllegalStateException("商户名不能重复!");
		}
		
		try {
			merchant.setName(merUpdate.getName());
			merchant.setTelephone(merUpdate.getTelephone());
			merchant.setEmail(merUpdate.getEmail());
			merchant.setStatus(merUpdate.getStatus());
			merchant.setRemark(merUpdate.getRemark());
			merchant.setUpdateDateTime(LocalDateTime.now());
			merchant.setAddress(merUpdate.getAddress());
			merchantRepository.save(merchant);
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("修改商户信息失败!",e);
		}
	}

	/**
	 * 新增商户信息
	 * @param merAdd
	 */
	public void addMerchant(MerchantAdd merAdd) {
		if(StringUtils.isEmpty(merAdd.getName())) {
			throw new IllegalArgumentException("商户名不能为空!");
		}
		if(StringUtils.isEmpty(merAdd.getEmail())) {
			throw new IllegalArgumentException("联系邮箱不能为空!");
		}
		if(StringUtils.isEmpty(merAdd.getAddress())) {
			throw new IllegalArgumentException("公司地址不能为空!");
		}
		if(merAdd.getStatus() == null) {
			throw new IllegalArgumentException("商户状态不能为空!");
		}
		
		Long count = merchantRepository.countByName(merAdd.getName());
		if(count > 0) {
			throw new IllegalStateException("商户名不能重复!");
		}
		
		try {
			Merchant merchant = new Merchant();
			merchant.setName(merAdd.getName());
			merchant.setCode(CodeUtil.fetchMerchantCode());
			merchant.setTelephone(merAdd.getTelephone());
			merchant.setEmail(merAdd.getEmail());
			merchant.setStatus(merAdd.getStatus());
			merchant.setRemark(merAdd.getRemark());
			merchant.setAddress(merAdd.getAddress());
			merchant.setAppKey(DigestUtils.md5Hex(UUID.randomUUID().toString()));
			merchant.setAppSecret(DigestUtils.md5Hex(UUID.randomUUID().toString()));
			merchant.setCreateDateTime(LocalDateTime.now());
			merchantRepository.save(merchant);
		} catch (Exception e) {
			throw new BusinessException("新增失败!",e);
		}
	}

}
