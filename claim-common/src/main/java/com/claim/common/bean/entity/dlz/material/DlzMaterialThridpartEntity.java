package com.claim.common.bean.entity.dlz.material;

import java.io.Serializable;

import com.claim.common.bean.entity.BaseEntity;

/**单证补传表映射
 * @author Joker
 */
public class DlzMaterialThridpartEntity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private String context;			//单证补传原因
	private String policyno;		//核心保单号即保险公司保单号
	private String businessno1;		//保险平台报案号
	private String businessno2;		//幂等单号
	private String businessno3;		//保险平台保单号


	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	public String getPolicyno() {
		return policyno;
	}


	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}


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
	
	
}
