package com.claimplat.common.bean.request;

import com.claimplat.common.enums.SignTypeEnum;

public class BasePlatfromHandleRequest extends BaseHandleRequest{

	private static final long serialVersionUID = -2809374356656865597L;

	protected String merchantCode;		//商户code
	
	private String requestId;			//第三方请求标识id，最大长度48位
	
	protected String nonce;				//第三方请求的随机数标识，最大长度36位
	
	protected Long timestamp;			//请求的UNIX时间戳格式，1970-01-01 00:00:00到现在的总毫秒数
	
	protected String version = "1.0.1";	//服务端接口的版本标识，默认1.0.1
	
	protected String charset = "UTF-8";	//字符集编码，目前只支持UTF-8
	
	protected SignTypeEnum signType = SignTypeEnum.MD5;	//签名类型
	
	protected String sign;				//根据signType生成的对于签名串

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public SignTypeEnum getSignType() {
		return signType;
	}

	public void setSignType(SignTypeEnum signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
