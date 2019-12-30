package com.claimplat.common.bean.forward.request.dlz.dismiss;

import java.util.List;

import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlPolicies;
import com.claimplat.common.bean.forward.request.dlz.materialpush.DlzMaterialXmlPolicy;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "body")
public class DlzDismissXmlBody {
	
	@JacksonXmlProperty(localName = "outBizNo")
	private String outBizNo;
	
	@JacksonXmlProperty(localName = "reportNo")
	private String reportNo;		//保险平台报案号
	
	@JacksonXmlProperty(localName = "outReportNo")
	private String outReportNo; 	//保险公司报案号
	
	@JacksonXmlProperty(localName = "policies")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<DlzMaterialXmlPolicies> policies; 		//保单列表
	
	@JacksonXmlProperty(localName = "action")
	private String action;
	
	@JacksonXmlProperty(localName = "actionTime")
	private String actionTime;
	
	@JacksonXmlProperty(localName = "actionDesc")
	private String actionDesc;
	
	@JacksonXmlProperty(localName = "actionContext")
	private DlzDismissXmlActionContext actionContext;

	public String getOutBizNo() {
		return outBizNo;
	}

	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getOutReportNo() {
		return outReportNo;
	}

	public void setOutReportNo(String outReportNo) {
		this.outReportNo = outReportNo;
	}

	public List<DlzMaterialXmlPolicies> getPolicies() {
		return policies;
	}

	public void setPolicies(List<DlzMaterialXmlPolicies> policies) {
		this.policies = policies;
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

	public DlzDismissXmlActionContext getActionContext() {
		return actionContext;
	}

	public void setActionContext(DlzDismissXmlActionContext actionContext) {
		this.actionContext = actionContext;
	}
	
	
}
