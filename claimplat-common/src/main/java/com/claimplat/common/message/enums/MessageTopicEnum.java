package com.claimplat.common.message.enums;

import java.io.Serializable;

public enum MessageTopicEnum implements Serializable{
	
	BUSINESS_SYSTEM("met_business_system","业务系统通知消息","");
	
	private String code;
	
	private String name;
	
	private String desc;

	
	private MessageTopicEnum(String code, String name, String desc) {
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
