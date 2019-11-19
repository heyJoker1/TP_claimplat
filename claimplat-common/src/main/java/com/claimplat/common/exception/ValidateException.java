package com.claimplat.common.exception;

public class ValidateException extends BaseRuntimeException{

	private static final long serialVersionUID = -8445446767377843460L;

	public ValidateException(String errorCode, String errorMsg, Throwable throwable) {
		super(errorCode, errorMsg, throwable);
	}

	public ValidateException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	public ValidateException(String errorMsg, Throwable throwable) {
		super(errorMsg, throwable);
	}

	public ValidateException(String errorMsg) {
		super(errorMsg);
	}

	public ValidateException(Throwable throwable) {
		super(throwable);
	}

	
}
