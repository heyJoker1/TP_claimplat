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

import com.alibaba.fastjson.JSONObject;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfo;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfos;
import com.claimplat.common.bean.forward.request.dlz.XmlHead;
import com.claimplat.common.bean.forward.request.dlz.XmlItem;
import com.claimplat.common.bean.forward.request.dlz.XmlList;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlBody;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlData;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlPolicies;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlPolicy;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlRequest;
import com.claimplat.common.bean.forward.request.dlz.receive.DlzCasesForwardRequest;
import com.claimplat.common.bean.forward.request.dlz.receive.DlzMaterialForwardRequest;
import com.claimplat.common.enums.BusinessForwardTypeEnum;
import com.claimplat.core.entity.Merchant;
import com.claimplat.core.entity.MerchantUrlConfig;
import com.claimplat.serviceapi.componentforward.BaseThridpartComonentForwarder;
import com.claimplat.serviceapi.componentforward.bean.ForwardContext;
import com.claimplat.serviceapi.utils.DlzSignUtils;
import com.claimplat.serviceapi.utils.RSA;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.jersey.core.util.Base64;

@Transactional
@Component(value = "dlzMaterialThridpartComponentForwarder")
public class dlzMaterialThridpartComponentForwarder  extends BaseThridpartComonentForwarder{

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
		DlzMaterialForwardRequest request = (DlzMaterialForwardRequest) context.getRequest();

		String publicKey4RSA = parameterConfigRepository.findByName("DLZ_RSA_PUBLIC_KEY").getValue();
		String privateKey4RSA =parameterConfigRepository.findByName("DLZ_RSA_PRIVATE_KEY").getValue();
		
		try {
			/*初始化xml报文head头部信息*/
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
			
			/*初始化xml报文body信息*/
			DlzMaterialXmlData xmlData = new DlzMaterialXmlData();
			DlzMaterialXmlRequest xmlRequest = new DlzMaterialXmlRequest();
			xmlRequest.setHead(xmlHead);
			
			DlzMaterialXmlBody xmlBody = new DlzMaterialXmlBody();
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
			
			xmlBody.setAction("10");
			xmlBody.setActionTime(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
			xmlBody.setActionDesc("单证补传");
			
			DlzMaterialXmlActionContext actionContext = new DlzMaterialXmlActionContext();
			XmlExtendInfos xmlExtendInfos = new XmlExtendInfos();
			xmlExtendInfos.setKey("progress");
			
			XmlList xmlList = new XmlList();
			List<XmlItem> itemList = new ArrayList<XmlItem>();
			XmlItem xmlItem = new XmlItem();

			List<XmlExtendInfo> list = new ArrayList<XmlExtendInfo>();
			for(int i=0;i<=2;i++) {
				XmlExtendInfo xmlExtendInfo = new XmlExtendInfo();
				if(i==0) {
					xmlExtendInfo.setKey("progressStatus");
					xmlExtendInfo.setValue("INSURANCE_COMPANY_MODIFY");
				}else if(i==1) {
					xmlExtendInfo.setKey("progressUpdateTime");
					xmlExtendInfo.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
				}else  {
					xmlExtendInfo.setKey("bizData");
					xmlExtendInfo.setValue(request.getRejectReason());
				}
				list.add(xmlExtendInfo);
			}
			
			xmlItem.setExtendInfo(list);
			itemList.add(xmlItem);
			xmlList.setItem(itemList);
			xmlExtendInfos.setList(xmlList);
			
			actionContext.setExtendInfos(xmlExtendInfos);
			xmlBody.setActionContext(actionContext);
			
			xmlRequest.setBody(xmlBody);
			
			//-----------------------------------------对request节点下内容加签--------------------------------------
			ObjectMapper xmlMapper = new XmlMapper();						
			xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			//自动优化格式
			xmlMapper.setSerializationInclusion(Include.NON_NULL);			//自动忽略无值字段

			try {
				String signStr = xmlMapper.writeValueAsString(xmlRequest);	//转xml格式
				signStr.replace("<DlzMaterialXmlRequest>", "");
				signStr.replace("</DlzMaterialXmlRequest>", "");
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
				MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_MATERIAL_INFO);

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
				Element errorElement = bodyElement.element("errorCode");
				Element errorMessageElement = bodyElement.element("errorMessage");

				context.getTempDateMap().put("success",successElement);
				context.getTempDateMap().put("errorCode",errorElement);
				context.getTempDateMap().put("errorMessage",errorMessageElement);

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
		JSONObject jsonObject = new JSONObject();
		jsonObject = new JSONObject(tempDateMap);
		
		if(StringUtils.isEmpty(jsonObject.toString())) {
			throw new IllegalStateException("调用外部第三方系统出错");
		}
		
	}

}
