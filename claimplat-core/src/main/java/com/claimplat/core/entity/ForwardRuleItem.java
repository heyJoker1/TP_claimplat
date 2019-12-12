package com.claimplat.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * 具体参数转换规则
 * @author Joker
 *
 */
@Entity
@Table(name = "forward_rule_item",uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class ForwardRuleItem extends BaseDateTimeEntity{

	private static final long serialVersionUID = 7593354574195777525L;

	private String code;			//唯一标识
	
	private String merchantCode;	//商户标识，作为路由字段
	
	private Long ruleConfigId;		//关联的RuleConfig对象id
	
	private String ruleConfigCode;	//关联的RuleConfig对象code
	
	private String fieldNameBefore;	//转换前的字段名
	
	private String fieldTypeBefore;	//转换前的字段类型
	
	private String fieldNameAfter;	//转换后的字段名
	
	private String fieldTypeAfter;	//转换后的字段类型
	
	private String explainBefore;	//转换前字段说明情况
	
	private String explainAfter;	//转换后字段说明情况
	
	private String remark;			//备注信息
	
	@Version
	private Long version = 1L;		//乐观锁标识

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public Long getRuleConfigId() {
		return ruleConfigId;
	}

	public void setRuleConfigId(Long ruleConfigId) {
		this.ruleConfigId = ruleConfigId;
	}

	public String getRuleConfigCode() {
		return ruleConfigCode;
	}

	public void setRuleConfigCode(String ruleConfigCode) {
		this.ruleConfigCode = ruleConfigCode;
	}

	public String getFieldNameBefore() {
		return fieldNameBefore;
	}

	public void setFieldNameBefore(String fieldNameBefore) {
		this.fieldNameBefore = fieldNameBefore;
	}

	public String getFieldTypeBefore() {
		return fieldTypeBefore;
	}

	public void setFieldTypeBefore(String fieldTypeBefore) {
		this.fieldTypeBefore = fieldTypeBefore;
	}

	public String getFieldNameAfter() {
		return fieldNameAfter;
	}

	public void setFieldNameAfter(String fieldNameAfter) {
		this.fieldNameAfter = fieldNameAfter;
	}

	public String getFieldTypeAfter() {
		return fieldTypeAfter;
	}

	public void setFieldTypeAfter(String fieldTypeAfter) {
		this.fieldTypeAfter = fieldTypeAfter;
	}

	public String getExplainBefore() {
		return explainBefore;
	}

	public void setExplainBefore(String explainBefore) {
		this.explainBefore = explainBefore;
	}

	public String getExplainAfter() {
		return explainAfter;
	}

	public void setExplainAfter(String explainAfter) {
		this.explainAfter = explainAfter;
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
