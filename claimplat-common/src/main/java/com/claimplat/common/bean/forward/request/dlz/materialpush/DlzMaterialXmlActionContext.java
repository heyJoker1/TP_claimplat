package com.claimplat.common.bean.forward.request.dlz.materialpush;

import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfos;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "actionContext")
public class DlzMaterialXmlActionContext {

	@JacksonXmlProperty(localName = "extendInfos")
	private XmlExtendInfos extendInfos;

	public XmlExtendInfos getExtendInfos() {
		return extendInfos;
	}

	public void setExtendInfos(XmlExtendInfos extendInfos) {
		this.extendInfos = extendInfos;
	}
	
	
}
