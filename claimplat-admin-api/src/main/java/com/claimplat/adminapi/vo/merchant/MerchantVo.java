package com.claimplat.adminapi.vo.merchant;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.claimplat.common.enums.MerchantStatusEnum;
import com.claimplat.common.vo.BaseValueObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商户列表")
public class MerchantVo extends BaseValueObject{

	private static final long serialVersionUID = 9142817551547639128L;

	@ApiModelProperty(value = "商户code，作为路由字段")
	private String code;
	
	@ApiModelProperty(value = "商户名称")
	private String name;
	
	@ApiModelProperty(value = "与商户对应的appkey")
	private String appKey;

	@ApiModelProperty(value = "签名时使用的appSecret")
	private String appSecret;
	
	@ApiModelProperty(value = "联系电话")
	private String telephone;
	
	@ApiModelProperty(value = "联系邮箱")
	private String email;
	
	@ApiModelProperty(value = "公司地址")
	private String address;
	
	@ApiModelProperty(value = "状态")
	@Enumerated(value = EnumType.STRING)
	private MerchantStatusEnum status = MerchantStatusEnum.NORMAL;
	
	@ApiModelProperty(value = "状态名字")
	private String statusName;

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

	public MerchantStatusEnum getStatus() {
		return status;
	}

	public void setStatus(MerchantStatusEnum status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
