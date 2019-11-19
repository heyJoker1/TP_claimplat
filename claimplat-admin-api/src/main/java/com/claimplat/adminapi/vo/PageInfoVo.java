package com.claimplat.adminapi.vo;

import java.util.ArrayList;
import java.util.List;

import com.claimplat.common.vo.BaseValueObject;

public class PageInfoVo<T> extends BaseValueObject{

	private static final long serialVersionUID = -6061713501007980390L;

	private int number;		//当前页码
	
	private int size;		//页面容量
	
	private long totalElements;	//记录总数
	
	private List<T> list = new ArrayList<T>();	//返回给前端的数据集合

	
	public PageInfoVo(long totalElements, int number, int size, List<T> list) {
		super();
		this.number = number;
		this.size = size;
		this.totalElements = totalElements;
		this.list = list;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
