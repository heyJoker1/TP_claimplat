package com.claimplat.adminapi.vo.merchanturl;

import com.claimplat.adminapi.vo.BasePageObject;
import com.claimplat.common.enums.BusinessForwardTypeEnum;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "商户查询对象")
public class MerchantUrlQuery extends BasePageObject{

	private static final long serialVersionUID = -8250009312611332123L;

	private String merchantCode;
	
	private BusinessForwardTypeEnum businessType = null;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public BusinessForwardTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessForwardTypeEnum businessType) {
		this.businessType = businessType;
	}
	
	
}
