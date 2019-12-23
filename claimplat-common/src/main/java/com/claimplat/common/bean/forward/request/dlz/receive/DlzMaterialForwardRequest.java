package com.claimplat.common.bean.forward.request.dlz.receive;

import com.claimplat.common.bean.forward.request.thridpart.BaseThridpartForwardRequest;

/**单证补传接收task定时任务的数据（内部系统传输）
 * @author Joker
 *
 */
public class DlzMaterialForwardRequest extends BaseThridpartForwardRequest{

	private static final long serialVersionUID = 1L;

	private String rejectReason;		//单证补传原因
	
	private String reportNo;			//保险平台报案号
	
	private String outReportNo;			//保险公司保案号
	
	private String policyNo;			//保单号
		
	private String outPolicyNo;			//外部保单号
	
	private String outBizNo;			//幂等单号
	
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
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
	public String getOutBizNo() {
		return outBizNo;
	}
	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}
	
	
}
