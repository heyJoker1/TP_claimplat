package com.claimplat.common.bean.forward.request.dlz;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "list")
public class XmlList {

	@JacksonXmlProperty(localName = "item")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XmlItem> item;

	public List<XmlItem> getItem() {
		return item;
	}

	public void setItem(List<XmlItem> item) {
		this.item = item;
	}
	
	
}
