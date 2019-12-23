package com.claim.common.exception;

public class BaseRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 2512740686678291836L;
	
	protected String errorCode;
	protected String errorMsg;
	protected Throwable throwable;
	
	
	public BaseRuntimeException() {
		super();
	}
	
	public BaseRuntimeException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public BaseRuntimeException(Throwable throwable) {
		super();
		this.throwable = throwable;
	}

	public BaseRuntimeException(String errorMsg, Throwable throwable) {
		super();
		this.errorMsg = errorMsg;
		this.throwable = throwable;
	}

	public BaseRuntimeException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public BaseRuntimeException(String errorCode, String errorMsg, Throwable throwable) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.throwable = throwable;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
}
