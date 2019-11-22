package com.claimplat.core.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.claimplat.common.enums.BusinessForwardTypeEnum;

/**
 * 外部第三方商户的主动通知或异步通知请求地址
 * @author Joker
 *
 */
@Entity
@Table(name = "merchant_url_config",uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class MerchantUrlConfig extends BaseDateTimeEntity{
	
	private static final long serialVersionUID = 4866071132889918114L;

	private String merchantCode;		//商户code，作为路由字段
	
	private String code;				//code唯一标识
	
	private String url;					//接口url地址
	
	private String backupUrl;			//备用接口地址，保留字段
	
	@Enumerated(value = EnumType.STRING)
	private BusinessForwardTypeEnum businessType;	//业务类型
	
	private String remark;				//备注信息
	
	private Long version = 1L;			//乐观锁标识

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBackupUrl() {
		return backupUrl;
	}

	public void setBackupUrl(String backUrl) {
		this.backupUrl = backUrl;
	}

	public BusinessForwardTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessForwardTypeEnum businessType) {
		this.businessType = businessType;
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
