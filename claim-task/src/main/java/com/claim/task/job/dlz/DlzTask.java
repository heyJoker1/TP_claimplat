package com.claim.task.job.dlz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.claim.common.bean.entity.GcClaimThirdinfoTask;
import com.claim.task.job.BaseTask;
import com.claim.task.service.dlz.DlzTaskService;

/**蚂蚁-顶梁柱案件状态推送定时任务
 * @author Joker
 */
@Component
public class DlzTask extends BaseTask{

	@Resource
	private DlzTaskService dlzTaskService;
	
	@Scheduled(cron = "*/5 * * * * ?")					//5分钟自动扫描表格查询
	public void run() {
		try {
			logger.info("DlzTask组件执行开始");
			
			List<GcClaimThirdinfoTask> dlzData = dlzTaskService.getDlzData();
			
			for (GcClaimThirdinfoTask gcClaimThirdinfoTask : dlzData) {
				dlzTaskService.getDlzPushMessage(gcClaimThirdinfoTask);
			}
			
		} catch (Exception e) {
			logger.info("DlzTask组件执行异常：",e);
		}
		logger.info("Dlz组件执行结束：");
	}
}
