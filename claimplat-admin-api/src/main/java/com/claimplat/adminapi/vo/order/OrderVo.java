package com.claimplat.adminapi.vo.order;

import com.claimplat.adminapi.vo.BasePageObject;
import com.claimplat.common.enums.BusinessHandleTypeEnum;
import com.claimplat.common.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModelProperty;

public class OrderVo extends BasePageObject{

	private static final long serialVersionUID = -5960794583120327496L;

	@ApiModelProperty(value = "商户code，作路由字段")
	private String merchantCode;

	@ApiModelProperty(value = "商户名")
	private String merchantName;

	@ApiModelProperty(value = "唯一标识")
	private String code;
	
	@ApiModelProperty(value = "外部第三方请求唯一标识")
	private String requestId;
	
	@ApiModelProperty(value = "状态")
	private OrderStatusEnum status = OrderStatusEnum.DEALING;
	
	@ApiModelProperty(value = "创建时间")
	private String createDateTime = null;
	
	@ApiModelProperty(value = "系统类型名")
	private String systemTypeEnumName;
	
	@ApiModelProperty(value = "业务类型")	
	private BusinessHandleTypeEnum businessType;
	
	@ApiModelProperty(value = "业务类型对应名称")	
	private String businessTypeName;
	
	@ApiModelProperty(value = "状态对应名称")	
	private String statusName;
	
	@ApiModelProperty(value = "转换后响应的数据（可为空）")
	private String bizContentAfter;
	
	@ApiModelProperty(value = "返回的响应数据")
	private String bizContentBefore;
	
	//外部第三方系统回调返回后的完成时间
	private String completeDateTime;
	
	@ApiModelProperty(value = "请求的业务数据")
	private String bizModelBefore;
	
	@ApiModelProperty(value = "转换后的业务参数数据（可为空）")
	private String bizModelAfter;
	
	//是否展示重发
	private boolean needSend = true;

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

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getSystemTypeEnumName() {
		return systemTypeEnumName;
	}

	public void setSystemTypeEnumName(String systemTypeEnumName) {
		this.systemTypeEnumName = systemTypeEnumName;
	}

	public BusinessHandleTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessHandleTypeEnum businessType) {
		this.businessType = businessType;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getBizContentAfter() {
		return bizContentAfter;
	}

	public void setBizContentAfter(String bizContentAfter) {
		this.bizContentAfter = bizContentAfter;
	}

	public String getBizContentBefore() {
		return bizContentBefore;
	}

	public void setBizContentBefore(String bizContentBefore) {
		this.bizContentBefore = bizContentBefore;
	}

	public String getCompleteDateTime() {
		return completeDateTime;
	}

	public void setCompleteDateTime(String completeDateTime) {
		this.completeDateTime = completeDateTime;
	}

	public String getBizModelBefore() {
		return bizModelBefore;
	}

	public void setBizModelBefore(String bizModelBefore) {
		this.bizModelBefore = bizModelBefore;
	}

	public String getBizModelAfter() {
		return bizModelAfter;
	}

	public void setBizModelAfter(String bizModelAfter) {
		this.bizModelAfter = bizModelAfter;
	}

	public boolean isNeedSend() {
		return needSend;
	}

	public void setNeedSend(boolean needSend) {
		this.needSend = needSend;
	}
	
	
}
