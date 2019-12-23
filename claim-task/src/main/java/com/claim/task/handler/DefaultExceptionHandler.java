package com.claim.task.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.claim.common.constant.ReturnCode;
import com.claim.common.constant.RpcResult;
import com.claim.common.exception.BusinessException;
import com.claim.common.exception.ValidateException;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public RpcResult handleException(Exception e) {
		RpcResult RpcResult = new RpcResult();
		if(e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
			RpcResult.setStatus(ReturnCode.REC_2.getCode());
			RpcResult.setMsg(e.getMessage());
		}else if(e instanceof ValidateException) {
			ValidateException validateException = (ValidateException) e;
			RpcResult.setStatus(validateException.getErrorCode());
			RpcResult.setMsg(validateException.getErrorMsg());
		}else if(e instanceof BusinessException){
			RpcResult.setStatus(ReturnCode.REC_0.getCode());
			RpcResult.setMsg(ReturnCode.REC_0.getName());
		}else {
			RpcResult.setStatus(ReturnCode.REC_0.getCode());
			RpcResult.setMsg(ReturnCode.REC_0.getName());
		}
		logger.error("请求出错了",e);
		return RpcResult;
	}
}
