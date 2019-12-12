package com.claimplat.serviceapi.componentforward;

import com.claimplat.serviceapi.componentforward.bean.ForwardContext;


/**
 * 第三方中间平台提供的标准组件转发器
 * @author Joker
 *
 */
public abstract class BasePlatfromComponentForwarder extends AbstractComponentForwarder{

	@Override
	public void execute(ForwardContext context) {
		validateAndBuild(context);
		
		generateResponse(context);
		
		callOutSystem(context);
		
	}

	public abstract void callOutSystem(ForwardContext context);

	protected abstract void generateResponse(ForwardContext context);

	protected abstract void validateAndBuild(ForwardContext context);
	
	protected abstract void changeRequestData(ForwardContext context);
	

}
