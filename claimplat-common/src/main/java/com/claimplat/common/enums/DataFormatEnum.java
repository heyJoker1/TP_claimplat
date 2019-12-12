package com.claimplat.common.enums;

import java.io.Serializable;


/**
 * 数据格式类型
 * @author Joker
 *
 */
public enum DataFormatEnum implements Serializable{
	
	JSON("dfe_json","json格式",""),
	XML("dfe_xml","xml格式","");
	
	private String code;
	
	private String name;
	
	private String desc;

	
	private DataFormatEnum(String code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
