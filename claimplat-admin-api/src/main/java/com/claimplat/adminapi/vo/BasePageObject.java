package com.claimplat.adminapi.vo;


import com.claimplat.common.vo.BaseValueObject;
import io.swagger.annotations.ApiModelProperty;

public class BasePageObject extends BaseValueObject{

	private static final long serialVersionUID = 400901332449103550L;

	@ApiModelProperty(value = "当前页码")
	private int pageIndex;
	
	@ApiModelProperty(value = "页面容量")
	private int pageSize;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
