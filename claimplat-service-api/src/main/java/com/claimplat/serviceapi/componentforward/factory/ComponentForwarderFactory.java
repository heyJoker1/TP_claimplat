package com.claimplat.serviceapi.componentforward.factory;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.claimplat.common.enums.ComponentForwaderTypeEnum;
import com.claimplat.serviceapi.componentforward.ComponentForwarder;
import com.claimplat.serviceapi.componentforward.SendAuditInfoPlatfromComponentForwarder;

@Component
public class ComponentForwarderFactory {
	
	@Resource(name = "sendAuditInfoPlatfromComponentForwarder")
	private SendAuditInfoPlatfromComponentForwarder sendAuditInfoPlatfromComponentForwarder;
	
	@Resource(name = "sendCassStatusPlatformComponentForwarder")
	private ComponentForwarder sendCassStatusPlatformComponentForwarder;
	
	@Resource(name = "shuiDiAuditThridpartComponentForwarder")
	private ComponentForwarder shuiDiAuditThridpartComponentForwarder;
	
	@Resource(name = "shuiDiProcessThridpartComponentForwarder")
	private ComponentForwarder shuiDiProcessThridpartComponentForwarder;
	
	@Resource(name = "dlzThridpartComponentForwarder")
	private ComponentForwarder dlzThridpartComponentForwarder;
	
	@Resource(name = "dlzMaterialThridpartComponentForwarder")
	private ComponentForwarder dlzMaterialThridpartComponentForwarder;
	
	
	public ComponentForwarder getComponentForwarder(ComponentForwaderTypeEnum type) {
		if(type == null) {
			throw new IllegalArgumentException("type不能为空");
		}
		
		if(type == ComponentForwaderTypeEnum.SEND_AUDIT_INFO) {
			return sendAuditInfoPlatfromComponentForwarder;
		} else if(type == ComponentForwaderTypeEnum.SEND_CASE_STATUS) {
			return sendCassStatusPlatformComponentForwarder;
		} else if(type == ComponentForwaderTypeEnum.SHUIDI_AUDIT) {
			return shuiDiAuditThridpartComponentForwarder;
		} else if(type == ComponentForwaderTypeEnum.SHUIDI_PROCESS) {
			return shuiDiProcessThridpartComponentForwarder;
		} else if(type == ComponentForwaderTypeEnum.DLZ_SEND_INFO) {
			return dlzThridpartComponentForwarder;
		} else if(type == ComponentForwaderTypeEnum.DLZ_MATERIAL_INFO) {
			return dlzMaterialThridpartComponentForwarder;
		}else {
			throw new IllegalArgumentException("不支持的type取值："+type);
		}
	}
}
