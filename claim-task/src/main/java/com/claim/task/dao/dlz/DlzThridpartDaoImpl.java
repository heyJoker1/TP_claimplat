package com.claim.task.dao.dlz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.claim.common.bean.entity.dlz.cases.DlzAdjustDetailsEntity;
import com.claim.common.bean.entity.dlz.cases.DlzAdjustTypeEntity;
import com.claim.common.bean.entity.dlz.cases.DlzClaimContextEntity;

/**蚂蚁-顶梁柱案件推送数据查询
 * @author Joker
 */
@Repository
public class DlzThridpartDaoImpl {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	RowMapper<DlzClaimContextEntity> dlzTask = new BeanPropertyRowMapper<DlzClaimContextEntity>(DlzClaimContextEntity.class);
	
	RowMapper<DlzAdjustTypeEntity> dlzAdjustType = new BeanPropertyRowMapper<DlzAdjustTypeEntity>(DlzAdjustTypeEntity.class);
	
	RowMapper<DlzAdjustDetailsEntity> dlzAdjustDetails = new BeanPropertyRowMapper<DlzAdjustDetailsEntity>(DlzAdjustDetailsEntity.class);
	
	/*先根据定时任务表中的报案号查询获取Adjustmenttype的值作为案件判断条件：adjustmenttype=1为正常结案，3为拒赔案，7为零赔案*/
	public DlzAdjustTypeEntity getAdjustType(String registNo) {
		String sql = "select t1.adjustmenttype,t2.denyreason,"+
				" (select t.codename from ggcode t where t.codetype = 'DenyReason' and t.companycode = '03' and t.codecode = t2.denyreason)"+
				"denyreasonname,t2.zerocasereason,"+
				"(select t.codename from ggcode t where t.codetype = 'ZeroCaseReason' and t.companycode = '03' and t.codecode = t2.zerocasereason)"+
				"zerocasereasonname,t3.businessno1,t3.businessno2,t3.businessno3,t1.policyno from  gcclaimmain t1, gcclaimext t2 ,gcregistinfo t3 "
				+ "where t1.registno = t2.registno and t1.registno = t3.registno and t1.registno= ? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		
		DlzAdjustTypeEntity query = jdbcTemplate.queryForObject(sql, dlzAdjustType,params.toArray());
		return query;
	}

	/*根据定时任务表中的报案号查询gcclaimmain与gcadjustmentmain表中理赔信息*/
	public List<DlzClaimContextEntity> getClaimContext(String registNo) {
		String sql = "select t1.registno,t3.businessno1,t3.businessno2,t3.businessno3, t1.endcasedate,t2.underwriteenddate,t1.opendate,"
				+ "t1.damagestartdate,t1.damagecode,t2.sumpaid from gcclaimmain t1,gcadjustmentmain t2,gcregistinfo t3"
				+ "where t1.claimno = t2.claimno and t1.registno = t3.registno and t2.paidtype <> '10' and t1.registno = ? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		
		List<DlzClaimContextEntity> query = jdbcTemplate.query(sql, dlzTask,params.toArray());
		return query;
	}

	/*根据定时任务表中的报案号查询gcpersonhurtadjustment表中理赔明细*/
	public List<DlzAdjustDetailsEntity> getAdjustmentDetails(String registNo) {
		String sql = "select m.*,n.outercode,n.outercodetype from "
				+ "(select t.compensationfee,t.totalFee,t.claimrate,t.insocialsecurityfee,t.outsocialsecurityfee,t.unreasonablefee,"
				+ "t.assessclaimfee,t.paidamount,t.deductible,t.policyno,t.kindcode, "
				+ "(select remark from gupolicyKindDeductible g where g.policyno=t.policyno and rownum=1)overDeductibleFee,"
				+ "(select a.projectcode from gupolicyitemacci a where a.policyno=t.policyno and rownum=1)projectcode "
				+ "from gcpersonhurtadjustment t where t.registno= ? )m,ggcodetransfer n where m.projectcode = n.innercode "
				+ "and n.configcode = 'MYDLZRefuseMap' and n.companycode = '03' and n.validind = '1' ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		
		List<DlzAdjustDetailsEntity> query = jdbcTemplate.query(sql, dlzAdjustDetails,params.toArray());
		return query;
	}

	/*根据报案号查询出责任历史累计免赔额*/
	public DlzAdjustDetailsEntity getTotalDeductibleFee(String registNo) {
		String sql = "select sum(nvl (t1.deductible,0))totalDeductibleFee from gcadjustmentpersonfee t ,gcregistpolicy t2"
				+ "where t1.policyno = t2.policyno  and t1.plancode = t2.plancode and t1.riskcode = t2.riskcode and t2.thridpolicyno = "
				+ "(select t.thridpolicyno from gcregistpolicy t where t.registno = ? and rownum=1)";
		
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		
		DlzAdjustDetailsEntity query = jdbcTemplate.queryForObject(sql, dlzAdjustDetails,params.toArray());
		return query;
	}
	
	/*根据报案号查询销案信息*/
	public DlzAdjustTypeEntity getClancel(String registNo) {
		String sql = "select t1.applyreason,"
				+ "(select t.codecname from ggcode t where t.codetype = 'ApplyReason' and t.companycode = '03' and t.codecode = t1.applyreason)applyreasonnamse,"
				+ "t3.businessno1,t3.businessno2,t3.businessno3,t2.policyno from gccancellation t1,gcclaimmain t2,gcregistinfo t3 "
				+ "where t1.businessno = t2.claimno and t2.registno = t3.registno and t3.registno = ? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(registNo);
		
		DlzAdjustTypeEntity query = jdbcTemplate.queryForObject(sql, dlzAdjustType,params.toArray());
		return query;
	}

}
