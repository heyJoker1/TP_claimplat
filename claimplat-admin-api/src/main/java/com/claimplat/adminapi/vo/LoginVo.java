package com.claimplat.adminapi.vo;

import com.claimplat.common.enums.UserPwdStatusEnum;
import com.claimplat.common.vo.BaseValueObject;

public class LoginVo extends BaseValueObject{

	private static final long serialVersionUID = 1L;

	private UserPwdStatusEnum status;
	
	private String token;

	public UserPwdStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserPwdStatusEnum status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginVo(UserPwdStatusEnum status, String token) {
		super();
		this.status = status;
		this.token = token;
	}
	
	
	
}
