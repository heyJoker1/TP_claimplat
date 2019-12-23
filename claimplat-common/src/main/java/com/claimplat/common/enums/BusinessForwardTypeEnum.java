package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 业务类型，也就是标识该次请求所属的业务操作
 * @author Joker
 *
 */
public enum BusinessForwardTypeEnum implements Serializable{
	SEND_AUDIT_INFO("bfte_send_sudit_info","发送单证审核状态",""),
	SEND_CASE_STATUS("bfte_send_case_status","发送案件状态",""),
	DLZ_SEND_INFO("bfte_dlz_send_info","已结案状态",""),
	DLZ_DISMISS_INFO("bfte_dlz_dismiss_info","不予处理",""),
	DLZ_MATERIAL_INFO("bfte_dlz_material_info","补传单证","");
	
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
