package com.claimplat.adminapi.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claimplat.adminapi.repository.UserRepository;
import com.claimplat.common.enums.UserPwdStatusEnum;
import com.claimplat.common.enums.UserStatusEnum;
import com.claimplat.core.entity.User;

@Service
public class UserService extends BaseService{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserLogRecordService userLogRecordService;
	
	public String login(String userName, String password, HttpServletRequest request) {
		int errorCount = 0;
		User user = userRepository.findByName(userName);
		if(user == null) {
			throw new IllegalStateException("账号输入错误！");
		}
		
		String md5Pwd = DigestUtils.md5Hex(password).toString();
		int errorNumber = user.getErrorNumber();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime upTime = user.getUpdateDateTime();
		long betweenTime = ChronoUnit.MINUTES.between(upTime, now);
		if(betweenTime > 3) {
			user.setErrorNumber(0);
			userRepository.save(user);
		}
		errorNumber = user.getErrorNumber();
		if(betweenTime <= 3 && errorNumber>=3) {
			throw new IllegalStateException("当前账户被锁定！");
		}
		
		if(!user.getPasswd().equals(md5Pwd.toUpperCase())) {
			errorNumber = errorNumber+1;
			user.setErrorNumber(errorNumber);
			user.setUpdateDateTime(LocalDateTime.now());
			userRepository.save(user);
			int count = 3-errorNumber;
			throw new IllegalStateException("密码输入错误"+errorNumber+"次，还可输入"+count+"次后将被锁定");
		}
		
		if(user.getStatus()==UserStatusEnum.FORBIDDEN) {
			throw new IllegalStateException("该账户已被禁用！");
		}
		
		//转成token存储
		String token = tokenService.generateToken(user);
		redisTemplate.opsForValue().set(token,user.getId(),Duration.ofSeconds(1800));
		
		//登录日志记录
		userLogRecordService.addUserLogRecord(request,token);
		user.setErrorNumber(0);
		user.setUpdateDateTime(LocalDateTime.now());
		userRepository.save(user);
		return token;
	}

	public UserPwdStatusEnum isFlagUpdatePwd(String userName) {
		User user = userRepository.findByName(userName);
		LocalDateTime upPwdTime = user.getUpdatePwdDateTime();
		if(upPwdTime == null) {
			return UserPwdStatusEnum.INFO;
		}else {
			LocalDateTime now = LocalDateTime.now();
			long between = ChronoUnit.DAYS.between(upPwdTime, now);
			if(between <= 85) {
				return UserPwdStatusEnum.NORMAL;
			}else if(between > 85 && between <= 90) {
				return UserPwdStatusEnum.WARRING;
			}else {
				return UserPwdStatusEnum.FORBIDDEN;
			}
		}
	}
	
	
	/**
	 * redis中移除当前登录状态
	 * @param token
	 */
	public void logout(String token) {
		redisTemplate.delete(token);
	}

	
	
	/**
	 * 验证密码
	 */
	public void checkPassword(String token, String password) {
		Long userId = (Long) redisTemplate.boundValueOps(token).get();
		User user = userRepository.findByIdEquals(userId);
		if(!user.getPasswd().equals(password)) {
			throw new IllegalArgumentException("旧密码错误！");
		}
	}

	/**
	 * 修改密码
	 */
	public void updatePassword(String token, String password) {
		
		try {
			
			Long userId = (Long) redisTemplate.boundValueOps(token).get();
			User user = userRepository.findByIdEquals(userId);
			user.setPasswd(password);
			user.setUpdatePwdDateTime(LocalDateTime.now());
			userRepository.save(user);
			
		} catch (IllegalStateException e) {
			throw new IllegalStateException("删除失败！",e);
		} catch (Exception e) {
			throw e;
		}
		
	}


	
}
