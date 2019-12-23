package com.claimplat.common.bean.forward.request.dlz;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**Json转XML标签
 * @author Joker
 */
@JacksonXmlRootElement(localName = "head")
public class XmlHead {
	
	@JacksonXmlProperty(localName = "version")
	private String version;
	
	@JacksonXmlProperty(localName = "function")
	private String function;
	
	@JacksonXmlProperty(localName = "transTime")
	private String transTime;
	
	@JacksonXmlProperty(localName = "transTimeZone")
	private String transTimeZone;
	
	@JacksonXmlProperty(localName = "reqMsgId")
	private String reqMsgId;
	
	@JacksonXmlProperty(localName = "format")
	private String format;
	
	@JacksonXmlProperty(localName = "signType")
	private String signType;
	
	@JacksonXmlProperty(localName = "asyn")
	private String asyn;
	
	@JacksonXmlProperty(localName = "cid")
	private String cid;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getTransTimeZone() {
		return transTimeZone;
	}

	public void setTransTimeZone(String transTimeZone) {
		this.transTimeZone = transTimeZone;
	}

	public String getReqMsgId() {
		return reqMsgId;
	}

	public void setReqMsgId(String reqMsgId) {
		this.reqMsgId = reqMsgId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAsyn() {
		return asyn;
	}

	public void setAsyn(String asyn) {
		this.asyn = asyn;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
}
