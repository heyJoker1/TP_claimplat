package com.claimplat.common.enums;

import java.io.Serializable;


/**
 * 终端类型
 * @author Joker
 *
 */
public enum TerminalTypeEnum implements Serializable{
	
	PC("PC","WEB浏览器",""),MOBILE("mobile","手机浏览器","");
	
	private String code;
	
	private String name;
	
	private String desc;

	private TerminalTypeEnum(String code, String name, String desc) {
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
