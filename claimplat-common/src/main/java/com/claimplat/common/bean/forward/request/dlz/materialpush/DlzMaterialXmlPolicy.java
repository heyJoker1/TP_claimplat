package com.claimplat.common.bean.forward.request.dlz.materialpush;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "policy")
public class DlzMaterialXmlPolicy {

	@JacksonXmlProperty(localName = "policyNo")
	private String policyNo;	//保单号
	
	@JacksonXmlProperty(localName = "outPolicyNo")
	private String outPolicyNo;	//外部保单号


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
	
	
	
}
