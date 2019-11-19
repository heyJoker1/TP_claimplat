package com.claimplat.adminapi.vo.order;

import java.time.LocalDateTime;
import com.claimplat.common.enums.BusinessHandleTypeEnum;
import com.claimplat.common.enums.ComponentHandleTypeEnum;
import com.claimplat.common.enums.DataTypeEnum;
import com.claimplat.common.enums.OrderStatusEnum;
import com.claimplat.common.vo.BaseValueObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商户列表")
public class OrderItemVo extends BaseValueObject{

	private static final long serialVersionUID = 62513355718601133L;

	@ApiModelProperty(value = "商户code，作路由字段")
	private String merchantCode;

	@ApiModelProperty(value = "唯一标识")
	private String code;
	
	@ApiModelProperty(value = "外部第三方请求唯一标识")
	private String requestId;
	
	@ApiModelProperty(value = "第三方中间平台处理平台，准备调用业务系统的事件")
	private String readyDateTime;
	
	@ApiModelProperty(value = "外部第三方请求随机数")
	private String requestNonce;
	
	@ApiModelProperty(value = "外部第三方请求时间戳")
	private Long requestTimestamp;
	
	@ApiModelProperty(value = "业务类型")	
	private BusinessHandleTypeEnum businessType = null;
	
	@ApiModelProperty(value = "数据类型")	
	private DataTypeEnum dataType = null;
	
	@ApiModelProperty(value = "执行请求单的组件类型")	
	private ComponentHandleTypeEnum ComponentType = null;
	
	@ApiModelProperty(value = "状态")
	private OrderStatusEnum status = OrderStatusEnum.DEALING;
	
	@ApiModelProperty(value = "请求的业务数据")
	private String bizModelBefore;
	
	@ApiModelProperty(value = "创建时间")
	private String createDateTime;
	
	@ApiModelProperty(value = "保留属性，扩展数据")
	private String extraData;
	
	@ApiModelProperty(value = "保留属性")
	private String passBackData;
	
	@ApiModelProperty(value = "返回的响应数据")
	private String bizContentBefore;
	
	@ApiModelProperty(value = "转换后的响应数据（可为空）")
	private String bizContentAfter;
	
	@ApiModelProperty(value = "回调地址")
	private String notifyUrl;
	
	@ApiModelProperty(value = "业务系统处理完成时间")
	private LocalDateTime successDateTime;
	
	//外部第三方系统回调返回后的完成时间
	private String completeDateTime;
	
	public String getCompleteDateTime() {
		return completeDateTime;
	}

	public void setCompleteDateTime(String completeDateTime) {
		this.completeDateTime = completeDateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	//备注信息
	private String remark;

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

	public String getReadyDateTime() {
		return readyDateTime;
	}

	public void setReadyDateTime(String readyDateTime) {
		this.readyDateTime = readyDateTime;
	}

	public String getRequestNonce() {
		return requestNonce;
	}

	public void setRequestNonce(String requestNonce) {
		this.requestNonce = requestNonce;
	}

	public Long getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(Long requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public BusinessHandleTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessHandleTypeEnum businessType) {
		this.businessType = businessType;
	}

	public DataTypeEnum getDataType() {
		return dataType;
	}

	public void setDataType(DataTypeEnum dataType) {
		this.dataType = dataType;
	}

	public ComponentHandleTypeEnum getComponentType() {
		return ComponentType;
	}

	public void setComponentType(ComponentHandleTypeEnum componentType) {
		ComponentType = componentType;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public String getBizModelBefore() {
		return bizModelBefore;
	}

	public void setBizModelBefore(String bizModelBefore) {
		this.bizModelBefore = bizModelBefore;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}

	public String getPassBackData() {
		return passBackData;
	}

	public void setPassBackData(String passBackData) {
		this.passBackData = passBackData;
	}

	public String getBizContentBefore() {
		return bizContentBefore;
	}

	public void setBizContentBefore(String bizContentBefore) {
		this.bizContentBefore = bizContentBefore;
	}

	public String getBizContentAfter() {
		return bizContentAfter;
	}

	public void setBizContentAfter(String bizContentAfter) {
		this.bizContentAfter = bizContentAfter;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public LocalDateTime getSuccessDateTime() {
		return successDateTime;
	}

	public void setSuccessDateTime(LocalDateTime successDateTime) {
		this.successDateTime = successDateTime;
	}
}
