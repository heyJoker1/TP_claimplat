package com.claimplat.common.enums;

import java.io.Serializable;

/**用户状态
 * @author Joker
 *
 */
public enum UserPwdStatusEnum implements Serializable{

	NORMAL("normal","正常",""),
	INFO("info","请修改初始密码",""),
	WARRING("warring","危险",""),
	FORBIDDEN("forbidden","禁用","");
	
	
	private String code;
	
	private String name;
	
	private String desc;
	
	private UserPwdStatusEnum(String code, String name, String desc) {
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
