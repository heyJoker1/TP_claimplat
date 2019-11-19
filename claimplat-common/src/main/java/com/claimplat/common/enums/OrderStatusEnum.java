package com.claimplat.common.enums;

import java.io.Serializable;


/**
 * 请求单状态
 * @author Joker
 *
 */
public enum OrderStatusEnum implements Serializable{
	DEALING("ose_dealing","待处理","中间平台在调用业务系统前未处理完毕"),
	SUCCESS("ose_success","处理成功","业务系统处理成功"),
	COMPLETE("ose_complete","处理完成","外部第三方系统响应成功");

	private String code;
	
	private String name;
	
	private String desc;
	
	private OrderStatusEnum(String code, String name, String desc) {
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
