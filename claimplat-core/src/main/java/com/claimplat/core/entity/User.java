package com.claimplat.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.claimplat.common.enums.UserStatusEnum;

/**
 * 用户信息
 * @author Joker
 *
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class User extends BaseDateTimeEntity{

	private static final long serialVersionUID = 809981185992837700L;

	private String code;		//用户code，作为路由字段
	
	private String name;		//用户名称
	
	private String passwd;		//用户密码
	
	private String telephone;	//联系电话
	
	private String email;		//联系邮箱
	
	@Enumerated(value = EnumType.STRING)
	private UserStatusEnum status = UserStatusEnum.NORMAL; //状态
	
	private String remark;		//备注信息
	
	private int errorNumber;	//标识锁
	
	private LocalDateTime updatePwdDateTime;	//密码修改时间
	
	@Version
	private Long version = 1L;	//乐观锁

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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public LocalDateTime getUpdatePwdDateTime() {
		return updatePwdDateTime;
	}

	public void setUpdatePwdDateTime(LocalDateTime updatePwdDateTime) {
		this.updatePwdDateTime = updatePwdDateTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
