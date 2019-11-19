package com.claimplat.adminapi.vo.parameter;

import com.claimplat.common.vo.BaseValueObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "基础参数列表")
public class ParameterConfigVo extends BaseValueObject{

	private static final long serialVersionUID = 4897786877959565152L;

	@ApiModelProperty(value = "唯一标识")
	private String code;
	
	@ApiModelProperty(value = "参数名")
	private String name;
	
	@ApiModelProperty(value = "参数值")
	private String value;
	
	@ApiModelProperty(value = "备注信息")
	private String remark;

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
}
