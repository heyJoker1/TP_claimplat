package com.claim.task.dao.dlz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.claim.common.bean.entity.dlz.material.DlzMaterialThridpartEntity;

@Repository
public class DlzMaterialThridpartDaoImpl {

	@Resource
	private JdbcTemplate jdbcTemplate;

	RowMapper<DlzMaterialThridpartEntity> dlzMaterialTask = new BeanPropertyRowMapper<DlzMaterialThridpartEntity>(DlzMaterialThridpartEntity.class);
	
	/*根据报案号查询补传资料*/
	public List<DlzMaterialThridpartEntity> getMaterialContext(String registNo) {
		String sql = "select t.context from gcregisttext t where t.registno=? and t.texttype='09' order by t.lineno desc ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		
		List<DlzMaterialThridpartEntity> query = jdbcTemplate.query(sql, dlzMaterialTask,params.toArray());
		return query;
	}
	
	/*根据报案号查询保单号*/
	public DlzMaterialThridpartEntity getPolicies(String registNo) {
		String sql = "select t1.policyno,t2.businessno1,t2.businessno2,t2.businessno3 "
				+ " from gcregistpolicy t1 ,gcregistinfo t2 where t1.registno=t2.registno and t1.registno=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		
		DlzMaterialThridpartEntity query = jdbcTemplate.queryForObject(sql, dlzMaterialTask,params.toArray());
		return query;
	}
	
}
