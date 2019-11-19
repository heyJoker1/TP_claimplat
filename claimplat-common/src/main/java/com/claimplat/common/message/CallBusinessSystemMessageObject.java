package com.claimplat.common.message;

/**
 * 调用业务系统接口消息对象
 * @author Joker
 *
 */
public class CallBusinessSystemMessageObject extends BaseMessageObject{

	//请求单code标识
	private String orderCode;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	
}
