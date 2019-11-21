package com.claimplat.core.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseDateTimeEntity extends BaseEntity{

	private static final long serialVersionUID = 1506818565079133954L;
	//修改时间
	protected LocalDateTime updateDateTime;
	//创建时间	
	protected LocalDateTime createDateTime=LocalDateTime.now();

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	
}
