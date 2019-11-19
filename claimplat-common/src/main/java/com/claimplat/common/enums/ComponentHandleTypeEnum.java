package com.claimplat.common.enums;

import java.io.Serializable;

/**
 * 组件类型，多个组件类型可关联一个业务类型
 * @author Joker
 *
 */
public enum ComponentHandleTypeEnum implements Serializable{
	APPLY_REPORT("cte_apply_report","保单报案标准参数",""),
	APPLY_REPORT_NONE_STANDARD("cte_apply_report_none_standard","保单报案非标准参数",""),
	VALIDATE_POLICY("cte_validate_policy","保单验证",""),
	SHUIDI_VERIFY("cte_shuidi_verify","水滴保单校验",""),
	SHUIDI_REPORT("cte_shuidi_report","水滴理赔报案",""),
	SHUIDI_PUSH("cte_shuidi_push","水滴资料推送","");	
	
	private String code;
	
	private String name;
	
	private String desc;
	
	private ComponentHandleTypeEnum(String code, String name, String desc) {
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
