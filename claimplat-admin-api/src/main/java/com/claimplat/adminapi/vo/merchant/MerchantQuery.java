package com.claimplat.adminapi.vo.merchant;

import com.claimplat.adminapi.vo.BasePageObject;
import com.claimplat.common.enums.MerchantStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商户查询对象")
public class MerchantQuery extends BasePageObject{

	private static final long serialVersionUID = 915821689809956613L;

	@ApiModelProperty(value = "商户code，作为路由字段")
	private String code;
	
	@ApiModelProperty(value = "商户名称")
	private String name;
	
	@ApiModelProperty(value = "状态")
	private MerchantStatusEnum status;

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

	public MerchantStatusEnum getStatus() {
		return status;
	}

	public void setStatus(MerchantStatusEnum status) {
		this.status = status;
	}
	
	
}
