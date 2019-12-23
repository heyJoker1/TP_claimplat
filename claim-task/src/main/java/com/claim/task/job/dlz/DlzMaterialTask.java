package com.claim.task.job.dlz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.claim.common.bean.entity.GcClaimThirdinfoTask;
import com.claim.task.job.BaseTask;
import com.claim.task.service.dlz.DlzMaterialTaskService;


/**蚂蚁-顶梁柱单证补传推送定时任务
 * @author Joker
 *
 */
@Component
public class DlzMaterialTask extends BaseTask{
	
	@Resource
	private DlzMaterialTaskService dlzMaterialTaskService;
	
	@Scheduled(cron = "*/5 * * * * ?")					//5分钟自动扫描表格查询
	public void run() {
		try {
			logger.info("DlzMaterialTask组件执行开始");
			
			List<GcClaimThirdinfoTask> dlzData = dlzMaterialTaskService.getDlzMaterialData();
			
			for (GcClaimThirdinfoTask gcClaimThirdinfoTask : dlzData) {
				dlzMaterialTaskService.getDlzMaterialPushMessage(gcClaimThirdinfoTask);
			}
			
		} catch (Exception e) {
			logger.info("DlzMaterialTask组件执行异常：",e);
		}
		logger.info("DlzMaterialTask组件执行结束：");
	}
}
