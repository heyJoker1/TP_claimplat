package com.claimplat.adminapi.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	/*获取请求主机ip，若通过代理进来则透过防火墙获取真实ip地址*/
	public static String getClientIp(HttpServletRequest request) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("x-forwarded-for");
				}
				
				if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("Proxy-Client-IP");
				}
				
				if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("WL-Proxy-Client-IP");
				}
				
				if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_CLIENT_IP");
				}
				
				if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				}
				
				if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
				}						
			}else if(ip.length() > 15) {
				String[] ips = ip.split(",");
				for(int index = 0;index < ips.length;index++) {
					String strIp = (String) ips[index];
					if(!("unknown".equalsIgnoreCase(strIp))) {
						ip = strIp;
						break;
					}
				}
			}
			return ip;
		} catch (Exception e) {
			logger.error("获取ip地址发生错误",e);
			return "";
		}
	}
}
