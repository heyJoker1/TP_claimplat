package com.claimplat.common.message;

import java.util.UUID;

import com.claimplat.common.vo.BaseValueObject;

public class BaseMessageObject extends BaseValueObject{

	//商户号
	protected String merchantCode;
	
	//消息对象标识
	protected String uuid = UUID.randomUUID().toString();

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
