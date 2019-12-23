package com.claimplat.serviceapi.client;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.common.bean.forward.request.dlz.receive.DlzCasesForwardRequest;
import com.claimplat.common.bean.forward.request.dlz.receive.DlzMaterialForwardRequest;
import com.claimplat.common.constant.RestResult;
import com.claimplat.serviceapi.service.DlzService;

@RestController
@RequestMapping(value = "/dlzclient")
public class DlzClient {

	@Resource
	private DlzService dlzService;
	
	/*案件状态推送*/
	@RequestMapping(value = "/push",method = {RequestMethod.POST})
	public RestResult casesPush(@RequestBody DlzCasesForwardRequest request) {
		RestResult restResult = new RestResult();
		dlzService.casesPush(request);
		return restResult;
	}
	
	/*单证补传推送*/
	@RequestMapping(value = "/materialpush",method = {RequestMethod.POST})
	public RestResult materialPush(@RequestBody DlzMaterialForwardRequest request) {
		RestResult restResult = new RestResult();
		dlzService.materialPush(request);
		return restResult;
	}
}
