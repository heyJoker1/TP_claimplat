package com.claimplat.common.exception;

public class BusinessException extends BaseRuntimeException{

	private static final long serialVersionUID = 8459468792556744033L;

	public BusinessException(String errorCode, String errorMsg, Throwable throwable) {
		super(errorCode, errorMsg, throwable);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String errorMsg, Throwable throwable) {
		super(errorMsg, throwable);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String errorMsg) {
		super(errorMsg);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	
}
