package com.claimplat.serviceapi.componentforward;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.claimplat.serviceapi.componentforward.bean.ForwardContext;

@Transactional
@Component(value = "SendAuditInfoPlatfromComponentForwarder")
public class SendAuditInfoPlatfromComponentForwarder extends BasePlatfromComponentForwarder{

	@Override
	public void callOutSystem(ForwardContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateResponse(ForwardContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateAndBuild(ForwardContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void changeRequestData(ForwardContext context) {
		// TODO Auto-generated method stub
		
	}

}
