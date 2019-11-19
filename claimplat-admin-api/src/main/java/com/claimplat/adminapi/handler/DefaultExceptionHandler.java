package com.claimplat.adminapi.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.claimplat.common.constant.RestResult;
import com.claimplat.common.constant.ReturnCode;
import com.claimplat.common.exception.BusinessException;
import com.claimplat.common.exception.ValidateException;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public RestResult handleException(Exception e) {
		RestResult restResult = new RestResult();
		if(e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
			restResult.setStatus(ReturnCode.REC_2.getCode());
			restResult.setMsg(e.getMessage());
		}else if(e instanceof ValidateException) {
			ValidateException validateException = (ValidateException) e;
			restResult.setStatus(validateException.getErrorCode());
			restResult.setMsg(validateException.getErrorMsg());
		}else if(e instanceof BusinessException){
			restResult.setStatus(ReturnCode.REC_0.getCode());
			restResult.setMsg(ReturnCode.REC_0.getName());
		}else {
			restResult.setStatus(ReturnCode.REC_0.getCode());
			restResult.setMsg(ReturnCode.REC_0.getName());
		}
		logger.error("请求出错了",e);
		return restResult;
	}
}
