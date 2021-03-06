package com.claim.common.constant;

public enum ReturnCode {
	REC_0("0","系统繁忙，请稍后再试",""),
	REC_1("1","操作成功",""),
	REC_2("2","参数验证出错",""),
	REC_3("3","参数映射错误",""),
	REC_4("4","认证出错",""),
	REC_5("5","找不到请求的接口地址",""),
	REC_6("6","不支持的请求方法",""),
	REC_7("7","不正确的Content-Type请求头标识",""),
	REC_10("10","请求出错了","");
	
	private String code;
	private String name;
	private String desc;
	
	private ReturnCode(String code,String name,String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}	
}
