package com.claimplat.core.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.claimplat.common.enums.MerchantStatusEnum;
import com.claimplat.common.enums.ThridpartTypeEnum;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
public class Merchant extends BaseDateTimeEntity{
	
	private static final long serialVersionUID = -3513693057692055410L;

	private String code;
	
	private String name;
	
	private String appKey;
	
	private String appSecret;
	
	private String telephone;
	
	private String email;
	
	private String address;
	
	@Enumerated(value = EnumType.STRING)
	private ThridpartTypeEnum thridpartType;
	
	@Enumerated(value = EnumType.STRING)
	private MerchantStatusEnum status = MerchantStatusEnum.NORMAL;
	
	private String remark;
	
	private Long version = 1L;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ThridpartTypeEnum getThridpartType() {
		return thridpartType;
	}

	public void setThridpartType(ThridpartTypeEnum thridpartType) {
		this.thridpartType = thridpartType;
	}

	public MerchantStatusEnum getStatus() {
		return status;
	}

	public void setStatus(MerchantStatusEnum status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
