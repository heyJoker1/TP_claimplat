package com.claimplat.serviceapi.componentforward.dlz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.claimplat.common.bean.forward.request.BaseForwardRequest;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfo;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfos;
import com.claimplat.common.bean.forward.request.dlz.XmlHead;
import com.claimplat.common.bean.forward.request.dlz.XmlItem;
import com.claimplat.common.bean.forward.request.dlz.XmlList;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlBody;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlClaim;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlData;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlRequest;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlBody;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlData;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlRequest;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlPolicy;
import com.claimplat.common.bean.forward.request.dlz.receive.DlzCasesForwardRequest;
import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.core.entity.Merchant;
import com.claimplat.core.entity.MerchantUrlConfig;
import com.claimplat.serviceapi.componentforward.BaseThridpartComonentForwarder;
import com.claimplat.serviceapi.componentforward.bean.ForwardContext;
import com.claimplat.serviceapi.utils.DlzSignUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Transactional
@Component(value = "dlzThridpartComponentForwarder")
public class dlzThridpartComponentForwarder extends BaseThridpartComonentForwarder{

	@Override
	protected void validateAndBuild(ForwardContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void changeRequestData(ForwardContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void callOutSystem(ForwardContext context) {
		Merchant merchant = context.getMerchant();
		DlzCasesForwardRequest request = (DlzCasesForwardRequest) context.getRequest();

		String publicKey4RSA = parameterConfigRepository.findByName("DLZ_RSA_PUBLIC_KEY").getValue();
		String privateKey4RSA =parameterConfigRepository.findByName("DLZ_RSA_PRIVATE_KEY").getValue();

		try {
			/*初始化xml报文头部信息*/
			XmlHead xmlHead = new XmlHead();
			xmlHead.setVersion("3.0");
			xmlHead.setFunction("ant.bxcloud");
			xmlHead.setTransTime(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
			xmlHead.setTransTimeZone("UTC+8");
			xmlHead.setReqMsgId(UUID.randomUUID().toString());
			xmlHead.setFormat("xml");
			xmlHead.setSignType("RSA");
			xmlHead.setAsyn("0");
			xmlHead.setCid("008");

			//ClaimStatus=1为已结案
			if(request.getClaimStatus().equals("1")) {

				//ClaimAdjustmenttype=1为正常结案
				if(request.getClaimAdjustmenttype().equals("1")) {
					
					/*初始化xml报文body信息*/
					DlzXmlData dlzXmlData = new DlzXmlData();
					DlzXmlRequest dlzXmlRequest = new DlzXmlRequest();

					DlzXmlBody dlzXmlBody = new DlzXmlBody();
					dlzXmlBody.setReportNo(request.getReportNo());
					dlzXmlBody.setOutReportNo(request.getOutReportNo());

					DlzXmlClaim dlzXmlClaim = new DlzXmlClaim();
					dlzXmlClaim.setOutBizNo(request.getOutBizNo());
					dlzXmlClaim.setPolicyNo(request.getPolicyNo());
					dlzXmlClaim.setOutPolicyNo(request.getOutPolicyNo());
					dlzXmlClaim.setOutClaimNo(request.getOutClaimNo());
					dlzXmlClaim.setAction("2");
					dlzXmlClaim.setActionTime(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
					dlzXmlClaim.setActionDesc("正常结案");

					DlzXmlActionContext dlzXmlActionContext = new DlzXmlActionContext();

					List<XmlExtendInfo> extendInfolist = new ArrayList<XmlExtendInfo>();
					for(int i=0;i<=6;i++) {
						XmlExtendInfo xmlExtendInfo = new XmlExtendInfo();
						if(i==0) {
							xmlExtendInfo.setKey("claimFee");
							xmlExtendInfo.setValue(request.getClaimFee());
						}else if(i==1) {
							xmlExtendInfo.setKey("claimSuccessTime");
							xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(request.getClaimSuccessTime()));
						}else if(i==2) {
							xmlExtendInfo.setKey("claimAssessTime");
							xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(request.getClaimAssessTime()));
						}else if(i==3) {
							xmlExtendInfo.setKey("claimRecordTime");
							xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(request.getClaimRecordTime()));
						}else if(i==4) {
							xmlExtendInfo.setKey("accidentTime");
							xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(request.getAccidentTime()));
						}else if(i==5){
							xmlExtendInfo.setKey("claimAccidentType");
							xmlExtendInfo.setValue(request.getClaimAccidentType());
						}else {
							xmlExtendInfo.setKey("resolveReason");
							xmlExtendInfo.setValue("正常结案");
						}
						extendInfolist.add(xmlExtendInfo);
					}

					//正常结案的理赔明细
					XmlExtendInfos xmlExtendInfos = new XmlExtendInfos();
					xmlExtendInfos.setKey("adjustmentDetails");

					XmlList xmlList = new XmlList();
					List<XmlItem> itemList = new ArrayList<XmlItem>();
					XmlItem xmlItem = new XmlItem();

					List<XmlExtendInfo> list = new ArrayList<XmlExtendInfo>();
					for(int i=0;i<=12;i++) {
						XmlExtendInfo xmlExtendInfo = new XmlExtendInfo();
						if(i==0) {
							xmlExtendInfo.setKey("liabilityCode");
							xmlExtendInfo.setValue(request.getOutercode());
						}else if(i==1) {
							xmlExtendInfo.setKey("liabilityItemName");
							xmlExtendInfo.setValue(request.getOutercodetype());
						}else if(i==2) {
							xmlExtendInfo.setKey("totalFee");
							xmlExtendInfo.setValue(request.getTotalFee());
						}else if(i==3) {
							xmlExtendInfo.setKey("compensationFee");
							xmlExtendInfo.setValue(request.getCompensationfee());
						}else if(i==4) {
							xmlExtendInfo.setKey("insocialsecurityFee");
							xmlExtendInfo.setValue(request.getInsocialsecurityfee());
						}else if(i==5){
							xmlExtendInfo.setKey("outsocialsecurityfee");
							xmlExtendInfo.setValue(request.getOutsocialsecurityfee());
						}else if(i==6){
							xmlExtendInfo.setKey("unreasonableFee");
							xmlExtendInfo.setValue(request.getUnreasonablefee());
						}else if(i==7){
							xmlExtendInfo.setKey("assessclaimFee");
							xmlExtendInfo.setValue(request.getAssessclaimfee());
						}else if(i==8){
							xmlExtendInfo.setKey("claimFee");
							xmlExtendInfo.setValue(request.getClaimPaidamount());
						}else if(i==9){
							xmlExtendInfo.setKey("paymentRatio");
							xmlExtendInfo.setValue(request.getPaymentRatio());
						}else if(i==10){
							xmlExtendInfo.setKey("deductibleFee");
							xmlExtendInfo.setValue(request.getDeductibleFee());
						}else if(i==11){
							xmlExtendInfo.setKey("totalDeductibleFee");
							xmlExtendInfo.setValue(request.getTotalDeductibleFee());
						}else {
							xmlExtendInfo.setKey("overDeductibleFee");
							xmlExtendInfo.setValue(request.getOverDeductibleFee());
						}
						list.add(xmlExtendInfo);
					}
					xmlItem.setExtendInfo(list);
					itemList.add(xmlItem);
					xmlList.setItem(itemList);
					xmlExtendInfos.setList(xmlList);

					dlzXmlActionContext.setExtendInfo(extendInfolist);
					dlzXmlActionContext.setExtendInfos(xmlExtendInfos);

					dlzXmlClaim.setActionContext(dlzXmlActionContext);

					List<DlzXmlClaim> claimList = new ArrayList<DlzXmlClaim>();
					claimList.add(dlzXmlClaim);

					dlzXmlBody.setClaims(claimList);

					dlzXmlRequest.setHead(xmlHead);
					dlzXmlRequest.setBody(dlzXmlBody);

					//加签
					DlzSignUtils.init(publicKey4RSA,privateKey4RSA,null);
					String sign = DlzSignUtils.sign(dlzXmlRequest.toString());

					dlzXmlData.setSignature(sign);
					dlzXmlData.setXmlRequest(dlzXmlRequest);

					ObjectMapper xmlMapper = new XmlMapper();						
					xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
					xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段
					String content = xmlMapper.writeValueAsString(dlzXmlData);		//转xml格式

					HttpEntity<String> normalEntity = new HttpEntity<>(content);
					logger.info("推送外部第三方系统的报文={}",normalEntity);

					MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_SEND_INFO);

					String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), normalEntity, String.class);

					context.getTempDateMap().put("result",result);
				}

				/*拒赔案*/
				if(request.getClaimAccidentType().equals("3")) {
					DlzDismissXmlData xmlData = new DlzDismissXmlData();
					DlzDismissXmlRequest xmlRequest = new DlzDismissXmlRequest();

					DlzDismissXmlBody xmlBody = new DlzDismissXmlBody();
					xmlBody.setOutBizNo(request.getOutBizNo());
					xmlBody.setReportNo(request.getReportNo());
					xmlBody.setOutReportNo(request.getOutReportNo());

					List<DlzMaterialXmlPolicy> policyList = new ArrayList<DlzMaterialXmlPolicy>();
					DlzMaterialXmlPolicy policy = new DlzMaterialXmlPolicy();
					policy.setPolicyNo(request.getPolicyNo());
					policy.setOutPolicyNo(request.getOutPolicyNo());
					policyList.add(policy);
					xmlBody.setPolicies(policyList);

					xmlBody.setAction("9");
					xmlBody.setActionTime(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
					xmlBody.setActionDesc("");

					DlzDismissXmlActionContext actionContext = new DlzDismissXmlActionContext();
					List<XmlExtendInfo> list = new ArrayList<XmlExtendInfo>();
					for(int i=0;i<=2;i++) {
						XmlExtendInfo xmlExtendInfo = new XmlExtendInfo();
						if(i==0) {
							xmlExtendInfo.setKey("rejectTime");
							xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
						}else if(i==1) {
							xmlExtendInfo.setKey("rejectReason");
							xmlExtendInfo.setValue(request.getDenyreasonname());
						}else  {
							xmlExtendInfo.setKey("rejectCategoryName");
							xmlExtendInfo.setValue("");
						}
						list.add(xmlExtendInfo);
					}
					actionContext.setExtendInfo(list);
					xmlBody.setActionContext(actionContext);
					
					xmlRequest.setHead(xmlHead);
					xmlRequest.setBody(xmlBody);
					
					//加签
					DlzSignUtils.init(publicKey4RSA,privateKey4RSA,null);
					String sign = DlzSignUtils.sign(xmlRequest.toString());

					xmlData.setSignature(sign);
					xmlData.setXmlRequest(xmlRequest);

					ObjectMapper xmlMapper = new XmlMapper();						
					xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			
					xmlMapper.setSerializationInclusion(Include.NON_NULL);			
					String content = xmlMapper.writeValueAsString(xmlData);			

					HttpEntity<String> dimissEntity = new HttpEntity<>(content);
					logger.info("推送外部第三方系统的报文={}",dimissEntity);

					/*此entity映射Merchant_url_cnfig表，推送外部第三方系统时需要在此表中手动添加地址*/
					MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_DISMISS_INFO);

					String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), dimissEntity, String.class);

					context.getTempDateMap().put("result",result);
				}
				
				/*零赔案*/
				if(request.getClaimAccidentType().equals("7")) {
					DlzDismissXmlData xmlData = new DlzDismissXmlData();
					DlzDismissXmlRequest xmlRequest = new DlzDismissXmlRequest();

					DlzDismissXmlBody xmlBody = new DlzDismissXmlBody();
					xmlBody.setOutBizNo(request.getOutBizNo());
					xmlBody.setReportNo(request.getReportNo());
					xmlBody.setOutReportNo(request.getOutReportNo());

					List<DlzMaterialXmlPolicy> policyList = new ArrayList<DlzMaterialXmlPolicy>();
					DlzMaterialXmlPolicy policy = new DlzMaterialXmlPolicy();
					policy.setPolicyNo(request.getPolicyNo());
					policy.setOutPolicyNo(request.getOutPolicyNo());
					policyList.add(policy);
					xmlBody.setPolicies(policyList);

					xmlBody.setAction("9");
					xmlBody.setActionTime(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
					xmlBody.setActionDesc("");

					DlzDismissXmlActionContext actionContext = new DlzDismissXmlActionContext();
					List<XmlExtendInfo> list = new ArrayList<XmlExtendInfo>();
					for(int i=0;i<=2;i++) {
						XmlExtendInfo xmlExtendInfo = new XmlExtendInfo();
						if(i==0) {
							xmlExtendInfo.setKey("rejectTime");
							xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
						}else if(i==1) {
							xmlExtendInfo.setKey("rejectReason");
							xmlExtendInfo.setValue(request.getZerocasereasonname());
						}else  {
							xmlExtendInfo.setKey("rejectCategoryName");
							xmlExtendInfo.setValue("");
						}
						list.add(xmlExtendInfo);
					}
					actionContext.setExtendInfo(list);
					xmlBody.setActionContext(actionContext);
					
					xmlRequest.setHead(xmlHead);
					xmlRequest.setBody(xmlBody);
					
					//加签
					DlzSignUtils.init(publicKey4RSA,privateKey4RSA,null);
					String sign = DlzSignUtils.sign(xmlRequest.toString());

					xmlData.setSignature(sign);
					xmlData.setXmlRequest(xmlRequest);

					ObjectMapper xmlMapper = new XmlMapper();						
					xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
					xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段
					String content = xmlMapper.writeValueAsString(xmlData);			//转xml格式

					HttpEntity<String> dimissEntity = new HttpEntity<>(content);
					logger.info("推送外部第三方系统的报文={}",dimissEntity);

					MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_DISMISS_INFO);

					String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), dimissEntity, String.class);

					context.getTempDateMap().put("result",result);
				}
			}
			
			
			/*ClaimStatus=2为销案*/
			if(request.getClaimStatus().equals("2")) {
				DlzDismissXmlData xmlData = new DlzDismissXmlData();
				DlzDismissXmlRequest xmlRequest = new DlzDismissXmlRequest();

				DlzDismissXmlBody xmlBody = new DlzDismissXmlBody();
				xmlBody.setOutBizNo(request.getOutBizNo());
				xmlBody.setReportNo(request.getReportNo());
				xmlBody.setOutReportNo(request.getOutReportNo());

				List<DlzMaterialXmlPolicy> policyList = new ArrayList<DlzMaterialXmlPolicy>();
				DlzMaterialXmlPolicy policy = new DlzMaterialXmlPolicy();
				policy.setPolicyNo(request.getPolicyNo());
				policy.setOutPolicyNo(request.getOutPolicyNo());
				policyList.add(policy);
				xmlBody.setPolicies(policyList);

				xmlBody.setAction("9");
				xmlBody.setActionTime(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
				xmlBody.setActionDesc("");

				DlzDismissXmlActionContext actionContext = new DlzDismissXmlActionContext();
				List<XmlExtendInfo> list = new ArrayList<XmlExtendInfo>();
				for(int i=0;i<=2;i++) {
					XmlExtendInfo xmlExtendInfo = new XmlExtendInfo();
					if(i==0) {
						xmlExtendInfo.setKey("rejectTime");
						xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
					}else if(i==1) {
						xmlExtendInfo.setKey("rejectReason");
						xmlExtendInfo.setValue(request.getApplyreasonname());
					}else  {
						xmlExtendInfo.setKey("rejectCategoryName");
						xmlExtendInfo.setValue("");
					}
					list.add(xmlExtendInfo);
				}
				actionContext.setExtendInfo(list);
				xmlBody.setActionContext(actionContext);
				
				xmlRequest.setHead(xmlHead);
				xmlRequest.setBody(xmlBody);
				
				//加签
				DlzSignUtils.init(publicKey4RSA,privateKey4RSA,null);
				String sign = DlzSignUtils.sign(xmlRequest.toString());

				xmlData.setSignature(sign);
				xmlData.setXmlRequest(xmlRequest);

				ObjectMapper xmlMapper = new XmlMapper();						
				xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
				xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段
				String content = xmlMapper.writeValueAsString(xmlData);			//转xml格式

				HttpEntity<String> dimissEntity = new HttpEntity<>(content);
				logger.info("推送外部第三方系统的报文={}",dimissEntity);

				MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_DISMISS_INFO);

				String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), dimissEntity, String.class);

				context.getTempDateMap().put("result",result);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void generateResponse(ForwardContext context) {
		// TODO Auto-generated method stub

	}

}
