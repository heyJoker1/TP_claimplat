package com.claimplat.core.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.claimplat.common.enums.TerminalTypeEnum;

/**
 * 系统日志
 * @author Joker
 *
 */
@Entity
@Table(name = "userlog_record")
public class UserLogRecord extends BaseDateTimeEntity{

	private static final long serialVersionUID = -4339439036591749393L;

	private Long userId;		//发起请求人
	
	@Enumerated(value = EnumType.STRING)
	private TerminalTypeEnum terminalType = TerminalTypeEnum.PC;	//终端类型
	
	private String userToken;	//用户token；
	
	private String ip;			//当前用户ip
	
	private String remark;		//备注信息

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TerminalTypeEnum getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(TerminalTypeEnum terminalType) {
		this.terminalType = terminalType;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
