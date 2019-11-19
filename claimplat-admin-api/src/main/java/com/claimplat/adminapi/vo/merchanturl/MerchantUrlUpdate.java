package com.claimplat.adminapi.vo.merchanturl;

import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.common.vo.BaseValueObject;

import io.swagger.annotations.ApiModelProperty;

public class MerchantUrlUpdate extends BaseValueObject{

	private static final long serialVersionUID = -4728374274139829734L;

	@ApiModelProperty(value = "code唯一标识")
	private String code;
	
	@ApiModelProperty(value = "接口url地址")
	private String url;
	
	@ApiModelProperty(value = "备用的接口地址")
	private String backupUrl;
	
	@ApiModelProperty(value = "业务类型")
	private BusinessForwardTypeEnum businessType;
	
	@ApiModelProperty(value = "备注信息")
	private String remark;

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

	public void setBackupUrl(String backupUrl) {
		this.backupUrl = backupUrl;
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

}
