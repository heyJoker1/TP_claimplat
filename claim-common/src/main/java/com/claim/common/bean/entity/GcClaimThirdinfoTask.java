package com.claim.common.bean.entity;

import java.io.Serializable;
import java.util.Date;

public class GcClaimThirdinfoTask extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -3568499099772220684L;

	private String UUID;		//主键UUID
	
	private String registNo;	//报案号
	
	private String claimNo;		//立案号
	
	private String type;		//外部系统类型
	
	private String claimStatus;	//理赔状态
	
	private String status;		//状态
	
	private Date createDate;	//创建时间
	
	private Date updateDate;	//修改时间
		
	private String remark;		//备注
	
	private Long failTimes;		//调用次数
	
	private String validind;	//有效性标志
	
	private String lossNo;		//赔案号码
	
	private String lossSeqNo;	//赔案序号
	
	private String flag;		//预留标识

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getRegistNo() {
		return registNo;
	}

	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getFailTimes() {
		return failTimes;
	}

	public void setFailTimes(Long failTimes) {
		this.failTimes = failTimes;
	}

	public String getValidind() {
		return validind;
	}

	public void setValidind(String validind) {
		this.validind = validind;
	}

	public String getLossNo() {
		return lossNo;
	}

	public void setLossNo(String lossNo) {
		this.lossNo = lossNo;
	}

	public String getLossSeqNo() {
		return lossSeqNo;
	}

	public void setLossSeqNo(String lossSeqNo) {
		this.lossSeqNo = lossSeqNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
