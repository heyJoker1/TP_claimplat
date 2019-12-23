package com.claimplat.common.bean.forward.request.dlz.receive;

import java.util.Date;

import com.claimplat.common.bean.forward.request.thridpart.BaseThridpartForwardRequest;

/**案件状态接收task定时任务的数据（内部系统传输）
 * @author Joker
 *
 */
public class DlzCasesForwardRequest extends BaseThridpartForwardRequest{

	private static final long serialVersionUID = 1L;

	private String claimStatus;
	private String claimAdjustmenttype;
	
	private String outBizNo; 
	private String reportNo;
	private String outReportNo;
	private String policyNo;
	private String outPolicyNo;
	private String outClaimNo;
	private String claimFee;
	private Date claimSuccessTime;
	private Date claimAssessTime;
	private Date claimRecordTime;
	private Date accidentTime;
	private String claimAccidentType;
	private String outercode;
	private String outercodetype;
	private String totalFee;
	private String compensationfee;
	private String insocialsecurityfee;
	private String outsocialsecurityfee;	
	private String unreasonablefee;
	private String assessclaimfee;
	private String claimPaidamount;
	private String deductibleFee;
	private String paymentRatio;
	private String overDeductibleFee;
	private String totalDeductibleFee;
	
	private String denyreasonname;
	private String zerocasereasonname;
	private String applyreasonname;
	
	
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getClaimAdjustmenttype() {
		return claimAdjustmenttype;
	}
	public void setClaimAdjustmenttype(String claimAdjustmenttype) {
		this.claimAdjustmenttype = claimAdjustmenttype;
	}
	public String getOutBizNo() {
		return outBizNo;
	}
	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}
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
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getOutPolicyNo() {
		return outPolicyNo;
	}
	public void setOutPolicyNo(String outPolicyNo) {
		this.outPolicyNo = outPolicyNo;
	}
	public String getOutClaimNo() {
		return outClaimNo;
	}
	public void setOutClaimNo(String outClaimNo) {
		this.outClaimNo = outClaimNo;
	}
	public String getClaimFee() {
		return claimFee;
	}
	public void setClaimFee(String claimFee) {
		this.claimFee = claimFee;
	}
	public Date getClaimSuccessTime() {
		return claimSuccessTime;
	}
	public void setClaimSuccessTime(Date claimSuccessTime) {
		this.claimSuccessTime = claimSuccessTime;
	}
	public Date getClaimAssessTime() {
		return claimAssessTime;
	}
	public void setClaimAssessTime(Date claimAssessTime) {
		this.claimAssessTime = claimAssessTime;
	}
	public Date getClaimRecordTime() {
		return claimRecordTime;
	}
	public void setClaimRecordTime(Date claimRecordTime) {
		this.claimRecordTime = claimRecordTime;
	}
	public Date getAccidentTime() {
		return accidentTime;
	}
	public void setAccidentTime(Date accidentTime) {
		this.accidentTime = accidentTime;
	}
	public String getClaimAccidentType() {
		return claimAccidentType;
	}
	public void setClaimAccidentType(String claimAccidentType) {
		this.claimAccidentType = claimAccidentType;
	}
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
	public String getClaimPaidamount() {
		return claimPaidamount;
	}
	public void setClaimPaidamount(String claimPaidamount) {
		this.claimPaidamount = claimPaidamount;
	}
	public String getDeductibleFee() {
		return deductibleFee;
	}
	public void setDeductibleFee(String deductibleFee) {
		this.deductibleFee = deductibleFee;
	}
	public String getPaymentRatio() {
		return paymentRatio;
	}
	public void setPaymentRatio(String paymentRatio) {
		this.paymentRatio = paymentRatio;
	}
	public String getOverDeductibleFee() {
		return overDeductibleFee;
	}
	public void setOverDeductibleFee(String overDeductibleFee) {
		this.overDeductibleFee = overDeductibleFee;
	}
	public String getTotalDeductibleFee() {
		return totalDeductibleFee;
	}
	public void setTotalDeductibleFee(String totalDeductibleFee) {
		this.totalDeductibleFee = totalDeductibleFee;
	}
	public String getDenyreasonname() {
		return denyreasonname;
	}
	public void setDenyreasonname(String denyreasonname) {
		this.denyreasonname = denyreasonname;
	}
	public String getZerocasereasonname() {
		return zerocasereasonname;
	}
	public void setZerocasereasonname(String zerocasereasonname) {
		this.zerocasereasonname = zerocasereasonname;
	}
	public String getApplyreasonname() {
		return applyreasonname;
	}
	public void setApplyreasonname(String applyreasonname) {
		this.applyreasonname = applyreasonname;
	}

	
}
