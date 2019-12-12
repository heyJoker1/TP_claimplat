package com.claimplat.serviceapi.componentforward.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.claimplat.common.bean.forward.request.BaseForwardRequest;
import com.claimplat.common.bean.forward.response.BaseForwardResponse;
import com.claimplat.core.entity.Merchant;

public class ForwardContext implements Serializable{

	private static final long serialVersionUID = -4595319731205775399L;

	private Merchant merchant;
	
	private BaseForwardRequest request;
	
	private BaseForwardResponse response;
	
	private Map<String,Object> tempDateMap = new HashMap<>();

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public BaseForwardRequest getRequest() {
		return request;
	}

	public void setRequest(BaseForwardRequest request) {
		this.request = request;
	}

	public BaseForwardResponse getResponse() {
		return response;
	}

	public void setResponse(BaseForwardResponse response) {
		this.response = response;
	}

	public Map<String, Object> getTempDateMap() {
		return tempDateMap;
	}

	public void setTempDateMap(Map<String, Object> tempDateMap) {
		this.tempDateMap = tempDateMap;
	}
	
	
}
