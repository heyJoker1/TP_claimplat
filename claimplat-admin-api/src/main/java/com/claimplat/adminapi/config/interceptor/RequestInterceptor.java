package com.claimplat.adminapi.config.interceptor;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		
		String method = request.getMethod();
		if(StringUtils.isNotEmpty(method) && method.equalsIgnoreCase("options")) {
			logger.info("method is options,return false");
			return false;
		}
		
		if(logger.isDebugEnabled()) {
			StringBuilder headerSb = new StringBuilder();
			StringBuilder parameterSb = new StringBuilder();
			HashMap<String, String> headerMap = new HashMap<>();
			HashMap<String, String> parameterMap = new HashMap<>();
			String url = request.getRequestURI();
			
			StringBuilder sb = new StringBuilder();
			sb.append("\n请求的url: ");
			sb.append("\n"+url);
			sb.append("\n");
			sb.append("\n请求头信息:  ");
			
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames !=null && headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				headerSb.append("\n").append(headerName+": "+headerValue);
				headerMap.put(headerName, headerValue);
			}
			sb.append(headerSb.toString());
			sb.append("\n");
			sb.append("\n请求的参数信息： ");
			
			Enumeration<String> parameterNames = request.getParameterNames();
			while(parameterNames !=null && parameterNames.hasMoreElements()) {
				String parameterName = parameterNames.nextElement();
				String parameterValue = request.getParameter(parameterName);
				headerSb.append("\n").append(parameterName+": "+parameterValue);
				headerMap.put(parameterName, parameterValue);
			}
			sb.append(parameterSb.toString());
			sb.append("\n");
			
			logger.debug(sb.toString());
		}
		return true;
	}

}
