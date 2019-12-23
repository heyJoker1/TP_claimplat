package com.claimplat.common.bean.forward.request.dlz.dismiss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**不予处理接口报文数据映射xml格式
 * @author Joker
 *
 */
@JacksonXmlRootElement(localName = "document")
public class DlzDismissXmlData {

	@JacksonXmlProperty(localName = "signature")
	private String signature;
	
	@JacksonXmlProperty(localName = "request")
	private DlzDismissXmlRequest xmlRequest;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public DlzDismissXmlRequest getXmlRequest() {
		return xmlRequest;
	}

	public void setXmlRequest(DlzDismissXmlRequest xmlRequest) {
		this.xmlRequest = xmlRequest;
	}


	
}
