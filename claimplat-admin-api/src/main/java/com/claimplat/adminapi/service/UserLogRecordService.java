package com.claimplat.adminapi.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claimplat.adminapi.repository.UserLogRecordRepository;
import com.claimplat.adminapi.util.RequestUtil;
import com.claimplat.adminapi.util.UserAgentUtils;
import com.claimplat.common.enums.TerminalTypeEnum;
import com.claimplat.common.exception.BusinessException;
import com.claimplat.core.entity.UserLogRecord;

@Service
@Transactional
public class UserLogRecordService extends BaseService{

	@Autowired
	private UserLogRecordRepository userLogRecordRepository;
	
	/**
	 * 新增用户登录日志
	 * @param request
	 * @param token
	 */
	public void addUserLogRecord(HttpServletRequest request, String token) {
		try {
			Long userId = (Long)redisTemplate.boundValueOps(token).get();
			UserLogRecord userLogRecord = new UserLogRecord();
			if(UserAgentUtils.isComputer(request)) {
				userLogRecord.setTerminalType(TerminalTypeEnum.PC);
			}
			if(UserAgentUtils.isMobile(request)) {
				userLogRecord.setTerminalType(TerminalTypeEnum.MOBILE);
			}
			userLogRecord.setCreateDateTime(LocalDateTime.now());
			userLogRecord.setId(userId);
			userLogRecord.setIp(RequestUtil.getClientIp(request));
			userLogRecord.setUserToken(token);
			
			userLogRecordRepository.save(userLogRecord);
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("修改商户信息失败！",e);
		}
		
	}

}
