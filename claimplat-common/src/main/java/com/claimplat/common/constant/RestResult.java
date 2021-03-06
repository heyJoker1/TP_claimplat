package com.claimplat.common.constant;

import java.io.Serializable;

public class RestResult implements Serializable{
	private static final long serialVersionUID = -5007715487173036938L; 
	/*返回的编码，标识接口调用成功或失败*/
	protected String status = ReturnCode.REC_1.getCode();
	/*返回的提示消息*/
	protected String msg = ReturnCode.REC_1.getName();
	/*返回的数据*/
	protected Object data;

	public static RestResult success(Object data) {
		RestResult restResult = new RestResult();
		restResult.setData(data);
		return restResult;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RestResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
}
