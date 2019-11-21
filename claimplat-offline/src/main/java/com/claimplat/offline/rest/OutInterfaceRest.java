package com.claimplat.offline.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.common.constant.RestResult;

@RestController
@RequestMapping(value = "/out")
public class OutInterfaceRest {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/ack", method = {RequestMethod.GET,RequestMethod.POST})
	public RestResult ack() {
		RestResult result = new RestResult();
		try {
			result.setData("ack ok");
			logger.info("ack ok");
		} catch (Exception e) {
			logger.error("心跳检测出错",e);
		}
		return result;
	}
}
