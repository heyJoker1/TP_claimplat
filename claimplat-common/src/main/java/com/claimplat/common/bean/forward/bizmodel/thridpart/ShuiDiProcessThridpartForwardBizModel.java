package com.claimplat.common.bean.forward.bizmodel.thridpart;

/**
 * 水滴资料审核状态业务对象
 * @author Joker
 *
 */
public class ShuiDiProcessThridpartForwardBizModel extends BaseThridPartForwardBizModel{


	private static final long serialVersionUID = -5072661608246981717L;
	
	private String policyNo;		//保单号
	private String claimNo;			//保险公司案件号
	private String payAmount;		//赔付金额
	private String processTime;
	private String status;
	private String chkClmUserNm;	//操作人
	private String chkClmUserId;	//操作人代码
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getProcessTime() {
		return processTime;
	}
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChkClmUserNm() {
		return chkClmUserNm;
	}
	public void setChkClmUserNm(String chkClmUserNm) {
		this.chkClmUserNm = chkClmUserNm;
	}
	public String getChkClmUserId() {
		return chkClmUserId;
	}
	public void setChkClmUserId(String chkClmUserId) {
		this.chkClmUserId = chkClmUserId;
	}

	
}
