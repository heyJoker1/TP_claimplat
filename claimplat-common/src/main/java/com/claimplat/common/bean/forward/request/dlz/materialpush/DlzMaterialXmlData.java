package com.claimplat.common.bean.forward.request.dlz.materialpush;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**单证补传推送数据映射xml格式
 * @author Joker
 *
 */
@JacksonXmlRootElement(localName = "document")
public class DlzMaterialXmlData {

	@JacksonXmlProperty(localName = "signature")
	private String signature;
	
	@JacksonXmlProperty(localName = "request")
	private DlzMaterialXmlRequest xmlRequest;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public DlzMaterialXmlRequest getXmlRequest() {
		return xmlRequest;
	}

	public void setXmlRequest(DlzMaterialXmlRequest xmlRequest) {
		this.xmlRequest = xmlRequest;
	}
	
	
}
