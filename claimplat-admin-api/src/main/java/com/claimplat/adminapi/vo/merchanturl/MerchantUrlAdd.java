package com.claimplat.adminapi.vo.merchanturl;

import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.common.vo.BaseValueObject;
import io.swagger.annotations.ApiModelProperty;

public class MerchantUrlAdd extends BaseValueObject{

	private static final long serialVersionUID = -1489827720911746793L;

	@ApiModelProperty(value = "商户code，作路由字段")
	private String merchantCode;
	
	@ApiModelProperty(value = "接口url地址")
	private String url;
	
	@ApiModelProperty(value = "备用接口地址")
	private String backupUrl;
	
	@ApiModelProperty(value = "业务类型")
	private BusinessForwardTypeEnum businessType;
	
	@ApiModelProperty(value = "备注信息")
	private String remark;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
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
