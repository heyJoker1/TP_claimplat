package com.claimplat.serviceapi.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.claimplat.common.bean.forward.request.dlz.receive.DlzCasesForwardRequest;
import com.claimplat.common.bean.forward.request.dlz.receive.DlzMaterialForwardRequest;
import com.claimplat.common.bean.forward.response.BaseForwardResponse;
import com.claimplat.common.bean.forward.response.thridpart.BaseThridpartForwardResponse;
import com.claimplat.common.enums.ComponentForwaderTypeEnum;
import com.claimplat.common.enums.ThridpartTypeEnum;
import com.claimplat.common.exception.BusinessException;
import com.claimplat.common.exception.ValidateException;
import com.claimplat.core.entity.Merchant;
import com.claimplat.serviceapi.componentforward.ComponentForwarder;
import com.claimplat.serviceapi.componentforward.bean.ForwardContext;
import com.claimplat.serviceapi.repository.ParameterConfigRepository;

@Service
public class DlzService extends BaseService{

	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	private ParameterConfigRepository parameterConfigRepository;
	
	/*案件状态推送*/
	public BaseThridpartForwardResponse casesPush(DlzCasesForwardRequest request) {
		if(request == null) {
			throw new IllegalArgumentException("请求数据不能为空！");
		}
		
		try {
			Merchant merchant = validateAndFetchMerchant(ThridpartTypeEnum.MA_YI);
			ForwardContext context = new ForwardContext();
			context.setMerchant(merchant);
			context.setRequest(request);
			
			Map<String, Object> tempDateMap = context.getTempDateMap();
			Object success = tempDateMap.get("success");
			if(!success.equals("1")) {
				throw new IllegalArgumentException("蚂蚁-顶梁柱案件状态success信息异常");
			}
			
			
			ComponentForwarder componentForwarder = componentForwarderFactory.getComponentForwarder(ComponentForwaderTypeEnum.DLZ_SEND_INFO);
			componentForwarder.execute(context);
			
			BaseThridpartForwardResponse response = (BaseThridpartForwardResponse) context.getResponse();
			return response;
		} catch (IllegalArgumentException | IllegalStateException | ValidateException e) {
			throw e;
		} catch(Exception e) {
			throw new BusinessException("蚂蚁-顶梁柱案件状态推送失败！",e);
		}
	}

	/*单证补传推送*/
	public BaseThridpartForwardResponse materialPush(DlzMaterialForwardRequest request) {
		if(request == null) {
			throw new IllegalArgumentException("请求数据不能为空！");
		}
		
		try {
			Merchant merchant = validateAndFetchMerchant(ThridpartTypeEnum.MA_YI);
			ForwardContext context = new ForwardContext();
			context.setMerchant(merchant);
			context.setRequest(request);
			
			ComponentForwarder componentForwarder = componentForwarderFactory.getComponentForwarder(ComponentForwaderTypeEnum.DLZ_MATERIAL_INFO);
			componentForwarder.execute(context);
			
			BaseThridpartForwardResponse response = (BaseThridpartForwardResponse) context.getResponse();
			return response;
		} catch (IllegalArgumentException | IllegalStateException | ValidateException e) {
			throw e;
		} catch(Exception e) {
			throw new BusinessException("蚂蚁-顶梁柱案件状态推送失败！",e);
		}
		
	}

}
