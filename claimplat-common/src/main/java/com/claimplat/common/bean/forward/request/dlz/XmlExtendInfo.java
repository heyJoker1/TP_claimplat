package com.claimplat.common.bean.forward.request.dlz;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


public class XmlExtendInfo {

	@JacksonXmlProperty(localName = "key",isAttribute = true)
	private String key;
	
	@JacksonXmlProperty(localName = "value",isAttribute = true)
	private String value;


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	
}
