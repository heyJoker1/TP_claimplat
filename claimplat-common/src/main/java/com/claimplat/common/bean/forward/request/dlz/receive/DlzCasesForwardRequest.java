package com.claimplat.common.bean.forward.request.dlz.receive;

import java.util.Date;

import com.claimplat.common.bean.forward.request.thridpart.BaseThridpartForwardRequest;

/**案件状态接收task定时任务的数据（内部系统传输）
 * @author Joker
 *
 */
public class DlzCasesForwardRequest extends BaseThridpartForwardRequest{

	private static final long serialVersionUID = 1L;

	//状态判断
	private String claimStatus;				//1-已结案，2-销案
	private String claimAdjustmenttype;		//3-拒赔，7-零结，1-正常案件
	
	//正常结案
	private String outBizNo; 				//幂等单号
	private String reportNo;				//保险平台报案号（外部）
	private String outReportNo;				//保险公司报案号（太平）
	private String policyNo;				//保险平台保单号
	private String outPolicyNo;				//保险公司保单号
	private String outClaimNo;				//保险公司赔案号
	private String claimFee;				//赔付金额
	private String claimSuccessTime;			//结案时间
	private String claimAssessTime;			//核赔时间
	private String claimRecordTime;			//立案时间
	private String accidentTime;				//出险时间
	private String claimAccidentType;		//出险原因（类型）
	private String outercode;				//险别代码
	private String outercodetype;			//险别名称
	private String totalFee;				//账单总金额
	private String compensationfee;			//补偿金额
	private String insocialsecurityfee;		//社保内费用
	private String outsocialsecurityfee;	//社保外费用
	private String unreasonablefee;			//不合理费用
	private String assessclaimfee;			//理赔金额
	private String claimPaidamount;			//实际赔付金额
	private String deductibleFee;			//当前责任免赔额
	private String paymentRatio;			//给付比率
	private String overDeductibleFee;		//累计免赔额上限
	private String totalDeductibleFee;		//责任历史累计免赔额
	
	/*其它结案*/
	private String denyreasonname;			//拒赔原因
	private String zerocasereasonname;		//零赔原因
	private String applyreasonname;			//销案原因
	
	
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
	public String getClaimSuccessTime() {
		return claimSuccessTime;
	}
	public void setClaimSuccessTime(String claimSuccessTime) {
		this.claimSuccessTime = claimSuccessTime;
	}
	public String getClaimAssessTime() {
		return claimAssessTime;
	}
	public void setClaimAssessTime(String claimAssessTime) {
		this.claimAssessTime = claimAssessTime;
	}
	public String getClaimRecordTime() {
		return claimRecordTime;
	}
	public void setClaimRecordTime(String claimRecordTime) {
		this.claimRecordTime = claimRecordTime;
	}
	public String getAccidentTime() {
		return accidentTime;
	}
	public void setAccidentTime(String accidentTime) {
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
