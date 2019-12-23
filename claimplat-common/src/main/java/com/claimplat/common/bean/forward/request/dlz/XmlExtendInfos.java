package com.claimplat.common.bean.forward.request.dlz;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "extendInfos")
public class XmlExtendInfos {

	@JacksonXmlProperty(localName = "key",isAttribute = true)
	private String key;
	
	@JacksonXmlProperty(localName = "list")
	private XmlList list;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public XmlList getList() {
		return list;
	}

	public void setList(XmlList list) {
		this.list = list;
	}
	
	
}
