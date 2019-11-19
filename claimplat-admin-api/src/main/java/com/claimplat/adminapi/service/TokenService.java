package com.claimplat.adminapi.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.claimplat.common.util.DateUtil;
import com.claimplat.core.entity.User;

@Service
public class TokenService extends BaseService{

	/**
	 * 生成当前请求登录用户的Token串
	 * @param user
	 * @return
	 */
	public String generateToken(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("token:");
		StringBuilder token = new StringBuilder();
		String userName = user.getName();
		String userCode = user.getCode();
		token.append(userCode);
		token.append("-" + userName);
		String generateDate = DateUtil.formatDate(new Date(),DateUtil.DATEFORMATT_DATETIME14).toString();
		token.append("-" + generateDate);
		sb.append(DigestUtils.md5Hex(token.toString()));
		return sb.toString();
	}
	
	/**
	 * token的置换
	 * @param oldToken
	 * @return
	 */
	public boolean checkToken(String oldToken) {
		boolean displace = false;
		Long timeOut = redisTemplate.boundValueOps(oldToken).getExpire();
		long expireTime = 60*5;
		if(timeOut>expireTime) {
			displace = redisTemplate.boundValueOps(oldToken).expire(1800, TimeUnit.SECONDS);
		}
		return displace;
	}

}
