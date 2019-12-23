package com.claim.task.service.dlz;

import java.text.SimpleDateFormat;
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
import com.claim.common.bean.entity.dlz.cases.DlzAdjustDetailsEntity;
import com.claim.common.bean.entity.dlz.cases.DlzAdjustTypeEntity;
import com.claim.common.bean.entity.dlz.cases.DlzClaimContextEntity;
import com.claim.task.dao.GcClaimThirdinfoTaskDaoImpl;
import com.claim.task.dao.dlz.DlzThridpartDaoImpl;
import com.claim.task.service.BaseService;

/**蚂蚁-顶梁柱案件推送查询出的数据传输到serviceapi处理
 * @author Joker
 */
@Service
public class DlzTaskService extends BaseService{

	@Resource
	private GcClaimThirdinfoTaskDaoImpl gcclaimThirdinfoTaskDaoImpl;

	@Resource
	private DlzThridpartDaoImpl dlzThridpartDaoImpl;

	@Value("${dlz.url}")
	private String dlzUrl;

	/*定时任务表查询*/
	public List<GcClaimThirdinfoTask> getDlzData() {

		List<String> status = new ArrayList<String>();
		status.add("W");			//待处理
		status.add("F");			//失败

		String type = "010";		//蚂蚁-顶梁柱类型

		List<GcClaimThirdinfoTask> dlzData = gcclaimThirdinfoTaskDaoImpl.getDlzData(status,type);

		return dlzData;
	}

	/*查询蚂蚁-顶梁柱案件状态推送需要的数据*/
	public void getDlzPushMessage(GcClaimThirdinfoTask gcClaimThirdinfoTask) {

		//将查询出的数据利用JSONObject转成JSON串
		JSONObject content = new JSONObject();
		content.put("claimStatus",gcClaimThirdinfoTask.getClaimStatus());		//传输理赔状态serviceapi作结案与销案判断
		content.put("outReportNo",gcClaimThirdinfoTask.getRegistNo());			//内部报案号即保险公司报案号

		/*定时任务表中claimStatus=1为已结案*/
		if(gcClaimThirdinfoTask.getClaimStatus().equals("1")) {

			//先根据定时任务表中的报案号查询获取Adjustmenttype的值作为案件判断条件
			DlzAdjustTypeEntity adjustType = dlzThridpartDaoImpl.getAdjustType(gcClaimThirdinfoTask.getRegistNo());
			content.put("claimAdjustmenttype",adjustType.getAdjustmenttype());	//传输结案状态serviceapi作是否正常结案判断
			
			//根据定时任务表中的报案号查询gcclaimmain与gcadjustmentmain表中理赔信息
			List<DlzClaimContextEntity> claimContext = dlzThridpartDaoImpl.getClaimContext(gcClaimThirdinfoTask.getRegistNo());

			if(gcClaimThirdinfoTask.getClaimStatus().equals("1")) {
				//Adjustmenttype=1为正常结案
				if(adjustType.getAdjustmenttype().equals("1")) {

					for (DlzClaimContextEntity dlzThridpartEntity : claimContext) {

						content.put("reportNo",dlzThridpartEntity.getBusinessno1());

						content.put("outBizNo",dlzThridpartEntity.getBusinessno2());

						content.put("policyNo",dlzThridpartEntity.getBusinessno3());

						content.put("outClaimNo",dlzThridpartEntity.getRegistno());

						content.put("claimFee",dlzThridpartEntity.getSumpaid());
						//将date转换成yyyyMMddHHmmss格式
						String successTime = new SimpleDateFormat("yyyyMMddHHmmss").format(dlzThridpartEntity.getEndcasedate());
						content.put("claimSuccessTime",successTime);

						String assessTime = new SimpleDateFormat("yyyyMMddHHmmss").format(dlzThridpartEntity.getUnderwriteenddate());
						content.put("claimAssessTime",assessTime);

						String claimRecordTime = new SimpleDateFormat("yyyyMMddHHmmss").format(dlzThridpartEntity.getOpendate());
						content.put("claimRecordTime",claimRecordTime);

						String accidentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(dlzThridpartEntity.getDamagestartdate());
						content.put("accidentTime",accidentTime);

						content.put("claimAccidentType",dlzThridpartEntity.getDamagecode());
					}


					//根据定时任务表中的报案号查询gcpersonhurtadjustment表中理赔明细
					List<DlzAdjustDetailsEntity> adjustmentDetails = dlzThridpartDaoImpl.getAdjustmentDetails(gcClaimThirdinfoTask.getRegistNo());
					for (DlzAdjustDetailsEntity dlzAdjustDetailsEntity : adjustmentDetails) {
						content.put("outercode",dlzAdjustDetailsEntity.getOutercode());

						content.put("outercodetype",dlzAdjustDetailsEntity.getOutercodetype());

						content.put("outPolicyNo",dlzAdjustDetailsEntity.getPolicyno());

						content.put("totalFee",dlzAdjustDetailsEntity.getTotalFee());

						content.put("compensationfee",dlzAdjustDetailsEntity.getCompensationfee());

						content.put("insocialsecurityfee",dlzAdjustDetailsEntity.getInsocialsecurityfee());

						content.put("outsocialsecurityfee",dlzAdjustDetailsEntity.getOutsocialsecurityfee());

						content.put("unreasonablefee",dlzAdjustDetailsEntity.getUnreasonablefee());

						content.put("assessclaimfee",dlzAdjustDetailsEntity.getAssessclaimfee());

						content.put("claimPaidamount",dlzAdjustDetailsEntity.getPaidamount());

						content.put("deductibleFee",dlzAdjustDetailsEntity.getDeductible());

						content.put("paymentRatio",dlzAdjustDetailsEntity.getClaimreate());

						content.put("overDeductibleFee",dlzAdjustDetailsEntity.getOverDeductibleFee());

						//根据报案号与gcpersonhurtadjustment表中的险别查询出责任历史累计免赔额
						DlzAdjustDetailsEntity disteributeind = dlzThridpartDaoImpl.getTotalDeductibleFee(gcClaimThirdinfoTask.getRegistNo());
						content.put("totalDeductibleFee",disteributeind.getTotalDeductibleFee());

					}
				}

				//Adjustmenttype=3为拒赔案
				if(adjustType.getAdjustmenttype().equals("3")) {
					content.put("denyreasonname",adjustType.getDenyreasonname());

					content.put("outBizNo",adjustType.getBusinessno2());

					content.put("reportNo",adjustType.getBusinessno1());

					content.put("outPolicyNo",adjustType.getPolicyno());

					content.put("policyNo",adjustType.getBusinessno3());
				}

				//Adjustmenttype=3为零赔案
				if(adjustType.getAdjustmenttype().equals("3")) {
					content.put("zerocasereasonname",adjustType.getZerocasereasonname());

					content.put("outBizNo",adjustType.getBusinessno2());

					content.put("reportNo",adjustType.getBusinessno1());

					content.put("outPolicyNo",adjustType.getPolicyno());

					content.put("policyNo",adjustType.getBusinessno3());
				}
			}
			
			/*定时任务表中claimStatus=2为销案*/
			if(gcClaimThirdinfoTask.getClaimStatus().equals("2")) {
				DlzAdjustTypeEntity adjustTypeEntity = dlzThridpartDaoImpl.getClancel(gcClaimThirdinfoTask.getRegistNo());
				
				content.put("applyreasonname",adjustTypeEntity.getApplyreasonname());
				
				content.put("outBizNo",adjustTypeEntity.getBusinessno2());

				content.put("reportNo",adjustTypeEntity.getBusinessno1());

				content.put("outPolicyNo",adjustTypeEntity.getPolicyno());

				content.put("policyNo",adjustTypeEntity.getBusinessno3());
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
			headers.add("Accept-Charset","utf-8");

			HttpEntity<String> httpEntity = new HttpEntity<String>(content.toString(),headers);
			logger.info("传输的数据 ={}",httpEntity);

			//通过http协议将数据传到serviceapi工程进行处理推送给外部第三方系统
			String JsonResult = restTemplate.postForObject(dlzUrl, httpEntity, String.class);
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

}
