package com.claimplat.serviceapi.componentforward;

import com.claimplat.serviceapi.componentforward.bean.ForwardContext;

/**适配第三方系统的组件转发器
 * @author Joker
 *
 */
public abstract class BaseThridpartComonentForwarder extends AbstractComponentForwarder{

	@Override
	public void execute(ForwardContext context) {
		validateAndBuild(context);
		
		callOutSystem(context);
		
		generateResponse(context);
		
	}

	protected abstract void validateAndBuild(ForwardContext context);
	
	protected abstract void changeRequestData(ForwardContext context);

	protected abstract void callOutSystem(ForwardContext context);

	protected abstract void generateResponse(ForwardContext context) ;

}
