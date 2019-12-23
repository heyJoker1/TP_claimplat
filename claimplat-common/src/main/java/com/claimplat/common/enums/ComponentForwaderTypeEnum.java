package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 组件类型，多个组件类型可以关联一个业务类型
 * @author Joker
 *
 */
public enum ComponentForwaderTypeEnum implements Serializable{
	
	SEND_AUDIT_INFO("cte_send_audit_info","发起单证审核状态",""),
	SEND_CASE_STATUS("cte_send_case_status","发起案件状态",""),
	SHUIDI_AUDIT("cte_shuidi_audit","水滴资料审核",""),
	SHUIDI_PROCESS("cte_shuidi_process","水滴案件状态同步",""),
	DLZ_SEND_INFO("cte_dlz_send_info","顶梁柱案件状态推送",""),
	DLZ_MATERIAL_INFO("cte_dlz_material_info","顶梁柱单证补传推送","");
	
	private String code;
	
	private String name;
	
	private String desc;
	
	private ComponentForwaderTypeEnum(String code, String name, String desc) {
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
