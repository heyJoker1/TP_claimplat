package com.claimplat.adminapi.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claimplat.common.constant.RestResult;
import com.claimplat.common.constant.ReturnCode;

@Controller
public class DefaultErrorController {
	
	private static Logger logger = LoggerFactory.getLogger(DefaultErrorController.class);
	
	private final static String ERROR_PATH = "/error";
	
	@ResponseBody
	@RequestMapping(value = ERROR_PATH)
	public ResponseEntity<Object> errorHtml(HttpServletRequest request){
		RestResult restResult = new RestResult();
		restResult.setStatus(ReturnCode.REC_5.getCode());
		restResult.setMsg(ReturnCode.REC_5.getName());
		logger.error("找不到请求地址，返回的响应数据={}",restResult);
		return new ResponseEntity<>(restResult,HttpStatus.NOT_FOUND);
	}
	
	public static String getErrorPath() {
		return ERROR_PATH;
	}
}
