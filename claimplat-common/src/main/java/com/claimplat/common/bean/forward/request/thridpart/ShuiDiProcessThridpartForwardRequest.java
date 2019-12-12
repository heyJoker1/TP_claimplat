package com.claimplat.common.bean.forward.request.thridpart;

import com.claimplat.common.bean.forward.bizmodel.thridpart.ShuiDiProcessThridpartForwardBizModel;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 水滴案件同步对象
 * @author Joker
 *
 */
public class ShuiDiProcessThridpartForwardRequest extends BaseThridpartForwardRequest{

	private static final long serialVersionUID = 6469414707358655489L;

	@JsonProperty("UUID")
	protected String uuid;			//唯一标识
	
	@JsonProperty("ServiceName")
	protected String serviceName;	//接口标识
	
	@JsonProperty("Timestamp")
	protected Long timestamp;		//时间戳
	
	@JsonProperty("BizContent")
	private ShuiDiProcessThridpartForwardBizModel bizContent;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public ShuiDiProcessThridpartForwardBizModel getBizContent() {
		return bizContent;
	}

	public void setBizContent(ShuiDiProcessThridpartForwardBizModel bizContent) {
		this.bizContent = bizContent;
	} 
	
	
}
