package com.claimplat.adminapi.vo.order;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.claimplat.adminapi.vo.BasePageObject;
import com.claimplat.common.enums.BusinessHandleTypeEnum;
import com.claimplat.common.enums.OrderStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class OrderQuery extends BasePageObject{

	private static final long serialVersionUID = -4252814694390168321L;

	//商户code，作路由字段
	@ApiModelProperty(value = "商户code，作路由字段")
	private String merchantCode;
	
	//code唯一标识
	private String code;
	
	//外部第三方请求唯一标识
	private String requestId;
	
	//状态
	@ApiModelProperty(value = "状态")
	private OrderStatusEnum status = null;
	
	//创建时间
	@ApiModelProperty(value = "开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate beginDate;
	
	//结束时间
	@ApiModelProperty(value = "结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	//业务类型
	private BusinessHandleTypeEnum businessType = null;

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

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public BusinessHandleTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessHandleTypeEnum businessType) {
		this.businessType = businessType;
	}
	
}
