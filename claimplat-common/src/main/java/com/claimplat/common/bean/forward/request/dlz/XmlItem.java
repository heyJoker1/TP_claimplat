package com.claimplat.common.bean.forward.request.dlz;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class XmlItem {

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
