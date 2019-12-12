package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 签名算法类型
 * @author Joker
 *
 */
public enum SignTypeEnum implements Serializable{
	MD5("sit_md5","MD5签名算法",""),
	SHA256("sit_sha256","SHA256签名算法",""),
	NONE("sit_none","没有任何签名算法","");
	
	private String code;
	private String name;
	private String desc;
	
	private SignTypeEnum(String code, String name, String desc) {
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
