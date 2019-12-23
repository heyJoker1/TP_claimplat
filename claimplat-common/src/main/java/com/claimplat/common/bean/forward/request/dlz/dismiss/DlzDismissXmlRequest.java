package com.claimplat.common.bean.forward.request.dlz.dismiss;

import com.claimplat.common.bean.forward.request.dlz.XmlHead;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "request")
public class DlzDismissXmlRequest {

	@JacksonXmlProperty(localName = "head")
	private XmlHead head;
	
	@JacksonXmlProperty(localName = "body")
	private DlzDismissXmlBody body;

	public XmlHead getHead() {
		return head;
	}

	public void setHead(XmlHead head) {
		this.head = head;
	}

	public DlzDismissXmlBody getBody() {
		return body;
	}

	public void setBody(DlzDismissXmlBody body) {
		this.body = body;
	}
	
	
}
