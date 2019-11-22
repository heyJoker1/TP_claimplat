package com.claimplat.core.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.claimplat.common.enums.MerchantStatusEnum;
import com.claimplat.common.enums.ThridpartTypeEnum;

/**
 * 商户信息
 * @author Joker
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
public class Merchant extends BaseDateTimeEntity{
	
	private static final long serialVersionUID = -3513693057692055410L;

	private String code;		//商户code，作为路由字段
	
	private String name;		//商户名称
	
	private String appKey;		//与商户对应的appKey
	
	private String appSecret;	//签名时使用的appSecret
	
	private String telephone;	//联系电话
	
	private String email;		//邮箱
	
	private String address;		//公司地址
	
	@Enumerated(value = EnumType.STRING)
	private ThridpartTypeEnum thridpartType;	//第三方类型标识
	
	@Enumerated(value = EnumType.STRING)
	private MerchantStatusEnum status = MerchantStatusEnum.NORMAL;	//状态
	
	private String remark;		//备注信息
	
	@Version
	private Long version = 1L;	//乐观锁标识

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
