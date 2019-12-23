package com.claimplat.common.bean.forward.request.dlz.casespush;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**案件状态推送数据映射xml格式
 * @author Joker
 *
 */
@JacksonXmlRootElement(localName = "document")
public class DlzXmlData {

	@JacksonXmlProperty(localName = "signature")
	private String signature;
	
	@JacksonXmlProperty(localName = "request")
	private DlzXmlRequest xmlRequest;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public DlzXmlRequest getXmlRequest() {
		return xmlRequest;
	}

	public void setXmlRequest(DlzXmlRequest xmlRequest) {
		this.xmlRequest = xmlRequest;
	}
	
	
}
