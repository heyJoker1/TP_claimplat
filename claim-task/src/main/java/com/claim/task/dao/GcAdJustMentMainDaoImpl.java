package com.claim.task.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GcAdJustMentMainDaoImpl {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	//通过报案号查询赔付金额
	public Object findPayAmountByRegistNo(String registNo) {
		String sql = "select g.sumpaid from gcadjustmentmain g where g.registno=? and g.paidtype<>'10'";
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		Double payAmount = jdbcTemplate.queryForObject(sql, params.toArray(),double.class);
		return payAmount;
	}

	//通过报案号查询保单号
	public Object findPolicyNoByRegistNo(String registNo) {
		String sql = "select g.policyno from gcadjustmentmain g where g.registno=? and g.paidtype<>'10'";
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		Double payAmount = jdbcTemplate.queryForObject(sql, params.toArray(),double.class);
		return payAmount;
	}

}
