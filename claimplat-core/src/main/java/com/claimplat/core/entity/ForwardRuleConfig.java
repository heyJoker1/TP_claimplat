package com.claimplat.core.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.common.enums.DataFormatEnum;

/**
 * forward参数转换规则定义信息
 * @author Joker
 *
 */
@Entity
@Table(name = "forward_rule_cnfig",uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class ForwardRuleConfig extends BaseDateTimeEntity{

	private static final long serialVersionUID = 8337208948876411071L;

	//code唯一标识
	private String code;			
	
	//商户code标识，作为路由字段，不同商户的参数转换规则可能是不一样的
	private String merchantCode;
	
	//业务类型，不同业务类型的参数转换规则可能是不一样的
	@Enumerated(value = EnumType.STRING)
	private BusinessForwardTypeEnum businessType;
	
	//转换前数据格式类型
	@Enumerated(value = EnumType.STRING)
	private DataFormatEnum dataFormatBefore = DataFormatEnum.JSON;
	
	//转换后数据格式类型，可为空
	@Enumerated(value = EnumType.STRING)
	private DataFormatEnum dataFormatAfter = DataFormatEnum.JSON;
	
	//业务系统数据请求格式
	private String templateBefore;
	
	//外部第三方系统数据请求格式
	private String templateAfter;
	
	//备注信息
	private String remark;
	
	//乐观锁标识
	@Version
	private Long version =1L;

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

	public BusinessForwardTypeEnum getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessForwardTypeEnum businessType) {
		this.businessType = businessType;
	}

	public DataFormatEnum getDataFormatBefore() {
		return dataFormatBefore;
	}

	public void setDataFormatBefore(DataFormatEnum dataFormatBefore) {
		this.dataFormatBefore = dataFormatBefore;
	}

	public DataFormatEnum getDataFormatAfter() {
		return dataFormatAfter;
	}

	public void setDataFormatAfter(DataFormatEnum dataFormatAfter) {
		this.dataFormatAfter = dataFormatAfter;
	}

	public String getTemplateBefore() {
		return templateBefore;
	}

	public void setTemplateBefore(String templateBefore) {
		this.templateBefore = templateBefore;
	}

	public String getTemplateAfter() {
		return templateAfter;
	}

	public void setTemplateAfter(String templateAfter) {
		this.templateAfter = templateAfter;
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
