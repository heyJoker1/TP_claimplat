package com.claimplat.common.bean.forward.request.dlz.casespush;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "body")
public class DlzXmlBody {

	@JacksonXmlProperty(localName = "reportNo")
	private String reportNo;			//保险平台报案号
	
	@JacksonXmlProperty(localName = "outReportNo")
	private String outReportNo;			//保险公司报案号
	
	@JacksonXmlProperty(localName = "claims")
	private List<DlzXmlClaim> claims;	//赔案信息

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getOutReportNo() {
		return outReportNo;
	}

	public void setOutReportNo(String outReportNo) {
		this.outReportNo = outReportNo;
	}

	public List<DlzXmlClaim> getClaims() {
		return claims;
	}

	public void setClaims(List<DlzXmlClaim> claims) {
		this.claims = claims;
	}
	
	
}
