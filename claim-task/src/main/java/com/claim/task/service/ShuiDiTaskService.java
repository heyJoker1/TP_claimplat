package com.claim.task.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.claim.common.bean.entity.GcClaimThirdinfoTask;
import com.claim.task.dao.GcAdJustMentMainDaoImpl;
import com.claim.task.dao.GcClaimThirdinfoTaskDaoImpl;

@Service
public class ShuiDiTaskService extends BaseService{

	@Resource
	private GcClaimThirdinfoTaskDaoImpl gcClaimThirdinfoTaskDaoImpl;
	
	@Resource
	private GcAdJustMentMainDaoImpl gcAdJustMentMainDaoImpl;
	
	@Value("${claimplat.url}")
	private String claimplatUrl;
	
	public List<GcClaimThirdinfoTask> queryBackCase() {
		List<GcClaimThirdinfoTask> gcClaimThirdinfoTasks = gcClaimThirdinfoTaskDaoImpl.findUntreated("001","W","F");
		return gcClaimThirdinfoTasks;
	}

	public void backCase(GcClaimThirdinfoTask gcClaimThirdinfoTask) {
		String claimStatus = gcClaimThirdinfoTask.getClaimStatus();
		long status = 0;
		if("1".equals(claimStatus)) { //结案
			status = 5;
		}
		if("2".equals(claimStatus)) { //销案
			status = 99;
		}
		if("3".equals(claimStatus)) { //拒赔
			status = 6;
		}
		if("7".equals(claimStatus)) { //零赔付
			status = 7;
		}
		
		JSONObject bizContent = new JSONObject();			//bizContent数据封装（JSON）
		bizContent.put("claimNo", gcClaimThirdinfoTask.getClaimNo());
		bizContent.put("payAmount", gcAdJustMentMainDaoImpl.findPayAmountByRegistNo(gcClaimThirdinfoTask.getRegistNo()));//通过报案号查询赔付金额
		bizContent.put("policyNo", gcAdJustMentMainDaoImpl.findPolicyNoByRegistNo(gcClaimThirdinfoTask.getRegistNo()));//通过报案号查询保单号
		bizContent.put("processTime", gcClaimThirdinfoTask.getCreateDate());
		bizContent.put("status",status);
		
		JSONObject content = new JSONObject();
		content.put("UUID",gcClaimThirdinfoTask.getUUID());
		content.put("BizContent",bizContent);				//content封装bizContent数据（JSON）
		content.put("ServiceName","iProcess");
		content.put("Timestamp",System.currentTimeMillis());
		
		HttpHeaders headers = new HttpHeaders();			//http请求头（utf-8）
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
		headers.add("Accept-Charset","utf-8");
		
		HttpEntity<String> formEntity = new HttpEntity<>(content.toString(),headers); 	//http请求
		
		//根据配置文件的水滴url传输到serviceapi工程
		String resultJson = restTemplate.postForObject(claimplatUrl, formEntity, String.class);	
		JSONObject jsonResult = JSONObject.parseObject(resultJson);
		String code = jsonResult.getString("code");
		
		//正常结案与调用次数小于3次，则修改案件状态
		if(code.equals("1") && gcClaimThirdinfoTask.getFailTimes() < 3) {
			gcClaimThirdinfoTaskDaoImpl.updatesStatus(gcClaimThirdinfoTask.getUUID());
		}
	}

}
