package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 外部第三方公司类型标识
 * @author Joker
 *
 */
public enum ThridpartTypeEnum implements Serializable{
	SHUT_DI("sit_shui_di","水滴",""),
	DEFAULT("default","默认","");

	private String code;
	private String name;
	private String desc;
	
	private ThridpartTypeEnum(String code, String name, String desc) {
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
