package com.claim.common.bean.entity.dlz.cases;

import java.io.Serializable;
import java.util.Date;

import com.claim.common.bean.entity.BaseEntity;

/**正常结案表映射
 * @author Joker
 */
public class DlzClaimContextEntity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/*正常结案*/
	private String businessno1;			//保险平台报案号
	
	private String businessno2;			//赔案幂等单号
	
	private String businessno3;			//赔案关联的保险平台赔案号
	
	private String registno;			//报案号即保险公司赔案号
	
	private String sumpaid;				//赔付金额
	
	private Date endcasedate;			//结案时间
	
	private Date underwriteenddate;		//核赔时间
	
	private Date opendate;				//立案时间
	
	private Date damagestartdate;		//出险时间
	
	private String damagecode;			//出险原因

	
	
	
	public String getBusinessno1() {
		return businessno1;
	}
	public void setBusinessno1(String businessno1) {
		this.businessno1 = businessno1;
	}
	public String getBusinessno2() {
		return businessno2;
	}
	public void setBusinessno2(String businessno2) {
		this.businessno2 = businessno2;
	}
	public String getBusinessno3() {
		return businessno3;
	}
	public void setBusinessno3(String businessno3) {
		this.businessno3 = businessno3;
	}
	public String getRegistno() {
		return registno;
	}
	public void setRegistno(String registno) {
		this.registno = registno;
	}
	public String getSumpaid() {
		return sumpaid;
	}
	public void setSumpaid(String sumpaid) {
		this.sumpaid = sumpaid;
	}
	public Date getEndcasedate() {
		return endcasedate;
	}
	public void setEndcasedate(Date endcasedate) {
		this.endcasedate = endcasedate;
	}
	public Date getUnderwriteenddate() {
		return underwriteenddate;
	}
	public void setUnderwriteenddate(Date underwriteenddate) {
		this.underwriteenddate = underwriteenddate;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public Date getDamagestartdate() {
		return damagestartdate;
	}
	public void setDamagestartdate(Date damagestartdate) {
		this.damagestartdate = damagestartdate;
	}
	public String getDamagecode() {
		return damagecode;
	}
	public void setDamagecode(String damagecode) {
		this.damagecode = damagecode;
	}
	
}
