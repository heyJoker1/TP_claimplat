package com.claim.task.service.dlz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.claim.common.bean.entity.GcClaimThirdinfoTask;
import com.claim.common.bean.entity.dlz.material.DlzMaterialThridpartEntity;
import com.claim.task.dao.GcClaimThirdinfoTaskDaoImpl;
import com.claim.task.dao.dlz.DlzMaterialThridpartDaoImpl;
import com.claim.task.dao.dlz.DlzThridpartDaoImpl;
import com.claim.task.service.BaseService;

@Service
public class DlzMaterialTaskService extends BaseService{

	@Resource
	private GcClaimThirdinfoTaskDaoImpl gcclaimThirdinfoTaskDaoImpl;

	@Resource
	private DlzThridpartDaoImpl dlzThridpartDaoImpl;
	
	@Resource
	private DlzMaterialThridpartDaoImpl dlzMaterialThridpartDaoImpl;
	
	@Value("${dlzmaterial.url}")
	private String dlzMaterialUrl;

	public List<GcClaimThirdinfoTask> getDlzMaterialData() {
		List<String> status = new ArrayList<String>();
		status.add("W");			//待处理
		status.add("F");			//失败
		
		String type = "010";	//蚂蚁-顶梁柱类型
		
		List<GcClaimThirdinfoTask> dlzData = gcclaimThirdinfoTaskDaoImpl.getDlzMaterial(status,type);
		
		return dlzData;
	}

	public void getDlzMaterialPushMessage(GcClaimThirdinfoTask gcClaimThirdinfoTask) {
		
		JSONObject content = new JSONObject();
		
		List<DlzMaterialThridpartEntity> materialContext = dlzMaterialThridpartDaoImpl.getMaterialContext(gcClaimThirdinfoTask.getRegistNo());
		for(DlzMaterialThridpartEntity dlzMaterialThridpartEntity : materialContext) {
			content.put("rejectReason",dlzMaterialThridpartEntity.getContext());
		}
		
		DlzMaterialThridpartEntity policies = dlzMaterialThridpartDaoImpl.getPolicies(gcClaimThirdinfoTask.getRegistNo());	
		content.put("outBizNo",policies.getBusinessno2());
		content.put("policyNo",policies.getBusinessno3());
		content.put("outPolicyNo",policies.getPolicyno());
		content.put("reportNo",policies.getBusinessno1());
		content.put("outReportNo",gcClaimThirdinfoTask.getRegistNo());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
		headers.add("Accept-Charset","utf-8");

		HttpEntity<String> httpEntity = new HttpEntity<String>(content.toString(),headers);
		logger.info("传输的数据 ={}",httpEntity);

		//通过http协议将数据传到serviceapi工程进行处理推送给外部第三方系统
		String JsonResult = restTemplate.postForObject(dlzMaterialUrl, httpEntity, String.class);
		logger.info("返回值= {}",JsonResult);

		//当定时任务处理完成后修改表中的数据
		JSONObject parseObject = JSONObject.parseObject(JsonResult);
		logger.info("parseObject值= {}",parseObject);
		
		String status = parseObject.getString("status");
		//只处理status=1与failtime小于3的任务
		if(status.equals("1") && gcClaimThirdinfoTask.getFailTimes()<3) {
			//成功处理后，修改status=s，定时任务处理完成
			gcclaimThirdinfoTaskDaoImpl.updatesStatus(gcClaimThirdinfoTask.getUUID());
		}else {
			//失败处理后，failtime次数+1，status=F待处理
			gcclaimThirdinfoTaskDaoImpl.updatesStatusAndFailtimes(gcClaimThirdinfoTask.getUUID());
		}
	}
	
	

}
