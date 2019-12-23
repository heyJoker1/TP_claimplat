package com.claim.common.exception;

public class ValidateException extends BaseRuntimeException{

	private static final long serialVersionUID = -5310403847391573657L;

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
