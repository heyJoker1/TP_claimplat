package com.claimplat.serviceapi.componentforward.dlz;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.claimplat.common.bean.forward.request.BaseForwardRequest;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfo;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfos;
import com.claimplat.common.bean.forward.request.dlz.XmlHead;
import com.claimplat.common.bean.forward.request.dlz.XmlItem;
import com.claimplat.common.bean.forward.request.dlz.XmlList;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlBody;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlClaim;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlClaims;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlData;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlRequest;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlBody;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlData;
import com.claimplat.common.bean.forward.request.dlz.dismiss.DlzDismissXmlRequest;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlPolicies;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlPolicy;
import com.claimplat.common.bean.forward.request.dlz.receive.DlzCasesForwardRequest;
import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.core.entity.Merchant;
import com.claimplat.core.entity.MerchantUrlConfig;
import com.claimplat.serviceapi.componentforward.BaseThridpartComonentForwarder;
import com.claimplat.serviceapi.componentforward.bean.ForwardContext;
import com.claimplat.serviceapi.utils.DlzSignUtils;
import com.claimplat.serviceapi.utils.RSA;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.jersey.core.util.Base64;

/**
 * @author Joker
 *
 */
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

			/*----------------------------------------------ClaimStatus=2为结案--------------------------------------------------------------------------------------------------------------------------*/
			if(request.getClaimStatus().equals("1")) {

				/*----------------------------------------------ClaimAdjustmenttype=1为正常结案案-----------------------------------------------------------------------------------------------*/
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
							xmlExtendInfo.setValue(request.getClaimSuccessTime());
						}else if(i==2) {
							xmlExtendInfo.setKey("claimAssessTime");
							xmlExtendInfo.setValue(request.getClaimAssessTime());
						}else if(i==3) {
							xmlExtendInfo.setKey("claimRecordTime");
							xmlExtendInfo.setValue(request.getClaimRecordTime());
						}else if(i==4) {
							xmlExtendInfo.setKey("accidentTime");
							xmlExtendInfo.setValue(request.getAccidentTime());
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

					DlzXmlClaims claims = new DlzXmlClaims();
					claims.setClaim(dlzXmlClaim);

					dlzXmlBody.setClaims(claims);

					dlzXmlRequest.setHead(xmlHead);
					dlzXmlRequest.setBody(dlzXmlBody);

					//-----------------------------------------对request节点下内容加签--------------------------------------
					ObjectMapper xmlMapper = new XmlMapper();						
					xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
					xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段

					try {
						String signStr = xmlMapper.writeValueAsString(dlzXmlRequest);	//转xml格式
						signStr.replace("<DlzXmlRequest>", "");
						signStr.replace("</DlzXmlRequest>", "");
						logger.info("待加签数据={}",signStr);
						
						String encode = new String(Base64.encode(signStr.getBytes("utf-8")));
						String sign = RSA.sign(encode,privateKey4RSA);

						dlzXmlData.setSignature(sign);
						dlzXmlData.setXmlRequest(dlzXmlRequest);

						String content = xmlMapper.writeValueAsString(dlzXmlData);
						logger.info("推送外部第三方系统的报文内容={}",content);

						//http协议传输得头部信息
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_XML);
						headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
						headers.add("Accept-Charset", "utf-8");

						HttpEntity<String> normalEntity = new HttpEntity<>(content,headers);
						logger.info("Http协议传输内容={}",normalEntity);
						MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_SEND_INFO);

						//推送
						String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), normalEntity, String.class);
						logger.info("回调报文={}",result);

						//-----------------------------------------解析xml--------------------------------------
						SAXReader saxReader = new SAXReader();
						ByteArrayInputStream resultByte = new ByteArrayInputStream(result.getBytes());

						//获取xml报文中结构节点下success、errorCode、errorMessage标签中的内容,并添加到map中
						Document docement = saxReader.read(resultByte);
						Element rootElement = docement.getRootElement();
						Element repsonseElement = rootElement.element("repsonse");
						Element bodyElement = repsonseElement.element("body");

						Element successElement = bodyElement.element("success");
						String successValue = successElement.getStringValue();
						Element errorElement = bodyElement.element("errorCode");
						String errorValue = errorElement.getStringValue();
						Element errorMessageElement = bodyElement.element("errorMessage");
						String errorMessageValue = errorMessageElement.getStringValue();

						context.getTempDateMap().put("success",successValue);
						context.getTempDateMap().put("errorCode",errorValue);
						context.getTempDateMap().put("errorMessage",errorMessageValue);

						//-----------------------------------------签名验证--------------------------------------
						logger.info("开始签名验证！");
						String sigetrue = result.substring(result.indexOf("<signature>")+11,result.lastIndexOf("</signature>"));
						String waitContext = result.substring(result.indexOf("<response>")+10,result.lastIndexOf("</response>"));
						
						if(!RSA.checkSign(new String(Base64.encode(waitContext.getBytes("UTF-8"))), sigetrue, privateKey4RSA)) {
							throw new IllegalArgumentException("签名验证失败");
						}
						logger.info("签名验证成功！");

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				/*----------------------------------------------ClaimAdjustmenttype=3为拒赔案-----------------------------------------------------------------------------------------------*/
				if(request.getClaimAccidentType().equals("3")) {
					DlzDismissXmlData xmlData = new DlzDismissXmlData();
					DlzDismissXmlRequest xmlRequest = new DlzDismissXmlRequest();

					DlzDismissXmlBody xmlBody = new DlzDismissXmlBody();
					xmlBody.setOutBizNo(request.getOutBizNo());
					xmlBody.setReportNo(request.getReportNo());
					xmlBody.setOutReportNo(request.getOutReportNo());

					List<DlzMaterialXmlPolicies> policyList = new ArrayList<DlzMaterialXmlPolicies>();
					DlzMaterialXmlPolicies policies = new DlzMaterialXmlPolicies();
					DlzMaterialXmlPolicy policy = new DlzMaterialXmlPolicy();
					policy.setPolicyNo(request.getPolicyNo());
					policy.setOutPolicyNo(request.getOutPolicyNo());
					policies.setPolicy(policy);
					policyList.add(policies);
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

					//-----------------------------------------对request节点下内容加签--------------------------------------
					ObjectMapper xmlMapper = new XmlMapper();						
					xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
					xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段

					try {
						String signStr = xmlMapper.writeValueAsString(xmlRequest);	//转xml格式
						signStr.replace("<DlzDismissXmlRequest>", "");
						signStr.replace("</DlzDismissXmlRequest>", "");
						logger.info("待加签数据={}",signStr);
						
						String encode = new String(Base64.encode(signStr.getBytes("utf-8")));
						String sign = RSA.sign(encode,privateKey4RSA);

						xmlData.setSignature(sign);
						xmlData.setXmlRequest(xmlRequest);

						String content = xmlMapper.writeValueAsString(xmlData);
						logger.info("推送外部第三方系统的报文内容={}",content);

						//http协议传输得头部信息
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_XML);
						headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
						headers.add("Accept-Charset", "utf-8");

						HttpEntity<String> normalEntity = new HttpEntity<>(content,headers);
						logger.info("Http协议传输内容={}",normalEntity);
						MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_DISMISS_INFO);

						//推送
						String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), normalEntity, String.class);
						logger.info("回调报文={}",result);

						//-----------------------------------------解析xml--------------------------------------
						SAXReader saxReader = new SAXReader();
						ByteArrayInputStream resultByte = new ByteArrayInputStream(result.getBytes());

						//获取xml报文中结构节点下success、errorCode、errorMessage标签中的内容,并添加到map中
						Document docement = saxReader.read(resultByte);
						Element rootElement = docement.getRootElement();
						Element repsonseElement = rootElement.element("repsonse");
						Element bodyElement = repsonseElement.element("body");

						Element successElement = bodyElement.element("success");
						String successValue = successElement.getStringValue();
						Element errorElement = bodyElement.element("errorCode");
						String errorValue = errorElement.getStringValue();
						Element errorMessageElement = bodyElement.element("errorMessage");
						String errorMessageValue = errorMessageElement.getStringValue();

						context.getTempDateMap().put("success",successValue);
						context.getTempDateMap().put("errorCode",errorValue);
						context.getTempDateMap().put("errorMessage",errorMessageValue);

						//-----------------------------------------签名验证--------------------------------------
						logger.info("开始签名验证！");
						String sigetrue = result.substring(result.indexOf("<signature>")+11,result.lastIndexOf("</signature>"));
						String waitContext = result.substring(result.indexOf("<response>")+10,result.lastIndexOf("</response>"));
						
						if(!RSA.checkSign(new String(Base64.encode(waitContext.getBytes("UTF-8"))), sigetrue, privateKey4RSA)) {
							throw new IllegalArgumentException("签名验证失败");
						}
						logger.info("签名验证成功！");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				/*----------------------------------------------ClaimAdjustmenttype=7为零赔案-----------------------------------------------------------------------------------------------*/
				if(request.getClaimAccidentType().equals("7")) {
					DlzDismissXmlData xmlData = new DlzDismissXmlData();
					DlzDismissXmlRequest xmlRequest = new DlzDismissXmlRequest();

					DlzDismissXmlBody xmlBody = new DlzDismissXmlBody();
					xmlBody.setOutBizNo(request.getOutBizNo());
					xmlBody.setReportNo(request.getReportNo());
					xmlBody.setOutReportNo(request.getOutReportNo());

					List<DlzMaterialXmlPolicies> policyList = new ArrayList<DlzMaterialXmlPolicies>();
					DlzMaterialXmlPolicies policies = new DlzMaterialXmlPolicies();
					DlzMaterialXmlPolicy policy = new DlzMaterialXmlPolicy();
					policy.setPolicyNo(request.getPolicyNo());
					policy.setOutPolicyNo(request.getOutPolicyNo());
					policies.setPolicy(policy);
					policyList.add(policies);
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

					//-----------------------------------------对request节点下内容加签--------------------------------------
					ObjectMapper xmlMapper = new XmlMapper();						
					xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
					xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段

					try {
						String signStr = xmlMapper.writeValueAsString(xmlRequest);	//转xml格式
						signStr.replace("<DlzDismissXmlRequest>", "");
						signStr.replace("</DlzDismissXmlRequest>", "");
						logger.info("待加签数据={}",signStr);
						
						String encode = new String(Base64.encode(signStr.getBytes("utf-8")));
						String sign = RSA.sign(encode,privateKey4RSA);

						xmlData.setSignature(sign);
						xmlData.setXmlRequest(xmlRequest);

						String content = xmlMapper.writeValueAsString(xmlData);
						logger.info("推送外部第三方系统的报文内容={}",content);

						//http协议传输得头部信息
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_XML);
						headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
						headers.add("Accept-Charset", "utf-8");

						HttpEntity<String> normalEntity = new HttpEntity<>(content,headers);
						logger.info("Http协议传输内容={}",normalEntity);
						MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_DISMISS_INFO);

						//推送
						String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), normalEntity, String.class);
						logger.info("回调报文={}",result);

						//-----------------------------------------解析xml--------------------------------------
						SAXReader saxReader = new SAXReader();
						ByteArrayInputStream resultByte = new ByteArrayInputStream(result.getBytes());

						//获取xml报文中结构节点下success、errorCode、errorMessage标签中的内容,并添加到map中
						Document docement = saxReader.read(resultByte);
						Element rootElement = docement.getRootElement();
						Element repsonseElement = rootElement.element("repsonse");
						Element bodyElement = repsonseElement.element("body");

						Element successElement = bodyElement.element("success");
						String successValue = successElement.getStringValue();
						Element errorElement = bodyElement.element("errorCode");
						String errorValue = errorElement.getStringValue();
						Element errorMessageElement = bodyElement.element("errorMessage");
						String errorMessageValue = errorMessageElement.getStringValue();

						context.getTempDateMap().put("success",successValue);
						context.getTempDateMap().put("errorCode",errorValue);
						context.getTempDateMap().put("errorMessage",errorMessageValue);

						//-----------------------------------------签名验证--------------------------------------
						logger.info("开始签名验证！");
						String sigetrue = result.substring(result.indexOf("<signature>")+11,result.lastIndexOf("</signature>"));
						String waitContext = result.substring(result.indexOf("<response>")+10,result.lastIndexOf("</response>"));
						
						if(!RSA.checkSign(new String(Base64.encode(waitContext.getBytes("UTF-8"))), sigetrue, privateKey4RSA)) {
							throw new IllegalArgumentException("签名验证失败");
						}
						logger.info("签名验证成功！");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}


			/*---------------------------------------------ClaimStatus=2为销案-----------------------------------------------------------------------------------------------------------------------------*/
			if(request.getClaimStatus().equals("2")) {
				DlzDismissXmlData xmlData = new DlzDismissXmlData();
				DlzDismissXmlRequest xmlRequest = new DlzDismissXmlRequest();

				DlzDismissXmlBody xmlBody = new DlzDismissXmlBody();
				xmlBody.setOutBizNo(request.getOutBizNo());
				xmlBody.setReportNo(request.getReportNo());
				xmlBody.setOutReportNo(request.getOutReportNo());

				List<DlzMaterialXmlPolicies> policyList = new ArrayList<DlzMaterialXmlPolicies>();
				DlzMaterialXmlPolicies policies = new DlzMaterialXmlPolicies();
				DlzMaterialXmlPolicy policy = new DlzMaterialXmlPolicy();
				policy.setPolicyNo(request.getPolicyNo());
				policy.setOutPolicyNo(request.getOutPolicyNo());
				policies.setPolicy(policy);
				policyList.add(policies);
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

				//-----------------------------------------对request节点下内容加签--------------------------------------
				ObjectMapper xmlMapper = new XmlMapper();						
				xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
				xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段

				try {
					String signStr = xmlMapper.writeValueAsString(xmlRequest);	//转xml格式
					signStr.replace("<DlzDismissXmlRequest>", "");
					signStr.replace("</DlzDismissXmlRequest>", "");
					logger.info("待加签数据={}",signStr);
					
					String encode = new String(Base64.encode(signStr.getBytes("utf-8")));
					String sign = RSA.sign(encode,privateKey4RSA);

					xmlData.setSignature(sign);
					xmlData.setXmlRequest(xmlRequest);

					String content = xmlMapper.writeValueAsString(xmlData);
					logger.info("推送外部第三方系统的报文内容={}",content);

					//http协议传输得头部信息
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_XML);
					headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
					headers.add("Accept-Charset", "utf-8");

					HttpEntity<String> normalEntity = new HttpEntity<>(content,headers);
					logger.info("Http协议传输内容={}",normalEntity);
					MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_DISMISS_INFO);

					//推送
					String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), normalEntity, String.class);
					logger.info("回调报文={}",result);

					//-----------------------------------------解析xml--------------------------------------
					SAXReader saxReader = new SAXReader();
					ByteArrayInputStream resultByte = new ByteArrayInputStream(result.getBytes());
					//获取xml报文中结构节点下success、errorCode、errorMessage标签中的内容,并添加到map中
					Document docement = saxReader.read(resultByte);
					Element rootElement = docement.getRootElement();
					Element repsonseElement = rootElement.element("repsonse");
					Element bodyElement = repsonseElement.element("body");

					Element successElement = bodyElement.element("success");
					String successValue = successElement.getStringValue();
					Element errorElement = bodyElement.element("errorCode");
					String errorValue = errorElement.getStringValue();
					Element errorMessageElement = bodyElement.element("errorMessage");
					String errorMessageValue = errorMessageElement.getStringValue();

					context.getTempDateMap().put("success",successValue);
					context.getTempDateMap().put("errorCode",errorValue);
					context.getTempDateMap().put("errorMessage",errorMessageValue);

					//-----------------------------------------签名验证--------------------------------------
					logger.info("开始签名验证！");
					String sigetrue = result.substring(result.indexOf("<signature>")+11,result.lastIndexOf("</signature>"));
					String waitContext = result.substring(result.indexOf("<response>")+10,result.lastIndexOf("</response>"));
					
					if(!RSA.checkSign(new String(Base64.encode(waitContext.getBytes("UTF-8"))), sigetrue, privateKey4RSA)) {
						throw new IllegalArgumentException("签名验证失败");
					}
					logger.info("签名验证成功！");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *取出推送外部第三方返回的信息，已作map处理的数据
	 */
	@Override
	protected void generateResponse(ForwardContext context) {
		Map<String, Object> tempDateMap = context.getTempDateMap();
		String jsonString = JSONUtils.toJSONString(tempDateMap);
		
		if(StringUtils.isEmpty(jsonString)) {
			throw new IllegalStateException("调用外部第三方系统出错");
		}
	}

}
