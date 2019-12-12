package com.claimplat.serviceapi.componentforward;

import com.claimplat.serviceapi.componentforward.bean.ForwardContext;

/**
 * 组件转发器，把业务系统的请求转发给外部第三方系统
 * @author Joker
 *
 */
public interface ComponentForwarder {
	
	void execute(ForwardContext context);
}
