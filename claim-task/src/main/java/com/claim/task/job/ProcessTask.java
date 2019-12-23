package com.claim.task.job;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.claim.common.bean.entity.GcClaimThirdinfoTask;
import com.claim.common.util.DateUtil;
import com.claim.task.service.ShuiDiTaskService;

/** 水滴筹案件状态推送定时查询任务
 * @author Joker
 */
@Component
public class ProcessTask extends BaseTask{

	private AtomicLong atomicLong = new AtomicLong(0);
	
	@Resource
	private ShuiDiTaskService shuiDiTaskService;
	
	@Scheduled(cron = "0/10 * * * * ?")
	public void call() {
		long count = 0L;
		
		//任务开始时间
		LocalDateTime beginDateTime = LocalDateTime.now();							
		
		try {
			count = atomicLong.incrementAndGet();
			
			logger.info("ProcessTask组件执行开始，atomicLong={},开始时间={}",count,DateUtil.formatLocalDateTime(beginDateTime, DateUtil.DATEFORMATT_TIMESTAMP));
			
			List<GcClaimThirdinfoTask> queryBackCase = shuiDiTaskService.queryBackCase();		//查询GcclaimThirdinfoTask表格是否有数据
			
			for(GcClaimThirdinfoTask gcclaimThirdinfoTask : queryBackCase) {
				try {
					shuiDiTaskService.backCase(gcclaimThirdinfoTask);
				} catch (Exception e) {
					logger.error("案件同步出现异常，异常信息为："+e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error("ProcessTask组件执行出错",e);
		} finally {
			//任务结束时间
			LocalDateTime endDateTime = LocalDateTime.now();
			
			Duration duration = Duration.between(beginDateTime, endDateTime);
			
			logger.error("ProcessTask组件执行结束，atomicLong={},结束时间={},消耗时间={}",count,DateUtil.formatLocalDateTime(endDateTime, DateUtil.DATEFORMATT_TIMESTAMP),duration.toMillis());
		}
	}
	
}
