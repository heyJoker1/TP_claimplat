package com.claimplat.serviceapi.componentforward.dlz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfo;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfos;
import com.claimplat.common.bean.forward.request.dlz.XmlHead;
import com.claimplat.common.bean.forward.request.dlz.XmlItem;
import com.claimplat.common.bean.forward.request.dlz.XmlList;
import com.claimplat.common.bean.forward.request.dlz.casespush.DlzXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlActionContext;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlBody;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlData;
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
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
			
			List<DlzMaterialXmlPolicy> policyList = new ArrayList<DlzMaterialXmlPolicy>();
			DlzMaterialXmlPolicy policy = new DlzMaterialXmlPolicy();
			policy.setPolicyNo(request.getPolicyNo());
			policy.setOutPolicyNo(request.getOutPolicyNo());
			policyList.add(policy);
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
			
			//加签
			DlzSignUtils.init(publicKey4RSA,privateKey4RSA,null);
			String sign = DlzSignUtils.sign(xmlRequest.toString());

			xmlData.setSignature(sign);
			xmlData.setXmlRequest(xmlRequest);

			/*转成xml格式*/
			ObjectMapper xmlMapper = new XmlMapper();						
			xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);			
			xmlMapper.setSerializationInclusion(Include.NON_NULL);			
			String content = xmlMapper.writeValueAsString(xmlData);			

			HttpEntity<String> materialEntity = new HttpEntity<>(content);
			logger.info("推送外部第三方系统的报文={}",materialEntity);

			/*此entity映射Merchant_url_cnfig表，推送外部第三方系统时需要在此表中手动添加地址*/
			MerchantUrlConfig urlConfig = fetchUrlConfig(merchant.getCode(),BusinessForwardTypeEnum.DLZ_MATERIAL_INFO);

			String result = restTemplateWithProxy.postForObject(urlConfig.getUrl(), materialEntity, String.class);

			context.getTempDateMap().put("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	protected void generateResponse(ForwardContext context) {
		// TODO Auto-generated method stub
		
	}

}
