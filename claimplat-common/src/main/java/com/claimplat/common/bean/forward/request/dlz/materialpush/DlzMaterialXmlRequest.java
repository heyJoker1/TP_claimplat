package com.claimplat.common.bean.forward.request.dlz.materialpush;

import com.claimplat.common.bean.forward.request.dlz.XmlHead;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "request")
public class DlzMaterialXmlRequest {
	
	@JacksonXmlProperty(localName = "head")
	private XmlHead head;
	
	@JacksonXmlProperty(localName = "body")
	private DlzMaterialXmlBody body;

	public XmlHead getHead() {
		return head;
	}

	public void setHead(XmlHead head) {
		this.head = head;
	}

	public DlzMaterialXmlBody getBody() {
		return body;
	}

	public void setBody(DlzMaterialXmlBody body) {
		this.body = body;
	}
	
	
}
