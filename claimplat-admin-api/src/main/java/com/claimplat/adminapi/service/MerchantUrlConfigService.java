package com.claimplat.adminapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claimplat.adminapi.repository.MerchantRepository;
import com.claimplat.adminapi.repository.MerchantUrlConfigRepository;
import com.claimplat.adminapi.util.CodeUtil;
import com.claimplat.adminapi.vo.PageInfoVo;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlAdd;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlQuery;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlUpdate;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlVo;
import com.claimplat.common.exception.BusinessException;
import com.claimplat.core.entity.Merchant;
import com.claimplat.core.entity.MerchantUrlConfig;

@Service
@Transactional
public class MerchantUrlConfigService {

	@Autowired
	private MerchantUrlConfigRepository merchantUrlConfigRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;

	/**
	 * 查询通知地址信息
	 * @param merchantUrlQuery
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfoVo<MerchantUrlVo> getUrlConfig(MerchantUrlQuery merchantUrlQuery) {
		if(merchantUrlQuery.getPageIndex() < 0) {
			throw new IllegalStateException("页码不能为负数!");
		}
		if(merchantUrlQuery.getPageSize() > 20) {
			throw new IllegalStateException("页面大小不能超过20!");
		}
		
		Pageable pageable = PageRequest.of(merchantUrlQuery.getPageIndex(), merchantUrlQuery.getPageSize());
		
		Page<MerchantUrlConfig> page = merchantUrlConfigRepository.getPage(merchantUrlQuery,pageable);
		List<MerchantUrlConfig> configs = page.getContent();
		List<MerchantUrlVo> configVos = new ArrayList<MerchantUrlVo>();
		for(MerchantUrlConfig urlConfig : configs) {
			MerchantUrlVo merchantUrlVo = new MerchantUrlVo();
			merchantUrlVo.setMerchantCode(urlConfig.getMerchantCode());
			merchantUrlVo.setBusinessType(urlConfig.getBusinessType());
			merchantUrlVo.setCode(urlConfig.getCode());
			merchantUrlVo.setRemark(urlConfig.getRemark());
			merchantUrlVo.setUrl(urlConfig.getUrl());
			merchantUrlVo.setBackupurl(urlConfig.getBackupUrl());
			merchantUrlVo.setBusinessTypeName(urlConfig.getBusinessType().getName());
			Merchant merchant = merchantRepository.findByCode(urlConfig.getMerchantCode());
			if(merchant == null) {
				merchantUrlVo.setMerchantName(null);
			}else {
				merchantUrlVo.setMerchantName(merchant.getName());
			}
			configVos.add(merchantUrlVo);
		}
		PageInfoVo<MerchantUrlVo> pageInfoVo = new PageInfoVo<MerchantUrlVo>(page.getTotalElements(), page.getNumber(), page.getSize(), configVos);
		return pageInfoVo;
	}

	
	public void addUrlConfig(MerchantUrlAdd merchantUrlAdd) {
		if(StringUtils.isEmpty(merchantUrlAdd.getUrl())) {
			throw new IllegalArgumentException("接口地址不能为空");
		}
		if(merchantUrlAdd.getBusinessType() == null) {
			throw new IllegalStateException("业务类型不能为空");
		}
		if(merchantUrlAdd.getMerchantCode() == null) {
			throw new IllegalStateException("商户code不能为空");
		}
		
		try {
			MerchantUrlConfig merchantUrlConfig = new MerchantUrlConfig();
			merchantUrlConfig.setMerchantCode(merchantUrlAdd.getMerchantCode());
			merchantUrlConfig.setCode(CodeUtil.fetchMerchantUrlCode());
			merchantUrlConfig.setBackupUrl(merchantUrlAdd.getBackupUrl());
			merchantUrlConfig.setBusinessType(merchantUrlAdd.getBusinessType());
			merchantUrlConfig.setRemark(merchantUrlAdd.getRemark());
			merchantUrlConfig.setUrl(merchantUrlAdd.getUrl());
			merchantUrlConfig.setCreateDateTime(LocalDateTime.now());
			merchantUrlConfigRepository.save(merchantUrlConfig);
		} catch (Exception e) {
			throw new BusinessException("新增失败",e);
		}
	}

	
	public void updateUrlConfig(MerchantUrlUpdate merchantUrlUpdate) {
		if(StringUtils.isEmpty(merchantUrlUpdate.getUrl())) {
			throw new IllegalArgumentException("接口地址不能为空");
		}
		if(merchantUrlUpdate.getBusinessType() == null) {
			throw new IllegalStateException("业务类型不能为空");
		}
		
		MerchantUrlConfig merchantUrlConfig = merchantUrlConfigRepository.findByCode(merchantUrlUpdate.getCode());
		if(merchantUrlConfig == null) {
			throw new IllegalStateException("当前接口对象不存在");
		}
		
		try {
			merchantUrlConfig.setBackupUrl(merchantUrlUpdate.getBackupUrl());
			merchantUrlConfig.setBusinessType(merchantUrlUpdate.getBusinessType());
			merchantUrlConfig.setRemark(merchantUrlUpdate.getRemark());
			merchantUrlConfig.setUrl(merchantUrlUpdate.getUrl());
			merchantUrlConfig.setUpdateDateTime(LocalDateTime.now());
			merchantUrlConfigRepository.save(merchantUrlConfig);
		}catch (IllegalArgumentException | IllegalStateException e){
			throw e;
		}catch (Exception e) {
			throw new BusinessException("修改商户信息失败",e);
		}
	}

	
	public void deleteUrlConfig(String code) {
		if(code == null) {
			throw new IllegalStateException("code不能为空！");
		}
		try {
			merchantUrlConfigRepository.deleteByCode(code);
		} catch (Exception e) {
			throw new BusinessException("删除基础参数配置失败",e);
		}
	}
	
}
