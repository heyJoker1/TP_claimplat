package com.claimplat.adminapi.vo.merchant;

import com.claimplat.common.enums.MerchantStatusEnum;
import com.claimplat.common.vo.BaseValueObject;

import io.swagger.annotations.ApiModelProperty;

public class MerchantUpdate extends BaseValueObject{

	private static final long serialVersionUID = -6173252028199284248L;

	@ApiModelProperty(value = "商户code，作为路由字段")
	private String code;
	
	@ApiModelProperty(value = "商户名称")
	private String name;
	
	@ApiModelProperty(value = "联系电话")
	private String telephone;
	
	@ApiModelProperty(value = "联系邮箱")
	private String email;
	
	@ApiModelProperty(value = "公司地址")
	private String address;
	
	@ApiModelProperty(value = "状态")
	private MerchantStatusEnum status;
	
	@ApiModelProperty(value = "备注信息")
	private String remark;

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

}
