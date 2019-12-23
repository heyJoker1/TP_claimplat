package com.claim.common.bean.entity.dlz.cases;

public class DlzAdjustTypeEntity {
	/*其它结案*/
	private String businessno1;			//保险平台报案号
	
	private String businessno2;			//赔案幂等单号
	
	private String businessno3;			//赔案关联的保险平台赔案号
	
	private String policyno;			//保单号即赔案关联的保险公司保单号
	
	private String adjustmenttype;		//案件状态：3-拒赔、7-零结、1-正常案件
	
	private String denyreason;
	private String denyreasonname;		//拒赔原因
	
	private String zerocasereason;
	private String zerocasereasonname;	//零赔原因
	
	private String applyreason;			
	private String applyreasonname;		//销案原因
	
	
	public String getBusinessno1() {
		return businessno1;
	}
	public void setBusinessno1(String businessno1) {
		this.businessno1 = businessno1;
	}
	public String getBusinessno2() {
		return businessno2;
	}
	public void setBusinessno2(String businessno2) {
		this.businessno2 = businessno2;
	}
	public String getBusinessno3() {
		return businessno3;
	}
	public void setBusinessno3(String businessno3) {
		this.businessno3 = businessno3;
	}
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	public String getAdjustmenttype() {
		return adjustmenttype;
	}
	public void setAdjustmenttype(String adjustmenttype) {
		this.adjustmenttype = adjustmenttype;
	}
	public String getDenyreason() {
		return denyreason;
	}
	public void setDenyreason(String denyreason) {
		this.denyreason = denyreason;
	}
	public String getDenyreasonname() {
		return denyreasonname;
	}
	public void setDenyreasonname(String denyreasonname) {
		this.denyreasonname = denyreasonname;
	}
	public String getZerocasereason() {
		return zerocasereason;
	}
	public void setZerocasereason(String zerocasereason) {
		this.zerocasereason = zerocasereason;
	}
	public String getZerocasereasonname() {
		return zerocasereasonname;
	}
	public void setZerocasereasonname(String zerocasereasonname) {
		this.zerocasereasonname = zerocasereasonname;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	public String getApplyreasonname() {
		return applyreasonname;
	}
	public void setApplyreasonname(String applyreasonname) {
		this.applyreasonname = applyreasonname;
	}
	
	
}
