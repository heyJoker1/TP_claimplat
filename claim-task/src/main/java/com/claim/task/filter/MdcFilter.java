package com.claim.task.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(asyncSupported = true,filterName = "mdc",urlPatterns = "/*")
public class MdcFilter implements Filter{

	private Logger logger = LoggerFactory.getLogger(MdcFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("mdcFilter init success");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			String requestUUID = httpRequest.getHeader("x-request-uuid");
			if(StringUtils.isEmpty(requestUUID)) {
				requestUUID = request.getParameter("request_uuid");
			}
			if(StringUtils.isEmpty(requestUUID)) {
				requestUUID = UUID.randomUUID().toString();
			}
			MDC.put("request_uuid", requestUUID);
			chain.doFilter(request, response);
		} catch (Exception e) {
			MDC.remove("request_uuid");
		}
		
	}
	
	@Override
	public void destroy() {
		logger.info("mdcFilter destroy success");
	}

}
