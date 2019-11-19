package com.claimplat.adminapi.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.claimplat.common.constant.RestResult;
import com.claimplat.common.constant.ReturnCode;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		RestResult restResult = new RestResult();
		restResult.setStatus(ReturnCode.REC_3.getCode());
		restResult.setMsg(ReturnCode.REC_3.getName());
		logger.error("请求的参数映射出错，返回的响应数据={}",restResult + ":" + request.getDescription(true),ex);
		return new ResponseEntity<>(restResult,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		RestResult restResult = new RestResult();
		restResult.setStatus(ReturnCode.REC_6.getCode());
		restResult.setMsg(ReturnCode.REC_6.getName());
		logger.error("请求的方法不支持，返回的响应数据={}",restResult + ":" + request.getDescription(true),ex);
		return new ResponseEntity<>(restResult,HttpStatus.METHOD_NOT_ALLOWED);
	}
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		RestResult restResult = new RestResult();
		restResult.setStatus(ReturnCode.REC_7.getCode());
		restResult.setMsg(ReturnCode.REC_7.getName());
		logger.error("请求的mediaType不支持，返回的响应数据={}",restResult + ":" + request.getDescription(true),ex);
		return new ResponseEntity<>(restResult,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		StringBuilder builder = new StringBuilder();
		for(FieldError error : fieldErrors) {
			builder.append(error.getDefaultMessage()).append(" ");
		}
		
		RestResult restResult = new RestResult();
		restResult.setStatus(ReturnCode.REC_2.getCode());
		restResult.setMsg(builder.toString().trim());
		logger.error("参数验证出错，返回的响应数据={}，异常ex={}",restResult + ":" + request.getDescription(true),ex);
		return new ResponseEntity<>(restResult,HttpStatus.OK);
	}
}
