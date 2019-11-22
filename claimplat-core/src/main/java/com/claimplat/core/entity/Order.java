package com.claimplat.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.claimplat.common.enums.BusinessHandleTypeEnum;
import com.claimplat.common.enums.ComponentHandleTypeEnum;
import com.claimplat.common.enums.DataTypeEnum;
import com.claimplat.common.enums.OrderStatusEnum;

/**
 * 接口请求数据
 * @author Joker
 *
 */
@Entity
@Table(name = "orders",uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Order extends BaseDateTimeEntity{

	private static final long serialVersionUID = 7307580299968069000L;

	private String merchantCode;			//商户code，作为路由字段
	
	private String code;					//code唯一标识
	
	private String requestId;				//外部第三方请求唯一标识
	
	private String requestNonce;			//外部第三方请求随机数
	
	private Long requestTimestamp;			//外部第三方请求时间戳
	
	@Enumerated(value = EnumType.STRING)
	private BusinessHandleTypeEnum businessType;	//业务类型
	
	@Enumerated(value = EnumType.STRING)
	private DataTypeEnum dataType = DataTypeEnum.STANDARD;	//数据类型
	
	@Enumerated(value = EnumType.STRING)
	private ComponentHandleTypeEnum componentType;	//执行请求单的组件类型
	
	@Lob
	private String encodeRequestData;		//第三方原始的加密请求数据
	
	@Lob
	private String bizModelBefore;			//请求的业务数据
	
	@Lob
	private String bizModelAfter;			//转换后的业务参数数据，可为空
	
	@Lob
	private String extraData;				//保留属性，扩展数据
	
	@Lob
	private String passbackData;			//保留属性，回调数据
	
	@Lob
	private String bizContentBefore;		//返回的响应数据
	
	@Lob
	private String bizContentAfter;			//转换后的响应数据，可为空
	
	private String notifyUrl;				//回调地址，可为空
	
	@Enumerated(value = EnumType.STRING)
	private OrderStatusEnum status = OrderStatusEnum.DEALING;	//状态
	
	private LocalDateTime successDateTime;		//业务系统处理成功的时间
	
	private LocalDateTime completeDateTime;		//外部第三方系统回调成功后的完成时间
	
	private LocalDateTime latestTaskDateTime;	//定时任务最后执行时间
	
	private String remark;						//备注信息
	
	@Version
	private Long version = 1L;					//乐观锁标识

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
		return componentType;
	}

	public void setComponentType(ComponentHandleTypeEnum componentType) {
		this.componentType = componentType;
	}

	public String getEncodeRequestData() {
		return encodeRequestData;
	}

	public void setEncodeRequestData(String encodeRequestData) {
		this.encodeRequestData = encodeRequestData;
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

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}

	public String getPassbackData() {
		return passbackData;
	}

	public void setPassbackData(String passbackData) {
		this.passbackData = passbackData;
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

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getSuccessDateTime() {
		return successDateTime;
	}

	public void setSuccessDateTime(LocalDateTime successDateTime) {
		this.successDateTime = successDateTime;
	}

	public LocalDateTime getCompleteDateTime() {
		return completeDateTime;
	}

	public void setCompleteDateTime(LocalDateTime completeDateTime) {
		this.completeDateTime = completeDateTime;
	}

	public LocalDateTime getLatestTaskDateTime() {
		return latestTaskDateTime;
	}

	public void setLatestTaskDateTime(LocalDateTime latestTaskDateTime) {
		this.latestTaskDateTime = latestTaskDateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
