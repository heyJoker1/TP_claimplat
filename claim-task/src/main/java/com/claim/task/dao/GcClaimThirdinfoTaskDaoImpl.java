package com.claim.task.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.claim.common.bean.entity.GcClaimThirdinfoTask;

@Repository
public class GcClaimThirdinfoTaskDaoImpl {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<GcClaimThirdinfoTask> gcTask = new BeanPropertyRowMapper<GcClaimThirdinfoTask>(GcClaimThirdinfoTask.class);
	
	/*水滴筹案件状态定时任务查询*/
	public List<GcClaimThirdinfoTask> findUntreated(String type, String status, String failStatus) {
		String sql = "select * from GCCLAIMTHIRDINFOTASK t where t.TYPE=? AND t.STATUS in(?,?) AND t.FAILTIMES<3 AND t.VALIDIND=1";
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		params.add(status);
		params.add(failStatus);
		List<GcClaimThirdinfoTask> queryForList = jdbcTemplate.query(sql, gcTask,params.toArray());
		return queryForList;
	}
	
	/*蚂蚁-顶梁柱案件状态定时任务查询*/
	public List<GcClaimThirdinfoTask> getDlzData(List<String> status, String type) {
		String sql = "select * from GCCLAIMTHIRDINFOTASK t where t.TYPE=? AND t.STATUS in(?,?) AND t.FAILTIMES<3 AND t.VALIDIND=1";
		
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		for(String statu : status) {
			params.add(statu);
		}
		
		List<GcClaimThirdinfoTask> queryForList = jdbcTemplate.query(sql, gcTask,params.toArray());
		return queryForList;
	}
	
	/*蚂蚁-顶梁柱单证补传定时任务查询*/
	public List<GcClaimThirdinfoTask> getDlzMaterial(List<String> status, String type) {
		String sql = "select * from GCCLAIMTHIRDINFOTASK t where t.TYPE=? AND t.STATUS in(?,?) AND t.FAILTIMES<3 AND t.VALIDIND=1";
		
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		for(String statu : status) {
			params.add(statu);
		}
		
		List<GcClaimThirdinfoTask> queryForList = jdbcTemplate.query(sql, gcTask,params.toArray());
		return queryForList;
	}
	
	
	
	//成功处理后，修改status=s，定时任务处理完成
	public void updatesStatus(String uuid) {
		String sql = "update GCCLAIMTHIRDINFOTASK set status='S',update=sysdate where UUID=?";
		List<Object> params = new ArrayList<Object>();
		params.add(uuid);
		jdbcTemplate.update(sql, params.toArray());	
	}

	//失败处理后，failtime次数+1，status=F待处理
	public void updatesStatusAndFailtimes(String uuid) {
		String sql = "update GCCLAIMTHIRDINFOTASK set FAILTIMES = FAILTIIMES+1 , status='F' where UUID=?";
		List<Object> params = new ArrayList<Object>();
		params.add(uuid);
		jdbcTemplate.update(sql, params.toArray());	
		
	}



}
