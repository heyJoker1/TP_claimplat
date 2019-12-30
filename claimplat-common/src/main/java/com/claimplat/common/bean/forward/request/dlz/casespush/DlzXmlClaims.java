package com.claimplat.common.bean.forward.request.dlz.casespush;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DlzXmlClaims {

	@JacksonXmlProperty(localName = "claim")
	private DlzXmlClaim claim;

	public DlzXmlClaim getClaim() {
		return claim;
	}

	public void setClaim(DlzXmlClaim claim) {
		this.claim = claim;
	}
	
	
}
