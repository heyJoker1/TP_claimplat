package com.claim.common.bean.entity.dlz.cases;

/**理算明细表映射类
 * @author Joker
 *
 */
public class DlzAdjustDetailsEntity {

	
	private String outercode; 				//险别代码
	
	private String outercodetype;			//险别名称
	
	private String policyno;				//保单号即赔案关联的保险公司保单号
	
	private String totalFee;				//账单总金额
	
	private String compensationfee;			//补偿金额
	
	private String insocialsecurityfee;		//社保内费用
	
	private String outsocialsecurityfee;	//社保外费用
	
	private String unreasonablefee;			//不合理费用
	
	private String assessclaimfee;			//理赔金额
	
	private String paidamount;				//实际赔付金额
	
	private String deductible;				//当前责任免赔额
	
	private String claimreate;				//给付比率
	
	private String totalDeductibleFee;		//责任历史累计免赔额
	
	private String overDeductibleFee;		//累计免赔额上限

	public String getOutercode() {
		return outercode;
	}

	public void setOutercode(String outercode) {
		this.outercode = outercode;
	}

	public String getOutercodetype() {
		return outercodetype;
	}

	public void setOutercodetype(String outercodetype) {
		this.outercodetype = outercodetype;
	}

	public String getPolicyno() {
		return policyno;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getCompensationfee() {
		return compensationfee;
	}

	public void setCompensationfee(String compensationfee) {
		this.compensationfee = compensationfee;
	}

	public String getInsocialsecurityfee() {
		return insocialsecurityfee;
	}

	public void setInsocialsecurityfee(String insocialsecurityfee) {
		this.insocialsecurityfee = insocialsecurityfee;
	}

	public String getOutsocialsecurityfee() {
		return outsocialsecurityfee;
	}

	public void setOutsocialsecurityfee(String outsocialsecurityfee) {
		this.outsocialsecurityfee = outsocialsecurityfee;
	}

	public String getUnreasonablefee() {
		return unreasonablefee;
	}

	public void setUnreasonablefee(String unreasonablefee) {
		this.unreasonablefee = unreasonablefee;
	}

	public String getAssessclaimfee() {
		return assessclaimfee;
	}

	public void setAssessclaimfee(String assessclaimfee) {
		this.assessclaimfee = assessclaimfee;
	}

	public String getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(String paidamount) {
		this.paidamount = paidamount;
	}

	public String getDeductible() {
		return deductible;
	}

	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}

	public String getClaimreate() {
		return claimreate;
	}

	public void setClaimreate(String claimreate) {
		this.claimreate = claimreate;
	}

	public String getTotalDeductibleFee() {
		return totalDeductibleFee;
	}

	public void setTotalDeductibleFee(String totalDeductibleFee) {
		this.totalDeductibleFee = totalDeductibleFee;
	}

	public String getOverDeductibleFee() {
		return overDeductibleFee;
	}

	public void setOverDeductibleFee(String overDeductibleFee) {
		this.overDeductibleFee = overDeductibleFee;
	}
	
	
}
