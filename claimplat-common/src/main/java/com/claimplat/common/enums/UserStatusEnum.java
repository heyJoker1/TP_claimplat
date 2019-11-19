package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 用户状态
 * @author Joker
 *
 */
public enum UserStatusEnum implements Serializable{
	NORMAL("normal","正常",""),FORBIDDEN("forbidden","禁用","");
	
	private String code;
	private String name;
	private String desc;
	
	
	private UserStatusEnum(String code, String name, String desc) {
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
