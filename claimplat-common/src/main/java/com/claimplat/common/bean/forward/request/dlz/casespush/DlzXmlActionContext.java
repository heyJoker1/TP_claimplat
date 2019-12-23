package com.claimplat.common.bean.forward.request.dlz.casespush;

import java.util.List;

import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfo;
import com.claimplat.common.bean.forward.request.dlz.XmlExtendInfos;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "actionContext")
public class DlzXmlActionContext {
	
	@JacksonXmlProperty(localName = "extendInfo")
	private List<XmlExtendInfo> extendInfo;
	
	@JacksonXmlProperty(localName = "extendInfos")
	private XmlExtendInfos extendInfos;

	public List<XmlExtendInfo> getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(List<XmlExtendInfo> extendInfo) {
		this.extendInfo = extendInfo;
	}

	public XmlExtendInfos getExtendInfos() {
		return extendInfos;
	}

	public void setExtendInfos(XmlExtendInfos extendInfos) {
		this.extendInfos = extendInfos;
	}
	
	
}
