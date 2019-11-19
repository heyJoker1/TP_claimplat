package com.claimplat.adminapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.common.constant.RestResult;

@RestController
@RequestMapping(value = "/out")
public class OutInterfaceRest extends BaseRest{
	
	@RequestMapping(value = "/ack",method = {RequestMethod.GET,RequestMethod.POST})
	public RestResult ack() {
		RestResult restResult = new RestResult();
		try {
			restResult.setData("ack ok");
		} catch (Exception e) {
			logger.error("心跳检测出错",e);
		}
		return restResult;
	}
}
