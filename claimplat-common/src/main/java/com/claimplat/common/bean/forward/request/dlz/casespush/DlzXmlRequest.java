package com.claimplat.common.bean.forward.request.dlz.casespush;

import com.claimplat.common.bean.forward.request.dlz.XmlHead;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "request")
public class DlzXmlRequest {

	@JacksonXmlProperty(localName = "head")
	private XmlHead head;
	
	@JacksonXmlProperty(localName = "body")
	private DlzXmlBody body;

	public XmlHead getHead() {
		return head;
	}

	public void setHead(XmlHead head) {
		this.head = head;
	}

	public DlzXmlBody getBody() {
		return body;
	}

	public void setBody(DlzXmlBody body) {
		this.body = body;
	}
	
	
}
