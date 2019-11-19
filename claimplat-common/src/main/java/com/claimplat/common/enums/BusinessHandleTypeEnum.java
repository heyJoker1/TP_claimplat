package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 业务类型
 * @author Joker
 *
 */
public enum BusinessHandleTypeEnum implements Serializable{
	VALIDATE_POLICY("bhte_validate_policy","保单验证",""),
	APPLY_REPORT("bhte_apply_report","保单报案",""),
	PUSH_DATA("bhte_push_data","资料推送","");
	
	private String code;
	
	private String name;
	
	private String desc;
	
	private BusinessHandleTypeEnum(String code, String name, String desc) {
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
