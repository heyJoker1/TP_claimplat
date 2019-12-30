package com.claimplat.common.bean.forward.request.dlz.dismiss;

import java.util.List;

import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "actionContext")
public class DlzDismissXmlActionContext {

	@JacksonXmlProperty(localName = "extendInfo")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XmlExtendInfo> extendInfo;

	public List<XmlExtendInfo> getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(List<XmlExtendInfo> extendInfo) {
		this.extendInfo = extendInfo;
	}
	
	
	
	
}
