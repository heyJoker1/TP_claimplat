package com.claimplat.common.bean.forward.request.dlz.casespush;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


public class DlzXmlClaim {

	@JacksonXmlProperty(localName = "outBizNo")
	private String outBizNo;		//赔案幂等单号
	
	@JacksonXmlProperty(localName = "policyNo")
	private String policyNo; 		//赔案关联的保险平台保单
	
	@JacksonXmlProperty(localName = "outPolicyNo")
	private String outPolicyNo;		//赔案关联的保险公司保单
	
	@JacksonXmlProperty(localName = "outClaimNo")
	private String outClaimNo;		//保险公司赔案号
	
	@JacksonXmlProperty(localName = "action")
	private String action;
	
	@JacksonXmlProperty(localName = "actionTime")
	private String actionTime;
	
	@JacksonXmlProperty(localName = "actionDesc")
	private String actionDesc;
	
	@JacksonXmlProperty(localName = "actionContext")
	private DlzXmlActionContext actionContext;
	
	
	public String getOutBizNo() {
		return outBizNo;
	}
	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getOutPolicyNo() {
		return outPolicyNo;
	}
	public void setOutPolicyNo(String outPolicyNo) {
		this.outPolicyNo = outPolicyNo;
	}
	public String getOutClaimNo() {
		return outClaimNo;
	}
	public void setOutClaimNo(String outClaimNo) {
		this.outClaimNo = outClaimNo;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public DlzXmlActionContext getActionContext() {
		return actionContext;
	}
	public void setActionContext(DlzXmlActionContext actionContext) {
		this.actionContext = actionContext;
	}

	
}
