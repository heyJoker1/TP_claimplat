package com.claimplat.adminapi.vo.merchanturl;

import com.claimplat.adminapi.vo.BasePageObject;
import com.claimplat.common.enums.BusinessForwardTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class MerchantUrlVo extends BasePageObject{

	private static final long serialVersionUID = -7222555207517826020L;

	@ApiModelProperty(value = "code唯一标识")
	private String code;
	
	@ApiModelProperty(value = "商户code")
	private String merchantCode;
	
	@ApiModelProperty(value = "商户名称")
	private String merchantName;
	
	@ApiModelProperty(value = "接口url地址")
	private String url;
	
	@ApiModelProperty(value = "业务类型")
	private BusinessForwardTypeEnum businessType;
	
	@ApiModelProperty(value = "业务类型名")
	private String businessTypeName;
	
	@ApiModelProperty(value = "备注信息")
	private String remark;
	
	@ApiModelProperty(value = "备用接口地址")
	private String backupurl;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BusinessForwardTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessForwardTypeEnum businessType) {
		this.businessType = businessType;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBackupurl() {
		return backupurl;
	}

	public void setBackupurl(String backupurl) {
		this.backupurl = backupurl;
	}
}
