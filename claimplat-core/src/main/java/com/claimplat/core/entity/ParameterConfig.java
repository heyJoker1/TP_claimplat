package com.claimplat.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * 系统参数定义信息
 * @author Joker
 *
 */
@Entity
@Table(name = "parameter_config",uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class ParameterConfig extends BaseDateTimeEntity{

	private static final long serialVersionUID = -7059337456724016458L;

	private String code;	//唯一标识
	
	private String name;	//参数名
	
	private String value;	//参数值
	
	private String remark;	//备注信息
	
	@Version
	private Long version = 1L;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
