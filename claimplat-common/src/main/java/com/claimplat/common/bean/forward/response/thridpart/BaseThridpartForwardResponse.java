package com.claimplat.common.bean.forward.response.thridpart;

import com.claimplat.common.bean.forward.response.BaseForwardResponse;

public class BaseThridpartForwardResponse extends BaseForwardResponse{

	private static final long serialVersionUID = 5716026768522907690L;

	private String messageCode;
	
	private String messageError;

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	
	
}
