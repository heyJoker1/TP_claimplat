package com.claim.task.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claim.common.constant.RpcResult;

@RestController
@RequestMapping(value = "/out")
public class OutInterfaceRest extends BaseRest{
	
	@RequestMapping(value = "/ack",method = {RequestMethod.GET,RequestMethod.POST})
	public RpcResult ack() {
		RpcResult RpcResult = new RpcResult();
		try {
			RpcResult.setData("ack ok");
		} catch (Exception e) {
			logger.error("心跳检测出错",e);
		}
		return RpcResult;
	}
}
