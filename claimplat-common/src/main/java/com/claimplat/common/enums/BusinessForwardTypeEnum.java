package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 业务类型，也就是标识该次请求所属的业务操作
 * @author Joker
 *
 */
public enum BusinessForwardTypeEnum implements Serializable{
	SEND_AUDIT_INFO("bfte_send_sudit_info","发送单证审核状态",""),
	SEND_CASE_STATUS("bfte_send_case_status","发送案件状态","");
	
private String code;
	
	private String name;
	
	private String desc;
	
	private BusinessForwardTypeEnum(String code, String name, String desc) {
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
