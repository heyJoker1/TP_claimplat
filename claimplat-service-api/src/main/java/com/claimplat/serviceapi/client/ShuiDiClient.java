package com.claimplat.serviceapi.client;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.common.bean.forward.request.thridpart.ShuiDiProcessThridpartForwardRequest;
import com.claimplat.common.constant.RestResult;
import com.claimplat.serviceapi.service.ShuiDiService;

/**
 * 中间平台针对水滴的转发接口
 * @author Joker
 *
 */
@RestController
@RequestMapping(value = "sdclient")
public class ShuiDiClient extends BaseClient{
	
	@Resource
	private ShuiDiService shuiDiService;

	/*核心系统案件结案后，通道外部第三方系统*/
	@RequestMapping(value = "/push",method = {RequestMethod.POST})
	public RestResult apply(@RequestBody ShuiDiProcessThridpartForwardRequest request) {
		RestResult restResult = new RestResult();;
		shuiDiService.push(request);
		return restResult;
	}
}
