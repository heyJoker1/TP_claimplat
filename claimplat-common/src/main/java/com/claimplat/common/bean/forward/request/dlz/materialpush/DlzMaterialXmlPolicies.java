package com.claimplat.common.bean.forward.request.dlz.materialpush;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DlzMaterialXmlPolicies {

	@JacksonXmlProperty(localName = "policy")
	private DlzMaterialXmlPolicy policy;

	public DlzMaterialXmlPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(DlzMaterialXmlPolicy policy) {
		this.policy = policy;
	}
	
	
}
