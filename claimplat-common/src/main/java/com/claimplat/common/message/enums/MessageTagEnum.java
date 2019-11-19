package com.claimplat.common.message.enums;

import java.io.Serializable;

public enum MessageTagEnum implements Serializable{
	
	CALL_BUSINESS_SYSTEM("tag_call_business_system","调用业务系统接口",""),
	CALL_OUT_SYSTEM("tag_call_out_system","调用外部第三方系统接口","");
	
	private String code;
	
	private String name;
	
	private String desc;

	
	private MessageTagEnum(String code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
	
	

}
