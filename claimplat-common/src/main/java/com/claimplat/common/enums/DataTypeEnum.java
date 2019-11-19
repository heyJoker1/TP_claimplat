package com.claimplat.common.enums;

import java.io.Serializable;


/**
 * 业务数据类型（标识请求数据的参数格式）
 * @author Joker
 *
 */
public enum DataTypeEnum implements Serializable{
	STANDARD("dte_standard","标准参数格式",""),
	NONE_STANDARD("dte_none_standard","非标准参数格式",""),
	THRIDPART("dte_thridpart","外部第三方自定义参数格式",""),
	FILE("dte_file","文件类型数据","");
	
	private String code;
	
	private String name;
	
	private String desc;
	
	private DataTypeEnum(String code, String name, String desc) {
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
