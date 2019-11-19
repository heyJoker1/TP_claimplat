package com.claimplat.adminapi.config.interceptor;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.claimplat.common.constant.RestResult;
import com.claimplat.common.constant.ReturnCode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginInterceptor implements HandlerInterceptor {
	
	private RedisTemplate redisTemplate;
	
	public LoginInterceptor(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String clientToken = request.getHeader("token");
		Long serverToken = (Long)redisTemplate.boundValueOps(clientToken).get();
		RestResult restResult = null;
		if(serverToken == null) {
			PrintWriter out = response.getWriter();
			restResult = new RestResult();
			restResult.setStatus(ReturnCode.REC_2003.getCode());
			restResult.setMsg(ReturnCode.REC_2003.getName());
			ObjectMapper objectMapper = new ObjectMapper();
			//返回登录失败状态码给前端接受
			out.print(objectMapper.writeValueAsString(restResult));
			out.flush();
			out.close();
			return false;
		}
		redisTemplate.boundValueOps(clientToken).expire(1800, TimeUnit.SECONDS);
		return true;
	}
}
